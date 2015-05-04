/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.controler.global;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class GlobalErrorHandler.
 */
@ControllerAdvice
public class GlobalErrorHandler {

	/**
	 * Handler.
	 *
	 * @param e the e
	 * @return the model and view
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ModelAndView handler(Exception e) {
		ModelAndView modelAndView = new ModelAndView("500");
		modelAndView.addObject("exception", e);
		return modelAndView;
	}
}
