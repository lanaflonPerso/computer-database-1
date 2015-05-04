package com.excilys.computerdatabase.console.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

/**
 * The Interface ConsoleService.
 */
public interface ConsoleService {
	
	/**
	 * Gets the all computer.
	 *
	 * @return the all computer
	 */
	public List<Computer> getAllComputer();
	
	/**
	 * Gets the all company.
	 *
	 * @return the all company
	 */
	public List<Company> getAllCompany();
	
	/**
	 * Gets the computer by id.
	 *
	 * @param id the id
	 * @return the computer by id
	 */
	public Computer getComputerById(Long id);
	
	/**
	 * Creates the computer.
	 *
	 * @param c the c
	 */
	public void createComputer(Computer c);
	
	/**
	 * Update computer.
	 *
	 * @param c the c
	 */
	public void updateComputer(Computer c);
	
	/**
	 * Delete computer.
	 *
	 * @param id the id
	 */
	public void deleteComputer(Long id);
	
	/**
	 * Delete company.
	 *
	 * @param id the id
	 */
	public void deleteCompany(Long id);
}
