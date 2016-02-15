/**
 * @author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.controler.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.computerdatabase.controler.AbstractController;

/**
 * The Class DefaultController.
 */
@Controller
public class DefaultController extends AbstractController{
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Gets the edits the computer page.
	 *
	 * @param model the model
	 * @return the edits the computer page
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected String getEditComputerPage(Model model) {

		log.info("Servlet : [GET] Default");

		return REDIRECT + GLOBAL + VIEW + HOME;
	}
	
	/**
	 * Gets the home.
	 *
	 * @param model the model
	 * @return the home
	 */
	@RequestMapping(value = GLOBAL + VIEW + HOME ,method = RequestMethod.GET)
	protected String getHome(Model model) {

		log.info("Servlet : [GET] Home");
		
		return GLOBAL + VIEW + HOME;
	}
	
}
