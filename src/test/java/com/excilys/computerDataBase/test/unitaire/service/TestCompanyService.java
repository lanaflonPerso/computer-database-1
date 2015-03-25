package com.excilys.computerDataBase.test.unitaire.service;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.dao.sort.SortCriteria;
import com.excilys.computerDataBase.exception.ServiceException;
import com.excilys.computerDataBase.service.impl.CompanyService;

public class TestCompanyService {

	private static CompanyService companyService = CompanyService.INSTANCE;

	@BeforeClass
	public static void tearUp(){
		//companyService.setCompanyDao(null);
	}
	
	@AfterClass
	public static void tearDown(){
		companyService.setCompanyDao(CompanyDao.INSTANCE);
	}
	
	@Test
	public void testGetAll() {
		assertEquals(companyService.list(new SortCriteria()).size(), 42);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testCreate() {
		companyService.create(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testDetails() {
		companyService.getById(null);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testUpdate() {
		companyService.update(null);
	}

	@Test(expected = ServiceException.class)
	public void testDelete() {
		companyService.delete(null);
	}

}
