/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.test.unitaire.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.dao.impl.CompanyDao;
import com.excilys.computerdatabase.dao.impl.ComputerDao;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-application-context.xml" })
public class TestCompanyDao {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;
	
	
	@Test
	public void testCreateCompany() {
		Company company = new Company(new Long(0), "company_test");
		companyDao.create( company);
		Company company2 = companyDao.getById(company.getId());
		assertEquals(company2, company);
	}

	@Test
	public void testListCompany() {
		List<Company> companies = companyDao.getAll(new SortCriteria());
		assertEquals(companies.get(0), new Company(1l, "Apple Inc."));
	}

	public void testDelete() {
		Company company = new Company(new Long(0), "company_test");
		companyDao.create(company);

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
