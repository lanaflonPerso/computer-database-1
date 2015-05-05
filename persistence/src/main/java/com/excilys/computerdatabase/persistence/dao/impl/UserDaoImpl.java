package com.excilys.computerdatabase.persistence.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.dao.UserDetailDao;
import com.excilys.computerdatabase.persistence.mapper.UserPersistenceMapper;
import com.excilys.computerdatabase.persistence.model.User;

@Repository
public class UserDaoImpl implements UserDetailDao {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionFactory sf;
	@Autowired
	private UserPersistenceMapper userPersistenceMapper;

	@Override
	public void create(UserDetail userDetail) {
		log.info("create : {}", userDetail);
		User user = userPersistenceMapper.mapFromModel(userDetail);
		sf.getCurrentSession().save(user);
		for (int i = 0; i < user.getUserRole().size(); i++) {
			user.getUserRole().stream().forEach(sf.getCurrentSession()::save);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserDetail> getAll() {
		log.info("getAll :");
		List<User> list = sf.getCurrentSession().createCriteria(User.class).list();
		return userPersistenceMapper.mapListToModel(list);
	}

	@Override
	@SuppressWarnings("unchecked")
	public UserDetail getByUsername(String userName) {
		log.info("getByName : {}", userName);
		List<User> list = sf.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", userName)).list();
		if (list.size() != 1) {
			return null;
		} else {
			return userPersistenceMapper.mapToModel(list.get(0));
		}
	}

	@Override
	public void update(UserDetail userDetail) {
		log.info("update : {}", userDetail);
		delete(userDetail.getUserName());
		create(userDetail);
	}

	@Override
	public void delete(String userName) {
		log.info("delete : {}", userName);
		Session session = sf.getCurrentSession();
		UserDetail userDetail = getByUsername(userName);
		User user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", userName)).list().get(0);
		if (userDetail != null) {
			session.delete(user);
		} else {
			log.warn("delete unexisting user with username : {}", userName);
		}

	}

}
