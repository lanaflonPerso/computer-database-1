package com.excilys.computerdatabase.console.service.impl;

import com.excilys.computerdatabase.console.exception.ConsoleException;
import com.excilys.computerdatabase.console.service.ConsoleService;
import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.validation.Validator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Vincent Galloy
 */
@Service
public class ConsoleServiceImpl implements ConsoleService {
    private static final String BASE_URL = "http://localhost:18080/webservice/rest";
    private static final String SERVER_ROOT_URI = BASE_URL + "/xml";
    private static final String SERVER_ROOT_COMPANY = SERVER_ROOT_URI + "/company";
    private static final String SERVER_ROOT_COMPUTER = SERVER_ROOT_URI + "/computer";
    @Autowired
    private ComputerDtoMapper computerDtoMapper;
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    @Override
    public List<Computer> getAllComputer() {
        return computerDtoMapper.mapListToModel(Client.create().resource(SERVER_ROOT_COMPUTER + "/getAll").get(new GenericType<List<ComputerDto>>() {
        }));
    }

    @Override
    public List<Company> getAllCompany() {
        return companyDtoMapper.mapListToModel(Client.create().resource(SERVER_ROOT_COMPANY + "/getAll").get(new GenericType<List<CompanyDto>>() {
        }));
    }

    @Override
    public Computer getComputerById(Long id) {
        return computerDtoMapper.mapToModel(Client.create().resource(SERVER_ROOT_COMPUTER + "/getById" + "/" + id).get(new GenericType<ComputerDto>() {
        }));
    }

    @Override
    public void createComputer(Computer c) {
        if (!Validator.isComputerCorrect(c)) {
            throw new ConsoleException(Validator.INVALID_COMPUTER);
        }
        Long id = computerDtoMapper.mapToModel(Client
                .create()
                .resource(SERVER_ROOT_COMPUTER + "/create")
                .accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_XML)
                .entity(computerDtoMapper.mapFromModel(c))
                .post(ClientResponse.class)
                .getEntity(new GenericType<ComputerDto>() {
                })).getId();
        c.setId(id);
    }

    @Override
    public void deleteComputer(Long id) {
        Client.create().resource(SERVER_ROOT_COMPUTER + "/delete/" + id).header("Content-Type", "application/xml").delete(ClientResponse.class);
    }

    @Override
    public void deleteCompany(Long id) {
        Client.create().resource(SERVER_ROOT_COMPANY + "/delete/" + id).header("Content-Type", "application/xml").delete(ClientResponse.class);
    }

    @Override
    public void updateComputer(Computer c) {
        if (!Validator.isComputerCorrect(c)) {
            throw new ConsoleException(Validator.INVALID_COMPUTER);
        }
        Client.create()
                .resource(SERVER_ROOT_COMPUTER + "/create")
                .accept(MediaType.APPLICATION_XML)
                .type(MediaType.APPLICATION_XML)
                .entity(computerDtoMapper.mapFromModel(c))
                .post(ClientResponse.class)
                .getEntity(new GenericType<ComputerDto>() {
                });
    }
}