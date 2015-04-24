package com.excilys.computerdatabase.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.dao.AuthorityDao;
import com.excilys.computerdatabase.persistence.dao.UserDao;
import com.excilys.computerdatabase.persistence.model.Authority;
import com.excilys.computerdatabase.service.SecurityService;
import com.excilys.computerdatabase.service.dao.UserDetailDao;
import com.excilys.computerdatabase.service.util.CodingUtil;
import com.excilys.computerdatabase.service.validation.ServiceValidator;

@Service
public class SecurityServiceImpl implements SecurityService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDetailDao userDetailDao;
	@Autowired
	private AuthorityDao authorityDao;
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetail getByName(String userName) {
		log.info("getByName : {} ", userName);
		if (!ServiceValidator.isUserNameCorrect(userName)) {
			throw new RuntimeException(ServiceValidator.INVALID_USERNAME);
		}
		return userDetailDao.getByName(userName);
	}

	@Override
	public void create(UserDetail userDetail) {
		log.info("create : {} ", userDetail);
		if (!ServiceValidator.isUserCorrect(userDetail)) {
			throw new RuntimeException(ServiceValidator.INVALID_USER);
		}
		userDetail.setPassword(CodingUtil.encode(userDetail.getPassword()));
		userDetailDao.create(userDetail);
	}

	@Override
	public void resetPassword(UserDetail userDetail) {
		log.info("resetPassword : {} ", userDetail);
		if (userDetail == null) {
			throw new RuntimeException(ServiceValidator.INVALID_USER);
		}
		String userName = userDetail.getUserName();
		if (!ServiceValidator.isUserNameCorrect(userName)) {
			throw new RuntimeException(ServiceValidator.INVALID_USERNAME);
		}
		userDetail.setPassword(CodingUtil.encode(userDetail.getPassword()));
		userDetailDao.updatePassword(userDetail);
	}

	@Override
	public void delete(String userName) {
		log.info("delete : {} ", userName);
		if (!ServiceValidator.isUserNameCorrect(userName)) {
			throw new RuntimeException(ServiceValidator.INVALID_USERNAME);
		}
		userDetailDao.delete(userName);
	}

	@Override
	public List<UserDetail> getAll() {
		log.info("getAll");
		return userDetailDao.getAll();
	}

	@Override
	public void updateRight(Rule rule) {
		log.info("updateRight : {} ", rule);
		if (!ServiceValidator.isRuleCorrect(rule)) {
			throw new RuntimeException(ServiceValidator.INVALID_RULE);
		}
		Authority authority = new Authority(rule.getUserName(), rule.getRole().toString());
		authorityDao.delete(authority);
		if (rule.isAuthorized()) {
			authorityDao.create(authority);
		}
	}

}
