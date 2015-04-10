/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.dto.page.creator.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.dto.page.model.ComputerPage;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.CorrectField;

@Service
public class AddPageCreator extends AbstractPageCreator {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private CompanyMapper companyMapper;
	
	public ComputerPage getPageFromGetRequest() {		
		ComputerPage page = pageGet();
		pageConverter(page, Locale.ENGLISH ,LocaleContextHolder.getLocaleContext().getLocale());
		return page;
	}
	
	public ComputerPage getPageFromPostRequest(ComputerDto computerDto) {
		mapComputerDto(computerDto);
		ComputerPage page = pagePost(computerDto);
		pageValidation(page,LocaleContextHolder.getLocaleContext().getLocale());
		if(page.getCorrectField().areAllFieldsOk()) {
			pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
		}
		return page;
	}
	
	private ComputerPage pageGet() {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		List<CompanyDto> companyDtos = companyMapper.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(new ComputerDto());
		page.setCorrectField(new CorrectField());
		return page;
	}
	
	private ComputerPage pagePost(ComputerDto computerDto) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		List<CompanyDto> companyDtos = companyMapper.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(computerDto);
		return page;
	}	
}
