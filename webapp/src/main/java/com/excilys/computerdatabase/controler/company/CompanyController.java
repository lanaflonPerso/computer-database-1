package com.excilys.computerdatabase.controler.company;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.dto.mapper.CompanyDtoMapper;
import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Vincent Galloy
 *         The Class CompanyController.
 */
@Controller
public class CompanyController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDtoMapper companyDtoMapper;

    /**
     * Adds the company.
     *
     * @param companyDto    the company dto
     * @param bindingResult the binding result
     * @return the string
     */
    @RequestMapping(value = COMPANY + CRUD + ADD, method = RequestMethod.POST)
    public String addCompany(@Valid @ModelAttribute("companyDto") CompanyDto companyDto, BindingResult bindingResult) {
        LOGGER.info("Servlet : [POST] company: add {}", companyDto);

        if (bindingResult.hasErrors() || companyDto.getName().trim().isEmpty()) {
            LOGGER.warn(WRONG_INPUT);
        } else {
            Company company = companyDtoMapper.mapToModel(companyDto);
            companyService.create(company);
            LOGGER.info("Company added : " + company);
        }
        return REDIRECT + COMPANY + VIEW + DASHBOARD;
    }

    /**
     * Edits the rule.
     *
     * @param companyDto    the company dto
     * @param bindingResult the binding result
     * @return the string
     */
    @RequestMapping(value = COMPANY + CRUD + EDIT, method = RequestMethod.POST)
    public String editRule(@Valid @ModelAttribute("companyDto") CompanyDto companyDto, BindingResult bindingResult) {
        LOGGER.info("Servlet : [POST] company: edit {}", companyDto);

        if (bindingResult.hasErrors() || companyDto.getName().trim().isEmpty()) {
            LOGGER.warn(WRONG_INPUT);
        } else {
            Company company = companyDtoMapper.mapToModel(companyDto);
            companyService.update(company);
        }
        return REDIRECT + COMPANY + VIEW + DASHBOARD;
    }

    /**
     * Delete computer.
     *
     * @param selection the selection
     * @return the string
     */
    @RequestMapping(value = COMPANY + CRUD + DELETE, method = RequestMethod.POST)
    public String deleteComputer(String selection) {
        LOGGER.info("Servlet : [POST] user-delete {}", selection);

        getList(selection).stream().forEach(companyService::delete);

        return REDIRECT + COMPANY + VIEW + DASHBOARD;
    }
}
