package com.excilys.computerdatabase.controler.computer;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.creator.computer.AddPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.session.AddComputerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Locale;

/**
 * @author Vincent Galloy
 *         The Class ComputerAdd.
 */
@Controller
@SessionAttributes({"computerDto"})
public class ComputerAdd extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerAdd.class);
    @Autowired
    private AddComputerSession addComputerSession;
    @Autowired
    private AddPageCreator addPageCreator;

    /**
     * Gets the adds the computer page.
     *
     * @param model the model
     * @return the adds the computer page
     */
    @RequestMapping(value = COMPUTER + VIEW + ADD, method = RequestMethod.GET)
    private String getAddComputerPage(Model model) {
        LOGGER.info("Servlet : [GET] addComputer");

        ComputerPage page = addPageCreator.getPageFromGetRequest();
        if (addComputerSession.getComputerDto() != null) {
            page.setComputerDto(addComputerSession.getComputerDto());
            AbstractPageCreator.pageConverter(page, Locale.ENGLISH, LocaleContextHolder.getLocaleContext().getLocale());
            model.addAttribute("org.springframework.validation.BindingResult.addComputerDto", addComputerSession.getBindingResult());
        }
        model.addAttribute("page", page);

        return COMPUTER + VIEW + ADD;
    }
}