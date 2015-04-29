package com.excilys.computerdatabase.controler.company;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;

@Controller
public class CompanyControler extends AbstractController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CompanyDtoMapper companyDtoMapper;

	@RequestMapping(value = COMPANY + CRUD + ADD, method = RequestMethod.POST)
	public String addCompany(@Valid @ModelAttribute("companyDto") CompanyDto companyDto, BindingResult bindingResult) {

		log.info("Servlet : [POST] company: add {}", companyDto);

		if (bindingResult.hasErrors()) {
			log.info("Wrong input");
		} else {
			Company company = companyDtoMapper.mapToModel(companyDto);
			companyService.create(company);
			log.info("Company added : " + company);
		}		
		return REDIRECT + COMPANY + VIEW + DASHBOARD;
	}

	@RequestMapping(value = COMPANY + CRUD + EDIT, method = RequestMethod.POST)
	public String editRule(@Valid @ModelAttribute("companyDto") CompanyDto companyDto, BindingResult bindingResult) {

		log.info("Servlet : [POST] company: edit {}", companyDto);

		if (bindingResult.hasErrors()) {
			log.info("Wrong input");
		} else {
			Company company = companyDtoMapper.mapToModel(companyDto);
			companyService.update(company);
		}
		return REDIRECT + COMPANY + VIEW + DASHBOARD;
	}
	
	@RequestMapping(value = COMPANY + CRUD + DELETE, method = RequestMethod.POST)
	public String deleteComputer(String selection) {

		log.info("Servlet : [POST] user-delete {}", selection);

		getList(selection).stream().forEach(companyService::delete);

		return REDIRECT + COMPANY + VIEW + DASHBOARD;
	}

	private List<Long> getList(String selection) {
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
