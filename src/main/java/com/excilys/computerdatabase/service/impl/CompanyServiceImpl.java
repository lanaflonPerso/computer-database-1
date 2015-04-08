/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.dao.CompanyDao;
import com.excilys.computerdatabase.dao.ComputerDao;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.factory.ConnectionFactory;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	ComputerDao computerDao;
	@Autowired
	CompanyDao companyDao;
	@Autowired
	ConnectionFactory connectionFactory;

	@Override
	public List<Company> list(SortCriteria sortCriteria) {
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return companyDao.getAll(sortCriteria);
	}

	@Override
	public Long getNumberOfElement() {
		return companyDao.getNumberOfElement();
	}

	@Override
	public List<Company> list(Long from, Long to, SortCriteria sortCriteria) {
		if (!Validator.isDateFromToCorrect(from, to)) {
			throw new ServiceException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return companyDao.getAll(from, to, sortCriteria);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPANY_ID);
		}
		deleteCompany(id);
	}

	@Override
	public void create(Company t) {
		if (!Validator.isCompanyCorrect(t)) {
			throw new ServiceException(Validator.INVALID_COMPANY);
		}
		companyDao.create(t);
	}

	@Override
	public Company getById(Long id) {
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPANY_ID);
		}
		return companyDao.getById(id);
	}
	
	private void deleteCompany(Long id) {
		computerDao.getByCompanyId(id).stream()
				.forEach(e -> computerDao.delete(e.getId()));
		companyDao.delete(id);
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public ComputerDao getComputerDao() {
		return computerDao;
	}

	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}

}
