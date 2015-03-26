/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDataBase.dto.page.ComputerPage;
import com.excilys.computerDataBase.mapper.ComputerMapper;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.impl.ComputerService;
import com.excilys.computerDataBase.util.ServletUtil;
import com.excilys.computerDataBase.validation.CorrectField;

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = LoggerFactory.getLogger(EditComputer.class);

	public AddComputer() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [GET] addComputer");

		HttpSession session = request.getSession();
		ComputerPage page = ServletUtil.getAddComputerPage(request);
		page.setCorrectField(new CorrectField());
		session.setAttribute("page", page);
		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [POST] addComputer");
		
		HttpSession session = request.getSession();
		ComputerPage page = ServletUtil.getAddComputerPage(request);
		session.setAttribute("page", page);

		if (page.getCorrectField().areAllFieldsOk()) {
			Computer computer = ComputerMapper.mapDtoToModel(page
					.getComputerDto());
			ComputerService.INSTANCE.create(computer);
			response.sendRedirect("dashboard");
		} else {
			log.info("Wrong input");
			request.getRequestDispatcher("WEB-INF/views/addComputer.jsp")
					.forward(request, response);
		}
	}

}