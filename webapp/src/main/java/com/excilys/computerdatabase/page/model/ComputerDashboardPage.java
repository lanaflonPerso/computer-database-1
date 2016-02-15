/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.page.model;

import java.util.List;

import com.excilys.computerdatabase.dto.model.ComputerDto;

/**
 * The Class ComputerDashboardPage.
 */
public class ComputerDashboardPage {
	
	/** The page. */
	private long page;
	
	/** The size. */
	private long size;
	
	/** The search. */
	private String search;
	
	/** The sort column. */
	private String sortColumn;
	
	/** The sort direction. */
	private String sortDirection;
	
	/** The computers. */
	private List<ComputerDto> computers;
	
	/** The page max. */
	private long pageMax;
	
	/** The number of computer. */
	private String numberOfComputer;

	/**
	 * Gets the page.
	 *
	 * @return the page
	 */
	public long getPage() {
		return page;
	}

	/**
	 * Sets the page.
	 *
	 * @param page the new page
	 */
	public void setPage(long page) {
		this.page = page;
	}
	

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * Gets the search.
	 *
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * Sets the search.
	 *
	 * @param search the new search
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	/**
	 * Gets the sort column.
	 *
	 * @return the sort column
	 */
	public String getSortColumn() {
		return sortColumn;
	}

	/**
	 * Sets the sort column.
	 *
	 * @param sortColumn the new sort column
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	/**
	 * Gets the sort direction.
	 *
	 * @return the sort direction
	 */
	public String getSortDirection() {
		return sortDirection;
	}

	/**
	 * Sets the sort direction.
	 *
	 * @param sortDirection the new sort direction
	 */
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	/**
	 * Gets the computers.
	 *
	 * @return the computers
	 */
	public List<ComputerDto> getComputers() {
		return computers;
	}

	/**
	 * Sets the computers.
	 *
	 * @param computers the new computers
	 */
	public void setComputers(List<ComputerDto> computers) {
		this.computers = computers;
	}

	/**
	 * Gets the page max.
	 *
	 * @return the page max
	 */
	public long getPageMax() {
		return pageMax;
	}

	/**
	 * Sets the page max.
	 *
	 * @param pageMax the new page max
	 */
	public void setPageMax(long pageMax) {
		this.pageMax = pageMax;
	}

	/**
	 * Gets the number of computer.
	 *
	 * @return the number of computer
	 */
	public String getNumberOfComputer() {
		return numberOfComputer;
	}

	/**
	 * Sets the number of computer.
	 *
	 * @param numberOfComputer the new number of computer
	 */
	public void setNumberOfComputer(String numberOfComputer) {
		this.numberOfComputer = numberOfComputer;
	}

	@Override
	public String toString() {
		return "DashboardPage [page=" + page + ", size=" + size + ", search="
				+ search + ", sortColumn=" + sortColumn + ", sortDirection="
				+ sortDirection + ", computers=" + computers + ", pageMax="
				+ pageMax + ", numberOfComputer=" + numberOfComputer + "]";
	}
}
