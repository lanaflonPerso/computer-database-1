package com.excilys.computerdatabase.persistence.mapper.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
		Set<UserRole> set = model.getRoles().stream().map(role -> new UserRole(user, role.toString())).collect(Collectors.toSet());
		user.setUserRole(set);
		return user;
	}

	@Override
	public UserDetail mapToModel(User dto) {
		UserDetail userDetail = new UserDetail();
		userDetail.setPassword(dto.getPassword());
		userDetail.setUserName(dto.getUsername());
		Set<Role> roles = new HashSet<>();
		if (dto.getUserRole() != null) {
			roles.addAll(dto.getUserRole().stream().map(userRole -> Role.build(userRole.getRole())).collect(Collectors.toList()));
		}
		userDetail.setRoles(roles);
		return userDetail;
	}
}
