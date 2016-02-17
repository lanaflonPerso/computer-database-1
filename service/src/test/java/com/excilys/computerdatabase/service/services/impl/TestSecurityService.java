package com.excilys.computerdatabase.service.services.impl;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.service.services.SecurityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Vincent Galloy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-service-application-context.xml"})
public class TestSecurityService extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SecurityService securityService;

    @Test
    public void testGetAll() {
        List<UserDetail> list = securityService.getAll();
        assertNotNull(list);
        assertNotEquals(list.size(), 0);
    }

    @Test
    public void testCreateOk() {
        UserDetail userDetail = new UserDetail();
        userDetail.setPassword("password");
        userDetail.setUserName("username" + System.currentTimeMillis());
        securityService.create(userDetail);
        UserDetail userDetail2 = securityService.getByName(userDetail.getUserName());
        assertEquals(userDetail, userDetail2);
    }

    @Test
    public void testCreateWithRoleOk() {
        UserDetail userDetail = new UserDetail();
        userDetail.setPassword("password");
        userDetail.setUserName("username" + System.currentTimeMillis());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        userDetail.setRoles(roles);
        securityService.create(userDetail);
        UserDetail userDetail2 = securityService.getByName(userDetail.getUserName());
        assertEquals(userDetail, userDetail2);
    }

    @Test
    public void testDeleteOk() {
        UserDetail userDetail = new UserDetail();
        userDetail.setPassword("password");
        userDetail.setUserName("username" + System.currentTimeMillis());
        int count1 = securityService.getAll().size();
        securityService.create(userDetail);
        int count2 = securityService.getAll().size();
        securityService.delete(userDetail.getUserName());
        int count3 = securityService.getAll().size();
        assertEquals(count1 + 1, count2);
        assertEquals(count1, count3);
    }

    @Test
    public void testResetPasswordOk() {
        UserDetail userDetail = new UserDetail();
        userDetail.setPassword("password");
        userDetail.setUserName("username" + System.currentTimeMillis());
        securityService.create(userDetail);
        userDetail.setPassword("password 2");
        securityService.resetPassword(userDetail);
        UserDetail userDetail2 = securityService.getByName(userDetail.getUserName());
        assertEquals(userDetail, userDetail2);
    }

    @Test
    public void testResetPasswordOk2() {
        UserDetail userDetail = new UserDetail();
        userDetail.setPassword("password");
        userDetail.setUserName("username" + System.currentTimeMillis());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        userDetail.setRoles(roles);
        securityService.create(userDetail);
        userDetail.setPassword("password 2");
        securityService.resetPassword(userDetail);
        UserDetail userDetail2 = securityService.getByName(userDetail.getUserName());
        assertEquals(userDetail, userDetail2);
        assertTrue(userDetail.getRoles().contains(Role.ADMIN));
    }

    @Test
    public void testUpdateRuleOk() {
        UserDetail userDetail = new UserDetail();
        userDetail.setPassword("password");
        userDetail.setUserName("username" + System.currentTimeMillis());
        securityService.create(userDetail);
        securityService.updateRight(new Rule(userDetail.getUserName(), Role.ADMIN, true));
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        userDetail.setRoles(roles);
        UserDetail userDetail2 = securityService.getByName(userDetail.getUserName());
        assertEquals(userDetail, userDetail2);
    }
}
