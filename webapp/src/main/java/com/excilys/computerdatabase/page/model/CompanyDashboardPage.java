package com.excilys.computerdatabase.page.model;

import com.excilys.computerdatabase.dto.model.CompanyDto;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class CompanyDashboardPage.
 */
public class CompanyDashboardPage {
    private List<CompanyDto> companyList;

    /**
     * Instantiates a new company dashboard page.
     */
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
