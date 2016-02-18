package com.excilys.computerdatabase.controler.global;

import com.excilys.computerdatabase.controler.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Vincent Galloy
 *         The Class SecurityController.
 */
@Controller
public class SecurityController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    /**
     * Login.
     *
     * @param auth  the auth
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = GLOBAL + VIEW + CUSTOM_LOGIN, method = RequestMethod.GET)
    public String login(@RequestParam(value = "auth", required = false) String auth, Model model) {
        LOGGER.debug("Login");

        if (auth == null || !"false".equals(auth)) {
            auth = "true";
        }
        model.addAttribute("auth", auth);
        return GLOBAL + VIEW + CUSTOM_LOGIN;
    }

    /**
     * Login.
     *
     * @return the string
     */
    @RequestMapping(value = GLOBAL + VIEW + LOGOUT, method = RequestMethod.GET)
    public String login() {
        LOGGER.info("Logout");

        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);

        return REDIRECT + GLOBAL + VIEW + CUSTOM_LOGIN;
    }
}
