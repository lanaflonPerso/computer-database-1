/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.controler.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.controler.AbstractController;

/**
 * The Class SecurityController.
 */
@Controller
public class SecurityController extends AbstractController {

	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * Login.
	 *
	 * @param auth
	 *          the auth
	 * @param model
	 *          the model
	 * @return the string
	 */
	@RequestMapping(value = GLOBAL + VIEW + LOGIN, method = RequestMethod.GET)
	public String login(@RequestParam(value = "auth", required = false) String auth, Model model) {

		log.debug("Login");

		if (auth == null || !auth.equals("false")) {
			auth = "true";
		}
		model.addAttribute("auth", auth);
		return GLOBAL + VIEW + LOGIN;
	}

	/**
	 * Login.
	 *
	 * @param model
	 *          the model
	 * @return the string
	 */
	@RequestMapping(value = GLOBAL + VIEW + LOGOUT, method = RequestMethod.GET)
	public String login(Model model) {

		log.info("Logout");

		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);

		return REDIRECT + GLOBAL + VIEW + LOGIN;
	}

}
