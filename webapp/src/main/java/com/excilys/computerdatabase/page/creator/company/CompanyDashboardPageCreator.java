package com.excilys.computerdatabase.page.creator.company;

import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.model.CompanyDashboardPage;
import com.excilys.computerdatabase.service.services.CompanyService;
import com.excilys.computerdatabase.sort.SortCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class CompanyDashboardPageCreator.
 */
@Service
public class CompanyDashboardPageCreator extends AbstractPageCreator {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    public CompanyDashboardPage getPageFromGetRequest() {
        return pageGet();
    }

    /**
     * Page get.
     *
     * @return the company dashboard page
     */
    private CompanyDashboardPage pageGet() {
        CompanyDashboardPage newPage = new CompanyDashboardPage();
        List<Company> list = companyService.list(new SortCriteria());
        newPage.setCompanyList(companyDtoMapper.mapListFromModel(list));
        return newPage;
    }
}
