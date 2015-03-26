/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.service;

import java.util.List;

import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.sort.SortCriteria;

public interface ComputerServiceInterface extends ServiceInterface<Computer> {
	public List<Computer> getNameContains(String string, Long from, Long to, SortCriteria sortCriteria);
	public Long getNameContainsElement(String string);
}
