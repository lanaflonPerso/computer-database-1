/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.persistence.model.Authority;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-persistence-context.xml" })
public class TestAuthorityDao {
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private DataSource dataSource;

	@Before
	public void setUpDatabase() {
		try {
			IDatabaseConnection dbc = new DatabaseConnection(dataSource.getConnection());			 
			IDataSet dataset = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/database/fakeDatabase.xml"));
			DatabaseOperation.CLEAN_INSERT.execute(dbc, dataset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateAuthorityOk() {
		Authority authority = new Authority("admin", "my_role" + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		authorityDao.create(authority);
		List<Authority> authorities = authorityDao.getByName("admin");
		assertEquals(authorities.contains(authority), true);
	}
	
	@Test
	public void testDeleteAuthorityOk() {
		Authority authority = new Authority("admin", "my_role" + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		authorityDao.create(authority);
		authorityDao.delete(authority);
		List<Authority> authorities = authorityDao.getByName("admin");
		assertEquals(authorities.contains(authority), false);
	}
}
