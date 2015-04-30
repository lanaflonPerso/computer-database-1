package com.excilys.computerdatabase.persistence.mapper.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.mapper.UserPersistenceMapper;
import com.excilys.computerdatabase.persistence.model.User;
import com.excilys.computerdatabase.persistence.model.UserRole;

@Component
public class UserPersistenceMapperImpl implements UserPersistenceMapper {

	@Override
	public User mapFromModel(UserDetail model) {
		User user = new User();
		user.setEnabled(true);
		user.setUsername(model.getUserName());
		user.setPassword(model.getPassword());
		Set<UserRole> set = new HashSet<UserRole>();
		for (Role role : model.getRoles()) {
			set.add(new UserRole(user, role.toString()));
		}
		user.setUserRole(set);
		return user;
	}

	@Override
	public UserDetail mapToModel(User dto) {
		UserDetail userDetail = new UserDetail();
		userDetail.setPassword(dto.getPassword());
		userDetail.setUserName(dto.getUsername());
		Set<Role> roles = new HashSet<Role>();
		if (dto.getUserRole() != null) {
			for (UserRole userRole : dto.getUserRole()) {
				roles.add(Role.build(userRole.getRole()));
			}
		}
		userDetail.setRoles(roles);
		return userDetail;
	}

}
