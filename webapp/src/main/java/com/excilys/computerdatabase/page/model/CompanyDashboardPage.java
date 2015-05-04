/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.page.model;

import java.util.List;

import com.excilys.computerdatabase.dto.model.CompanyDto;

/**
 * The Class CompanyDashboardPage.
 */
public class CompanyDashboardPage {
	
	/** The company list. */
	private List<CompanyDto> companyList;

	/**
	 * Instantiates a new company dashboard page.
	 */
	public CompanyDashboardPage() {
		super();
	}

	/**
	 * Gets the company list.
	 *
	 * @return the company list
	 */
	public List<CompanyDto> getCompanyList() {
		return companyList;
	}

	/**
	 * Sets the company list.
	 *
	 * @param companyList the new company list
	 */
	public void setCompanyList(List<CompanyDto> companyList) {
		this.companyList = companyList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CompanyDashboardPage [companyList=" + companyList + "]";
	}
	
}
