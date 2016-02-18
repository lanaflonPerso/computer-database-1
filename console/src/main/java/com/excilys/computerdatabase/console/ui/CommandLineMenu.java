package com.excilys.computerdatabase.console.ui;

import com.excilys.computerdatabase.console.service.ConsoleService;
import com.excilys.computerdatabase.exception.ParsingException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @author Vincent Galloy
 *         The Class CommandLineMenu.
 */
@Component
public class CommandLineMenu {
    @Autowired
    private ConsoleService consoleService;
    private Scanner scanner = new Scanner(System.in);

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/application-context.xml");
        CommandLineMenu commandLineMenu = applicationContext.getBean(CommandLineMenu.class);
        commandLineMenu.displayMainMenu();
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
        System.out.println("7 - Delete a company");

        String result = scanner.nextLine();
        int resultAsAnInt = 0;
        try {
            resultAsAnInt = Integer.parseInt(result);
        } catch (NumberFormatException e) {
            System.out.println("You should enter a number");
            System.exit(1);
        }
        operationSwitch(resultAsAnInt);
        scanner.close();
    }

    /**
     * Operation switch.
     *
     * @param resultAsAnInt the result as an int
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
            case 7:
                deleteCompany();
                break;
            default:
                break;
        }
    }

    /**
     * List computer.
     */
    private void listComputer() {
        Paginator.print(consoleService.getAllComputer());
    }

    /**
     * List companies.
     */
    private void listCompanies() {
        Paginator.print(consoleService.getAllCompany());
    }

    /**
     * Show computer details.
     */
    private void showComputerDetails() {
        Long id = getLongFromCommandLine("Computer Id : ");
        Computer computer = consoleService.getComputerById(id);
        System.out.println(computer.toString());
    }

    /**
     * Creates the computer.
     */
    private void createComputer() {
        String name = getStringFromCommandLine("Computer name : ");
        LocalDateTime introduced = getLocalDateTimeFromCommandLine("Computer introduced : ");
        LocalDateTime discontinued = getLocalDateTimeFromCommandLine("Computer discontinued : ");
        Long companyId = getLongFromCommandLine("Computer company_id : ");
        Computer computer = new Computer(0L, name, introduced, discontinued, new Company(companyId, null));
        consoleService.createComputer(computer);
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
        Long companyId = getLongFromCommandLine("Computer (new) company_id : ");
        Computer computer = new Computer(id, name, introduced, discontinued, new Company(companyId, null));
        consoleService.updateComputer(computer);
        System.out.println("computer updated : " + computer.toString());
    }

    /**
     * Delete computer.
     */
    private void deleteComputer() {
        Long id = getLongFromCommandLine("Computer id : ");
        consoleService.deleteComputer(id);
    }

    /**
     * Delete company.
     */
    private void deleteCompany() {
        Long id = getLongFromCommandLine("Company id : ");
        consoleService.deleteCompany(id);
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
     * Prints the request and gets the string from command line.
     *
     * @param request the request
     * @return the string from command line
     */
    private String getStringFromCommandLine(String request) {
        System.out.println(request);
        return scanner.nextLine();
    }

    /**
     * Gets the local date time from command line.
     *
     * @param request the request
     * @return the local date time from command line
     */
    private LocalDateTime getLocalDateTimeFromCommandLine(String request) {
        String result = getStringFromCommandLine(request);
        if (Validator.isDateValid(result)) {
            return LocalDateTime.parse(result);
        } else {
            System.out.println(Validator.WRONG_DATE_FORMAT);
        }
        return null;
    }
}
