/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.page.creator.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.model.DashboardPage;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.sort.SortColumn;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.sort.SortDirection;
import com.excilys.computerdatabase.util.DateFormat;

@Service
public class DashboardPageCreator extends AbstractPageCreator {
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDtoMapper computerDtoMapper;

	public DashboardPage getPageFromGetRequest(DashboardPage currentDashboardPage) {
		DashboardPage dashboardPage = pageGet(currentDashboardPage);
		pageConverter(dashboardPage, Locale.ENGLISH, LocaleContextHolder.getLocaleContext().getLocale());
		return dashboardPage;
	}

	private DashboardPage pageGet(DashboardPage currentDashboardPage) {
		DashboardPage dashboardPage = new DashboardPage();

		List<Computer> computers = null;
		List<ComputerDto> computerDtos = null;
		long numberOfComputer = 0;
		String search = currentDashboardPage.getSearch();
		long page = currentDashboardPage.getPage();
		if (page == 0) {
			page = 1;
		}
		String sortColumn = currentDashboardPage.getSortColumn();
		String sortDirection = currentDashboardPage.getSortDirection();
		
		long size = currentDashboardPage.getSize();
		if (size == 0) {
			size = 10;
		}
		
		SortCriteria sortCriteria = new SortCriteria(
				SortColumn.build(sortColumn),
				SortDirection.build(sortDirection));
		
		if (search != null && !"".equals(search.trim())) {
			computers = computerService.getNameContains(search, new Long(
					(page - 1) * size), new Long(page * size), sortCriteria);
			numberOfComputer = computerService.getNameContainsElement(search);
		} else {
			computers = computerService.list(new Long((page - 1) * size),
					new Long(page * size), sortCriteria);
			numberOfComputer = computerService.getNumberOfElement();
		}

		computerDtos = computerDtoMapper.mapListFromModel(computers);
		dashboardPage.setNumberOfComputer(String.valueOf(numberOfComputer));
		dashboardPage.setPage(page);
		dashboardPage.setSearch(search);
		dashboardPage.setPageMax((numberOfComputer - 1 )/ size + 1);
		dashboardPage.setSortColumn(sortCriteria.getSortColumn().toPrint());
		dashboardPage.setSortDirection(sortCriteria.getSortDirection()
				.toPrint());
		dashboardPage.setComputers(computerDtos);
		dashboardPage.setSize(size);

		return dashboardPage;
	}

	private void listconverter(List<ComputerDto> list,
			DateFormat dateFormatFrom, DateFormat dateFormatTo) {
		list.stream().forEach(e->convertDto(e, dateFormatFrom, dateFormatTo));
	}

	public void pageConverter(DashboardPage page, Locale LocaleFrom,
			Locale LocaleTo) {
		DateFormat dateFormatFrom = DateFormat.ENGLISH;
		DateFormat dateFormatTo = DateFormat.ENGLISH;
		if (LocaleFrom.equals(Locale.FRENCH)) {
			dateFormatFrom = DateFormat.FRENCH;
		}
		if (LocaleTo.equals(Locale.FRENCH)) {
			dateFormatTo = DateFormat.FRENCH;
		}
		listconverter(page.getComputers(), dateFormatFrom, dateFormatTo);
	}

}
