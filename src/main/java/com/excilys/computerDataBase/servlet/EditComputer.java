package com.excilys.computerDataBase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDataBase.dto.CompanyDto;
import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.mapper.CompanyMapper;
import com.excilys.computerDataBase.mapper.ComputerMapper;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.impl.CompanyService;
import com.excilys.computerDataBase.service.impl.ComputerService;
import com.excilys.computerDataBase.util.ServletUtil;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = LoggerFactory.getLogger(EditComputer.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputer() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [GET] editComputer");
		
		Long computerId = new Long(1);
		try {
			String string = request.getParameter("computerId");
			computerId = Long.valueOf(string);
		} catch (Exception e) {
			response.sendRedirect("dashboard");
		}

		HttpSession session = request.getSession();
		List<Company> companies = CompanyService.INSTANCE.list();
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = CompanyMapper.mapListModelToDto(companies);
		
		Computer computer = ComputerService.INSTANCE.getById(computerId);
		ComputerDto computerDto = ComputerMapper.mapModelToDto(computer);

		session.setAttribute("companies", companyDtos);
		session.setAttribute("computer", computerDto);
		
		request.getRequestDispatcher("WEB-INF/views/editComputer.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		log.info("Servlet : [POST] editComputer");
		
		try {			
			ComputerDto computerDto = ServletUtil.getComputerDto(request);
			Computer computer = ComputerMapper.mapDtoToModel(computerDto);
			ComputerService.INSTANCE.update(computer);

		} catch (ParsingException e) {
			log.error("One of the field is not correct : computer can not be updated.");
		} catch (Exception e) {
			log.error("Exception : " + e.getMessage());
		}
		response.sendRedirect("dashboard");
	}
}
