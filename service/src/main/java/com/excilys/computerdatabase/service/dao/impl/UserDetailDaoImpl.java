package com.excilys.computerdatabase.service.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.dao.AuthorityDao;
import com.excilys.computerdatabase.persistence.dao.UserDao;
import com.excilys.computerdatabase.persistence.model.Authority;
import com.excilys.computerdatabase.persistence.model.User;
import com.excilys.computerdatabase.service.dao.UserDetailDao;
import com.excilys.computerdatabase.service.mapper.UserDetailMapper;

@Service
public class UserDetailDaoImpl implements UserDetailDao {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthorityDao authorityDao;
	
	@Override
	public void create(UserDetail userDetail) {
		log.info("create : {} ", userDetail);
		User user = UserDetailMapper.extractUser(userDetail);
		userDao.create(user);
		List<Authority> authorities = UserDetailMapper.extractAuthorities(userDetail);
		if(authorities != null) {
			authorities.stream().forEach(authorityDao::create);
		}
	}

	@Override
	public UserDetail getByName(String userName) {
		log.info("getByName : {}", userName);
		User user = userDao.getByName(userName);
		List<Authority> authorities = authorityDao.getByName(userName);
		return UserDetailMapper.build(user, authorities);
	}

	@Override
	public void updated(UserDetail userDetail) {
		log.info("updated : {}", userDetail);
		delete(userDetail.getUserName());
		create(userDetail);
	}

	@Override
	public void delete(String userName) {
		log.info("delete : {}", userName);
		UserDetail userDetail = getByName(userName);
		List<Authority> authorities = UserDetailMapper.extractAuthorities(userDetail);
		authorities.stream().forEach(authorityDao::delete);
		userDao.delete(userName);
	}

	@Override
	public List<UserDetail> getAll() {
		log.info("getAll");
		List<User> users = userDao.getAll();
		return users.stream().map(e-> this.getByName(e.getUserName())).collect(Collectors.toList());
	}

	@Override
	public void updatePassword(UserDetail userDetail) {
		log.info("updatePassword : {}", userDetail);
		userDao.update(new User(userDetail.getUserName(), userDetail.getPassword()));
	}

}
