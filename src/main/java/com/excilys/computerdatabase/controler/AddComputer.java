/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.page.creator.impl.AddPageCreator;
import com.excilys.computerdatabase.dto.page.model.ComputerPage;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputer implements ControlerReference {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private AddPageCreator addPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap modelMap) {
		
		log.info("Servlet : [GET] addComputer");
		
		ComputerPage page = addPageCreator.getPageFromGetRequest();		
		modelMap.addAttribute("page", page);
		
		return ADD_COMPUTER;

	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@ModelAttribute("computerDto") ComputerDto computerDto, ModelMap modelMap) {
		
		log.info("Servlet : [POST] addComputer");
		
		ComputerPage page = addPageCreator.getPageFromPostRequest(computerDto);		
		modelMap.addAttribute("page", page);

		if (page.getCorrectField().areAllFieldsOk()) {
			Computer computer = computerMapper.mapDtoToModel(page.getComputerDto());
			computerService.create(computer);
			log.info("Computer added : " + computer);
			return REDIRECT + DASHBOARD;
		} else {
			log.info("Wrong input");
			return ADD_COMPUTER;
		}
	}

}