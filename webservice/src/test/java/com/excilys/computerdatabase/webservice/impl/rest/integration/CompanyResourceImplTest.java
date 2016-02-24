package com.excilys.computerdatabase.webservice.impl.rest.integration;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 15/02/16.
 */
public class CompanyResourceImplTest {
    private TestService testService = new TestService();

    @Test
    public void testGetCompany() {
        List<Company> companies = testService.getAllCompany();
        assertNotNull(companies);
    }

    @Test
    public void testGetComputer() {
        List<Computer> computers = testService.getAllComputer();
        assertNotNull(computers);
    }

    @Test
    public void testAddSimpleComputerOk() {
        Computer computer = new Computer(0L, "name-test-2", null, null, new Company());
        testService.createComputer(computer);
        assertNotEquals(computer.getId(), (Long) 0L);
        assertEquals(computer, testService.getComputerById(computer.getId()));
    }

    @Test
    public void testAddComputerOk() {
        Computer computer = new Computer(0L, "name-test-2", LocalDateTime.now().truncatedTo(ChronoUnit.DAYS), null, new Company());
        testService.createComputer(computer);
        assertNotEquals(computer.getId(), (Long) 0L);
        assertEquals(computer, testService.getComputerById(computer.getId()));
    }

    @Test
    public void testDeleteComputer() {
        int computerNumber = testService.getAllComputer().size();
        List<Computer> computers = testService.getAllComputer();
        testService.deleteComputer(computers.get(computers.size() - 1).getId());
        assertEquals(computerNumber, testService.getAllComputer().size() + 1);
    }

    @Test
    public void testUpdateComputer() {
        int computerNumber = testService.getAllComputer().size();
        List<Computer> computers = testService.getAllComputer();
        testService.deleteComputer(computers.get(computers.size() - 1).getId());
        assertEquals(computerNumber, testService.getAllComputer().size() + 1);
    }
}

class TestService {
    private static final String SERVER_ROOT_URI = "http://localhost:18080/webservice/rest/xml";
    private static final String SERVER_ROOT_COMPANY = SERVER_ROOT_URI + "/company";
    private static final String SERVER_ROOT_COMPUTER = SERVER_ROOT_URI + "/computer";


    public List<Computer> getAllComputer() {
        return Client.create().resource(SERVER_ROOT_COMPUTER + "/getAll").get(ClientResponse.class).getEntity(new GenericType<List<Computer>>() {
        });
    }

    public List<Company> getAllCompany() {
        return Client.create().resource(SERVER_ROOT_COMPANY + "/getAll").get(ClientResponse.class).getEntity(new GenericType<List<Company>>() {
        });
    }

    public Computer getComputerById(Long id) {
        Computer c = Client.create().resource(SERVER_ROOT_COMPUTER + "/getById" + "/" + id).get(ClientResponse.class).getEntity(new GenericType<Computer>() {
        });
        if (c.getCompany() == null) {
            c.setCompany(new Company());
        }
        return c;
    }

    public void createComputer(Computer c) {
        Long id = Client.create().resource(SERVER_ROOT_COMPUTER + "/create").accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).entity(c).post(ClientResponse.class).getEntity(new GenericType<Computer>() {
        }).getId();
        c.setId(id);
    }

    public void deleteComputer(Long id) {
        Client.create().resource(SERVER_ROOT_COMPUTER + "/delete/" + id).header("Content-Type", "application/xml").delete(ClientResponse.class);
    }
}

