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

import com.excilys.computerdatabase.dao.ComputerDao;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Service
public class ComputerServiceImpl implements ComputerService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ComputerDao computerDao;

	public ComputerServiceImpl() {
		super();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Computer> list(SortCriteria sortCriteria) {
		log.info("List computers with criteria : {} ", sortCriteria);
		return computerDao.getAll(sortCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public Computer getById(Long id) {
		log.info("Get computer with id : {0}", id);
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPUTER_ID);
		}
		return computerDao.getById(id);

	}

	@Override
	@Transactional
	public void create(Computer c) {
		log.info("Update computer : {0}", c);
		if (!Validator.isComputerCorrect(c)) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.create(c);
	}

	@Override
	@Transactional
	public void update(Computer c) {
		log.info("Update computer : {0}", c);
		if (!Validator.isComputerCorrect(c) && Validator.isIdCorrect(c.getId())) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.update(c);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		log.info("Get computer with id : {0}", id);
		if (!Validator.isIdCorrect(id)) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNumberOfElement() {
		log.info("Get number of computers");
		return computerDao.getNumberOfElement();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Computer> list(Long from, Long to, SortCriteria sortCriteria) {
		log.info("List computers with criteria : {} from {} to {} ", sortCriteria, from, to);
		if (!Validator.isDateFromToCorrect(from, to)) {
			throw new ServiceException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return computerDao.getAll(from, to, sortCriteria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Computer> getNameContains(String search, Long from, Long to,
			SortCriteria sortCriteria) {
		log.info("List computers with criteria : {} and contains {} from {} to {} ", sortCriteria, search, from, to);
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
	@Transactional(readOnly = true)
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
