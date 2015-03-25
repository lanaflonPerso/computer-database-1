package com.excilys.computerDataBase.test.unitaire.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.excilys.computerDataBase.dao.ComputerDaoInterface;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.exception.ServiceException;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.impl.ComputerService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ComputerDao.class })
public class TestComputerService extends TestCase {

	private static final Long COMPUTER_ID = new Long(45);
	private static final Long NUMBER_OF_ELEMENT = new Long(101);

	private ComputerService computerService = ComputerService.INSTANCE;

	private ComputerDaoInterface computerDao = Mockito
			.mock(ComputerDaoInterface.class);

	Computer computer1 = null;

	@Before
	public void init() {
		computer1 = new Computer(new Long(1), "myName", LocalDateTime.now(),
				LocalDateTime.now(), new Company(new Long(3), "myCompany"));
	}

	@Override
	public void setUp() {
		Mockito.when(computerDao.getById(COMPUTER_ID)).thenReturn(computer1);
		List<Computer> computers = new ArrayList<Computer>();
		computers.add(computer1);
		Mockito.when(computerDao.getAll()).thenReturn(computers);
		Mockito.when(computerDao.getNumberOfElement()).thenReturn(
				NUMBER_OF_ELEMENT);

		computerService.setComputerDao(computerDao);
	}

	@Test
	public void testGetByIdOk() {
		assertThat(computerService.getById(COMPUTER_ID).getId(),
				is(new Long(1)));
	}

	@Test
	public void testGetByIdWrong() {
		try {
			computerService.getById(new Long(0));
			fail("no exception occured");
		} catch (ServiceException e) {
			assertThat(e.getMessage(), is(ServiceException.INVALID_COMPUTER_ID));
		}
	}

	@Test
	public void testAll() {
		List<Computer> computers = computerService.list();
		assertThat(computers.size(), is(1));
		assertThat(computers.get(0), is(computer1));
	}

	@Test
	public void testCreateOk() {
		computerService.create(computer1);
	}

	@Test
	public void testCreateWrongComputer() {
		try {
			computerService.create(new Computer());
			fail("no exception occured");
		} catch (ServiceException e) {
			assertThat(e.getMessage(), is(ServiceException.INVALID_COMPUTER));
		}
	}

	@Test
	public void testCreateNameWithSpace1() {
		try {
		Computer computer = new Computer(new Long(1), "   ", LocalDateTime.now(),
				LocalDateTime.now(), new Company(new Long(15), "Canon"));
		computerService.create(computer);
		fail("no exception occured");
		} catch (ServiceException e) {
			assertThat(e.getMessage(), is(ServiceException.INVALID_COMPUTER));
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
			assertThat(e.getMessage(), is(ServiceException.INVALID_COMPUTER));
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
			assertThat(e.getMessage(), is(ServiceException.INVALID_COMPUTER));
		}
	}

	@Test
	public void testNumberOfElementOk() {
		Long numberforElement = computerService.getNumberOfElement();
		assertThat(numberforElement, is(NUMBER_OF_ELEMENT));
	}
	
	@Test
	public void testListFromToOk() {
			computerService.list(new Long(0), new Long(1));
	}
	
	@Test
	public void testListFromToWrong() {
		try {
			computerService.list(new Long(0), null);
			fail("no exception occured");
		} catch (ServiceException e) {
			assertThat(e.getMessage(), is(ServiceException.INVALID_PARAMETER));
		}
	}

}
