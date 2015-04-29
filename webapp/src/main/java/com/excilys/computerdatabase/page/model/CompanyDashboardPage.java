package com.excilys.computerdatabase.page.model;

import java.util.List;

import com.excilys.computerdatabase.dto.model.CompanyDto;

public class CompanyDashboardPage {
	private List<CompanyDto> companyList;

	public CompanyDashboardPage() {
		super();
	}

	public List<CompanyDto> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<CompanyDto> companyList) {
		this.companyList = companyList;
	}

	@Override
	public String toString() {
		return "CompanyDashboardPage [companyList=" + companyList + "]";
	}
	
}
