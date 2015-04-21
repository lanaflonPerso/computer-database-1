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
import com.excilys.computerdatabase.page.creator.impl.EditPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.session.EditComputerSession;
@Controller
@RequestMapping("/editComputer")
public class EditComputer extends AbstractController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EditComputerSession editComputerSession;
	@Autowired
	private EditPageCreator editPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	private String getEditComputerPage(@RequestParam(value="computerId", defaultValue="") Long id, Model model) {

		log.info("Servlet : [GET] editComputer with id : {}", id);
		
		ComputerPage page = null;
		if(id == null ) {
			page = editPageCreator.getPageFromGetRequest(Long.valueOf(editComputerSession.getComputerDto().getId()));
			page.setComputerDto(editComputerSession.getComputerDto());
			model.addAttribute("org.springframework.validation.BindingResult.editComputerDto", editComputerSession.getBindingResult());
		} else {
			page = editPageCreator.getPageFromGetRequest(id);
		}
		model.addAttribute("page", page);
		
		return EDIT_COMPUTER;

	}

}