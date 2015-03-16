package com.excilys.computerDataBase.ui;

import java.sql.Timestamp;
import java.util.Scanner;

import com.excilys.computerDataBase.entity.Computer;
import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.service.Paginator;
import com.excilys.computerDataBase.service.ServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandLineMenu.
 *
 * @author excilys
 */
public class CommandLineMenu {

	/** The scanner. */
	Scanner scanner = new Scanner(System.in);

	/** The service. */
	private ServiceImpl service;

	/**
	 * Instantiates a new command line menu.
	 */
	public CommandLineMenu() {
		super();
		this.service = ServiceImpl.INSTANCE;
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
		Paginator.INSTANCE.print(service.listComputers());
	}

	/**
	 * List companies.
	 */
	private void listCompanies() {
		Paginator.INSTANCE.print(service.listCompanies());
	}

	/**
	 * Show computer details.
	 */
	private void showComputerDetails() {
		Long id = getLongFromCommandLine("Computer Id : ");
		Computer computer = service.computerDetails(id);
		System.out.println(computer.toString());
	}

	/**
	 * Creates the computer.
	 */
	private void createComputer() {
		String name = getStringFromCommandLine("Computer name : ");
		Timestamp introduced = getTimestampFromCommandLine("Computer introduced : ");
		Timestamp discontinued = getTimestampFromCommandLine("Computer discontinued : ");
		Long company_id = getLongFromCommandLine("Computer company_id : ");
		Computer computer = new Computer(new Long(0), name, introduced,
				discontinued, company_id);
		service.createComputer(computer);
		System.out.println("computer created : " + computer.toString());
	}

	/**
	 * Update computer.
	 */
	private void updateComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		String name = getStringFromCommandLine("Computer (new) name : ");
		Timestamp introduced = getTimestampFromCommandLine("Computer (new) introduced : ");
		Timestamp discontinued = getTimestampFromCommandLine("Computer (new) discontinued : ");
		Long company_id = getLongFromCommandLine("Computer (new) company_id : ");
		Computer computer = new Computer(id, name, introduced, discontinued,
				company_id);
		service.updateComputer(computer);
		System.out.println("computer updated : " + computer.toString());

	}

	/**
	 * Delete computer.
	 */
	private void deleteComputer() {
		Long id = getLongFromCommandLine("Computer id : ");
		service.deleteComputer(id);
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
	private Timestamp getTimestampFromCommandLine(String request) {
		System.out.println(request);
		String result = scanner.nextLine();
		Timestamp resultAsATimestamp = null;
		try {
			resultAsATimestamp = new Timestamp(new Long(result));
		} catch (NumberFormatException e) {
			System.out.println(ParsingException.CAN_NOT_PARSE_INTO_TIMESTAMP);
		}
		return resultAsATimestamp;
	}

}
