package com.excilys.computerdatabase.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.computerdatabase.model.Role;
import com.excilys.computerdatabase.persistence.model.Authority;

public class RoleMapper {
	public static Role build(Authority authority) {
		return Role.build(authority.getRole());
	}
	
	public static List<Role> build(List<Authority> authorities) {
		return authorities.stream().map(RoleMapper::build).collect(Collectors.toList());
	}

}
