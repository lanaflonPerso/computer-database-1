/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.computer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.page.creator.computer.ComputerDashboardPageCreator;
import com.excilys.computerdatabase.page.model.ComputerDashboardPage;
import com.excilys.computerdatabase.service.services.ComputerService;

@Controller
public class ComputerDashboard extends AbstractController {
	final static Logger log = LoggerFactory.getLogger(ComputerDashboard.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDashboardPageCreator dashboardPageCreator;

	@RequestMapping(value = COMPUTER + VIEW + DASHBOARD, method = RequestMethod.GET)
	public String getDashboard(ComputerDashboardPage dashboardPage, ModelMap modelMap) {

		log.info("Servlet : [GET] dashboard {}", dashboardPage);

		ComputerDashboardPage page = dashboardPageCreator.getPageFromGetRequest(dashboardPage);
		modelMap.addAttribute("page", page);

		return COMPUTER + VIEW + DASHBOARD;
	}
}
