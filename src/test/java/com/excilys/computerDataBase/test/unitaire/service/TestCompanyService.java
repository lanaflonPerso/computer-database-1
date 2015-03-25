package com.excilys.computerDataBase.test.unitaire.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.excilys.computerDataBase.service.impl.CompanyService;

public class TestCompanyService {

	CompanyService companyServiceImpl = CompanyService.INSTANCE;

	@Test
	public void testGetAll() {
		assertThat(companyServiceImpl.list().size(), is(42));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testCreate() {
		companyServiceImpl.create(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDetails() {
		companyServiceImpl.getById(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() {
		companyServiceImpl.update(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDelete() {
		companyServiceImpl.delete(null);
	}

}
