/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.test.databaseSimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DataBaseSimulator {
	private static final String CREATE_DATABASE_FILE = "database/createDataBase.sql";
	private static final String CONFIG_PROPERTIES = "src/test/resources/test-config.properties";

	private IDatabaseTester databaseTester;
	private DataSource dataSource;
	private String jdbcDriver;
	private String jdbcUrl;
	private String user;
	private String password;

	static {
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataBaseSimulator(DataSource dataSource) {
			this.dataSource = dataSource;	
	}

	public void initDatabase() throws Exception {
		Properties properties = new Properties();
		InputStream is = new FileInputStream(new File(CONFIG_PROPERTIES));
		properties.load(is);
		jdbcDriver = properties.getProperty("driverClassName");
		jdbcUrl = properties.getProperty("url");
		user = properties.getProperty("username");
		password = properties.getProperty("password");
		databaseTester = new JdbcDatabaseTester(jdbcDriver, jdbcUrl, user, password);
		
		Connection connection = dataSource.getConnection();
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource(CREATE_DATABASE_FILE));
		rdp.populate(connection);
	}

	public void resetTable() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(
				"src/test/resources/database/fakeDatabase.xml"));
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
	}

	public void tearDown() throws Exception {
		databaseTester.onTearDown();
	}
}
