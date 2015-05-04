/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.dao;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;

/**
 * The Interface ComputerDao.
 */
public interface ComputerDao extends CommonDao<Computer> {
	
	/**
	 * Gets the by name.
	 *
	 * @param string the string
	 * @param from the from
	 * @param to the to
	 * @param sortCriteria the sort criteria
	 * @return the by name
	 */
	public List<Computer> getByName(String string, Long from, Long to, SortCriteria sortCriteria);

	/**
	 * Gets the by name number of element.
	 *
	 * @param string the string
	 * @return the by name number of element
	 */
	public Long getByNameNumberOfElement(String string);

	/**
	 * Gets the by company id.
	 *
	 * @param id the id
	 * @return the by company id
	 */
	public List<Computer> getByCompanyId(Long id);
}
