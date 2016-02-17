package com.excilys.computerdatabase.service.mapper.impl;

import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.service.mapper.UserDetailsMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Vincent Galloy
 *         The Class UserDetailsMapperImpl.
 */
@Component
public class UserDetailsMapperImpl implements UserDetailsMapper {

    @Override
    public UserDetails mapFromModel(UserDetail model) {
        Set<GrantedAuthority> grantedAuthorities = model.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toSet());
        return new User(model.getUserName(), model.getPassword(), true, true, true, true, grantedAuthorities);
    }

    @Override
    public UserDetail mapToModel(UserDetails dto) {
        throw new UnsupportedOperationException();
    }
}
