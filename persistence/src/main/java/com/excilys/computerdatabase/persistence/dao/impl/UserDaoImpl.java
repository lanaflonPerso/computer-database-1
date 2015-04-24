package com.excilys.computerdatabase.persistence.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.persistence.dao.UserDao;
import com.excilys.computerdatabase.persistence.model.User;

@Repository
@SuppressWarnings("unchecked")
@Transactional
public class UserDaoImpl implements UserDao {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionFactory sf;

	@Override
	public void create(User daoUser) {
		log.trace("create : {} ", daoUser);
		sf.getCurrentSession().save(daoUser);
	}

	@Override
	public User getByName(String userName) {
		log.trace("getByName : {} ", userName);
		return (User) sf.getCurrentSession().get(User.class, userName);
	}

	@Override
	public void update(User daoUser) {
		log.trace("update : {} ", daoUser);
		sf.getCurrentSession().merge(daoUser);
	}

	@Override
	public void delete(String userName) {
		log.trace("delete : {} ", userName);
		User daoUser = getByName(userName);
		if (daoUser == null) {
			return;
		}
		sf.getCurrentSession().delete(daoUser);
	}

	@Override
	public List<User> getAll() {
		log.trace("getAll");
		return (List<User>) sf.getCurrentSession().createCriteria(User.class).list();
	}

}
