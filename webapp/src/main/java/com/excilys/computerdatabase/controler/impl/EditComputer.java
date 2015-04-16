/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.page.creator.impl.EditPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
@Controller
@RequestMapping("/editComputer")
public class EditComputer extends AbstractController {
	final static Logger log = LoggerFactory.getLogger(EditComputer.class);
	
	@Autowired
	private EditPageCreator editPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	protected String getEditComputerPage(@RequestParam("computerId") Long id, Model model) {

		log.info("Servlet : [GET] editComputer");

		model.addAttribute("computerDto", new ComputerDto()); 
		ComputerPage page = editPageCreator.getPageFromGetRequest(id);
		model.addAttribute("page", page);
		
		return EDIT_COMPUTER;

	}

}