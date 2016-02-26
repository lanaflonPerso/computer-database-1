package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.dao.UserDetailDao;
import com.excilys.computerdatabase.persistence.mapper.UserPersistenceMapper;
import com.excilys.computerdatabase.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vincent Galloy
 */
@Repository
@SuppressWarnings("unchecked")
public class UserDaoImpl implements UserDetailDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserPersistenceMapper userPersistenceMapper;

    @Override
    public void create(UserDetail userDetail) {
        LOGGER.info("create : {}", userDetail);
        User user = userPersistenceMapper.mapFromModel(userDetail);
        sessionFactory.getCurrentSession().save(user);
        for (int i = 0; i < user.getUserRole().size(); i++) {
            user.getUserRole().stream().forEach(sessionFactory.getCurrentSession()::save);
        }
    }

    @Override
    public List<UserDetail> getAll() {
        LOGGER.info("getAll :");
        List<User> list = sessionFactory.getCurrentSession().createCriteria(User.class).list();
        return userPersistenceMapper.mapListToModel(list);
    }

    @Override
    public UserDetail getByUsername(String userName) {
        LOGGER.info("getByName : {}", userName);
        User user = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("username", userName)).uniqueResult();
        return userPersistenceMapper.mapToModel(user);
    }

    @Override
    public void update(UserDetail userDetail) {
        LOGGER.info("update : {}", userDetail);
        delete(userDetail.getUserName());
        create(userDetail);
    }

    @Override
    public void delete(String userName) {
        LOGGER.info("delete : {}", userName);
        Session session = sessionFactory.getCurrentSession();
        UserDetail userDetail = getByUsername(userName);
        User user = (User) session.createCriteria(User.class).add(Restrictions.eq("username", userName)).uniqueResult();
        if (userDetail != null) {
            session.delete(user);
        } else {
            LOGGER.warn("delete no existing user with username : {}", userName);
        }
    }
}
