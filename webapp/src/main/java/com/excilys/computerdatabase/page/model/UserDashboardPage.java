package com.excilys.computerdatabase.page.model;

import java.util.List;

import com.excilys.computerdatabase.dto.model.UserDetailDto;

public class UserDashboardPage {
	private List<UserDetailDto> userList;

	public UserDashboardPage() {
		super();
	}

	public UserDashboardPage(List<UserDetailDto> userList) {
		super();
		this.userList = userList;
	}

	public List<UserDetailDto> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDetailDto> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "UserDashboardPage [userList=" + userList + "]";
	}
	
}
