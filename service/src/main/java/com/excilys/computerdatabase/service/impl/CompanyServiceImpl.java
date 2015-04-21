/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.CompanyDao;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Service
public class CompanyServiceImpl implements CompanyService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ComputerDao computerDao;
	@Autowired
	CompanyDao companyDao;

	@Override
	@Transactional(readOnly = true)
	public List<Company> list(SortCriteria sortCriteria) {
		log.info("List companies");
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return companyDao.getAll(sortCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNumberOfElement() {
		log.info("Get number of companies");
		return companyDao.getNumberOfElement();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Company> list(Long from, Long to, SortCriteria sortCriteria) {
		log.info("List companies");
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
		log.info("Delete company : {0}", id);
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPANY_ID);
		}
		deleteCompany(id);
	}

	@Override
	@Transactional
	public void create(Company t) {
		log.info("Company create : {0}", t);
		if (!Validator.isCompanyCorrect(t)) {
			throw new ServiceException(Validator.INVALID_COMPANY);
		}
		companyDao.create(t);
	}

	@Override
	@Transactional(readOnly = true)
	public Company getById(Long id) {
		log.info("Find company with id : {0} ", id);
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
