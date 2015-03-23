package com.excilys.computerDataBase.service.impl;

import java.util.List;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.exception.ServiceException;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.service.ServiceInterface;
import com.excilys.computerDataBase.validation.Validator;

/**
 * The Enum CompanyServiceImpl.
 */
public enum CompanyService implements ServiceInterface<Company> {
	
	/** The instance. */
	INSTANCE;

	private CompanyDao companyDao  = CompanyDao.INSTANCE;
	

	@Override
	public List<Company> list() {
		return companyDao.getAll();
	}

	@Override
	public Long getNumberOfElement() {
		return companyDao.getNumberOfElement();
	}
	
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public List<Company> list(Long from, Long to) {
		if (Validator.INSTANCE.checkFromTo(from, to) == true) {
			return companyDao.getAll(from, to);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}
	
}
