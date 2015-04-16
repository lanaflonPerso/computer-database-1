/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.page.creator.impl.DashboardPageCreator;
import com.excilys.computerdatabase.page.model.DashboardPage;
import com.excilys.computerdatabase.service.ComputerService;

/**
 * Servlet implementation class Dashboard
 */

@Controller
@RequestMapping("/dashboard")
public class Dashboard extends AbstractController {
	final static Logger log = LoggerFactory.getLogger(Dashboard.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private DashboardPageCreator dashboardPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	public String getDashboard(DashboardPage dashboardPage, ModelMap modelMap) {
		
		log.info("Servlet : [GET] dashboard");
		
		DashboardPage page = dashboardPageCreator.getPageFromGetRequest(dashboardPage);
		modelMap.addAttribute("page", page);

		return DASHBOARD;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String deleteComputer(@RequestParam("selection") String selection, ModelMap modelMap) {
		
		log.info("Servlet : [POST] dashboard");

		getListLong(selection).stream().forEach(e -> (computerService.delete(e)));

		return REDIRECT + DASHBOARD;
	}

	private List<Long> getListLong(String selection) {
		List<Long> list = new ArrayList<Long>();
		if("".equals(selection)){
			return list;
		}
		for (String s : selection.split(",")) {
			list.add(Long.valueOf(s));
		}
		return list;
	}
}
