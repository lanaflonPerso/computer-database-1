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
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.persistence.dao.UserDao;
import com.excilys.computerdatabase.persistence.model.User;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-persistence-context.xml" })
public class TestUserDao {
	@Autowired
	private UserDao userDao;
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
	public void testCreateUserOk() {
		User daoUser = new User("test", "test");
		userDao.create(daoUser);
		User daoUser2 = userDao.getByName(daoUser.getUserName());
		assertEquals(daoUser, daoUser2);
	}

	@Test
	public void testGetAllUserOk() {
		int numberOfElement = userDao.getAll().size();
		User daoUser = new User("test" + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "test");
		userDao.create(daoUser);
		List<User> daoUsers2 = userDao.getAll();
		int numberOfElement2 = daoUsers2.size();
		assertEquals(numberOfElement + 1 , numberOfElement2);
	}
	
	@Test
	public void testUpdateUserOk() {
		List<User> daoUsers = userDao.getAll();
		User daoUser = daoUsers.get(0);
		daoUser.setPassword("test2");
		userDao.update(daoUser);
		List<User> daoUsers2 = userDao.getAll();
		assertEquals(daoUsers, daoUsers2);
	}
	
	@Test
	public void testDeleteUserOk() {
		User daoUser = new User("test", "test");
		int numberOfElement = userDao.getAll().size();
		userDao.create(daoUser);
		int numberOfElement2 = userDao.getAll().size();
		assertEquals(numberOfElement + 1 , numberOfElement2);
		userDao.delete(daoUser.getUserName());		
		int numberOfElement3 = userDao.getAll().size();
		assertEquals(numberOfElement, numberOfElement3);
	}
	
}
