package com.excilys.computerdatabase.controler.global;

import com.excilys.computerdatabase.session.AddComputerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Vincent Galloy
 *         The Class GlobalErrorHandler.
 */
@ControllerAdvice
public class GlobalErrorHandler {
    @Autowired
    private AddComputerSession addComputerSession;

    /**
     * Handler.
     *
     * @param e the e
     * @return the model and view
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e) {
        e.printStackTrace();
        addComputerSession.setComputerDto(null);
        addComputerSession.setBindingResult(null);
        ModelAndView modelAndView = new ModelAndView("500");
        modelAndView.addObject("exception", e);
        return modelAndView;
    }
}
