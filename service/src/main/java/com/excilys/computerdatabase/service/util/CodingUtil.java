package com.excilys.computerdatabase.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Vincent Galloy
 *         The Class CodingUtil.
 */
public class CodingUtil {

    /**
     * Encode.
     *
     * @param password the password
     * @return the string
     */
    public static String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
