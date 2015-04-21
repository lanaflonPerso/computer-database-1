package com.excilys.computerdatabase.console.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public interface ConsoleService {
	public List<Computer> getAllComputer();
	public List<Company> getAllCompany();
	public Computer getComputerById(Long id);
	public void createComputer(Computer c);
	public void updateComputer(Computer c);
	public void deleteComputer(Long id);
	public void deleteCompany(Long id);
}
