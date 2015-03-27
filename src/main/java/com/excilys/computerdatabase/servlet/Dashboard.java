/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.computerdatabase.dto.page.DashboardPage;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.util.ServletUtil;

/**
 * Servlet implementation class Dashboard
 */
@Controller
@WebServlet("/dashboard")
public class Dashboard extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	final static Logger log = LoggerFactory.getLogger(Dashboard.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ServletUtil servletUtil;
	@Autowired
	private ComputerMapper computerMapper;

	public Dashboard() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		log.info("Servlet : [GET] dashboard");

		HttpSession session = request.getSession();
		DashboardPage page = servletUtil.getDashboardPageGet(request);
		session.setAttribute("page", page);

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

	private List<Long> getListLong(HttpServletRequest request) {
		List<Long> list = new ArrayList<Long>();
		String st = request.getParameter("selection");
		for (String s : st.split(",")) {
			list.add(Long.valueOf(s));
		}
		return list;
	}
}
