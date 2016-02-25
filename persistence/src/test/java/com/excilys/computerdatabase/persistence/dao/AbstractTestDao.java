package com.excilys.computerdatabase.persistence.dao;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 17/02/16.
 */
public abstract class AbstractTestDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractTestDao.class);

    public void setUpDatabase(DataSource dataSource) {
        try {
            IDatabaseConnection dbc = new DatabaseConnection(dataSource.getConnection());
            DatabaseConfig config = dbc.getConfig();
            config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            IDataSet dataSet = new FlatXmlDataSetBuilder().setColumnSensing(true).build(new FileInputStream("src/test/resources/database/fakeDatabase.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(dbc, dataSet);
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage(), e);
        }
    }
}
