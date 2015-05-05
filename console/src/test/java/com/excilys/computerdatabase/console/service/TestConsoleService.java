package com.excilys.computerdatabase.console.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.console.exception.ConsoleException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-console-context.xml" })
public class TestConsoleService {
	@Autowired
	ConsoleService consoleService;

	@Test
	public void testGetCompany() {
		List<Company> companies = consoleService.getAllCompany();
		assertNotNull(companies);
	}

	@Test
	public void testGetComputer() {
		List<Computer> computers = consoleService.getAllComputer();
		assertNotNull(computers);
	}

	@Test
	public void testAddSimpleComputerOk() {
		Computer computer = new Computer(0L, "name-test-2", null, null, new Company());
		consoleService.createComputer(computer);
		assertNotEquals((Long) computer.getId(), (Long) 0L);
		assertEquals(computer, consoleService.getComputerById(computer.getId()));
	}

	@Test
	public void testAddComputerOk() {
		Computer computer = new Computer(0L, "name-test-2", LocalDateTime.now().truncatedTo(ChronoUnit.DAYS), null, new Company());
		consoleService.createComputer(computer);
		assertNotEquals((Long) computer.getId(), (Long) 0L);
		assertEquals(computer, consoleService.getComputerById(computer.getId()));
	}

	@Test(expected = ConsoleException.class)
	public void testAddComputerWithNullCompanyId() {
		Computer computer = new Computer(0L, "name-test-2", null, null, null);
		consoleService.createComputer(computer);
	}

	@Test
	public void testDeleteComputer() {
		int computerNumber = consoleService.getAllComputer().size();
		List<Computer> computers = consoleService.getAllComputer();
		consoleService.deleteComputer(computers.get(computers.size() - 1).getId());
		assertEquals(computerNumber, consoleService.getAllComputer().size() + 1);
	}

	@Test
	public void testUpdateComputer() {
		int computerNumber = consoleService.getAllComputer().size();
		List<Computer> computers = consoleService.getAllComputer();
		consoleService.deleteComputer(computers.get(computers.size() - 1).getId());
		assertEquals(computerNumber, consoleService.getAllComputer().size() + 1);
	}

}
