package com.excilys.computerDataBase.service;

import java.util.List;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.entity.Company;
import com.excilys.computerDataBase.entity.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Enum ServiceImpl.
 */
public enum ServiceImpl implements ServiceInterface{
	
	/** The instance. */
	INSTANCE;

	/* (non-Javadoc)
	 * @see com.excilys.computerDataBase.service.ServiceInterface#listComputers()
	 */
	@Override
	public List<Computer> listComputers() {
		return ComputerDao.INSTANCE.selectAll();
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerDataBase.service.ServiceInterface#listCompanies()
	 */
	@Override
	public List<Company> listCompanies() {
		return CompanyDao.INSTANCE.selectAll();
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerDataBase.service.ServiceInterface#computerDetails(java.lang.Long)
	 */
	@Override
	public Computer computerDetails(Long computerId) {
		return ComputerDao.INSTANCE.get(computerId);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerDataBase.service.ServiceInterface#createComputer(com.excilys.computerDataBase.entity.Computer)
	 */
	@Override
	public Computer createComputer(Computer c) {
		return ComputerDao.INSTANCE.create(c);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerDataBase.service.ServiceInterface#updateComputer(com.excilys.computerDataBase.entity.Computer)
	 */
	@Override
	public Computer updateComputer(Computer c) {
		return ComputerDao.INSTANCE.update(c);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerDataBase.service.ServiceInterface#deleteComputer(java.lang.Long)
	 */
	@Override
	public void deleteComputer(Long computerId) {
		Computer c = new Computer(computerId, null, null, null, null);
		ComputerDao.INSTANCE.delete(c);
	}
}
