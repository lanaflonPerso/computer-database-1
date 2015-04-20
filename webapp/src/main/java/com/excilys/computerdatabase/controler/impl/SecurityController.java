package com.excilys.computerdatabase.controler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.controler.AbstractController;

@Controller
public class SecurityController extends AbstractController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/customLogin", method = RequestMethod.GET)
	public String login(@RequestParam(value="auth", required=false) String auth, Model model) {
		
		log.debug("Login");
		
		if(auth == null || !auth.equals("false")){
			auth="true";
		}
		model.addAttribute("auth", auth );
		return LOGIN;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String login(Model model) {
		
		log.info("Logout");
		
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		
		return REDIRECT + LOGIN;
	}
	
}
