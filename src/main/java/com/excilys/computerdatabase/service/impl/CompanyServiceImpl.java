/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dao.CompanyDaoInterface;
import com.excilys.computerdatabase.dao.ComputerDaoInterface;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.factory.ConnectionFactory;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	ComputerDaoInterface computerDao;
	@Autowired
	CompanyDaoInterface companyDao;
	@Autowired
	ConnectionFactory connectionFactory;

	@Override
	public List<Company> list(SortCriteria sortCriteria) {
		return companyDao.getAll(sortCriteria);
	}

	@Override
	public Long getNumberOfElement() {
		return companyDao.getNumberOfElement();
	}

	@Override
	public List<Company> list(Long from, Long to, SortCriteria sortCriteria) {
		if (Validator.isDateFromToCorrect(from, to)) {
			return companyDao.getAll(from, to, sortCriteria);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}

	@Override
	public void delete(Long id) {
		if (Validator.isIdCorrect(id)) {
			deleteCompany(id);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}

	private void deleteCompany(Long id) {
		connectionFactory.startTransaction();
		computerDao.getByCompanyId(id).stream().forEach(e -> computerDao.delete(e.getId()));
		companyDao.delete(id);
		connectionFactory.commit();
		connectionFactory.forcedCloseConnection();
	}

	@Override
	public void create(Company t) {
		if (Validator.isCompanyCorrect(t)) {
			companyDao.create(t);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPANY);
		}
		
	}
	
	public CompanyDaoInterface getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDaoInterface companyDao) {
		this.companyDao = companyDao;
	}


}
