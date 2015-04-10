/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.test.unitaire.service;

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

import com.excilys.computerdatabase.dao.ComputerDao;
import com.excilys.computerdatabase.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.impl.ComputerServiceImpl;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
public class TestComputerService {

	private static final Long COMPUTER_ID = new Long(45);
	private static final Long NUMBER_OF_ELEMENT = new Long(101);

	@Autowired
	private ComputerServiceImpl computerService;

	@Autowired
	private ComputerDaoImpl computerDao2;

	private ComputerDao computerDao = Mockito
			.mock(ComputerDao.class);

	private final Computer computer1 = new Computer(new Long(1), "myName",
			LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(3),
					"myCompany"));

	@Before
	public void setUp() {
		Mockito.when(computerDao.getById(COMPUTER_ID)).thenReturn(computer1);
		List<Computer> computers = new ArrayList<Computer>();
		computers.add(computer1);
		Mockito.when(computerDao.getAll(Mockito.any())).thenReturn(computers);
		Mockito.when(computerDao.getNumberOfElement()).thenReturn(
				NUMBER_OF_ELEMENT);
		Mockito.doThrow(new RuntimeException()).when(computerDao)
				.create(computer1);
		computerService.setComputerDao(computerDao);
	}

	@After
	public void setDown() {
		computerService.setComputerDao(computerDao2);
	}

	@Test
	public void testGetByIdOk() {
		assertEquals(computerService.getById(COMPUTER_ID).getId(), new Long(1));
	}

	@Test
	public void testGetByIdWrong() {
		try {
			computerService.getById(new Long(0));
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER_ID);
		}
	}

	@Test
	public void testAll() {
		List<Computer> computers = computerService.list(new SortCriteria());
		assertEquals(computers.size(), 1);
		assertEquals(computers.get(0), computer1);
	}

	@Test(expected = RuntimeException.class)
	public void testCreateOk() {
		computerService.create(computer1);
	}

	@Test
	public void testCreateWrongComputer() {
		try {
			computerService.create(new Computer());
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testCreateNameWithSpace1() {
		try {
			Computer computer = new Computer(new Long(1), "   ",
					LocalDateTime.now(), LocalDateTime.now(), new Company(
							new Long(15), "Canon"));
			computerService.create(computer);
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testUpdateOk() {
		computerService.update(computer1);
	}

	@Test
	public void testUpdateWrongComputer() {
		Computer computer = new Computer(new Long(0), "myName",
				LocalDateTime.now(), LocalDateTime.now(), null);
		try {
			computerService.create(computer);
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testDeleteOk() {
		computerService.delete(computer1.getId());
	}

	@Test
	public void testDeleteWrongComputer() {
		try {
			computerService.delete(new Long(-1));
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
		}
	}

	@Test
	public void testNumberOfElementOk() {
		Long numberforElement = computerService.getNumberOfElement();
		assertEquals(numberforElement, NUMBER_OF_ELEMENT);
	}

	@Test
	public void testListFromToOk() {
		computerService.list(new Long(0), new Long(1), new SortCriteria());
	}

	@Test
	public void testListFromToWrong() {
		try {
			computerService.list(new Long(0), null, new SortCriteria());
			fail("no exception occured");
		} catch (ServiceException e) {
			assertEquals(e.getMessage(), Validator.INVALID_BOUND);
		}
	}

}
