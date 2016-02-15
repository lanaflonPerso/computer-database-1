/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.service.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.CompanyDao;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/test-service-application-context.xml" })
public class TestCompanyService extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private ComputerService computerService;
	private CompanyServiceImpl companyServiceImpl = new CompanyServiceImpl();
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private ComputerDao computerDao;

	@Before
	public void setUp() {
		companyServiceImpl.setCompanyDao(companyDao);
		companyServiceImpl.setComputerDao(computerDao);
	}
	
	@After
	public void after() {
		companyServiceImpl.setCompanyDao(companyDao);
		companyServiceImpl.setComputerDao(computerDao);
	}

	@Test
	public void testGetAllOk() {
		CompanyDao companyDaoMock = Mockito.mock(CompanyDao.class);
		List<Company> companies = new ArrayList<Company>();
		companies.add(new Company());
		Mockito.when(companyDaoMock.getAll(Mockito.any())).thenReturn(companies);

		companyServiceImpl.setCompanyDao(companyDaoMock);
		assertEquals(companyServiceImpl.list(new SortCriteria()).size(), 1);
		companyServiceImpl.setCompanyDao(companyDao);
	}

	@Test(expected = ServiceException.class)
	public void testCreateWrong() {
		companyServiceImpl.create(null);
	}

	@Test
	public void testCreateOk() {
		Long l = companyServiceImpl.getNumberOfElement();
		Company company = new Company(null, "company_test");
		companyServiceImpl.create(company);
		Company company2 = companyServiceImpl.getById(company.getId());
		Long l2 = companyServiceImpl.getNumberOfElement();
		assertEquals(new Long(l + 1), l2);
		assertEquals(company2, company);
	}

	@Test(expected = ServiceException.class)
	public void testDetailsWrong() {
		companyServiceImpl.getById(null);
	}

	@Test(expected = ServiceException.class)
	public void testUpdateWrong() {
		companyServiceImpl.update(null);
	}

	@Test
	public void testUpdateOk() {
		Company company = new Company(0L, "name");
		companyServiceImpl.create(company);
		assertNotEquals((long) company.getId(), 0L);
		assertEquals(company,companyServiceImpl.getById(company.getId()));
		company.setName("name2");
		companyServiceImpl.update(company);
		assertEquals(company,companyServiceImpl.getById(company.getId()));
	}
	
	@Test(expected = ServiceException.class)
	public void testDeleteWrong() {
		companyServiceImpl.delete(null);
	}
	
	@Test
	public void testDeleteOk() {
		Company company = new Company(null, "company_test");
		companyServiceImpl.create(company);
		Computer computer = new Computer(null, "nameDeleteCompanyTest", null,
				null, new Company(company.getId(), null));
		computerService.create(computer);

		companyServiceImpl.delete(company.getId());

		
		Company company2 = companyServiceImpl.getById(company.getId());
		assertNull(company2);
		Computer computer2 = computerService.getById(computer.getId());
		assertNull(computer2);
		
	}

	@Test
	public void testDeleteNullCompanyDao() {
		Company company = new Company(null, "company_test");
		companyServiceImpl.create(company);
		Computer computer = new Computer(null, "nameDeleteCompanyTest", null, null, company);
		computerService.create(computer);
		companyServiceImpl.setCompanyDao(null);
		try {
			companyServiceImpl.delete(company.getId());
			fail("No exception occured");
		} catch (NullPointerException expectedException) {
			
		}
		companyServiceImpl.setCompanyDao(companyDao);
		Company company2 = companyServiceImpl.getById(company.getId());
		assertEquals(company, company2);
	}
	
	@Test
	public void testDeleteNullComputerDao() {
		Company company = new Company(null, "company_test");
		companyServiceImpl.create(company);
		Computer computer = new Computer(null, "nameDeleteCompanyTest", null,
				null, company);
		computerService.create(computer);
		companyServiceImpl.setComputerDao(null);
		try {
			companyServiceImpl.delete(company.getId());
			fail("No exception occured");
		} catch (NullPointerException expectedException) {
		}
		Computer computer2 = computerService.getById(computer.getId());
		assertEquals(computer, computer2);
		Company company2 = companyServiceImpl.getById(company.getId());
		assertEquals(company, company2);
	}

}
