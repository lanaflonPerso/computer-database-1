package com.excilys.computerdatabase.service.services.impl;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.service.services.ComputerService;
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
 *         The Class ComputerServiceImpl.
 */
@Service
@Transactional(readOnly = true)
public class ComputerServiceImpl implements ComputerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerServiceImpl.class);
    @Autowired
    private ComputerDao computerDao;

    @Override
    public List<Computer> list(SortCriteria sortCriteria) {
        LOGGER.info("List computers with criteria : {} ", sortCriteria);
        return computerDao.getAll(sortCriteria);
    }

    @Override
    public Computer getById(Long id) {
        LOGGER.info("Get computer with id : {}", id);
        if (!Validator.isIdCorrect(id)) {
            throw new ServiceException(Validator.INVALID_COMPUTER_ID);
        }
        return computerDao.getById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void create(Computer c) {
        LOGGER.info("Update computer : {}", c);
        if (!Validator.isComputerCorrect(c)) {
            throw new ServiceException(Validator.INVALID_COMPUTER);
        }
        computerDao.create(c);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Computer c) {
        LOGGER.info("Update computer : {}", c);
        if (!Validator.isComputerCorrect(c) && Validator.isIdCorrect(c.getId())) {
            throw new ServiceException(Validator.INVALID_COMPUTER);
        }
        computerDao.update(c);

    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        LOGGER.info("Get computer with id : {}", id);
        if (!Validator.isIdCorrect(id)) {
            throw new ServiceException(Validator.INVALID_COMPUTER);
        }
        computerDao.delete(id);
    }

    @Override
    public Long getNumberOfElement() {
        LOGGER.info("Get number of computers");
        return computerDao.getNumberOfElement();
    }

    @Override
    public List<Computer> list(Long from, Long to, SortCriteria sortCriteria) {
        LOGGER.info("List computers with criteria : {} from {} to {} ", sortCriteria, from, to);
        if (!Validator.isDateFromToCorrect(from, to)) {
            throw new ServiceException(Validator.INVALID_BOUND);
        }
        if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
            throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
        }
        return computerDao.getAll(from, to, sortCriteria);
    }

    @Override
    public List<Computer> getNameContains(String search, Long from, Long to, SortCriteria sortCriteria) {
        LOGGER.info("List computers with criteria : {} and contains {} from {} to {} ", sortCriteria, search, from, to);
        if (!Validator.isStringForSearchCorrect(search)) {
            throw new ServiceException(Validator.INVALID_STRING_FOR_SEARCH);
        }
        if (!Validator.isStringForSearchCorrect(search)) {
            throw new ServiceException(Validator.INVALID_BOUND);
        }
        if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
            throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
        }
        return computerDao.getByName(search, from, to, sortCriteria);
    }

    @Override
    public Long getNameContainsElement(String string) {
        if (!Validator.isStringForSearchCorrect(string)) {
            throw new ServiceException(Validator.INVALID_STRING_FOR_SEARCH);
        }
        return computerDao.getByNameNumberOfElement(string);
    }

    public ComputerDao getComputerDao() {
        return computerDao;
    }

    public void setComputerDao(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }
}
