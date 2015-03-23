package com.excilys.computerDataBase.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.excilys.computerDataBase.dto.ComputerDto;

public final class ServletUtil {

	private static final Logger log = Logger.getLogger(ServletUtil.class
			.getName());

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
		String name = getStringFromRequest(request, "computerName");
		String introduced = getStringFromRequest(request, "introduced");
		String discontinued = getStringFromRequest(request, "discontinued");
		String companyId = getStringFromRequest(request, "companyId");
		if("0".equals(companyId)) {
			companyId = null;
		}
		String companyName = getStringFromRequest(request, "companyName");
		return new ComputerDto(null, name, introduced, discontinued, companyId, companyName);
	}
}
