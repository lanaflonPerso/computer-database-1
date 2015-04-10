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
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.page.creator.impl.EditPageCreator;
import com.excilys.computerdatabase.dto.page.model.ComputerPage;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;

@Controller
@RequestMapping("/editComputer")
public class EditComputer {
	final static Logger log = LoggerFactory.getLogger(EditComputer.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private EditPageCreator editPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@RequestParam("computerId") Long id, ModelMap modelMap) {

		log.info("Servlet : [GET] editComputer");

		ComputerPage page = editPageCreator.getPageFromGetRequest(id);
		modelMap.addAttribute("page", page);

		return "editComputer";

	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@ModelAttribute("computerDto") ComputerDto computerDto, ModelMap modelMap) {

		log.info("Servlet : [POST] editComputer " + computerDto);
		
		ComputerPage page = editPageCreator.getPageFromPostRequest(computerDto);
		modelMap.addAttribute("page", page);
		
		if (page.getCorrectField().areAllFieldsOk()) {
			Computer computer = computerMapper.mapDtoToModel(page.getComputerDto());
			computerService.update(computer);
			log.info("Computer updated : " + computer);
			return "redirect:/dashboard";
		} else {
			log.info("Wrong input");
			return "editComputer";
		}
	}

}