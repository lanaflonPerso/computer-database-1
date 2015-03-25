package com.excilys.computerDataBase.test.unitaire.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.dao.sort.SortCriteria;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.factory.ConnectionFactory;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;

public class TestCompanyDao {

	private CompanyDao companyDao = CompanyDao.INSTANCE;
	private ComputerDao computerDao = ComputerDao.INSTANCE;
	private Connection connection = null;

	@Before
	public void init() throws SQLException {
		connection = ConnectionFactory.INSTANCE.createConnection();
		connection.setAutoCommit(false);
	}

	@After
	public void after() throws SQLException {
		connection.rollback();
	}

	@Test
	public void testCreateCompany() {
		Company company = new Company(new Long(0), "company_test");
		companyDao.create(connection, company);
		Company company2 = companyDao.getById(connection, company.getId());
		assertEquals(company2, company);
	}

	@Test
	public void testListCompany() {
		List<Company> companies = companyDao.getAll(new SortCriteria());
		assertEquals(companies.get(0), new Company(1l, "Apple Inc."));
	}

	public void testDelete() {
		Company company = new Company(new Long(0), "company_test");
		companyDao.create(connection, company);

		Computer computer = new Computer(null, "name", null, null, company);
		computerDao.create(computer);
		companyDao.delete(company.getId());

		try {
			companyDao.getById(company.getId());
			fail("no exception : company not deleted");
		} catch (NullPointerException e) {

		}

		try {
			computerDao.getById(computer.getId());
			fail("no exception : computer not deleted");
		} catch (NullPointerException e) {

		}

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() {
		companyDao.update(null);
	}

	@Test(expected = DaoException.class)
	public void testGetByIdWithNullCompanyId() {
		companyDao.getById(null);
	}

	public void testGetByIdOk() {
		Company company = companyDao.getById(new Long(4));
		assertEquals(company.getId(), new Long(4));
	}

	@Test
	public void testGetAllFromToWithZero() {
		List<Company> companies = companyDao.getAll(new Long(0), new Long(0), new SortCriteria());
		assertEquals(companies == null, false);
		assertEquals(companies.size(), 0);
	}

	@Test
	public void testGetAllFromTo() {
		List<Company> companies = companyDao.getAll(new Long(0), new Long(1), new SortCriteria());
		assertEquals(companies == null, false);
		assertEquals(companies.size(), 1);
		assertEquals(companies.get(0).getName(), "Apple Inc.");
	}

	@Test(expected = NullPointerException.class)
	public void testGetAllFromToWrong() {
		companyDao.getAll(null, new Long(1), new SortCriteria());
	}

	@Test(expected = DaoException.class)
	public void testGetAllFromToWrong2() {
		companyDao.getAll(new Long(4), new Long(1), new SortCriteria());
	}

	@Test
	public void testGetNumberOfElement() {
		List<Company> companies = companyDao.getAll(new SortCriteria());
		Long total = companyDao.getNumberOfElement();
		assertEquals(total, new Long(companies.size()));
	}

}
