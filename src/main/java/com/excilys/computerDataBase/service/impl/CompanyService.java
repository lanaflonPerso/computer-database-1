/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.exception.DaoException;
import com.excilys.computerDataBase.exception.ServiceException;
import com.excilys.computerDataBase.factory.ConnectionFactory;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.ServiceCompanyInterface;
import com.excilys.computerDataBase.sort.SortCriteria;
import com.excilys.computerDataBase.util.DaoUtil;
import com.excilys.computerDataBase.validation.Validator;

/**
 * The Enum CompanyServiceImpl.
 */
public enum CompanyService implements ServiceCompanyInterface {

	/** The instance. */
	INSTANCE;

	private CompanyDao companyDao = CompanyDao.INSTANCE;

	@Override
	public List<Company> list(SortCriteria sortCriteria) {
		return companyDao.getAll(sortCriteria);
	}

	@Override
	public Long getNumberOfElement() {
		return companyDao.getNumberOfElement();
	}

	@Override
	public List<Company> list(Long from, Long to, SortCriteria sortCriteria) {
		if (Validator.isDateFromToCorrect(from, to)) {
			return companyDao.getAll(from, to, sortCriteria);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}

	@Override
	public void delete(Long id) {
		if (Validator.isIdCorrect(id)) {
			deleteCompany(id);
		} else {
			throw new ServiceException(ServiceException.INVALID_PARAMETER);
		}
	}

	private void deleteCompany(Long id) {
		Connection connection = null;
		try {
			connection = ConnectionFactory.INSTANCE.createConnection();
			connection.setAutoCommit(false);
			List<Computer> computers = ComputerDao.INSTANCE.getByCompanyId(connection, id);
			for (Computer computer : computers) {
				ComputerDao.INSTANCE.delete(connection, computer.getId());
			}
			companyDao.delete(connection, id);
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new ServiceException(
						ServiceException.CAN_NOT_ROLLBACK_TRANSACTION, e);
			}
			throw new DaoException(DaoException.CAN_NOT_DELETE_ELEMENT, e);
		} finally {
			DaoUtil.closeConnection(connection);
		}
	}

	public CompanyDao getCompanyDao() {
		return companyDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

}
