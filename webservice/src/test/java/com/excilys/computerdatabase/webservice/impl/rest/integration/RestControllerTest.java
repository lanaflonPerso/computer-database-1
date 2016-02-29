package com.excilys.computerdatabase.webservice.impl.rest.integration;

import com.excilys.computerdatabase.webservice.impl.rest.integration.mock.TestService;
import com.excilys.computerdatabase.webservice.impl.rest.integration.mock.impl.MockRestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/02/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-webservice-context.xml"})
public class RestControllerTest extends AbstractWebserviceTest {
    @Autowired
    private MockRestService testService;

    @Override
    protected TestService getTestService() {
        return testService;
    }

    @Test
    public void testGetAllCompany() {
        super.testGetAllCompany();
    }

    @Test
    public void testGetAllComputer() {
        super.testGetAllComputer();
    }

    @Test
    public void testGetComputerById() {
        super.testGetComputerById();
    }

    @Test
    public void testAddSimpleComputerOk() {
        super.testAddSimpleComputerOk();
    }

    @Test
    public void testAddComputerOk() {
        super.testAddComputerOk();
    }

    @Test
    public void testDeleteComputer() {
        super.testDeleteComputer();
    }
}