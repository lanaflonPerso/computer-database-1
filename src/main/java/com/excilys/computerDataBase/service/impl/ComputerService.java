package com.excilys.computerDataBase.service.impl;

import java.util.List;

import com.excilys.computerDataBase.dao.ComputerDaoInterface;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.exception.ServiceException;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.ServiceInterface;
import com.excilys.computerDataBase.validation.Validator;

/**
 * The Enum ServiceImpl.
 */
public enum ComputerService implements ServiceInterface<Computer> {
	INSTANCE;

	private ComputerDaoInterface computerDao = ComputerDao.INSTANCE;

	@Override
	public List<Computer> list() {
		return computerDao.getAll();
	}

	@Override
	public Computer details(Long computerId) {
		if (Validator.INSTANCE.checkComputerId(computerId) == true) {
			return computerDao.getById(computerId);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPUTER_ID);
		}
	}

	@Override
	public void create(Computer c) {
		if (Validator.INSTANCE.checkComputer(c) == true) {
			computerDao.create(c);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPUTER);
		}
	}

	@Override
	public void update(Computer c) {
		if (Validator.INSTANCE.checkComputer(c) == true && Validator.INSTANCE.checkComputerId(c.getId()) == true) {
			computerDao.update(c);
		} else {
			throw new ServiceException(ServiceException.INVALID_COMPUTER);
		}
	}

	@Override
	public void delete(Long computerId) {
		if (Validator.INSTANCE.checkComputerId(computerId) == true) {
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
		if (Validator.INSTANCE.checkFromTo(from, to) == true) {
			return computerDao.getAll(from, to);
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
