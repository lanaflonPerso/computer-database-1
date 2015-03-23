package com.excilys.computerDataBase.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.mapper.impl.DtoMapper;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.impl.ComputerService;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int page = getPage(request);
		session.setAttribute("page", page);
		int size = getSize(request);
		session.setAttribute("size", size);
		Long numberOfComputer = ComputerService.INSTANCE.getNumberOfElement();
		session.setAttribute("numberOfComputer", numberOfComputer);
		
		List<Computer> computers = ComputerService.INSTANCE.list(new Long((page - 1 )* size), new Long(page * size));
		List<ComputerDto> computerDtos = DtoMapper.INSTANCE.mapComputers(computers);
		session.setAttribute("computers", computerDtos);
				
		session.setAttribute("pageMax", ( numberOfComputer - 1) / size  + 1);
		
		request.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}

	private int getPage(HttpServletRequest request){
		int page = 1;
		try {
			page = Integer.valueOf(request.getParameter("page"));
		} catch (Exception e) {
			page = 1;
		}	
		return page;
	}
	
	private int getSize(HttpServletRequest request){
		try {
			return Integer.valueOf(request.getParameter("size"));
		} catch (Exception e) {
			return 10;
		}	
	}
}
