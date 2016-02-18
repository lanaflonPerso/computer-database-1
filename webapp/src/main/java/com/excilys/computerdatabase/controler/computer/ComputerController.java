package com.excilys.computerdatabase.controler.computer;

import com.excilys.computerdatabase.controler.AbstractController;
import com.excilys.computerdatabase.dto.mapper.ComputerDtoMapper;
import com.excilys.computerdatabase.dto.model.ComputerDto;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.creator.computer.AddPageCreator;
import com.excilys.computerdatabase.page.creator.computer.EditPageCreator;
import com.excilys.computerdatabase.page.model.ComputerPage;
import com.excilys.computerdatabase.service.services.ComputerService;
import com.excilys.computerdatabase.session.AddComputerSession;
import com.excilys.computerdatabase.session.EditComputerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Vincent Galloy
 *         The Class ComputerController.
 */
@Controller
@SessionAttributes({"computerDto"})
public class ComputerController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);
    @Autowired
    private AddComputerSession addComputerSession;
    @Autowired
    private EditComputerSession editComputerSession;
    @Autowired
    private ComputerService computerService;
    @Autowired
    private ComputerDtoMapper computerDtoMapper;
    @Autowired
    private AddPageCreator addPageCreator;
    @Autowired
    private EditPageCreator editPageCreator;

    /**
     * Adds the computer.
     *
     * @param computerDto   the computer dto
     * @param bindingResult the binding result
     * @return the string
     */
    @RequestMapping(value = COMPUTER + CRUD + ADD, method = RequestMethod.POST)
    public String addComputer(@Valid @ModelAttribute("addComputerDto") ComputerDto computerDto, BindingResult bindingResult) {
        LOGGER.info("Servlet : [POST] computer-add {}", computerDto);

        ComputerPage page = addPageCreator.getPageFromPostRequest(computerDto);
        addComputerSession.setComputerDto(page.getComputerDto());
        addComputerSession.setBindingResult(bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.info(WRONG_INPUT);
            return REDIRECT + COMPUTER + VIEW + ADD;
        } else {
            AbstractPageCreator.pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
            Computer computer = computerDtoMapper.mapToModel(page.getComputerDto());
            computerService.create(computer);
            LOGGER.info("Computer added : " + computer);
            return REDIRECT + COMPUTER + VIEW + DASHBOARD;
        }
    }

    /**
     * Edits the computer.
     *
     * @param computerDto   the computer dto
     * @param bindingResult the binding result
     * @return the string
     */
    @RequestMapping(value = COMPUTER + CRUD + EDIT, method = RequestMethod.POST)
    public String editComputer(@Valid @ModelAttribute("editComputerDto") ComputerDto computerDto, BindingResult bindingResult) {
        LOGGER.info("Servlet : [POST] computer-edit {}", computerDto);

        ComputerPage page = editPageCreator.getPageFromPostRequest(computerDto);
        editComputerSession.setComputerDto(page.getComputerDto());
        editComputerSession.setBindingResult(bindingResult);

        if (bindingResult.hasErrors()) {
            LOGGER.info(WRONG_INPUT);
            return REDIRECT + COMPUTER + VIEW + EDIT;
        } else {
            AbstractPageCreator.pageConverter(page, LocaleContextHolder.getLocaleContext().getLocale(), Locale.ENGLISH);
            Computer computer = computerDtoMapper.mapToModel(page.getComputerDto());
            computerService.update(computer);
            LOGGER.info("Computer updated : " + computer);
            return REDIRECT + COMPUTER + VIEW + DASHBOARD;
        }
    }

    /**
     * Delete computer.
     *
     * @param selection the selection
     * @return the string
     */
    @RequestMapping(value = COMPUTER + CRUD + DELETE, method = RequestMethod.POST)
    public String deleteComputer(String selection) {
        LOGGER.info("Servlet : [POST] computer-delete {}", selection);

        getList(selection).stream().forEach(computerService::delete);

        return REDIRECT + COMPUTER + VIEW + DASHBOARD;
    }
}
