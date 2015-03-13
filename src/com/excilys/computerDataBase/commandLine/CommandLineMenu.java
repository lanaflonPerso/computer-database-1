package com.excilys.computerDataBase.commandLine;

import java.util.Scanner;

import com.excilys.computerDataBase.service.Service;

/**
 * The Class CommandLineMenu.
 *
 * @author excilys
 */
public class CommandLineMenu {

	/** The scanner. */
	Scanner scanner = new Scanner(System.in);

	private Service service;

	/**
	 * Instantiates a new command line menu.
	 */
	public CommandLineMenu() {
		super();
		this.service = Service.INSTANCE;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
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
	}

	/**
	 * Operation switch.
	 *
	 * @param resultAsAnInt the result as an int
	 */
	private void operationSwitch(int resultAsAnInt) {
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

	/**
	 * Delete computer.
	 */
	private void deleteComputer() {
		service.deleteComputer();
	}

	/**
	 * Update computer.
	 */
	private void updateComputer() {
		service.updateComputer();
	}

	/**
	 * Creates the computer.
	 */
	private void createComputer() {
		service.createComputer();
	}

	/**
	 * Show computer details.
	 */
	private void showComputerDetails() {
		service.showComputerDetails();
	}

	/**
	 * List companies.
	 */
	private void listCompanies() {
		service.listCompanies();
	}

	/**
	 * List computers.
	 */
	private void listComputers() {
		service.listComputers();
	}
}
