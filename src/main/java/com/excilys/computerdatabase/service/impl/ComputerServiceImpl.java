/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dao.ComputerDao;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Service
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	ComputerDao computerDao;

	public ComputerServiceImpl() {
		super();
	}

	@Override
	public List<Computer> list(SortCriteria sortCriteria) {
		return computerDao.getAll(sortCriteria);
	}

	@Override
	public Computer getById(Long computerId) {
		if (!Validator.isIdCorrect(computerId)) {
			throw new ServiceException(Validator.INVALID_COMPUTER_ID);
		}
		return computerDao.getById(computerId);

	}

	@Override
	public void create(Computer c) {
		if (!Validator.isComputerCorrect(c)) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.create(c);
	}

	@Override
	public void update(Computer c) {
		if (!Validator.isComputerCorrect(c) && Validator.isIdCorrect(c.getId())) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.update(c);

	}

	@Override
	public void delete(Long computerId) {
		if (!Validator.isIdCorrect(computerId)) {
			throw new ServiceException(Validator.INVALID_COMPUTER);
		}
		computerDao.delete(computerId);

	}

	@Override
	public Long getNumberOfElement() {
		return computerDao.getNumberOfElement();
	}

	@Override
	public List<Computer> list(Long from, Long to, SortCriteria sortCriteria) {
		if (!Validator.isDateFromToCorrect(from, to)) {
			throw new ServiceException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return computerDao.getAll(from, to, sortCriteria);
	}

	@Override
	public List<Computer> getNameContains(String string, Long from, Long to,
			SortCriteria sortCriteria) {
		if (!Validator.isStringForSearchCorrect(string)) {
			throw new ServiceException(Validator.INVALID_STRING_FOR_SEARCH);
		}
		if (!Validator.isStringForSearchCorrect(string)) {
			throw new ServiceException(Validator.INVALID_BOUND);
		}
		if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
			throw new ServiceException(Validator.INVALID_SORT_CRITERIA);
		}
		return computerDao.getByName(string, from, to, sortCriteria);

	}

	public ComputerDao getComputerDao() {
		return computerDao;
	}

	public void setComputerDao(ComputerDao computerDao) {
		this.computerDao = computerDao;
	}

	@Override
	public Long getNameContainsElement(String string) {
		if (!Validator.isStringForSearchCorrect(string)) {
			throw new ServiceException(Validator.INVALID_STRING_FOR_SEARCH);
		}
		return computerDao.getByNameNumberOfElement(string);
	}
}
