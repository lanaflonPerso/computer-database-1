/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.dto.CompanyDto;
import com.excilys.computerDataBase.dto.ComputerDto;
import com.excilys.computerDataBase.dto.page.ComputerPage;
import com.excilys.computerDataBase.mapper.CompanyMapper;
import com.excilys.computerDataBase.mapper.ComputerMapper;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.service.impl.CompanyService;
import com.excilys.computerDataBase.service.impl.ComputerService;
import com.excilys.computerDataBase.sort.SortCriteria;
import com.excilys.computerDataBase.validation.CorrectField;
import com.excilys.computerDataBase.validation.Validator;

public final class ServletUtil {

	final static Logger log = LoggerFactory.getLogger(ServletUtil.class);

	private static String getStringFromRequest(HttpServletRequest request,
			String name) {
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

	public static ComputerDto getComputerDto(HttpServletRequest request) {
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

	public static CorrectField checkComputerDto(ComputerDto computerDto) {
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

	public static ComputerPage getAddComputerPage(HttpServletRequest request) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = CompanyService.INSTANCE
				.list(new SortCriteria());
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = CompanyMapper
				.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(ServletUtil.getComputerDto(request));
		page.setCorrectField(ServletUtil.checkComputerDto(page.getComputerDto()));
		return page;
	}

	public static ComputerPage getEditComputerPageGet(HttpServletRequest request) {
		ComputerPage page = new ComputerPage();
		List<Company> companies = CompanyService.INSTANCE
				.list(new SortCriteria());
		companies.add(0, new Company(null, "--"));
		List<CompanyDto> companyDtos = CompanyMapper
				.mapListModelToDto(companies);
		page.setCompanies(companyDtos);
		page.setComputerDto(ServletUtil.getComputerDto(request));
		Computer computer = ComputerDao.INSTANCE.getById(Long.valueOf(page
				.getComputerDto().getId()));
		page.setComputerDto(ComputerMapper.mapModelToDto(computer));
		page.setCorrectField(ServletUtil.checkComputerDto(page.getComputerDto()));
		return page;
	}

	public static ComputerPage getEditComputerPagePost(
			HttpServletRequest request) {
		ComputerPage page = getAddComputerPage(request);
		Computer c = ComputerService.INSTANCE.getById(Long.valueOf(page
				.getComputerDto().getId()));
		if (c.getCompany() != null) {
			page.getComputerDto().setCompanyName(c.getCompany().getName());
		}
		return page;
	}
}
