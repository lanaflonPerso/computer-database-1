package com.excilys.computerdatabase.service.mapper;

import com.excilys.computerdatabase.mapper.Mapper;
import com.excilys.computerdatabase.model.UserDetail;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Vincent Galloy
 *         The Interface UserDetailsMapper.
 */
public interface UserDetailsMapper extends Mapper<UserDetail, UserDetails> {

}
