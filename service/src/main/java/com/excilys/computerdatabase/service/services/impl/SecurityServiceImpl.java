/**
 * @author Vincent Galloy
 * 
 */
package com.excilys.computerdatabase.service.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.dao.UserDetailDao;
import com.excilys.computerdatabase.service.mapper.UserDetailsMapper;
import com.excilys.computerdatabase.service.services.SecurityService;
import com.excilys.computerdatabase.service.util.CodingUtil;
import com.excilys.computerdatabase.service.validation.ServiceValidator;

/**
 * The Class SecurityServiceImpl.
 */
@Service("userDetailsService")
@Transactional(readOnly=false)
public class SecurityServiceImpl implements SecurityService, UserDetailsService {
	
	/** The log. */
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/** The user detail dao. */
	@Autowired
	private UserDetailDao userDetailDao;
	
	/** The user details mapper. */
	@Autowired
	private UserDetailsMapper userDetailsMapper;

	@Override
	@Transactional(readOnly=true)
	public UserDetail getByName(String userName) {
		log.info("getByName : {} ", userName);
		if (!ServiceValidator.isUserNameCorrect(userName)) {
			throw new RuntimeException(ServiceValidator.INVALID_USERNAME);
		}
		return userDetailDao.getByUsername(userName);
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
		UserDetail newUserDetail = userDetailDao.getByUsername(userName);
		newUserDetail.setPassword(userDetail.getPassword());
		userDetailDao.update(newUserDetail);
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
	@Transactional(readOnly=true)
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
		UserDetail userDetail = userDetailDao.getByUsername(rule.getUserName());
		if(rule.isAuthorized()) {
			userDetail.getRoles().add(rule.getRole());
		} else {
			userDetail.getRoles().remove(rule.getRole());
		}
		userDetailDao.update(userDetail);
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			UserDetail userDetail = userDetailDao.getByUsername(username);
			return userDetailsMapper.mapFromModel(userDetail);
	}

}
