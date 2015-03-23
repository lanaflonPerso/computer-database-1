package com.excilys.computerDataBase.test.unitaire.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;

public class TestComputerDao {

	ComputerDao computerDao = ComputerDao.INSTANCE;

	@Test
	public void testInsert() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(
						15), "Canon"));
		computerDao.create(computer);
		assertThat(computer.getId() == 1, is(false));

		Computer computer2 = computerDao.getById(computer.getId());
		
		assertThat(computer.getId(), is(computer2.getId()));
		assertThat(computer.getCompany(), is(computer2.getCompany()));
		assertThat(computer.getName(), is(computer2.getName()));
	}

	@Test
	public void testInsertNullDateTime() {
		Computer computer = new Computer(new Long(1), "test_name", null, null,
				new Company(new Long(15), null));
		computerDao.create(computer);

	}

	@Test
	public void testInsertNullCompanyId() {
		Computer computer = new Computer(new Long(1), "test_name", null, null,
				new Company(null, null));
		computerDao.create(computer);

	}

	@Test
	public void testInsertNullName() {
		Computer computer = new Computer(new Long(1), null,
				LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(
						15), "Canon"));
		computerDao.create(computer);
	}

	@Test
	public void testInsertEmptyName() {
		Computer computer = new Computer(new Long(1), "", LocalDateTime.now(),
				LocalDateTime.now(), new Company(new Long(15), "Canon"));
		computerDao.create(computer);
	}

	@Test
	public void testInsertComputerWithNullAttributs() {
		try {
			Computer computer = new Computer();
			computerDao.create(computer);
		} catch (DaoException e) {
			assertThat(e.getMessage(), is(DaoException.CAN_NOT_INSERT_ELEMENT));
		}
		
	}

	@Test
	public void testInsertNullComputer() {
		try {
			Computer computer = null;
			computerDao.create(computer);
			fail("testInsertNullComputer : no exception");
		} catch (DaoException e) {
			assertThat(e.getMessage(), is(DaoException.CAN_NOT_INSERT_ELEMENT));
		}
	}
	
	@Test
	public void testListComputer() {
		List<Computer> computers = computerDao.getAll();
		assertThat(computers.get(0), is(new Computer(1l,
				"MacBook Pro 15.4 inch", null, null, new Company(new Long(1),
						"Apple Inc."))));
		assertThat(computers.size() > 1, is(true));
	}

	@Test
	public void testDelete() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(
						15), "Canon"));
		computerDao.create(computer);
		computerDao.delete(computer.getId());
		try {
			computerDao.getById(computer.getId());
			fail("testDelete : no exception");
		} catch (DaoException e) {
			assertThat(e.getMessage(), is(DaoException.CAN_NOT_GET_ELEMENT));
		}
	}

	@Test
	public void testUpdate() {
		Computer computer = new Computer(new Long(2), "test_name2",
				LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(
						15), "Canon"));
		computerDao.create(computer);
		computer.setName("second_name");
		computerDao.update(computer);
		Computer computer2 = computerDao.getById(computer.getId());
		assertThat(computer2.getId(), is(computer.getId()));
		assertThat("second_name".equals(computer2.getName()), is(true));
	}

	@Test
	public void testGetAllFromToWithZero() {
		List<Computer> computers = computerDao.getAll(new Long(0), new Long(0));
		assertThat(computers == null, is(false));
		assertThat(computers.size(), is(0));
	}

	@Test
	public void testGetAllFromTo() {
		List<Computer> computers = computerDao.getAll(new Long(0), new Long(1));
		assertThat(computers == null, is(false));
		assertThat(computers.size(), is(1));
		assertThat(computers.get(0).getName(), is("MacBook Pro 15.4 inch"));
	}

	@Test
	public void testGetNumberOfElement() {
		List<Computer> computers = computerDao.getAll();
		Long total = computerDao.getNumberOfElement();
		assertThat(computers == null, is(false));
		assertThat(total == null, is(false));
		assertThat(total, is(new Long(computers.size())));
	}

}
