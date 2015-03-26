/**
 * @Author Vincent Galloy
 */
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
import com.excilys.computerDataBase.dto.page.AddComputerPage;
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
		AddComputerPage addComputerPage = getAddComputerPage(request);
		session.setAttribute("page", addComputerPage);

		request.getRequestDispatcher("WEB-INF/views/addComputer.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [POST] addComputer");

		
		HttpSession session = request.getSession();
		AddComputerPage addComputerPage = getAddComputerPage(request);
		session.setAttribute("page", addComputerPage);
		
		if (addComputerPage.getCorrectField().areAllFieldsOk()) {
			Computer computer = ComputerMapper.mapDtoToModel(addComputerPage.getComputerDto());
			ComputerService.INSTANCE.create(computer);
			response.sendRedirect("dashboard");
		} else {
			log.info("Wrong input");
			request.getRequestDispatcher("WEB-INF/views/addComputer.jsp")
					.forward(request, response);
		}
	}

	
	private AddComputerPage getAddComputerPage(HttpServletRequest request) {
		AddComputerPage addComputerPage = new AddComputerPage();
		List<Company> companies = CompanyService.INSTANCE
				.list(new SortCriteria());
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = CompanyMapper
				.mapListModelToDto(companies);
		addComputerPage.setCompanies(companyDtos);
		addComputerPage.setComputerDto(ServletUtil.getComputerDto(request));
		addComputerPage.setCorrectField( ServletUtil.checkComputerDto(addComputerPage.getComputerDto()));
		return addComputerPage;
	}
	
}
