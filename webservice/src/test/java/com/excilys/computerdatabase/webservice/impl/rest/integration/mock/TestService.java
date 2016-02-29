package com.excilys.computerdatabase.webservice.impl.rest.integration.mock;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/02/16.
 */
public interface TestService {
    String BASE_URL = "http://localhost:18080/webservice/rest";

    List<Computer> getAllComputer();

    List<Company> getAllCompany();

    Computer getComputerById(Long id);

    void createComputer(Computer c);

    void deleteComputer(Long id);
}
