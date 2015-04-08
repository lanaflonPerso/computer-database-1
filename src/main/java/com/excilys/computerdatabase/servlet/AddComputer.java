/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.servlet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.dto.page.ComputerPage;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.util.ServletUtil;

@Controller
@RequestMapping("/addComputer")
public class AddComputer {
	final static Logger log = LoggerFactory.getLogger(AddComputer.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ServletUtil servletUtil;
	@Autowired
	private ComputerMapper computerMapper;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap modelMap) {
		
		log.info("Servlet : [GET] addComputer");
		
		ComputerPage page = servletUtil.getAddComputerPageGet();		
		modelMap.addAttribute("page", page);
		return "addComputer";

	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(HttpServletRequest request, ModelMap modelMap) {

		log.info("Servlet : [POST] addComputer");
		ComputerPage page = servletUtil.getAddComputerPagePost(request);		
		modelMap.addAttribute("page", page);

		if (page.getCorrectField().areAllFieldsOk()) {
			Computer computer = computerMapper.mapDtoToModel(page
					.getComputerDto());
			computerService.create(computer);
			log.info("Computer added : " + computer);
			return "redirect:/dashboard";
		} else {
			log.info("Wrong input");
			return "addComputer";
		}
		
	}

}