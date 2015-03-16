package com.excilys.computerDataBase.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerDataBase.exception.ParsingException;

/**
 * Servlet implementation class addComputer
 */
@WebServlet("/addComputer")
public class addComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addComputer() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/addComputer.html").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = null;
		Timestamp introducedTimestamp = null;
		Timestamp discontinuedTimestamp = null;

		name = (String) request.getParameter("computerName");

		try {
			String introduced = (String) request.getParameter("introduced");
			Long introducedLong = Long.valueOf(introduced);
			introducedTimestamp = new Timestamp(introducedLong);

			System.out.println(introducedTimestamp.toString());
		} catch (Exception e) {
			System.out.println(ParsingException.CAN_NOT_PARSE_INTO_TIMESTAMP);
			nextStep(request, response);
			return;
		}

		try {
			String discontinued = (String) request.getParameter("discontinued");
			Long discontinuedLong = Long.valueOf(discontinued);
			discontinuedTimestamp = new Timestamp(discontinuedLong);
		} catch (Exception e) {
			System.out.println(ParsingException.CAN_NOT_PARSE_INTO_TIMESTAMP);
			nextStep(request, response);
			return;
		}

		System.out.println("Le computerName vaut : " + name);
		System.out.println("Le introducedTimestamp vaut : "
				+ introducedTimestamp.toString());
		System.out.println("Le discontinuedTimestamp vaut : "
				+ discontinuedTimestamp.toString());

		/* TODO */
		nextStep(request, response);
	}

	private void nextStep(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/dashboard.html").forward(request,
				response);
	}

}
