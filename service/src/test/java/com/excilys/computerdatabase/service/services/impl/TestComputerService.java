/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.service.services.impl.ComputerServiceImpl;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-service-application-context.xml" })
public class TestComputerService {

	private static final Long COMPUTER_ID = new Long(45);
	private static final Long NUMBER_OF_ELEMENT = new Long(101);
	private final Computer computer1 = new Computer(new Long(1), "myName", LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(3), "myCompany"));
	@Autowired
	private ComputerDao computerDao2;

	private ComputerServiceImpl computerServiceImpl;
	private ComputerDao computerDao = Mockito.mock(ComputerDao.class);

	@Before
	public void setUp() {
		computerServiceImpl  = new ComputerServiceImpl();
		computerServiceImpl.setComputerDao(computerDao);
		Mockito.when(computerDao.getById(COMPUTER_ID)).thenReturn(computer1);
		List<Computer> computers = new ArrayList<Computer>();
		computers.add(computer1);
		Mockito.when(computerDao.getAll(Mockito.any())).thenReturn(computers);
		Mockito.when(computerDao.getNumberOfElement()).thenReturn(NUMBER_OF_ELEMENT);
		Mockito.doThrow(new RuntimeException()).when(computerDao).create(computer1);
		computerServiceImpl.setComputerDao(computerDao);
	}

	@After
	public void setDown() {
		computerServiceImpl.setComputerDao(computerDao2);
	}

	@Test
	public void testGetByIdOk() {
		assertEquals(computerServiceImpl.getById(COMPUTER_ID).getId(), new Long(1));
	}

	@Test
	public void testGetByIdWrong() {
		try {
			computerServiceImpl.getById(new Long(0));
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER_ID);
		}
	}

	@Test
	public void testAll() {
		List<Computer> computers = computerServiceImpl.list(new SortCriteria());
		assertEquals(computers.size(), 1);
		assertEquals(computers.get(0), computer1);
	}

	@Test(expected = RuntimeException.class)
	public void testCreateOk() {
		computerServiceImpl.create(computer1);
	}

	@Test
	public void testCreateWrongComputer() {
		try {
			computerServiceImpl.create(new Computer());
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testCreateNameWithSpace1() {
		try {
			Computer computer = new Computer(new Long(1), "   ", LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(15), "Canon"));
			computerServiceImpl.create(computer);
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testUpdateOk() {
		computerServiceImpl.update(computer1);
	}

	@Test
	public void testUpdateWrongComputer() {
		Computer computer = new Computer(new Long(0), "myName", LocalDateTime.now(), LocalDateTime.now(), null);
		try {
			computerServiceImpl.create(computer);
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testDeleteOk() {
		computerServiceImpl.delete(computer1.getId());
	}

	@Test
	public void testDeleteWrongComputer() {
		try {
			computerServiceImpl.delete(new Long(-1));
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testNumberOfElementOk() {
		Long numberforElement = computerServiceImpl.getNumberOfElement();
		assertEquals(numberforElement, NUMBER_OF_ELEMENT);
	}

	@Test
	public void testListFromToOk() {
		computerServiceImpl.list(new Long(0), new Long(1), new SortCriteria());
	}

	@Test
	public void testListFromToWrong() {
		try {
			computerServiceImpl.list(new Long(0), null, new SortCriteria());
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_BOUND);
		}
	}

}
