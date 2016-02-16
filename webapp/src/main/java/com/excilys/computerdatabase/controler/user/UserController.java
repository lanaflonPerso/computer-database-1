package com.excilys.computerdatabase.controler.user;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.dto.mapper.RuleDtoMapper;
import com.excilys.computerdatabase.dto.mapper.UserDetailDtoMapper;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.dto.model.UserDetailDto;
import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.service.services.SecurityService;
import com.excilys.computerdatabase.session.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class UserController.
 */
@Controller
public class UserController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserDetailDtoMapper userDetailDtoMapper;
    @Autowired
    private RuleDtoMapper ruleDtoMapper;
    @Autowired
    private UserSession userSession;
    @Autowired
    private MessageSource messageSource;

    /**
     * Adds the user.
     *
     * @param userDetailDto the user detail dto
     * @param bindingResult the binding result
     * @return the string
     */
    @RequestMapping(value = USER + CRUD + ADD, method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("userDetailDto") UserDetailDto userDetailDto, BindingResult bindingResult) {
        LOGGER.info("Servlet : [POST] user: add {}", userDetailDto);
        userSession.setErrorMessage(null);

        if (bindingResult.hasErrors() || userDetailDto.getUserName().trim().isEmpty() || userDetailDto.getPassword().trim().isEmpty()) {
            LOGGER.warn("Wrong input");
            if (userDetailDto.getUserName().trim().isEmpty()) {
                userSession.setErrorMessage(messageSource.getMessage("username.can.not.be.null", null, LocaleContextHolder.getLocale()));
            } else if (userDetailDto.getPassword().trim().isEmpty()) {
                userSession.setErrorMessage(messageSource.getMessage("password.can.not.be.null", null, LocaleContextHolder.getLocale()));
            }
        } else {
            UserDetail userDetail = userDetailDtoMapper.mapToModel(userDetailDto);
            securityService.create(userDetail);
            LOGGER.info("User added : " + userDetail);
        }
        return REDIRECT + USER + VIEW + DASHBOARD;
    }

    /**
     * Edits the rule.
     *
     * @param ruleDto       the rule dto
     * @param bindingResult the binding result
     * @return the string
     */
    @RequestMapping(value = USER + CRUD + EDIT, method = RequestMethod.POST)
    public String editRule(@Valid @ModelAttribute("ruleDto") RuleDto ruleDto, BindingResult bindingResult) {
        LOGGER.info("Servlet : [POST] user: edit rule {}", ruleDto);
        userSession.setErrorMessage(null);

        RuleDto ruleDto2 = new RuleDto(SecurityContextHolder.getContext().getAuthentication().getName(), Role.SUPER_ADMIN.toString(), false);

        if (bindingResult.hasErrors() || ruleDto.equals(ruleDto2)) {
            LOGGER.warn("Wrong input");
            if (ruleDto.equals(ruleDto2)) {
                userSession.setErrorMessage(messageSource.getMessage("can.not.remove.this.authorities", null, LocaleContextHolder.getLocale()));
            }
        } else {
            Rule rule = ruleDtoMapper.mapToModel(ruleDto);
            securityService.updateRight(rule);
        }
        return REDIRECT + USER + VIEW + DASHBOARD;
    }

    /**
     * Reset password.
     *
     * @param userDetailDto the user detail dto
     * @param bindingResult the binding result
     * @return the string
     */
    @RequestMapping(value = USER + CRUD + RESET_PASSWORD, method = RequestMethod.POST)
    public String resetPassword(@Valid @ModelAttribute("userDetailDto") UserDetailDto userDetailDto, BindingResult bindingResult) {
        LOGGER.info("Servlet : [POST] user: reset password {}", userDetailDto);
        userSession.setErrorMessage(null);

        if (bindingResult.hasErrors() || userDetailDto.getPassword().trim().isEmpty()) {
            LOGGER.info("Wrong input");
            if (userDetailDto.getPassword().trim().isEmpty()) {
                userSession.setErrorMessage(messageSource.getMessage("password.can.not.be.null", null, LocaleContextHolder.getLocale()));
            }
        } else {
            UserDetail userDetail = userDetailDtoMapper.mapToModel(userDetailDto);
            securityService.resetPassword(userDetail);
        }
        return REDIRECT + USER + VIEW + DASHBOARD;
    }

    /**
     * Delete computer.
     *
     * @param selection the selection
     * @return the string
     */
    @RequestMapping(value = USER + CRUD + DELETE, method = RequestMethod.POST)
    public String deleteComputer(String selection) {
        LOGGER.info("Servlet : [POST] user: delete {}", selection);
        userSession.setErrorMessage(null);

        getList(selection).stream().forEach(securityService::delete);

        return REDIRECT + USER + VIEW + DASHBOARD;
    }

    /**
     * Gets the list.
     *
     * @param selection the selection
     * @return the list
     */
    private List<String> getList(String selection) {
        List<String> list = new ArrayList<>();
        if ("".equals(selection)) {
            return list;
        }
        for (String s : selection.split(",")) {
            if (SecurityContextHolder.getContext().getAuthentication().getName().equals(s)) {
                userSession.setErrorMessage(messageSource.getMessage("can.not.delete.yourself", null, LocaleContextHolder.getLocale()));
                LOGGER.warn("wrong input : can't delete the current user");
            } else {
                list.add(s);
            }
        }
        return list;
    }
}
