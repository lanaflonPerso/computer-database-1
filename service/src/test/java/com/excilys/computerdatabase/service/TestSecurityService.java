/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.UserDetail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/service-test-application-context.xml" })
public class TestSecurityService {
	@Autowired
	private SecurityService securityService;	
	
	@Test
	public void testGetAll(){
		List<UserDetail> list = securityService.getAll();
		assertNotEquals(list.size(), 0);
	}
	
	@Test
	public void testCreate() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.ADMIN);
		String name = "name" + LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
		UserDetail userDetail = new UserDetail(name , "password", roles);
		securityService.create(userDetail);
		UserDetail userDetail2 = securityService.getByName(name);
		assertEquals(userDetail, userDetail2);
	}	
}
