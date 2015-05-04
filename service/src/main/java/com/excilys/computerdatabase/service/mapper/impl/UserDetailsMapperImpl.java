/**
 * @Author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.service.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.service.mapper.UserDetailsMapper;

/**
 * The Class UserDetailsMapperImpl.
 */
@Component
public class UserDetailsMapperImpl implements UserDetailsMapper {

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.Mapper#mapFromModel(java.lang.Object)
	 */
	@Override
	public UserDetails mapFromModel(UserDetail model) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (Role role : model.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
		}
		return new User(model.getUserName(), model.getPassword(), true, true, true, true, grantedAuthorities);
	}

	/* (non-Javadoc)
	 * @see com.excilys.computerdatabase.mapper.Mapper#mapToModel(java.lang.Object)
	 */
	@Override
	public UserDetail mapToModel(UserDetails dto) {
		throw new UnsupportedOperationException();
	}

}
