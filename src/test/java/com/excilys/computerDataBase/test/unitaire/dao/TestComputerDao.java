/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.test.unitaire.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context.xml" })
public class TestComputerDao {

	@Autowired
	private ComputerDaoImpl computerDao;
	
	@Test
	public void testInsert() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				new Company(new Long(15), "Canon"));
		computerDao.create(computer);
		assertEquals(computer.getId() == 1, false);

		Computer computer2 = computerDao.getById(computer.getId());
		assertEquals(computer2, computer);
	}

	@Test
	public void testInsertNullDateTime() {
		Computer computer = new Computer(new Long(1), "test_name", null, null,
				new Company(new Long(15), "Canon"));
		computerDao.create(computer);
		assertEquals(computer.getId() == 1, false);

		Computer computer2 = computerDao.getById(computer.getId());
		assertEquals(computer2, computer);
	}

	@Test
	public void testInsertNullCompanyId() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				new Company(null, "Canon"));
		computerDao.create(computer);
		computer.getCompany().setName(null);
		computer.getCompany().setId(new Long(0));

		Computer computer2 = computerDao.getById(computer.getId());
		assertEquals(computer2, computer);
	}

	@Test(expected = DaoException.class)
	public void testInsertNullName() {
		Computer computer = new Computer(new Long(1), null, LocalDateTime.now()
				.truncatedTo(ChronoUnit.SECONDS), LocalDateTime.now()
				.truncatedTo(ChronoUnit.SECONDS), new Company(null, "Canon"));
		computerDao.create(computer);
	}

	@Test(expected = DaoException.class)
	public void testInsertEmptyName() {
		Computer computer = new Computer(new Long(1), "", LocalDateTime.now(),
				LocalDateTime.now(), new Company(new Long(15), "Canon"));
		computerDao.create(computer);
	}

	@Test(expected = DaoException.class)
	public void testInsertComputerWithNullAttributs() {
		Computer computer = new Computer();
		computerDao.create(computer);
	}

	@Test(expected = DaoException.class)
	public void testInsertNullComputer() {
			Computer computer = null;
		computerDao.create(computer);
	}

	@Test
	public void testListComputer() {
		List<Computer> computers = computerDao.getAll(new SortCriteria());
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
		computerDao.create(computer);
		computerDao.delete(computer.getId());
		try {
			computerDao.getById(computer.getId());
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
		computerDao.create(computer);
		computer.setName("second_name");
		computerDao.update(computer);
		Computer computer2 = computerDao.getById(computer.getId());
		assertEquals(computer, computer2);
	}

	@Test
	public void testGetAllFromToWithZero() {
		List<Computer> computers = computerDao.getAll(new Long(0), new Long(0), new SortCriteria());
		assertEquals(computers.size(), 0);
	}

	@Test
	public void testGetAllFromTo() {
		List<Computer> computers = computerDao.getAll(new Long(0), new Long(1), new SortCriteria());
		assertEquals(computers.size(), 1);
		assertEquals(computers.get(0).getName(), "MacBook Pro 15.4 inch");
	}

	@Test
	public void testGetNumberOfElement() {
		List<Computer> computers = computerDao.getAll(new SortCriteria());
		Long total = computerDao.getNumberOfElement();
		assertEquals(total, new Long(computers.size()));
	}

	@Test
	public void testNameContains() {
		List<Computer> computers = computerDao.getByName("App", new Long(0), new Long(10), new SortCriteria());
		assertEquals(computers.get(0).getName(), "MacBook Pro 15.4 inch");
	}

	@Test
	public void testGetByNameNumberOfElement(){
		Long l1 = computerDao.getByNameNumberOfElement("test");
		Long l2 = (long) computerDao.getByName("test", new Long(0), new Long(computerDao.getNumberOfElement()), new SortCriteria()).size();
		assertEquals(l1, l2);
	}
	
}
