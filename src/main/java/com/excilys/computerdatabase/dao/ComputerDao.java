/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dao;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;

public interface ComputerDao extends CommonDao<Computer> {
	
	public List<Computer> getByName(String string, Long from, Long to, SortCriteria sortCriteria);

	public Long getByNameNumberOfElement(String string);

	public List<Computer> getByCompanyId(Long id);
}
