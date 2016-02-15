package com.excilys.computerdatabase.console.service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Interface ConsoleService.
 */
public interface ConsoleService {

    /**
     * Gets the all computer.
     *
     * @return the all computer
     */
    List<Computer> getAllComputer();

    /**
     * Gets the all company.
     *
     * @return the all company
     */
    List<Company> getAllCompany();

    /**
     * Gets the computer by id.
     *
     * @param id the id
     * @return the computer by id
     */
    Computer getComputerById(Long id);

    /**
     * Creates the computer.
     *
     * @param c the c
     */
    void createComputer(Computer c);

    /**
     * Update computer.
     *
     * @param c the c
     */
    void updateComputer(Computer c);

    /**
     * Delete computer.
     *
     * @param id the id
     */
    void deleteComputer(Long id);

    /**
     * Delete company.
     *
     * @param id the id
     */
    void deleteCompany(Long id);
}
