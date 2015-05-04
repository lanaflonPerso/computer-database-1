/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.controler.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.dto.mapper.RuleDtoMapper;
import com.excilys.computerdatabase.dto.mapper.UserDetailDtoMapper;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.dto.model.UserDetailDto;
import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.service.services.SecurityService;

/**
 * The Class UserControler.
 */
@Controller
public class UserControler extends AbstractController {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/** The security service. */
	@Autowired
	private SecurityService securityService;
	
	/** The user detail dto mapper. */
	@Autowired
	private UserDetailDtoMapper userDetailDtoMapper;
	
	/** The rule dto mapper. */
	@Autowired
	private RuleDtoMapper ruleDtoMapper;

	/**
	 * Adds the user.
	 *
	 * @param userDetailDto the user detail dto
	 * @param bindingResult the binding result
	 * @return the string
	 */
	@RequestMapping(value = USER + CRUD + ADD, method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("userDetailDto") UserDetailDto userDetailDto, BindingResult bindingResult) {

		log.info("Servlet : [POST] user: add {}", userDetailDto);

		if (bindingResult.hasErrors() || userDetailDto.getUserName().trim().isEmpty() || userDetailDto.getPassword().trim().isEmpty()) {
			log.warn("Wrong input");
		} else {
			UserDetail userDetail = userDetailDtoMapper.mapToModel(userDetailDto);
			securityService.create(userDetail);
			log.info("User added : " + userDetail);
		}		
		return REDIRECT + USER + VIEW + DASHBOARD;
	}

	/**
	 * Edits the rule.
	 *
	 * @param ruleDto the rule dto
	 * @param bindingResult the binding result
	 * @return the string
	 */
	@RequestMapping(value = USER + CRUD + EDIT, method = RequestMethod.POST)
	public String editRule(@Valid @ModelAttribute("ruleDto") RuleDto ruleDto, BindingResult bindingResult) {

		log.info("Servlet : [POST] user: edit rule {}", ruleDto);

		RuleDto ruleDto2 = new RuleDto(SecurityContextHolder.getContext().getAuthentication().getName(), Role.SUPER_ADMIN.toString(), false);
		
		if (bindingResult.hasErrors() || ruleDto.equals(ruleDto2)) {
			log.warn("Wrong input");
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

		log.info("Servlet : [POST] user: reset password {}", userDetailDto);

		if (bindingResult.hasErrors() || userDetailDto.getPassword().trim().isEmpty() ) {
			log.info("Wrong input");
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

		log.info("Servlet : [POST] user: delete {}", selection);
		
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
		List<String> list = new ArrayList<String>();
		if ("".equals(selection)) {
			return list;
		}
		for (String s : selection.split(",")) {
			if(SecurityContextHolder.getContext().getAuthentication().getName().equals(s)){
				log.warn("wrong input : can't delete the current user");
			}
			else {
				list.add(s);
			}
		}
		return list;
	}

}
