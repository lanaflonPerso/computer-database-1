/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.servlet;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.dto.page.DashboardPage;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.util.ServletUtil;

/**
 * Servlet implementation class Dashboard
 */

@Controller
@RequestMapping("/dashboard")
public class Dashboard {
	final static Logger log = LoggerFactory.getLogger(Dashboard.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ServletUtil servletUtil;
	@Autowired
	private ComputerMapper computerMapper;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(HttpServletRequest request, ModelMap modelMap) {

		log.info("Servlet : [GET] dashboard");
		
		DashboardPage page = servletUtil.getDashboardPageGet(request);
		modelMap.addAttribute("page", page);

		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, ModelMap modelMap) {

		log.info("Servlet : [POST] dashboard");

		List<Long> list = getListLong(request);

		for (Long l : list) {
			computerService.delete(l);
		}
		
		return "dashboard";
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
