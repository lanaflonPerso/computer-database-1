package com.excilys.computerDataBase.commandLine;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerDataBase.dao.impl.CompanyDao;
import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.entity.Company;
import com.excilys.computerDataBase.entity.Computer;

/**
 * @author excilys
 *
 */
/**
 * @author excilys
 *
 */
/**
 * @author excilys
 *
 */
/**
 * @author excilys
 *
 */
public class CommandLineMenu {
	private ComputerDao computerDao = null;
	private CompanyDao companyDao = null;

	public CommandLineMenu() {
		super();
		this.computerDao = ComputerDao.getInstance();
		this.companyDao = CompanyDao.getInstance();
		try {
			computerDao.openConnection("computer-database-db", "root", "root");
			companyDao.openConnection("computer-database-db", "root", "root");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new CommandLineMenu().displayMainMenu();
	}

	private void displayMainMenu() {
		System.out.println("Chose operation : ");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create a computer");
		System.out.println("5 - Update a computer");
		System.out.println("6 - Delete a computer");

		Scanner scanner = new Scanner(System.in);

		String result = scanner.nextLine();
		int resultAsAnInt = 0;
		try {
			resultAsAnInt = Integer.parseInt(result);
		} catch (NumberFormatException e) {

		}

		//scanner.close();

		switch (resultAsAnInt) {
		case 1:
			listComputers();
			break;
		case 2:
			listCompanies();
			break;
		case 3:
			showComputerDetails();
			break;
		case 4:
			createComputer();
			break;
		case 5:
			updateComputer();
			break;
		case 6:
			deleteComputer();
			break;
		default:
			break;
		}

	}

	private void deleteComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		this.computerDao.delete(new Computer(id, null, null, null, null));
		System.out.println("computer with id " + id + " deleted");
	}

	private void updateComputer() {
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

	private void createComputer() {
		String name = getStringFromCommandLine("Computer name : ");
		Timestamp introduced = getTimestampFromCommandLine("Computer introduced : ");
		Timestamp discontinued = getTimestampFromCommandLine("Computer discontinued : ");
		Long company_id = getLongFromCommandLine("Computer company_id : ");
		Computer computer = new Computer(new Long(0), name, introduced,
				discontinued, company_id);
		this.computerDao.create(computer);
		System.out.println("new computer added : " + computer.toString());
	}

	private void showComputerDetails() {
		Long id = getLongFromCommandLine("Computer Id : ");
		Computer computer = this.computerDao.get(id);
		System.out.println(computer.toString());

	}

	private void listCompanies() {
		List<Company> companies = this.companyDao.selectAll();
		for (Company company : companies) {
			System.out.println(company.toString());
		}
	}

	private void listComputers() {
		List<Computer> computers = this.computerDao.selectAll();
		for (Computer computer : computers) {
			System.out.println(computer.toString());
		}
	}

	private Long getLongFromCommandLine(String request) {
		System.out.println(request);
		Scanner scanner = new Scanner(System.in);
		String result = scanner.nextLine();
		Long resultAsALong = null;
		try {
			resultAsALong = new Long(result);
		} catch (NumberFormatException e) {

		} finally {
			// scanner.close();
		}
		return resultAsALong;
	}

	private String getStringFromCommandLine(String request) {
		System.out.println(request);
		Scanner scanner = new Scanner(System.in);
		String result = scanner.nextLine();
		// scanner.close();
		return result;
	}

	private Timestamp getTimestampFromCommandLine(String request) {
		System.out.println(request);
		Scanner scanner = new Scanner(System.in);
		String result = scanner.nextLine();
		Timestamp resultAsATimestamp = null;
		try {
			resultAsATimestamp = new Timestamp(new Long(result));
		} catch (NumberFormatException e) {

		} finally {
			// scanner.close();
		}
		return resultAsATimestamp;
	}
}
