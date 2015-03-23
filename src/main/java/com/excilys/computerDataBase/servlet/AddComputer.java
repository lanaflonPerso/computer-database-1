package com.excilys.computerDataBase.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.impl.CompanyService;
import com.excilys.computerDataBase.service.impl.ComputerService;
import com.excilys.computerDataBase.validation.Validator;

/**
 * Servlet implementation class addComputer
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Company> companies = CompanyService.INSTANCE.list();
		session.setAttribute("companies", companies);

		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Computer computer = null;
		try {
			String name = getName(request);
			LocalDateTime introduced = getIntroduced(request);
			LocalDateTime discontinued = getDiscontinued(request);
			Long companyId = getCompanyId(request);

			computer = new Computer(new Long(0), name, introduced,
					discontinued, new Company(companyId, null));
			
			ComputerService.INSTANCE.create(computer);
			
		} catch (ParsingException e) {
			System.out
					.println("One of the field is not correct : computer can not be added.");
		} catch (Exception e) {
			System.out.println("Exception :  " + e.getMessage());
		}
		
		response.sendRedirect("dashboard");
	}

	private Long getCompanyId(HttpServletRequest request) {
		String string = (String) request.getParameter("companyId");
		if (string == "" || string == null) {
			return null;
		}
		string = string.trim();
		try {
			Long companyId = new Long(string);
			if (companyId.equals(new Long(0))) {
				System.out.println("companyId must be not equal to 0.");
				throw new ParsingException();
			}
			return companyId;
		} catch (Exception e) {
			System.out.println(ParsingException.CAN_NOT_PARSE_INTO_LONG
					+ " : companyId");
			throw new ParsingException();
		}
	}

	private LocalDateTime getDiscontinued(HttpServletRequest request) {
		String string = (String) request.getParameter("discontinued");
		if (string == "" || string == null) {
			return null;
		}  
		string = string.trim();
		if (Validator.INSTANCE.validateDate(string)) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter
					.ofPattern("yyyy-MM-dd HH:mm:ss");
			return LocalDateTime.parse(string, dateTimeFormatter);
		} else {
			System.out.println(Validator.WRONG_DATE_FORMAT + " : discontinued");
			throw new ParsingException();
		}
	}

	private LocalDateTime getIntroduced(HttpServletRequest request) {
		String string = (String) request.getParameter("introduced");
		if (string == "" || string == null) {
			return null;
		}  
		string = string.trim();
		if (Validator.INSTANCE.validateDate(string)) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter
					.ofPattern("yyyy-MM-dd HH:mm:ss");
			return LocalDateTime.parse(string, dateTimeFormatter);
		} else {
			System.out.println(Validator.WRONG_DATE_FORMAT + " : introduced");
			throw new ParsingException();
		}
	}

	private String getName(HttpServletRequest request) {
		String string = (String) request.getParameter("computerName");
		string = string.trim();
		if (string == "" || string == null) {
			System.out.println("name is mandatory, can not be null");
			throw new ParsingException();
		} else {
			return string;
		}
	}
}
