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

/**
 * @author Vincent Galloy
 * The Class ComputerDashboard.
 */
@Controller
public class ComputerDashboard extends AbstractController {
	public static final Logger LOGGER = LoggerFactory.getLogger(ComputerDashboard.class);
	@Autowired
	private ComputerDashboardPageCreator dashboardPageCreator;

	/**
	 * Gets the dashboard.
	 *
	 * @param dashboardPage the dashboard page
	 * @param modelMap the model map
	 * @return the dashboard
	 */
	@RequestMapping(value = COMPUTER + VIEW + DASHBOARD, method = RequestMethod.GET)
	public String getDashboard(ComputerDashboardPage dashboardPage, ModelMap modelMap) {
		LOGGER.info("Servlet : [GET] dashboard {}", dashboardPage);

		ComputerDashboardPage page = dashboardPageCreator.getPageFromGetRequest(dashboardPage);
		modelMap.addAttribute("page", page);

		return COMPUTER + VIEW + DASHBOARD;
	}
}
