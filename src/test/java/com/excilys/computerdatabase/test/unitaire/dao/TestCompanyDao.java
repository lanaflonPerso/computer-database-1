/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.test.unitaire.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.dao.CompanyDao;
import com.excilys.computerdatabase.dao.ComputerDao;
import com.excilys.computerdatabase.exception.DaoException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.test.databaseSimulator.DataBaseSimulator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-application-context.xml" })
public class TestCompanyDao {
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;
	
	private static DataBaseSimulator dataBaseSimulator;
	
	@BeforeClass
	public static void test () {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/test-application-context.xml");
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		dataBaseSimulator = new DataBaseSimulator(dataSource);
		try {
			dataBaseSimulator.initDatabase();
		} catch (Exception e) {
			e.printStackTrace();
			fail("can not create table");
		}
	}
	
	@Before
	public void setUpDatabase(){
		try {
			dataBaseSimulator.resetTable();
		} catch (Exception e) {
			e.printStackTrace();
			fail("can not reset table");
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

	public void testGetByIdWithNullCompany() {
		assertEquals(companyDao.getById(null), null);
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
