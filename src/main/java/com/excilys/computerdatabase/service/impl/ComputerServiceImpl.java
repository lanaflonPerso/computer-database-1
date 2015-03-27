/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dao.ComputerDaoInterface;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Service
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	ComputerDaoInterface computerDao;

	public ComputerServiceImpl() {
		super();
	}

	@Override
	public List<Computer> list(SortCriteria sortCriteria) {
		return computerDao.getAll(sortCriteria);
	}

	@Override
	public Computer getById(Long computerId) {
		if (Validator.isIdCorrect(computerId)) {
			return computerDao.getById(computerId);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPUTER_ID);
		}
	}

	@Override
	public void create(Computer c) {
		if (Validator.isComputerCorrect(c)) {
			computerDao.create(c);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPUTER);
		}
	}

	@Override
	public void update(Computer c) {
		if (Validator.isComputerCorrect(c) && Validator.isIdCorrect(c.getId())) {
			computerDao.update(c);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPUTER);
		}
	}

	@Override
	public void delete(Long computerId) {
		if (Validator.isIdCorrect(computerId)) {
			computerDao.delete(computerId);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPUTER);
		}
	}

	@Override
	public Long getNumberOfElement() {
		return computerDao.getNumberOfElement();
	}

	@Override
	public List<Computer> list(Long from, Long to, SortCriteria sortCriteria) {
		if (Validator.isDateFromToCorrect(from, to)) {
			return computerDao.getAll(from, to, sortCriteria);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}

	@Override
	public List<Computer> getNameContains(String string, Long from, Long to,
			SortCriteria sortCriteria) {
		if (Validator.isNameForSearchCorrect(string)) {
			return computerDao.getByName(string, from, to, sortCriteria);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}

	public ComputerDaoInterface getComputerDao() {
		return computerDao;
	}

	public void setComputerDao(ComputerDaoInterface computerDao) {
		this.computerDao = computerDao;
	}

	@Override
	public Long getNameContainsElement(String string) {
		return computerDao.getByNameNumberOfElement(string);
	}
}