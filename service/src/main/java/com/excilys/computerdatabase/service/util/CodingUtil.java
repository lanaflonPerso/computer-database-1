package com.excilys.computerdatabase.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CodingUtil {
	public static String encode(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
