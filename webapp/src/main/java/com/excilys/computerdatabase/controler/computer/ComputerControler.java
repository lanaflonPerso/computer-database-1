package com.excilys.computerdatabase.controler.computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.creator.computer.AddPageCreator;
import com.excilys.computerdatabase.page.creator.computer.EditPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.session.AddComputerSession;
import com.excilys.computerdatabase.session.EditComputerSession;

@Controller
@SessionAttributes({ "computerDto" })
public class ComputerControler extends AbstractController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AddComputerSession addComputerSession;
	@Autowired
	private EditComputerSession editComputerSession;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDtoMapper computerDtoMapper;
	@Autowired
	private AddPageCreator addPageCreator;
	@Autowired
	private EditPageCreator editPageCreator;

	@RequestMapping(value = COMPUTER + CRUD + ADD, method = RequestMethod.POST)
	public String addComputer(@Valid @ModelAttribute("addComputerDto") ComputerDto computerDto, BindingResult bindingResult) {

		log.info("Servlet : [POST] computer-add {}", computerDto);

		ComputerPage page = addPageCreator.getPageFromPostRequest(computerDto);
		addComputerSession.setComputerDto(page.getComputerDto());
		addComputerSession.setBindingResult(bindingResult);

		if (bindingResult.hasErrors()) {
			log.info("Wrong input");
			return REDIRECT + COMPUTER + VIEW + ADD;
		} else {
			AbstractPageCreator.pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
			Computer computer = computerDtoMapper.mapToModel(page.getComputerDto());
			computerService.create(computer);
			log.info("Computer added : " + computer);
			return REDIRECT + COMPUTER + VIEW + DASHBOARD;
		}
	}

	@RequestMapping(value = COMPUTER + CRUD + EDIT, method = RequestMethod.POST)
	public String editComputer(@Valid @ModelAttribute("editComputerDto") ComputerDto computerDto, BindingResult bindingResult, Model model) {

		log.info("Servlet : [POST] computer-edit {}", computerDto);

		ComputerPage page = editPageCreator.getPageFromPostRequest(computerDto);
		editComputerSession.setComputerDto(page.getComputerDto());
		editComputerSession.setBindingResult(bindingResult);

		if (bindingResult.hasErrors()) {
			log.info("Wrong input");
			return REDIRECT + COMPUTER + VIEW + EDIT;
		} else {
			AbstractPageCreator.pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
			Computer computer = computerDtoMapper.mapToModel(page.getComputerDto());
			computerService.update(computer);
			log.info("Computer updated : " + computer);
			return REDIRECT + COMPUTER + VIEW + DASHBOARD;
		}
	}

	@RequestMapping(value = COMPUTER + CRUD + DELETE, method = RequestMethod.POST)
	public String deleteComputer(String selection) {
		
		log.info("Servlet : [POST] computer-delete {}", selection);

		getListLong(selection).stream().forEach(computerService::delete);

		return REDIRECT + COMPUTER + VIEW + DASHBOARD;
	}

	private List<Long> getListLong(String selection) {
		List<Long> list = new ArrayList<Long>();
		if ("".equals(selection)) {
			return list;
		}
		for (String s : selection.split(",")) {
			list.add(Long.valueOf(s));
		}
		return list;
	}

}
