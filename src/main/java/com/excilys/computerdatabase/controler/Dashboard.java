/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler;

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

import com.excilys.computerdatabase.dto.page.creator.impl.DashboardPageCreator;
import com.excilys.computerdatabase.dto.page.model.DashboardPage;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.ComputerService;

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
	private ComputerMapper computerMapper;
	@Autowired
	private DashboardPageCreator dashboardPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(DashboardPage dashboardPage, ModelMap modelMap) {
		
		log.info("Servlet : [GET] dashboard");

		DashboardPage page = dashboardPageCreator.getPageFromGetRequest(dashboardPage);
		modelMap.addAttribute("page", page);

		return "dashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@RequestParam("selection") String selection, ModelMap modelMap) {
		
		log.info("Servlet : [POST] dashboard");

		getListLong(selection).stream().forEach(e -> (computerService.delete(e)));

		return "redirect:/dashboard";
	}

	private List<Long> getListLong(String selection) {
		List<Long> list = new ArrayList<Long>();
		for (String s : selection.split(",")) {
			list.add(Long.valueOf(s));
		}
		return list;
	}
}
