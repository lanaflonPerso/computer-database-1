/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service.services;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;

public interface ComputerService extends CommonService<Computer> {
	public List<Computer> getNameContains(String string, Long from, Long to, SortCriteria sortCriteria);
	public Long getNameContainsElement(String string);
}
