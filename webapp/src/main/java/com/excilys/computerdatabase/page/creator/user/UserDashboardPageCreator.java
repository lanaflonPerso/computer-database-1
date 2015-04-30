/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.page.creator.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.dto.mapper.UserDetailDtoMapper;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.page.creator.AbstractPageCreator;
import com.excilys.computerdatabase.page.model.UserDashboardPage;
import com.excilys.computerdatabase.service.services.SecurityService;

@Service
public class UserDashboardPageCreator extends AbstractPageCreator {
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserDetailDtoMapper userDetailDtoMapper;

	public UserDashboardPage getPageFromGetRequest() {
		return pageGet();
	}

	private UserDashboardPage pageGet() {
		UserDashboardPage newPage = new UserDashboardPage();
		List<UserDetail> list = securityService.getAll();
		newPage.setUserList(userDetailDtoMapper.mapListFromModel(list));
		return newPage;
	}

}
