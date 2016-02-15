/**
 * @author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.service.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.CompanyDao;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

/**
 * The Class CompanyServiceImpl.
 */
@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/** The computer dao. */
	@Autowired
	private ComputerDao computerDao;
	
	/** The company dao. */
	@Autowired
	private CompanyDao companyDao;

	@Override
	public List<Company> list(SortCriteria sortCriteria) {
		log.info("List companies");
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return companyDao.getAll(sortCriteria);
	}

	@Override
	public Long getNumberOfElement() {
		log.info("Get number of companies");
		return companyDao.getNumberOfElement();
	}

	@Override
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
	@Transactional(readOnly=false)
	public void delete(Long id) {
		log.info("Delete company : {}", id);
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPANY_ID);
		}
		deleteCompany(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void create(Company t) {
		log.info("Company create : {}", t);
		if (!Validator.isCompanyCorrect(t)) {
			throw new ServiceException(Validator.INVALID_COMPANY);
		}
		companyDao.create(t);
	}

	@Override
	public Company getById(Long id) {
		log.info("Find company with id : {} ", id);
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPANY_ID);
		}
		return companyDao.getById(id);
	}
	
	/**
	 * Delete company.
	 *
	 * @param id the id
	 */
	private void deleteCompany(Long id) {
		computerDao.getByCompanyId(id).stream()
				.forEach(e -> computerDao.delete(e.getId()));
		companyDao.delete(id);
	}

	@Override
	@Transactional(readOnly=false)
	public void update(Company t) {
		log.info("Company update : {}", t);
		if (!Validator.isCompanyCorrect(t)) {
			throw new ServiceException(Validator.INVALID_COMPANY);
		}
		companyDao.update(t);
	}
	
	/**
	 * Gets the company dao.
	 *
	 * @return the company dao
	 */
	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	/**
	 * Sets the company dao.
	 *
	 * @param companyDao the new company dao
	 */
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	/**
	 * Gets the computer dao.
	 *
	 * @return the computer dao
	 */
	public ComputerDao getComputerDao() {
		return computerDao;
	}

	/**
	 * Sets the computer dao.
	 *
	 * @param computerDao the new computer dao
	 */
	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}

}