/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.impl.ComputerDaoImpl;
import com.excilys.computerdatabase.persistence.exception.DaoException;
import com.excilys.computerdatabase.sort.SortColumn;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.sort.SortDirection;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-persistence-context.xml" })
public class TestComputerDao {
	@Autowired
	private ComputerDaoImpl computerDao;
	@Autowired
	private DataSource dataSource;

	@Before
	public void setUpDatabase() {
		try {
			IDatabaseConnection dbc = new DatabaseConnection(dataSource.getConnection());			 
			IDataSet dataset = new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/database/fakeDatabase.xml"));
			DatabaseOperation.CLEAN_INSERT.execute(dbc, dataset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertOk() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				new Company(new Long(2), "RCA"));
		computerDao.create(computer);
		assertEquals(computer.getId() == 1, false);

		Computer computer2 = computerDao.getById(computer.getId());
		assertEquals(computer, computer2);
	}

	@Test
	public void testInsertNullDateTime() {
		Computer computer = new Computer(new Long(0), "test_name", null, null,
				new Company(new Long(2), "RCA"));
		computerDao.create(computer);
		assertEquals(computer.getId() == 0, false);

		Computer computer2 = computerDao.getById(computer.getId());
		assertEquals(computer, computer2);
	}

	@Test
	public void testInsertNullCompanyId() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
				new Company(null, "Canon"));
		computerDao.create(computer);
		computer.setCompany(null);

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
		assertEquals(computers.get(0), new Computer(1l,
				"MacBook Pro 15.4 inch", null, null, new Company(new Long(1),
						"Apple Inc.")));
		assertEquals(computers.size() , 5);
	}

	@Test
	public void testListComputerOrderByComputerNameASC(){
		List<Computer> computers = computerDao.getAll(new SortCriteria(SortColumn.COMPUTER_NAME, SortDirection.ASC));
		Computer previous = computers.get(0);
		for(Computer current : computers) {
			assertTrue(previous.getName().compareTo(current.getName()) <= 0);
			previous = current;
		}
		
	}
	
	@Test
	public void testListComputerOrderByComputerNameDESC(){
		List<Computer> computers = computerDao.getAll(new SortCriteria(SortColumn.COMPUTER_NAME, SortDirection.DESC));
		Computer previous = computers.get(0);
		for(Computer current : computers) {
			assertTrue(previous.getName().compareTo(current.getName()) >= 0);
			previous = current;
		}
		
	}
	
	@Test
	public void testListComputerOrderByCompanyNameASC(){
		List<Computer> computers = computerDao.getAll(new SortCriteria(SortColumn.COMPANY_NAME, SortDirection.ASC));
		Computer previous = computers.get(0);
		for(Computer current : computers) {
			assertTrue(previous.getCompany().getName().compareTo(current.getCompany().getName()) <= 0);
			previous = current;
		}
		
	}
	
	@Test
	public void testListComputerOrderByCompanyNameDESC(){
		List<Computer> computers = computerDao.getAll(new SortCriteria(SortColumn.COMPANY_NAME, SortDirection.DESC));
		Computer previous = computers.get(0);
		for(Computer current : computers) {
			assertTrue(previous.getCompany().getName().compareTo(current.getCompany().getName()) >= 0);
			previous = current;
		}
		
	}
	
	
	@Test
	public void testDelete() {
		Computer computer = new Computer(new Long(1), "test_name",
				LocalDateTime.now(), LocalDateTime.now(), new Company(new Long(
						2), "RCA"));
		computerDao.create(computer);
		computerDao.delete(computer.getId());
		Computer computer2 = computerDao.getById(computer.getId());
		assertEquals(null, computer2);
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

	@Test(expected = DaoException.class)
	public void testGetAllFromToWithZero() {
		computerDao.getAll(new Long(0), new Long(0), new SortCriteria());
	}

	@Test
	public void testGetAllFromTo() {
		List<Computer> computers = computerDao.getAll(new Long(0), new Long(1),
				new SortCriteria());
		assertEquals(computers.size(), 1);
		assertEquals(computers.get(0).getName(), "MacBook Pro 15.4 inch");
	}

	@Test
	public void testGetNumberOfElement() {
		List<Computer> computers = computerDao.getAll(new SortCriteria());
		Long total = computerDao.getNumberOfElement();
		assertEquals(new Long(computers.size()), total);
	}

	@Test
	public void testNameContains() {
		List<Computer> computers = computerDao.getByName("App", new Long(0),
				new Long(10), new SortCriteria());
		assertEquals(4, computers.size());
		assertEquals(computers.get(0).getName(), "MacBook Pro 15.4 inch");
	}

	@Test
	public void testGetByNameNumberOfElement() {
		Long l1 = computerDao.getByNameNumberOfElement("test");
		Long l2 = (long) computerDao.getByName("test", new Long(0),
				new Long(computerDao.getNumberOfElement()), new SortCriteria())
				.size();
		assertEquals(l1, l2);
	}

}
