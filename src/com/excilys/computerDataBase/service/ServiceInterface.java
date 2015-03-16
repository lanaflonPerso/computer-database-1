package com.excilys.computerDataBase.service;

import java.util.List;

import com.excilys.computerDataBase.entity.Company;
import com.excilys.computerDataBase.entity.Computer;

// TODO: Auto-generated Javadoc
/**
 * The Interface ServiceInterface.
 */
public interface ServiceInterface {
	
	/**
	 * List computers.
	 *
	 * @return the list
	 */
	public List<Computer> listComputers();
	
	/**
	 * List companies.
	 *
	 * @return the list
	 */
	public List<Company> listCompanies();
	
	/**
	 * Show computer details.
	 *
	 * @param computerId the computer id
	 * @return the computer
	 */
	public Computer computerDetails(Long computerId);
	
	/**
	 * Creates the computer.
	 *
	 * @param c the c
	 * @return the computer
	 */
	public Computer	createComputer(Computer c);
	
	/**
	 * Update computer.
	 *
	 * @param c the c
	 * @return the computer
	 */
	public Computer updateComputer(Computer c);
	
	/**
	 * Delete computer.
	 *
	 * @param computerId the computer id
	 */
	public void deleteComputer(Long computerId);
}
