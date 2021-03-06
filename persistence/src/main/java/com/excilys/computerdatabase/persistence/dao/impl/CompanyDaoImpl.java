package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.CompanyDao;
import com.excilys.computerdatabase.persistence.exception.DaoException;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
public class CompanyDaoImpl implements CompanyDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Company t) {
        LOGGER.info("Company create : {}", t);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isCompanyCorrectForCreate(t)) {
            LOGGER.warn(Validator.INVALID_COMPANY);
            throw new DaoException(Validator.INVALID_COMPANY);
        }
        Long id = (Long) session.save(t);
        t.setId(id);
    }

    @Override
    public List<Company> getAll(SortCriteria sortCriteria) {
        LOGGER.info("Company getAll");
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Company.class).addOrder(Order.asc("name")).list();
    }

    @Override
    public List<Company> getAll(Long from, Long to, SortCriteria sortCriteria) {
        LOGGER.info("Company getAll : {} to {}", from, to);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isDateFromToCorrect(from, to)) {
            LOGGER.warn(Validator.INVALID_BOUND);
            throw new DaoException(Validator.INVALID_BOUND);
        }
        return session.createCriteria(Company.class)
                .setFirstResult(from.intValue())
                .setMaxResults(to.intValue() - from.intValue())
                .list();
    }

    @Override
    public Long getNumberOfElement() {
        LOGGER.info("Company getNumberOfElement");
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(Company.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Company delete : {}", id);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isIdCorrect(id)) {
            LOGGER.warn(Validator.INVALID_COMPANY_ID);
            throw new DaoException(Validator.INVALID_COMPANY_ID);
        }
        Company company = session.get(Company.class, id);
        if (company == null) {
            return;
        }
        session.delete(company);
    }

    @Override
    public Company getById(Long id) {
        LOGGER.info("Company getById : {}", id);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isIdCorrect(id)) {
            LOGGER.warn(Validator.INVALID_COMPANY_ID);
            throw new DaoException(Validator.INVALID_COMPANY_ID);
        }
        return session.get(Company.class, id);
    }

    @Override
    public void update(Company t) {
        LOGGER.info("Company update : {}", t);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isCompanyCorrectForUpdate(t)) {
            LOGGER.warn(Validator.INVALID_COMPANY);
            throw new DaoException(Validator.INVALID_COMPANY);
        }
        session.merge(t);
    }
}
