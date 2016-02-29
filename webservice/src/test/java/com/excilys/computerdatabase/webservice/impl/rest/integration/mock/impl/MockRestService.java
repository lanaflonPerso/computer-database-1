package com.excilys.computerdatabase.webservice.impl.rest.integration.mock.impl;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.webservice.impl.rest.integration.mock.TestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 29/02/16.
 */
@Service
public class MockRestService implements TestService {
    public static final Logger LOGGER = LoggerFactory.getLogger(MockRestService.class);
    private static final String SERVER_ROOT_URI = BASE_URL + "/json";
    private static final String SERVER_ROOT_COMPANY = SERVER_ROOT_URI + "/company";
    private static final String SERVER_ROOT_COMPUTER = SERVER_ROOT_URI + "/computer";
    @Autowired
    private ComputerDtoMapper computerDtoMapper;
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    @Override
    public List<Computer> getAllComputer() {
        return computerDtoMapper.mapListToModel(Arrays.asList(new RestTemplate()
                .getForEntity(SERVER_ROOT_COMPUTER + "/getAll", ComputerDto[].class).getBody()));
    }

    @Override
    public List<Company> getAllCompany() {
        return companyDtoMapper.mapListToModel(Arrays.asList(new RestTemplate()
                .getForEntity(SERVER_ROOT_COMPANY + "/getAll", CompanyDto[].class)
                .getBody()));
    }

    @Override
    public Computer getComputerById(Long id) {
        return computerDtoMapper.mapToModel(new RestTemplate()
                .getForEntity(SERVER_ROOT_COMPUTER + "/getById" + "/" + id, ComputerDto.class)
                .getBody());
    }

    @Override
    public void createComputer(Computer c) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(computerDtoMapper.mapFromModel(c));
        } catch (JsonProcessingException e) {
            LOGGER.error("{}", e.getMessage(), e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        Long id = computerDtoMapper.mapToModel(new RestTemplate()
                .postForEntity(SERVER_ROOT_COMPUTER + "/create", entity, ComputerDto.class)
                .getBody())
                .getId();
        c.setId(id);
    }

    @Override
    public void deleteComputer(Long id) {
        new RestTemplate().delete(SERVER_ROOT_COMPUTER + "/delete/" + id);
    }
}
