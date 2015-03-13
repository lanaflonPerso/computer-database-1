package com.excilys.computerDataBase.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.entity.Company;

public class TestCompanyDao {

	CompanyDao companyDao = CompanyDao.INSTANCE;

	@Test
	public void testListComputer() {
		try {
			List<Company> companies = companyDao.selectAll();
			assertThat(companies.get(0), is(new Company(1l, "Apple Inc.")));
		} catch (Exception e) {
			fail("List Computer Exception");
		}	
	}

}
