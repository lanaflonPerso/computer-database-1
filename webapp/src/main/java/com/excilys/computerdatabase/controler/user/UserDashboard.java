/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.page.creator.user.UserDashboardPageCreator;
import com.excilys.computerdatabase.page.model.UserDashboardPage;
import com.excilys.computerdatabase.service.services.ComputerService;

/**
 * The Class UserDashboard.
 */

@Controller
public class UserDashboard extends AbstractController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/** The computer service. */
	@Autowired
	private ComputerService computerService;
	
	/** The page creator. */
	@Autowired
	private UserDashboardPageCreator pageCreator;

	/**
	 * Gets the dashboard.
	 *
	 * @param modelMap the model map
	 * @return the dashboard
	 */
	@RequestMapping(value = USER + VIEW + DASHBOARD, method = RequestMethod.GET)
	public String getDashboard(ModelMap modelMap) {
		
		log.info("Servlet : [GET] user: dashboard ");
			
		UserDashboardPage newPage = pageCreator.getPageFromGetRequest();
		modelMap.addAttribute("page", newPage);

		return USER + VIEW + DASHBOARD;
	}
}
