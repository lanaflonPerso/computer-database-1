/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.io.FileInputStream;
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
import com.excilys.computerdatabase.persistence.dao.CompanyDao;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.persistence.exception.DaoException;
import com.excilys.computerdatabase.sort.SortCriteria;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-persistence-context.xml" })
public class TestCompanyDao {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;
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
	public void testCreateCompany() {
		Company company = new Company(new Long(0), "company_test");

		companyDao.create(company);
		Company company2 = companyDao.getById(company.getId());

		assertNotEquals(company.getId(), new Long(0));
		assertEquals(company2, company);
	}

	@Test
	public void testListCompany() {
		List<Company> companies = companyDao.getAll(new SortCriteria());
		assertEquals(companies.get(0), new Company(1l, "Apple Inc."));
		assertEquals(companies.size(), 2);
	}

	@Test
	public void testDeleteOk() {
		Company company = new Company(new Long(0), "company_test");
		companyDao.create(company);
		assertNotEquals(company.getId(), new Long(0));
		companyDao.delete(company.getId());
		Company c = companyDao.getById(company.getId());
		assertNull(c);		
	}
	
	@Test(expected = DaoException.class)
	public void testUpdateWrong() {
		companyDao.update(null);
	}

	@Test
	public void testUpdateOk() {
		Company company = new Company(0L, "name");
		companyDao.create(company);
		assertNotEquals((long) company.getId(), 0L);
		assertEquals(company, companyDao.getById(company.getId()));
		company.setName("name2");
		companyDao.update(company);
		assertEquals(company, companyDao.getById(company.getId()));
	}
	
	@Test(expected = DaoException.class)
	public void testGetByIdWithNullCompany() {
		assertEquals(companyDao.getById(null), null);
	}

	@Test
	public void testGetByIdOk() {
		Company company = companyDao.getById(new Long(2));
		assertEquals(company.getId(), new Long(2));
	}

	@Test(expected = DaoException.class)
	public void testGetAllFromToWithZero() {
		companyDao.getAll(new Long(0), new Long(0), new SortCriteria());
	}

	@Test
	public void testGetAllFromTo() {
		List<Company> companies = companyDao.getAll(new Long(0), new Long(1),
				new SortCriteria());
		assertEquals(companies == null, false);
		assertEquals(companies.size(), 1);
		assertEquals(companies.get(0).getName(), "Apple Inc.");
	}

	@Test(expected = DaoException.class)
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
		assertEquals(total, new Long(2));
	}

	@Test
	public void testDeleteElementOk() {
		Company company = new Company(new Long(0), "company_test");
		companyDao.create(company);
		Long total = companyDao.getNumberOfElement();
		companyDao.delete(company.getId());
		assertEquals(new Long(total - 1), companyDao.getNumberOfElement());
	}

	@Test
	public void testDeleteElementWrong() {
		Company company = new Company(new Long(0), "company_test");
		companyDao.create(company);
		Long total = companyDao.getNumberOfElement();
		companyDao.delete(company.getId() + 1);
		assertEquals(total, companyDao.getNumberOfElement());
	}

}
