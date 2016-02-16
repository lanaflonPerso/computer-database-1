package com.excilys.computerdatabase.controler.company;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.page.creator.company.CompanyDashboardPageCreator;
import com.excilys.computerdatabase.page.model.CompanyDashboardPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Vincent Galloy
 *         The Class CompanyDashboard.
 */
@Controller
public class CompanyDashboard extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDashboard.class);
    @Autowired
    private CompanyDashboardPageCreator pageCreator;

    /**
     * Gets the dashboard.
     *
     * @param modelMap the model map
     * @return the dashboard
     */
    @RequestMapping(value = COMPANY + VIEW + DASHBOARD, method = RequestMethod.GET)
    public String getDashboard(ModelMap modelMap) {
        LOGGER.info("Servlet : [GET] company dashboard ");

        CompanyDashboardPage newPage = pageCreator.getPageFromGetRequest();
        modelMap.addAttribute("page", newPage);

        return COMPANY + VIEW + DASHBOARD;
    }
}
