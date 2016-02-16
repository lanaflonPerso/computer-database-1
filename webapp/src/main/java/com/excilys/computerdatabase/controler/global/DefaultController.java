package com.excilys.computerdatabase.controler.global;

import com.excilys.computerdatabase.controler.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Vincent Galloy
 *         The Class DefaultController.
 */
@Controller
public class DefaultController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    /**
     * Gets the edits the computer page.
     *
     * @return the edits the computer page
     */
    @RequestMapping(method = RequestMethod.GET)
    protected String getEditComputerPage() {
        LOGGER.info("Servlet : [GET] Default");

        return REDIRECT + GLOBAL + VIEW + HOME;
    }

    /**
     * Gets the home.
     *
     * @return the home
     */
    @RequestMapping(value = GLOBAL + VIEW + HOME, method = RequestMethod.GET)
    protected String getHome() {
        LOGGER.info("Servlet : [GET] Home");

        return GLOBAL + VIEW + HOME;
    }
}
