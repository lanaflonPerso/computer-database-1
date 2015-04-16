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

import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.creator.impl.AddPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.service.ComputerService;

@Controller
@RequestMapping("/addComputer")
public class AddComputer implements ControlerReference {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDtoMapper computerDtoMapper;
	@Autowired
	private AddPageCreator addPageCreator;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(Model model) {
		
		log.info("Servlet : [GET] addComputer");
		
		model.addAttribute("computerDto", new ComputerDto()); 
		ComputerPage page = addPageCreator.getPageFromGetRequest();		
		model.addAttribute("page", page);
		
		return ADD_COMPUTER;

	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@Valid ComputerDto computerDto, BindingResult result,Model model) {
		
		log.info("Servlet : [POST] addComputer");
		
		ComputerPage page = addPageCreator.getPageFromPostRequest(computerDto);		
		model.addAttribute("page", page);

		if (result.hasErrors()) {
			log.info("Wrong input");
			return ADD_COMPUTER;
		} else {
			AbstractPageCreator.pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
			Computer computer = computerDtoMapper.mapToModel(page.getComputerDto());
			computerService.create(computer);
			log.info("Computer added : " + computer);
			return REDIRECT + DASHBOARD;
		}
	}

}