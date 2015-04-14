/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.sort;

public class SortCriteria {
	
	private SortColumn sortColumn;
	private SortDirection sortDirection;	
	
	public SortCriteria() {
		super();
		this.sortColumn = SortColumn.ID;
		this.sortDirection = SortDirection.ASC;
	}

	public SortColumn getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(SortColumn sortColumn) {
		this.sortColumn = sortColumn;
	}

	public SortDirection getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	public SortCriteria(SortColumn sortColumn, SortDirection sortDirection) {
		super();
		this.sortColumn = sortColumn;
		this.sortDirection = sortDirection;
	}

	public String toString() {
		return sortColumn.toString() + " " + sortDirection.toString();
	}
	
}
