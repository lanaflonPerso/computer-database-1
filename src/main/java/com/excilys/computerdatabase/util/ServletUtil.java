/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.page.ComputerPage;
import com.excilys.computerdatabase.dto.page.DashboardPage;
import com.excilys.computerdatabase.mapper.CompanyMapper;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortColumn;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.sort.SortDirection;
import com.excilys.computerdatabase.validation.CorrectField;
import com.excilys.computerdatabase.validation.Validator;

@Service
public class ServletUtil {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerMapper computerMapper;
	@Autowired
	private CompanyMapper companyMapper;

	public CorrectField checkComputerDto(ComputerDto computerDto) {
		CorrectField incorrectField = new CorrectField(true, true, true, true);
		if (computerDto.getName() == null) {
			incorrectField.setComputerNameTrue(false);
		} else if (computerDto.getName().trim().isEmpty()) {
			incorrectField.setComputerNameTrue(false);
		}
		if (computerDto.getIntroduced() != null
				&& !Validator.isDateValid(computerDto.getIntroduced())) {
			incorrectField.setIntroducedDateTrue(false);
		}
		if (computerDto.getDiscontinued() != null
				&& !Validator.isDateValid(computerDto.getDiscontinued())) {
			incorrectField.setDiscontinuedDateTrue(false);
		}
		return incorrectField;
	}

	public ComputerPage getAddComputerPage(HttpServletRequest request) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = companyMapper
				.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(getComputerDto(request));
		page.setCorrectField(checkComputerDto(page.getComputerDto()));
		return page;
	}

	public ComputerPage getEditComputerPageGet(HttpServletRequest request) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = companyMapper
				.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(getComputerDto(request));
		Computer computer = computerService.getById(Long.valueOf(page
				.getComputerDto().getId()));
		page.setComputerDto(computerMapper.mapModelToDto(computer));
		page.setCorrectField(checkComputerDto(page.getComputerDto()));
		return page;
	}

	public ComputerPage getEditComputerPagePost(HttpServletRequest request) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = companyService.list(new SortCriteria());
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = companyMapper
				.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(getComputerDto(request));
		page.setCorrectField(checkComputerDto(page.getComputerDto()));
		Computer c = computerService.getById(Long.valueOf(page.getComputerDto()
				.getId()));
		if (c.getCompany() != null) {
			page.getComputerDto().setCompanyName(c.getCompany().getName());
		}
		return page;
	}

	public DashboardPage getDashboardPageGet(HttpServletRequest request) {
		DashboardPage dashboardPage = new DashboardPage();
		String pageString = getStringFromRequest(request, "page");
		long page = 1;
		if (pageString != null && pageString.trim() != "") {
			page = Long.parseLong(pageString);
		}
		String sizeString = getStringFromRequest(request, "size");
		long size = 10;
		if (sizeString != null && sizeString.trim() != "") {
			size = Long.parseLong(sizeString);
		}
		String search = getStringFromRequest(request, "search");
		SortCriteria sortCriteria = getSortCriteria(request);

		List<Computer> computers = null;
		List<ComputerDto> computerDtos = null;
		long numberOfComputer = 0;

		if (search != null && !"".equals(search.trim())) {
			computers = computerService.getNameContains(search, new Long(
					(page - 1) * size), new Long(page * size), sortCriteria);
			numberOfComputer = computerService.getNameContainsElement(search);
		} else {
			computers = computerService.list(new Long((page - 1) * size),
					new Long(page * size), sortCriteria);
			numberOfComputer = computerService.getNumberOfElement();
		}

		computerDtos = computerMapper.mapListModelToDto(computers);

		
		dashboardPage.setNumberOfComputer(String.valueOf(numberOfComputer));
		dashboardPage.setPage(page);
		dashboardPage.setSearch(search);
		dashboardPage.setPageMax(numberOfComputer / size);
		dashboardPage.setSortColumn(sortCriteria.getSortColumn().toPrint());
		dashboardPage.setSortDirection(sortCriteria.getSortDirection()
				.toPrint());
		dashboardPage.setComputers(computerDtos);
		dashboardPage.setSize(String.valueOf(size));

		return dashboardPage;
	}

	private String getStringFromRequest(HttpServletRequest request, String name) {
		String string = (String) request.getParameter(name);
		if (string == null) {
			log.warn("Can find parameter : " + name);
			return null;
		} else if (string.trim().isEmpty()) {
			return null;
		}

		string = string.trim();
		return string;
	}

	private ComputerDto getComputerDto(HttpServletRequest request) {
		String id = getStringFromRequest(request, "computerId");
		if ("".equals(id)) {
			id = null;
		}
		String name = getStringFromRequest(request, "computerName");
		String introduced = getStringFromRequest(request, "introduced");
		String discontinued = getStringFromRequest(request, "discontinued");
		String companyId = getStringFromRequest(request, "companyId");
		if ("0".equals(companyId)) {
			companyId = null;
		}
		String companyName = getStringFromRequest(request, "companyName");
		return new ComputerDto(id, name, introduced, discontinued, companyId,
				companyName);
	}

	private SortCriteria getSortCriteria(HttpServletRequest request) {
		String string = request.getParameter("sortColumn");
		if (string != null) {
			string = string.trim();
		}
		SortColumn sortColumn = SortColumn.build(string);

		string = request.getParameter("sortDirection");
		if (string != null) {
			string = string.trim();
		}
		SortDirection sortDirection = SortDirection.build(string);

		return new SortCriteria(sortColumn, sortDirection);
	}

}
