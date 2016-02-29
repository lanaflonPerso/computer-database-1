package com.excilys.computerdatabase.webservice.impl.rest.integration;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.webservice.impl.rest.integration.mock.TestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/02/16.
 */
public abstract class AbstractWebserviceTest {
    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;

    protected abstract TestService getTestService();

    public void testGetAllCompany() {
        List<Company> companies = getTestService().getAllCompany();
        assertEquals(companyService.list(new SortCriteria()), companies);
    }

    public void testGetAllComputer() {
        List<Computer> computers = getTestService().getAllComputer();
        assertEquals(computerService.list(new SortCriteria()), computers);
    }

    public void testGetComputerById() {
        Computer computer = computerService.list(new SortCriteria()).get(0);
        assertEquals(computer, getTestService().getComputerById(computer.getId()));
    }

    public void testAddSimpleComputerOk() {
        Computer computer = new Computer(0L, "name-getTestService()-2", null, null, null);
        getTestService().createComputer(computer);
        assertNotEquals(computer.getId(), (Long) 0L);
        assertEquals(computerService.getById(computer.getId()), computer);
    }

    public void testAddComputerOk() {
        Computer computer = new Computer(0L, "name-getTestService()-2", LocalDateTime.now().truncatedTo(ChronoUnit.DAYS), null, null);
        getTestService().createComputer(computer);
        assertNotEquals(computer.getId(), (Long) 0L);
        assertEquals(computerService.getById(computer.getId()), computer);
    }

    public void testDeleteComputer() {
        int computerNumber = getTestService().getAllComputer().size();
        List<Computer> computers = getTestService().getAllComputer();
        getTestService().deleteComputer(computers.get(computers.size() - 1).getId());
        assertEquals(computerNumber, getTestService().getAllComputer().size() + 1);
    }
}
