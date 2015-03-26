/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.dao;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerDataBase.dao.sort.SortCriteria;
import com.excilys.computerDataBase.model.Computer;

/**
 * The Interface CommonDaoInterface.
 *
 * @param <T>
 *            the generic type
 */
public interface ComputerDaoInterface extends DaoInterface<Computer> {
	public List<Computer> getNameContains(String string, Long from, Long to, SortCriteria sortCriteria);

	public Long getNameContainsElement(String string);

	public List<Computer> getByCompanyId(Connection connection, Long id);
}
