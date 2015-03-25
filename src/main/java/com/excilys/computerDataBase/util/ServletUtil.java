package com.excilys.computerDataBase.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerDataBase.dto.ComputerDto;

public final class ServletUtil {

	final static Logger log = LoggerFactory.getLogger(ServletUtil.class);


	private static String getStringFromRequest(HttpServletRequest request,
			String name) {
		String string = (String) request.getParameter(name);
		if (string == null) {
			log.warn("Can find parameter : " + name);
			return null;
		} else if (string.trim().isEmpty() ) {
			return null;
		}
		
		string = string.trim();
		return string;
	}

	public static ComputerDto getComputerDto(HttpServletRequest request) {
		String id = getStringFromRequest(request, "computerId");
		if("".equals(id)) {
			id = null;
		}
		String name = getStringFromRequest(request, "computerName");
		String introduced = getStringFromRequest(request, "introduced");
		String discontinued = getStringFromRequest(request, "discontinued");
		String companyId = getStringFromRequest(request, "companyId");
		if("0".equals(companyId)) {
			companyId = null;
		}
		String companyName = getStringFromRequest(request, "companyName");
		return new ComputerDto(id, name, introduced, discontinued, companyId, companyName);
	}
}
