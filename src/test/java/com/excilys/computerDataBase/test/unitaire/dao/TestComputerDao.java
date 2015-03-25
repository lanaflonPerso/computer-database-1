package com.excilys.computerDataBase.test.unitaire.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.factory.ConnectionFactory;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;

public class TestComputerDao {

	ComputerDao computerDao = ComputerDao.INSTANCE;
	private Connection connection = null;
	
	
	@Before
	public void init() throws SQLException {
		connection = ConnectionFactory.INSTANCE.createConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void after() throws SQLException {
		connection.rollback();
		connection.close();
	}
	
	@Test
	public void testInsert() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				new Company(new Long(15), "Canon"));
		computerDao.create(connection, computer);
		assertEquals(computer.getId() == 1, false);

		Computer computer2 = computerDao.getById(connection, computer.getId());
		assertEquals(computer2, computer);
	}

	@Test
	public void testInsertNullDateTime() {
		Computer computer = new Computer(new Long(1), "test_name", null, null,
				new Company(new Long(15), "Canon"));
		computerDao.create(connection, computer);
		assertEquals(computer.getId() == 1, false);

		Computer computer2 = computerDao.getById(connection, computer.getId());
		assertEquals(computer2, computer);
	}

	@Test
	public void testInsertNullCompanyId() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				new Company(null, "Canon"));
		computerDao.create(connection, computer);
		computer.getCompany().setName(null);
		computer.getCompany().setId(new Long(0));

		Computer computer2 = computerDao.getById(connection, computer.getId());
		assertEquals(computer2, computer);
	}

	@Test(expected = NullPointerException.class)
	public void testInsertNullName() {
		Computer computer = new Computer(new Long(1), null, LocalDateTime.now()
				.truncatedTo(ChronoUnit.SECONDS), LocalDateTime.now()
				.truncatedTo(ChronoUnit.SECONDS), new Company(null, "Canon"));
		computerDao.create(connection, computer);
	}

	@Test(expected = NullPointerException.class)
	public void testInsertEmptyName() {
		Computer computer = new Computer(new Long(1), "", LocalDateTime.now(),
				LocalDateTime.now(), new Company(new Long(15), "Canon"));
		computerDao.create(connection, computer);
	}

	@Test(expected = NullPointerException.class)
	public void testInsertComputerWithNullAttributs() {
		Computer computer = new Computer();
		computerDao.create(connection, computer);
	}

	@Test(expected = NullPointerException.class)
	public void testInsertNullComputer() {
			Computer computer = null;
		computerDao.create(connection, computer);
	}

	@Test
	public void testListComputer() {
		List<Computer> computers = computerDao.getAll();
		assertEquals(computers.get(0), new Computer(4l,
				"MacBook Pro 15.4 inch", null, null, new Company(new Long(1),
						"Apple Inc.")));
		assertEquals(computers.size() > 1, true);
	}

	@Test
	public void testDelete() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(
						15), "Canon"));
		computerDao.create(connection, computer);
		computerDao.delete(connection, computer.getId());
		try {
			computerDao.getById(connection, computer.getId());
			fail("testDelete : no exception");
		} catch (DaoException e) {
			assertEquals(e.getMessage(), DaoException.CAN_NOT_GET_ELEMENT);
		}
	}

	@Test
	public void testUpdate() {
		Computer computer = new Computer(new Long(1), "test_name_2",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				new Company(new Long(1), "Apple Inc."));
		computerDao.create(connection, computer);
		computer.setName("second_name");
		computerDao.update(connection, computer);
		Computer computer2 = computerDao.getById(connection, computer.getId());
		assertEquals(computer, computer2);
	}

	@Test
	public void testGetAllFromToWithZero() {
		List<Computer> computers = computerDao.getAll(new Long(0), new Long(0));
		assertEquals(computers.size(), 0);
	}

	@Test
	public void testGetAllFromTo() {
		List<Computer> computers = computerDao.getAll(new Long(0), new Long(1));
		assertEquals(computers.size(), 1);
		assertEquals(computers.get(0).getName(), "MacBook Pro 15.4 inch");
	}

	@Test
	public void testGetNumberOfElement() {
		List<Computer> computers = computerDao.getAll();
		Long total = computerDao.getNumberOfElement();
		assertEquals(total, new Long(computers.size()));
	}

	@Test
	public void testNameContains() {
		List<Computer> computers = computerDao.getNameContains("App", new Long(0), new Long(10));
		assertEquals(computers.get(0).getName(), "MacBook Pro 15.4 inch");
	}

}
