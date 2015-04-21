package com.excilys.computerdatabase.controler;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.excilys.computerdatabase.dto.model.ComputerDto;

public abstract class AbstractController {
	public static final String EDIT_COMPUTER = "editComputer";
	public static final String ADD_COMPUTER = "addComputer";
	public static final String DASHBOARD = "dashboard";
	public static final String LOGIN = "customLogin";
	public static final String REDIRECT = "redirect:/";
	
	@ModelAttribute("addComputerDto")
	public ComputerDto getAddComputerDto(){
	    return new ComputerDto();
	}
	
	@ModelAttribute("editComputerDto")
	public ComputerDto getEditComputerDto(){
	    return new ComputerDto();
	}
	
}
