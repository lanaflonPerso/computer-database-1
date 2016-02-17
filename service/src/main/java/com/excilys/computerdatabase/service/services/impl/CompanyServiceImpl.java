package com.excilys.computerdatabase.service.services.impl;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.CompanyDao;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class CompanyServiceImpl.
 */
@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
    @Autowired
    private ComputerDao computerDao;
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> list(SortCriteria sortCriteria) {
        LOGGER.info("List companies");
        if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
            throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
        }
        return companyDao.getAll(sortCriteria);
    }

    @Override
    public Long getNumberOfElement() {
        LOGGER.info("Get number of companies");
        return companyDao.getNumberOfElement();
    }

    @Override
    public List<Company> list(Long from, Long to, SortCriteria sortCriteria) {
        LOGGER.info("List companies");
        if (!Validator.isDateFromToCorrect(from, to)) {
            throw new ServiceException(Validator.INVALID_BOUND);
        }
        if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
            throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
        }
        return companyDao.getAll(from, to, sortCriteria);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        LOGGER.info("Delete company : {}", id);
        if (!Validator.isIdCorrect(id)) {
            throw new ServiceException(Validator.INVALID_COMPANY_ID);
        }
        deleteCompany(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void create(Company t) {
        LOGGER.info("Company create : {}", t);
        if (!Validator.isCompanyCorrect(t)) {
            throw new ServiceException(Validator.INVALID_COMPANY);
        }
        companyDao.create(t);
    }

    @Override
    public Company getById(Long id) {
        LOGGER.info("Find company with id : {} ", id);
        if (!Validator.isIdCorrect(id)) {
            throw new ServiceException(Validator.INVALID_COMPANY_ID);
        }
        return companyDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Company t) {
        LOGGER.info("Company update : {}", t);
        if (!Validator.isCompanyCorrect(t)) {
            throw new ServiceException(Validator.INVALID_COMPANY);
        }
        companyDao.update(t);
    }

    /**
     * Delete company.
     *
     * @param id the id
     */
    private void deleteCompany(Long id) {
        computerDao.getByCompanyId(id).stream().forEach(e -> computerDao.delete(e.getId()));
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