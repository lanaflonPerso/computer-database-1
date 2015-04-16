/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.creator.impl.EditPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.service.ComputerService;
@Controller
@RequestMapping("/editComputer")
public class EditComputer implements ControlerReference {
	final static Logger log = LoggerFactory.getLogger(EditComputer.class);

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDtoMapper computerDtoMapper;
	@Autowired
	private EditPageCreator editPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@RequestParam("computerId") Long id, Model model) {

		log.info("Servlet : [GET] editComputer");

		model.addAttribute("computerDto", new ComputerDto()); 
		ComputerPage page = editPageCreator.getPageFromGetRequest(id);
		model.addAttribute("page", page);
		
		return EDIT_COMPUTER;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@Valid ComputerDto computerDto, BindingResult result, Model model) {
		
		log.info("Servlet : [POST] editComputer " + computerDto);
		
		ComputerPage page = editPageCreator.getPageFromPostRequest(computerDto);
		model.addAttribute("page", page);
		
		
		if (result.hasErrors()) {
			return EDIT_COMPUTER;
		} else {
			AbstractPageCreator.pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
			Computer computer = computerDtoMapper.mapToModel(page.getComputerDto());
			computerService.update(computer);
			log.info("Computer updated : " + computer);
			return REDIRECT + DASHBOARD;
		}
	}

}