/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.sort.SortCriteria;

@Service
public interface ComputerService extends CommonService<Computer> {
	public List<Computer> getNameContains(String string, Long from, Long to, SortCriteria sortCriteria);
	public Long getNameContainsElement(String string);
}
