/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerDataBase.ui;

import java.time.LocalDateTime;
import java.util.Scanner;

import com.excilys.computerDataBase.dao.sort.SortCriteria;
import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.impl.CompanyService;
import com.excilys.computerDataBase.service.impl.ComputerService;
import com.excilys.computerDataBase.validation.Validator;


/**
 * The Class CommandLineMenu.
 */
public class CommandLineMenu {

	/** The scanner. */
	Scanner scanner = new Scanner(System.in);

	/**
	 * Instantiates a new command line menu.
	 */
	public CommandLineMenu() {
		super();
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		new CommandLineMenu().displayMainMenu();
	}

	/**
	 * Display main menu.
	 */
	private void displayMainMenu() {
		System.out.println("Chose operation : ");
		System.out.println("1 - List computers");
		System.out.println("2 - List companies");
		System.out.println("3 - Show computer details");
		System.out.println("4 - Create a computer");
		System.out.println("5 - Update a computer");
		System.out.println("6 - Delete a computer");

		String result = scanner.nextLine();
		int resultAsAnInt = 0;
		try {
			resultAsAnInt = Integer.parseInt(result);
		} catch (NumberFormatException e) {

		}
		operationSwitch(resultAsAnInt);
		scanner.close();
	}

	/**
	 * Operation switch.
	 *
	 * @param resultAsAnInt
	 *            the result as an int
	 */
	private void operationSwitch(int resultAsAnInt) {
		switch (resultAsAnInt) {
		case 1:
			listComputer();
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

	/**
	 * List computer.
	 */
	private void listComputer() {
		Paginator.INSTANCE.print(ComputerService.INSTANCE.list(new SortCriteria()));
	}

	/**
	 * List companies.
	 */
	private void listCompanies() {
		Paginator.INSTANCE.print(CompanyService.INSTANCE.list(new SortCriteria()));
	}

	/**
	 * Show computer details.
	 */
	private void showComputerDetails() {
		Long id = getLongFromCommandLine("Computer Id : ");
		Computer computer = ComputerService.INSTANCE.getById(id);
		System.out.println(computer.toString());
	}

	/**
	 * Creates the computer.
	 */
	private void createComputer() {
		String name = getStringFromCommandLine("Computer name : ");
		LocalDateTime introduced = getLocalDateTimeFromCommandLine("Computer introduced : ");
		LocalDateTime discontinued = getLocalDateTimeFromCommandLine("Computer discontinued : ");
		Long company_id = getLongFromCommandLine("Computer company_id : ");
		Computer computer = new Computer(new Long(0), name, introduced,
				discontinued, new Company(company_id, null));
		ComputerService.INSTANCE.create(computer);
		System.out.println("computer created : " + computer.toString());
	}

	/**
	 * Update computer.
	 */
	private void updateComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		String name = getStringFromCommandLine("Computer (new) name : ");
		LocalDateTime introduced = getLocalDateTimeFromCommandLine("Computer (new) introduced : ");
		LocalDateTime discontinued = getLocalDateTimeFromCommandLine("Computer (new) discontinued : ");
		Long company_id = getLongFromCommandLine("Computer (new) company_id : ");
		Computer computer = new Computer(id, name, introduced, discontinued,
				new Company(company_id, null));
		ComputerService.INSTANCE.update(computer);
		System.out.println("computer updated : " + computer.toString());

	}

	/**
	 * Delete computer.
	 */
	private void deleteComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		ComputerService.INSTANCE.delete(id);
	}

	/**
	 * Gets the long from command line.
	 *
	 * @param request
	 *            the request
	 * @return the long from command line
	 */
	private Long getLongFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		Long resultAsALong = null;
		try {
			resultAsALong = new Long(result);
		} catch (NumberFormatException e) {
			System.out.println(ParsingException.CAN_NOT_PARSE_INTO_LONG);
		}
		return resultAsALong;
	}

	/**
	 * Gets the string from command line.
	 *
	 * @param request
	 *            the request
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
	 * @param request
	 *            the request
	 * @return the timestamp from command line
	 */
	private LocalDateTime getLocalDateTimeFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		if (Validator.validateDate(result)) {
			return LocalDateTime.parse(result);
		} else {
			System.out.println(Validator.WRONG_DATE_FORMAT);
		}
		return null;
	}

}
