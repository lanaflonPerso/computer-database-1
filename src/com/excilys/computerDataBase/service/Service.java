package com.excilys.computerDataBase.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.entity.Company;
import com.excilys.computerDataBase.entity.Computer;

public enum Service implements ServiceInterface{
	INSTANCE;

	/** The computer dao. */
	private ComputerDao computerDao = ComputerDao.INSTANCE;
	
	/** The company dao. */
	private CompanyDao companyDao = CompanyDao.INSTANCE;

	/** The scanner. */
	Scanner scanner = new Scanner(System.in);
	
	
	@Override
	public void listComputers() {
		List<Computer> computers = this.computerDao.selectAll();
		for (Computer computer : computers) {
			System.out.println(computer.toString());
		}
	}

	@Override
	public void listCompanies() {
		List<Company> companies = this.companyDao.selectAll();
		for (Company company : companies) {
			System.out.println(company.toString());
		}
	}

	@Override
	public void showComputerDetails() {
		Long id = getLongFromCommandLine("Computer Id : ");
		Computer computer = this.computerDao.get(id);
		System.out.println(computer.toString());
	}

	@Override
	public void createComputer() {
		String name = getStringFromCommandLine("Computer name : ");
		Timestamp introduced = getTimestampFromCommandLine("Computer introduced : ");
		Timestamp discontinued = getTimestampFromCommandLine("Computer discontinued : ");
		Long company_id = getLongFromCommandLine("Computer company_id : ");
		Computer computer = new Computer(new Long(0), name, introduced,
				discontinued, company_id);
		this.computerDao.create(computer);
		System.out.println("new computer added : " + computer.toString());
	}

	@Override
	public void updateComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		String name = getStringFromCommandLine("Computer (new) name : ");
		Timestamp introduced = getTimestampFromCommandLine("Computer (new) introduced : ");
		Timestamp discontinued = getTimestampFromCommandLine("Computer (new) discontinued : ");
		Long company_id = getLongFromCommandLine("Computer (new) company_id : ");
		Computer computer = new Computer(id, name, introduced, discontinued,
				company_id);
		this.computerDao.update(computer);
		System.out.println("computer updated : " + computer.toString());
	}

	@Override
	public void deleteComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		this.computerDao.delete(new Computer(id, null, null, null, null));
		System.out.println("computer with id " + id + " deleted");
	}
	
	/**
	 * Gets the long from command line.
	 *
	 * @param request the request
	 * @return the long from command line
	 */
	private Long getLongFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		Long resultAsALong = null;
		try {
			resultAsALong = new Long(result);
		} catch (NumberFormatException e) {

		}
		return resultAsALong;
	}

	/**
	 * Gets the string from command line.
	 *
	 * @param request the request
	 * @return the string from command line
	 */
	private String getStringFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		return result;
	}

	/**
	 * Gets the timestamp from command line.
	 *
	 * @param request the request
	 * @return the timestamp from command line
	 */
	private Timestamp getTimestampFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		Timestamp resultAsATimestamp = null;
		try {
			resultAsATimestamp = new Timestamp(new Long(result));
		} catch (NumberFormatException e) {

		}
		return resultAsATimestamp;
	}
}
