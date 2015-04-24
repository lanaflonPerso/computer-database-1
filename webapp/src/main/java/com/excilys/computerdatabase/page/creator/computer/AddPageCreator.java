/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.page.creator.computer;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortCriteria;

@Service
public class AddPageCreator extends AbstractPageCreator {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private CompanyDtoMapper companyDtoMapper;
	
	public ComputerPage getPageFromGetRequest() {		
		ComputerPage page = pageGet();
		pageConverter(page, Locale.ENGLISH ,LocaleContextHolder.getLocaleContext().getLocale());
		return page;
	}
	
	public ComputerPage getPageFromPostRequest(ComputerDto computerDto) {
		mapComputerDto(computerDto);
		ComputerPage page = pagePost(computerDto);
		return page;
	}
	
	private ComputerPage pageGet() {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		List<CompanyDto> companyDtos = companyDtoMapper.mapListFromModel(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(new ComputerDto());
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
