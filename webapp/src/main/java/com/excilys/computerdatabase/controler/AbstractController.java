package com.excilys.computerdatabase.controler;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.excilys.computerdatabase.dto.model.CompanyDto;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.dto.model.RuleDto;
import com.excilys.computerdatabase.dto.model.UserDetailDto;

public abstract class AbstractController {
	public static final String USER 					= "user/";
	public static final String GLOBAL 				= "global/";
	public static final String COMPANY 				= "company/";
	public static final String COMPUTER 			= "computer/";
	
	public static final String CRUD 					= "crud/";
	public static final String VIEW 					= "view/";
	
	public static final String EDIT 					= "edit";
	public static final String ADD  					= "add";
	public static final String DELETE  				= "delete";
	public static final String DASHBOARD 			= "dashboard";
	public static final String LOGIN 					= "customLogin";
	public static final String LOGOUT					= "logout";
	public static final String HOME						= "home";
	public static final String RESET_PASSWORD	= "resetPassword";
	
	
	public static final String REDIRECT 	= "redirect:/";
	
	@ModelAttribute("addComputerDto")
	public ComputerDto getAddComputerDto(){
	    return new ComputerDto();
	}
	
	@ModelAttribute("editComputerDto")
	public ComputerDto getEditComputerDto(){
	    return new ComputerDto();
	}
	
	@ModelAttribute("userDetailDto")
	public UserDetailDto getUserDetailDto(){
	    return new UserDetailDto();
	}
	
	@ModelAttribute("ruleDto")
	public RuleDto getRuleDto(){
	    return new RuleDto();
	}
	
	@ModelAttribute("companyDto")
	public CompanyDto getCompanyDto(){
	    return new CompanyDto();
	}
}
