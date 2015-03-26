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

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = LoggerFactory.getLogger(EditComputer.class);

	public EditComputer() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [GET] editComputer");

		HttpSession session = request.getSession();
		ComputerPage page = ServletUtil.getEditComputerPageGet(request);
		session.setAttribute("page", page);
		request.getRequestDispatcher("WEB-INF/views/editComputer.jsp").forward(
				request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [POST] editComputer");

		HttpSession session = request.getSession();
		ComputerPage page = ServletUtil.getEditComputerPagePost(request);
		session.setAttribute("page", page);

		if (page.getCorrectField().areAllFieldsOk()) {
			Computer computer = ComputerMapper.mapDtoToModel(page
					.getComputerDto());
			ComputerService.INSTANCE.update(computer);
			response.sendRedirect("dashboard");
		} else {
			log.info("Wrong input");
			request.getRequestDispatcher("WEB-INF/views/editComputer.jsp")
					.forward(request, response);
		}
	}

	
	
}