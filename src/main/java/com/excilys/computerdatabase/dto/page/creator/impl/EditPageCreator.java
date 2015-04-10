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
import com.excilys.computerdatabase.model.Computer;
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
	private ComputerMapper computerMapper;
	@Autowired
	private CompanyMapper companyMapper;

	public ComputerPage getPageFromGetRequest(Long id) {
		ComputerPage page = pageGet(id);
		pageValidation(page, Locale.ENGLISH);
		pageConverter(page, Locale.ENGLISH, LocaleContextHolder.getLocaleContext().getLocale());
		return page;
	}
	
	public ComputerPage getPageFromPostRequest(ComputerDto computerDto) {
		mapComputerDto(computerDto);
		ComputerPage page = pagePost(computerDto);
		pageValidation(page, LocaleContextHolder.getLocaleContext().getLocale());
		if(page.getCorrectField().areAllFieldsOk()) {
			pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
		}
		return page;
	}
	
	private ComputerPage pageGet(Long id) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		List<CompanyDto> companyDtos = companyMapper.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		Computer computer = computerService.getById(id);
		ComputerDto computerDto = computerMapper.mapModelToDto(computer);
		page.setComputerDto(computerDto);
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
