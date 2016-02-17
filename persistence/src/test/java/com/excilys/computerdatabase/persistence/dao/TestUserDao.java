/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

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

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.UserDetail;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-persistence-context.xml" })
public class TestUserDao extends AbstractTestDao {
	@Autowired
	private UserDetailDao userDetailDao;
	@Autowired
	private DataSource dataSource;

	@Before
	public void setUpDatabase() {
		super.setUpDatabase(dataSource);
	}


	@Test
	public void testCreateOk() {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName("name");
		userDetail.setPassword("password");
		userDetailDao.create(userDetail);
		UserDetail userDetail2 = userDetailDao.getByUsername(userDetail.getUserName());
		assertEquals(userDetail, userDetail2);
	}
	
	@Test
	public void testCreateWithRolesOk() {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName("name");
		userDetail.setPassword("password");
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ADMIN);
		roles.add(Role.SUPER_ADMIN);
		userDetail.setRoles(roles);
		userDetailDao.create(userDetail);
		UserDetail userDetail2 = userDetailDao.getByUsername(userDetail.getUserName());
		assertEquals(userDetail, userDetail2);
	}

	@Test
	public void testGetAllOk() {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName("name");
		userDetail.setPassword("password");
		int count = userDetailDao.getAll().size();
		userDetailDao.create(userDetail);
		int count2 = userDetailDao.getAll().size();
		assertEquals(count + 1, count2);
	}
	
	@Test
	public void testUpdateOk() {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName("name");
		userDetail.setPassword("password");
		userDetailDao.create(userDetail);
		userDetail.setPassword("password2");
		userDetailDao.update(userDetail);
		UserDetail userDetail2 = userDetailDao.getByUsername(userDetail.getUserName());
		assertEquals(userDetail, userDetail2);
	}

	@Test
	public void testUpdatePasswordOk() {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName("name");
		userDetail.setPassword("password");
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ADMIN);
		userDetail.setRoles(roles);
		userDetailDao.create(userDetail);
		userDetail.setPassword("password2");
		userDetailDao.update(userDetail);
		UserDetail userDetail2 = userDetailDao.getByUsername(userDetail.getUserName());
		assertEquals(userDetail, userDetail2);
	}
	
	@Test
	public void testUpdateRightOk() {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName("name");
		userDetail.setPassword("password");
		Set<Role> roles = new HashSet<Role>();
		roles.add(Role.ADMIN);
		userDetail.setRoles(roles);
		userDetailDao.create(userDetail);
		roles.add(Role.USER);
		userDetailDao.update(userDetail);
		UserDetail userDetail2 = userDetailDao.getByUsername(userDetail.getUserName());
		assertEquals(userDetail, userDetail2);
	}
	
	@Test
	public void testDeleteOk() {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName("name" + LocalDateTime.now().truncatedTo(ChronoUnit.NANOS));
		userDetail.setPassword("password");
		int count = userDetailDao.getAll().size();
		userDetailDao.create(userDetail);
		int count2 = userDetailDao.getAll().size();
		userDetailDao.delete(userDetail.getUserName());
		int count3 = userDetailDao.getAll().size();
		assertEquals(count + 1, count2);
		assertEquals(count, count3);
	}
	
}
