package com.excilys.computerdatabase.service.services.impl;

import com.excilys.computerdatabase.model.Rule;
import com.excilys.computerdatabase.model.UserDetail;
import com.excilys.computerdatabase.persistence.dao.UserDetailDao;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.service.mapper.UserDetailsMapper;
import com.excilys.computerdatabase.service.services.SecurityService;
import com.excilys.computerdatabase.service.util.CodingUtil;
import com.excilys.computerdatabase.service.validation.ServiceValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

/**
 * @author Vincent Galloy
 *         The Class SecurityServiceImpl.
 */
@Service("userDetailsService")
@Transactional(readOnly = false)
public class SecurityServiceImpl implements SecurityService, UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Autowired
    private UserDetailDao userDetailDao;
    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    @Transactional(readOnly = true)
    public UserDetail getByName(String userName) {
        LOGGER.info("getByName : {} ", userName);
        if (!ServiceValidator.isUserNameCorrect(userName)) {
            throw new ServiceException(ServiceValidator.INVALID_USERNAME);
        }
        return userDetailDao.getByUsername(userName);
    }

    @Override
    public void create(UserDetail userDetail) {
        LOGGER.info("create : {} ", userDetail);
        if (!ServiceValidator.isUserCorrect(userDetail)) {
            throw new ServiceException(ServiceValidator.INVALID_USER);
        }
        userDetail.setPassword(CodingUtil.encode(userDetail.getPassword()));
        userDetailDao.create(userDetail);
    }

    @Override
    public void resetPassword(UserDetail userDetail) {
        LOGGER.info("resetPassword : {} ", userDetail);
        if (userDetail == null) {
            throw new ServiceException(ServiceValidator.INVALID_USER);
        }
        String userName = userDetail.getUserName();
        if (!ServiceValidator.isUserNameCorrect(userName)) {
            throw new ServiceException(ServiceValidator.INVALID_USERNAME);
        }
        userDetail.setPassword(CodingUtil.encode(userDetail.getPassword()));
        UserDetail newUserDetail = userDetailDao.getByUsername(userName);
        newUserDetail.setPassword(userDetail.getPassword());
        userDetailDao.update(newUserDetail);
    }

    @Override
    public void delete(String userName) {
        LOGGER.info("delete : {} ", userName);
        if (!ServiceValidator.isUserNameCorrect(userName)) {
            throw new ServiceException(ServiceValidator.INVALID_USERNAME);
        }
        userDetailDao.delete(userName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDetail> getAll() {
        LOGGER.info("getAll");
        return userDetailDao.getAll();
    }

    @Override
    public void updateRight(Rule rule) {
        LOGGER.info("updateRight : {} ", rule);
        if (!ServiceValidator.isRuleCorrect(rule)) {
            throw new ServiceException(ServiceValidator.INVALID_RULE);
        }
        UserDetail userDetail = userDetailDao.getByUsername(rule.getUserName());
        if (rule.isAuthorized()) {
            userDetail.getRoles().add(rule.getRole());
        } else {
            userDetail.getRoles().remove(rule.getRole());
        }
        userDetailDao.update(userDetail);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserDetail userDetail = userDetailDao.getByUsername(username);
        if (userDetail == null) {
            userDetail = new UserDetail("Not a user", "fake password", new HashSet<>());
        }
        return userDetailsMapper.mapFromModel(userDetail);
    }
}
