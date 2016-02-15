package com.excilys.computerdatabase.console.service.impl;

import com.excilys.computerdatabase.console.exception.ConsoleException;
import com.excilys.computerdatabase.console.service.ConsoleService;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.validation.Validator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Vincent Galloy
 */
@Service
public class ConsoleServiceImpl implements ConsoleService {
    private static final String SERVER_ROOT_URI = "http://localhost:8080/webservice/rest/xml";
    private static final String SERVER_ROOT_COMPANY = SERVER_ROOT_URI + "/company";
    private static final String SERVER_ROOT_COMPUTER = SERVER_ROOT_URI + "/computer";

    @Override
    public List<Computer> getAllComputer() {
        return Client.create().resource(SERVER_ROOT_COMPUTER + "/getAll").get(ClientResponse.class).getEntity(new GenericType<List<Computer>>() {
        });
    }

    @Override
    public List<Company> getAllCompany() {
        return Client.create().resource(SERVER_ROOT_COMPANY + "/getAll").get(ClientResponse.class).getEntity(new GenericType<List<Company>>() {
        });
    }

    @Override
    public Computer getComputerById(Long id) {
        if (!Validator.isIdCorrect(id)) {
            throw new ConsoleException(Validator.INVALID_COMPUTER_ID);
        }
        Computer c = Client.create().resource(SERVER_ROOT_COMPUTER + "/getById" + "/" + id).get(ClientResponse.class).getEntity(new GenericType<Computer>() {
        });
        if (c.getCompany() == null) {
            c.setCompany(new Company());
        }
        return c;
    }

    @Override
    public void createComputer(Computer c) {
        if (!Validator.isComputerCorrect(c)) {
            throw new ConsoleException(Validator.INVALID_COMPUTER);
        }
        Long id = Client.create().resource(SERVER_ROOT_COMPUTER + "/create").accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).entity(c).post(ClientResponse.class).getEntity(new GenericType<Computer>() {
        }).getId();
        c.setId(id);
    }

    @Override
    public void updateComputer(Computer c) {
        if (!Validator.isComputerCorrect(c)) {
            throw new ConsoleException(Validator.INVALID_COMPUTER);
        }
        Client.create().resource(SERVER_ROOT_COMPUTER + "/update").accept(MediaType.APPLICATION_XML).type(MediaType.APPLICATION_XML).entity(c).post(ClientResponse.class).getEntity(new GenericType<Computer>() {
        });
    }

    @Override
    public void deleteComputer(Long id) {
        if (!Validator.isIdCorrect(id)) {
            throw new ConsoleException(Validator.INVALID_COMPUTER_ID);
        }
        Client.create().resource(SERVER_ROOT_COMPUTER + "/delete/" + id).header("Content-Type", "application/xml").delete(ClientResponse.class);
    }

    @Override
    public void deleteCompany(Long id) {
        if (!Validator.isIdCorrect(id)) {
            throw new ConsoleException(Validator.INVALID_COMPANY_ID);
        }
        Client.create().resource(SERVER_ROOT_COMPANY + "/delete/" + id).header("Content-Type", "application/xml").delete(ClientResponse.class);
    }
}