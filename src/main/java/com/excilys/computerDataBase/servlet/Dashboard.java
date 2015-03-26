/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.mapper.ComputerMapper;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.ComputerServiceInterface;
import com.excilys.computerDataBase.service.impl.ComputerService;
import com.excilys.computerDataBase.sort.SortColumn;
import com.excilys.computerDataBase.sort.SortCriteria;
import com.excilys.computerDataBase.sort.SortDirection;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = LoggerFactory.getLogger(Dashboard.class);
	
	ComputerServiceInterface computerService = ComputerService.INSTANCE;

	public Dashboard() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [GET] dashboard");

		HttpSession session = request.getSession();

		int page = getPage(request);
		int size = getSize(request);
		SortCriteria sortCriteria = getSortCriteria(request);

		List<Computer> computers = null;
		List<ComputerDto> computerDtos = null;
		Long numberOfComputer = null;

		String search = getSearch(request);
		if (search != null && !"".equals(search.trim())) {
			computers = computerService.getNameContains(search, new Long(
					(page - 1) * size), new Long(page * size), sortCriteria);
			numberOfComputer = computerService.getNameContainsElement(search);
		} else {
			computers = computerService.list(new Long((page - 1) * size),
					new Long(page * size), sortCriteria);
			numberOfComputer = computerService.getNumberOfElement();
		}
		computerDtos = ComputerMapper.mapListModelToDto(computers);
		session.setAttribute("numberOfComputer", numberOfComputer);

		session.setAttribute("page", page);
		session.setAttribute("size", size);
		session.setAttribute("search", search);
		session.setAttribute("sortColumn", sortCriteria.getSortColumn()
				.toPrint());
		session.setAttribute("sortDirection", sortCriteria.getSortDirection()
				.toPrint());
		session.setAttribute("computers", computerDtos);
		session.setAttribute("pageMax", (numberOfComputer - 1) / size + 1);
		session.setAttribute("numberOfComputer", numberOfComputer);

		request.getRequestDispatcher("WEB-INF/views/dashboard.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [POST] dashboard");

		List<Long> list = getListLong(request);

		for (Long l : list) {
			computerService.delete(l);
		}

		response.sendRedirect("dashboard");
	}

	private int getPage(HttpServletRequest request) {
		try {
			return Integer.valueOf(request.getParameter("page"));
		} catch (Exception e) {
			return 1;
		}
	}

	private int getSize(HttpServletRequest request) {
		try {
			return Integer.valueOf(request.getParameter("size"));
		} catch (Exception e) {
			return 10;
		}
	}

	private String getSearch(HttpServletRequest request) {
		try {
			String search = request.getParameter("search");
			if (search != null) {
				search = search.trim();
			}
			if ("".equals(search)) {
				return "";
			}
			return search;
		} catch (Exception e) {
			return "";
		}
	}

	private List<Long> getListLong(HttpServletRequest request) {
		List<Long> list = new ArrayList<Long>();

		String st = request.getParameter("selection");
		for (String s : st.split(",")) {
			list.add(Long.valueOf(s));
		}

		return list;
	}

	private SortCriteria getSortCriteria(HttpServletRequest request) {
		String string = request.getParameter("sortColumn");
		if (string != null) {
			string = string.trim();
		}
		SortColumn sortColumn = SortColumn.build(string);

		string = request.getParameter("sortDirection");
		if (string != null) {
			string = string.trim();
		}
		SortDirection sortDirection = SortDirection.build(string);

		return new SortCriteria(sortColumn, sortDirection);
	}
}
