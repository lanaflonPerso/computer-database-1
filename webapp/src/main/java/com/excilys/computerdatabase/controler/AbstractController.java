package com.excilys.computerdatabase.controler;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.dto.model.UserDetailDto;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Vincent Galloy
 *         The Class AbstractController.
 */
public abstract class AbstractController {
    public static final String USER = "user/";
    public static final String GLOBAL = "global/";
    public static final String COMPANY = "company/";
    public static final String COMPUTER = "computer/";
    public static final String CRUD = "crud/";
    public static final String VIEW = "view/";
    public static final String EDIT = "edit";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String DASHBOARD = "dashboard";
    public static final String LOGIN = "customLogin";
    public static final String LOGOUT = "logout";
    public static final String HOME = "home";
    public static final String RESET_PASSWORD = "resetPassword";
    public static final String REDIRECT = "redirect:/";

    /**
     * Gets the adds the computer dto.
     *
     * @return the adds the computer dto
     */
    @ModelAttribute("addComputerDto")
    public ComputerDto getAddComputerDto() {
        return new ComputerDto();
    }

    /**
     * Gets the edits the computer dto.
     *
     * @return the edits the computer dto
     */
    @ModelAttribute("editComputerDto")
    public ComputerDto getEditComputerDto() {
        return new ComputerDto();
    }

    /**
     * Gets the user detail dto.
     *
     * @return the user detail dto
     */
    @ModelAttribute("userDetailDto")
    public UserDetailDto getUserDetailDto() {
        return new UserDetailDto();
    }

    /**
     * Gets the rule dto.
     *
     * @return the rule dto
     */
    @ModelAttribute("ruleDto")
    public RuleDto getRuleDto() {
        return new RuleDto();
    }

    /**
     * Gets the company dto.
     *
     * @return the company dto
     */
    @ModelAttribute("companyDto")
    public CompanyDto getCompanyDto() {
        return new CompanyDto();
    }
}
