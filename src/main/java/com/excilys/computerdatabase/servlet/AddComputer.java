/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.computerdatabase.dto.page.ComputerPage;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.util.ServletUtil;
import com.excilys.computerdatabase.validation.CorrectField;

@Controller
@WebServlet("/addComputer")
public class AddComputer extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = LoggerFactory.getLogger(EditComputer.class);

	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private ServletUtil servletUtil;
	
	@Autowired
	private ComputerMapper computerMapper;
	
	public AddComputer() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [GET] addComputer");

		HttpSession session = request.getSession();
		ComputerPage page = servletUtil.getAddComputerPage(request);
		page.setCorrectField(new CorrectField());
		session.setAttribute("page", page);
		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [POST] addComputer");
		
		HttpSession session = request.getSession();
		ComputerPage page = servletUtil.getAddComputerPage(request);
		session.setAttribute("page", page);

		if (page.getCorrectField().areAllFieldsOk()) {
			Computer computer = computerMapper.mapDtoToModel(page
					.getComputerDto());
			computerService.create(computer);
			response.sendRedirect("dashboard");
		} else {
			log.info("Wrong input");
			request.getRequestDispatcher("WEB-INF/views/addComputer.jsp")
					.forward(request, response);
		}
	}

}