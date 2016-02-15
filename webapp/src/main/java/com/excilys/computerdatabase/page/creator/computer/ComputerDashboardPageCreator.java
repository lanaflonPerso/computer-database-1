/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.page.creator.computer;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.model.ComputerDashboardPage;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.sort.SortColumn;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.sort.SortDirection;
import com.excilys.computerdatabase.util.DateFormat;

/**
 * The Class ComputerDashboardPageCreator.
 */
@Service
public class ComputerDashboardPageCreator extends AbstractPageCreator {
	
	/** The computer service. */
	@Autowired
	private ComputerService computerService;
	
	/** The computer dto mapper. */
	@Autowired
	private ComputerDtoMapper computerDtoMapper;

	/**
	 * Gets the page from get request.
	 *
	 * @param currentDashboardPage the current dashboard page
	 * @return the page from get request
	 */
	public ComputerDashboardPage getPageFromGetRequest(ComputerDashboardPage currentDashboardPage) {
		ComputerDashboardPage dashboardPage = pageGet(currentDashboardPage);
		pageConverter(dashboardPage, Locale.ENGLISH, LocaleContextHolder.getLocaleContext().getLocale());
		return dashboardPage;
	}

	/**
	 * Page get.
	 *
	 * @param currentDashboardPage the current dashboard page
	 * @return the computer dashboard page
	 */
	private ComputerDashboardPage pageGet(ComputerDashboardPage currentDashboardPage) {
		ComputerDashboardPage dashboardPage = new ComputerDashboardPage();

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

	/**
	 * Listconverter.
	 *
	 * @param list the list
	 * @param dateFormatFrom the date format from
	 * @param dateFormatTo the date format to
	 */
	private void listconverter(List<ComputerDto> list,
			DateFormat dateFormatFrom, DateFormat dateFormatTo) {
		list.stream().forEach(e->convertDto(e, dateFormatFrom, dateFormatTo));
	}

	/**
	 * Page converter.
	 *
	 * @param page the page
	 * @param LocaleFrom the locale from
	 * @param LocaleTo the locale to
	 */
	public void pageConverter(ComputerDashboardPage page, Locale LocaleFrom,
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
