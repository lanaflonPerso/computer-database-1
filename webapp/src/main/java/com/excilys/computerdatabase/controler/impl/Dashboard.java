/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		log.info("Servlet : [GET] dashboard {}", dashboardPage);
			
		DashboardPage page = dashboardPageCreator.getPageFromGetRequest(dashboardPage);
		modelMap.addAttribute("page", page);

		return DASHBOARD;
	}
}
