package com.excilys.computerDataBase.test.unitaire.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.model.Company;

public class TestCompanyDao {

	CompanyDao companyDao = CompanyDao.INSTANCE;

	@Test
	public void testListComputer() {
		List<Company> companies = companyDao.getAll();
		assertThat(companies.get(0), is(new Company(1l, "Apple Inc.")));

	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDelete() {
		companyDao.delete(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testCreate() {
		companyDao.create(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() {
		companyDao.update(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetById() {
		companyDao.getById(null);
	}

	@Test
	public void testGetAllFromToWithZero() {
		List<Company> companies = companyDao.getAll(new Long(0), new Long(0));
		assertThat(companies == null, is(false));
		assertThat(companies.size(), is(0));
	}

	@Test
	public void testGetAllFromTo() {
		List<Company> companies = companyDao.getAll(new Long(0), new Long(1));
		assertThat(companies == null, is(false));
		assertThat(companies.size(), is(1));
		assertThat(companies.get(0).getName(), is("Apple Inc."));
	}

	@Test(expected = DaoException.class)
	public void testGetAllFromToWrong() {
		companyDao.getAll(null, new Long(1));
	}

	@Test(expected = DaoException.class)
	public void testGetAllFromToWrong2() {
		companyDao.getAll(new Long(4), new Long(1));
	}

	@Test
	public void testGetNumberOfElement() {
		List<Company> companies = companyDao.getAll();
		Long total = companyDao.getNumberOfElement();
		assertThat(companies == null, is(false));
		assertThat(total == null, is(false));
		assertThat(total, is(new Long(companies.size())));
	}

}
