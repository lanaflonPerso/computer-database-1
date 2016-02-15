/**
 * @author Vincent Galloy
 */
package com.excilys.computerdatabase.sort;

/**
 * The Class SortCriteria.
 */
public class SortCriteria {
	
	/** The sort column. */
	private SortColumn sortColumn;
	
	/** The sort direction. */
	private SortDirection sortDirection;	
	
	/**
	 * Instantiates a new sort criteria.
	 */
	public SortCriteria() {
		super();
		this.sortColumn = SortColumn.COMPUTER_ID;
		this.sortDirection = SortDirection.ASC;
	}

	/**
	 * Gets the sort column.
	 *
	 * @return the sort column
	 */
	public SortColumn getSortColumn() {
		return sortColumn;
	}

	/**
	 * Sets the sort column.
	 *
	 * @param sortColumn the new sort column
	 */
	public void setSortColumn(SortColumn sortColumn) {
		this.sortColumn = sortColumn;
	}

	/**
	 * Gets the sort direction.
	 *
	 * @return the sort direction
	 */
	public SortDirection getSortDirection() {
		return sortDirection;
	}

	/**
	 * Sets the sort direction.
	 *
	 * @param sortDirection the new sort direction
	 */
	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}

	/**
	 * Instantiates a new sort criteria.
	 *
	 * @param sortColumn the sort column
	 * @param sortDirection the sort direction
	 */
	public SortCriteria(SortColumn sortColumn, SortDirection sortDirection) {
		super();
		this.sortColumn = sortColumn;
		this.sortDirection = sortDirection;
	}

	public String toString() {
		return sortColumn.toString() + " " + sortDirection.toString();
	}
	
}
