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

import com.excilys.computerDataBase.dao.sort.SortCriteria;
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
/**
 * Servlet implementation class addComputer
 */
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
		List<Company> companies = CompanyService.INSTANCE.list(new SortCriteria());
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = CompanyMapper.mapListModelToDto(companies);
		session.setAttribute("companies", companyDtos);

		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		log.info("Servlet : [POST] addComputer");
		
		try {
			ComputerDto computerDto = ServletUtil.getComputerDto(request);
			Computer computer = ComputerMapper.mapDtoToModel(computerDto);
			ComputerService.INSTANCE.create(computer);
			
		} catch (ParsingException e) {
			log.warn("One of the field is not correct : computer can not be added.");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception :  " + e.getMessage());
		}
		
		response.sendRedirect("dashboard");
	}
}
