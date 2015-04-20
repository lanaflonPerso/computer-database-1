/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.page.creator.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;

@Service
public class EditPageCreator extends AbstractPageCreator {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDtoMapper computerDtoMapper;
	@Autowired
	private CompanyDtoMapper companyDtoMapper;

	public ComputerPage getPageFromGetRequest(Long id) {
		ComputerPage page = pageGet(id);
		pageConverter(page, Locale.ENGLISH, LocaleContextHolder.getLocaleContext().getLocale());
		return page;
	}
	
	public ComputerPage getPageFromPostRequest(ComputerDto computerDto) {
		mapComputerDto(computerDto);
		ComputerPage page = pagePost(computerDto);
		return page;
	}
	
	private ComputerPage pageGet(Long id) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		List<CompanyDto> companyDtos = companyDtoMapper.mapListFromModel(companies);
		page.setCompanies(companyDtos);
		Computer computer = computerService.getById(id);
		ComputerDto computerDto = computerDtoMapper.mapFromModel(computer);
		page.setComputerDto(computerDto);
		return page;
	}
	
	private ComputerPage pagePost(ComputerDto computerDto) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		List<CompanyDto> companyDtos = companyDtoMapper.mapListFromModel(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(computerDto);
		return page;
	}
	
}
