/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.controler;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.dto.model.UserDetailDto;

/**
 * The Class AbstractController.
 */
public abstract class AbstractController {
	
	/** The Constant USER. */
	public static final String USER 					= "user/";
	
	/** The Constant GLOBAL. */
	public static final String GLOBAL 				= "global/";
	
	/** The Constant COMPANY. */
	public static final String COMPANY 				= "company/";
	
	/** The Constant COMPUTER. */
	public static final String COMPUTER 			= "computer/";
	
	/** The Constant CRUD. */
	public static final String CRUD 					= "crud/";
	
	/** The Constant VIEW. */
	public static final String VIEW 					= "view/";
	
	/** The Constant EDIT. */
	public static final String EDIT 					= "edit";
	
	/** The Constant ADD. */
	public static final String ADD  					= "add";
	
	/** The Constant DELETE. */
	public static final String DELETE  				= "delete";
	
	/** The Constant DASHBOARD. */
	public static final String DASHBOARD 			= "dashboard";
	
	/** The Constant LOGIN. */
	public static final String LOGIN 					= "customLogin";
	
	/** The Constant LOGOUT. */
	public static final String LOGOUT					= "logout";
	
	/** The Constant HOME. */
	public static final String HOME						= "home";
	
	/** The Constant RESET_PASSWORD. */
	public static final String RESET_PASSWORD	= "resetPassword";
	
	
	/** The Constant REDIRECT. */
	public static final String REDIRECT 	= "redirect:/";
	
	/**
	 * Gets the adds the computer dto.
	 *
	 * @return the adds the computer dto
	 */
	@ModelAttribute("addComputerDto")
	public ComputerDto getAddComputerDto(){
	    return new ComputerDto();
	}
	
	/**
	 * Gets the edits the computer dto.
	 *
	 * @return the edits the computer dto
	 */
	@ModelAttribute("editComputerDto")
	public ComputerDto getEditComputerDto(){
	    return new ComputerDto();
	}
	
	/**
	 * Gets the user detail dto.
	 *
	 * @return the user detail dto
	 */
	@ModelAttribute("userDetailDto")
	public UserDetailDto getUserDetailDto(){
	    return new UserDetailDto();
	}
	
	/**
	 * Gets the rule dto.
	 *
	 * @return the rule dto
	 */
	@ModelAttribute("ruleDto")
	public RuleDto getRuleDto(){
	    return new RuleDto();
	}
	
	/**
	 * Gets the company dto.
	 *
	 * @return the company dto
	 */
	@ModelAttribute("companyDto")
	public CompanyDto getCompanyDto(){
	    return new CompanyDto();
	}
}
