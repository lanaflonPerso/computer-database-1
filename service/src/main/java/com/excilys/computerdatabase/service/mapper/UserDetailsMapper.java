/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.service.mapper;

import org.springframework.security.core.userdetails.UserDetails;

import com.excilys.computerdatabase.mapper.Mapper;
import com.excilys.computerdatabase.model.UserDetail;

/**
 * The Interface UserDetailsMapper.
 */
public interface UserDetailsMapper extends Mapper <UserDetail, UserDetails> {

}
