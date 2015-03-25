package com.excilys.computerDataBase.service.impl;

import java.util.List;

import com.excilys.computerDataBase.dao.ComputerDaoInterface;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.exception.ServiceException;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.ServiceComputerInterface;
import com.excilys.computerDataBase.validation.Validator;

/**
 * The Enum ServiceImpl.
 */
public enum ComputerService implements ServiceComputerInterface {
	INSTANCE;

	private ComputerDaoInterface computerDao = ComputerDao.INSTANCE;

	@Override
	public List<Computer> list() {
		return computerDao.getAll();
	}

	@Override
	public Computer details(Long computerId) {
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
		if (Validator.isComputerCorrect(c)&& Validator.isIdCorrect(c.getId())) {
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
	public List<Computer> list(Long from, Long to) {
		if (Validator.isDateFromToCorrect(from, to)) {
			return computerDao.getAll(from, to);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}

	@Override
	public List<Computer> getNameContains(String string, Long from, Long to) {
		if (Validator.isNameForSearchCorrect(string)) {
			return computerDao.getNameContains(string, from, to);
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
}
