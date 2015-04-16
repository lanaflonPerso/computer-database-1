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

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.page.creator.impl.AddPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;

@Controller
@RequestMapping("/addComputer")
public class AddComputer extends AbstractController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AddPageCreator addPageCreator;
	
	@RequestMapping(method = RequestMethod.GET)
	protected String getAddComputerPage(Model model) {
		
		log.info("Servlet : [GET] addComputer");
		
		ComputerPage page = addPageCreator.getPageFromGetRequest();		
		model.addAttribute("page", page);
		
		return ADD_COMPUTER;
		
	}

}