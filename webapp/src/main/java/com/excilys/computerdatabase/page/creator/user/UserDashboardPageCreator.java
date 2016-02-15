/**
 * @author Vincent Galloy
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
import com.excilys.computerdatabase.session.UserSession;

/**
 * The Class UserDashboardPageCreator.
 */
@Service
public class UserDashboardPageCreator extends AbstractPageCreator {

	/** The security service. */
	@Autowired
	private SecurityService securityService;

	/** The user detail dto mapper. */
	@Autowired
	private UserDetailDtoMapper userDetailDtoMapper;

	/** The user session. */
	@Autowired
	private UserSession userSession;

	public UserDashboardPage getPageFromGetRequest() {
		return pageGet();
	}

	/**
	 * Page get.
	 *
	 * @return the user dashboard page
	 */
	private UserDashboardPage pageGet() {
		UserDashboardPage newPage = new UserDashboardPage();
		List<UserDetail> list = securityService.getAll();
		newPage.setUserList(userDetailDtoMapper.mapListFromModel(list));
		newPage.setErrorMessage(userSession.getErrorMessage());
		return newPage;
	}

}
