package com.excilys.computerdatabase.service.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.model.Authority;
import com.excilys.computerdatabase.persistence.model.User;

public class UserDetailMapper {
	public static UserDetail build(User user, List<Authority> authorities) {
		UserDetail userDetail = new UserDetail();
		userDetail.setUserName(user.getUserName());
		userDetail.setPassword(user.getPassword());
		userDetail.setRoles(RoleMapper.build(authorities));
		return userDetail;
	}

	public static User extractUser(UserDetail userDetail) {
		User user = new User();
		user.setUserName(userDetail.getUserName());
		user.setPassword(userDetail.getPassword());
		return user;
	}
	
	public static List<Authority> extractAuthorities(UserDetail userDetail) {
		if(userDetail.getRoles() != null){
			return userDetail.getRoles().stream().map(e-> new Authority(userDetail.getUserName(), e.toString())).collect(Collectors.toList());
		}
		return new ArrayList<Authority>();
	}
}
