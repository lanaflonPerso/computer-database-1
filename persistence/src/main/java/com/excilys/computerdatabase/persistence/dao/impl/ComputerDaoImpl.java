package com.excilys.computerdatabase.persistence.dao.impl;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.persistence.exception.DaoException;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.sort.SortDirection;
import com.excilys.computerdatabase.validation.Validator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
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
public class ComputerDaoImpl implements ComputerDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Computer t) {
        LOGGER.info("Computer create : {}", t);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isComputerCorrect(t)) {
            throw new DaoException(Validator.INVALID_COMPUTER);
        }
        Long id = (Long) session.save(t);
        t.setId(id);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Computer delete : {}", id);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isIdCorrect(id)) {
            throw new DaoException(Validator.INVALID_COMPUTER_ID);
        }
        Computer computer = session.get(Computer.class, id);
        if (computer == null) {
            return;
        }
        session.delete(computer);
    }

    @Override
    public void update(Computer t) {
        LOGGER.info("Computer update : {}", t);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isComputerCorrect(t)) {
            throw new DaoException(Validator.INVALID_COMPUTER);
        }
        session.merge(t);
    }

    @Override
    public Computer getById(Long id) {
        LOGGER.info("Computer getById : {}", id);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isIdCorrect(id)) {
            throw new DaoException(Validator.INVALID_COMPANY_ID);
        }
        return session.get(Computer.class, id);
    }

    @Override
    public List<Computer> getAll(SortCriteria sortCriteria) {
        LOGGER.info("Computer getAll");
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
            throw new DaoException(Validator.INVALID_SORT_CRITERIA);
        }
        Order order;
        if (sortCriteria.getSortDirection() == SortDirection.ASC) {
            order = Order.asc(sortCriteria.getSortColumn().toString());
        } else {
            order = Order.desc(sortCriteria.getSortColumn().toString());
        }
        return session.createCriteria(Computer.class, "computer")
                .createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
                .addOrder(order)
                .list();
    }

    @Override
    public List<Computer> getAll(Long from, Long to, SortCriteria sortCriteria) {
        LOGGER.info("Computer getAll from : {} to : {}", from, to);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isDateFromToCorrect(from, to)) {
            throw new DaoException(Validator.INVALID_COMPUTER);
        }
        if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
            throw new DaoException(Validator.INVALID_SORT_CRITERIA);
        }

        Order order;
        if (sortCriteria.getSortDirection() == SortDirection.ASC) {
            order = Order.asc(sortCriteria.getSortColumn().toString());
        } else {
            order = Order.desc(sortCriteria.getSortColumn().toString());
        }

        return session.createCriteria(Computer.class, "computer")
                .createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
                .addOrder(order)
                .setFirstResult(from.intValue())
                .setMaxResults(to.intValue() - from.intValue())
                .list();
    }

    @Override
    public Long getNumberOfElement() {
        LOGGER.info("Computer getNumberOfElement");
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.createCriteria(Computer.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }

    @Override
    public List<Computer> getByName(String search, Long from, Long to, SortCriteria sortCriteria) {
        LOGGER.info("Computer getByName from : {} to : {}", from, to);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isStringForSearchCorrect(search)) {
            throw new DaoException(Validator.INVALID_STRING_FOR_SEARCH);
        }
        if (!Validator.isDateFromToCorrect(from, to)) {
            throw new DaoException(Validator.INVALID_BOUND);
        }
        if (!Validator.isSortCriteriaCorrect(sortCriteria)) {
            throw new DaoException(Validator.INVALID_SORT_CRITERIA);
        }

        Order order;
        if (sortCriteria.getSortDirection() == SortDirection.ASC) {
            order = Order.asc(sortCriteria.getSortColumn().toString());
        } else {
            order = Order.desc(sortCriteria.getSortColumn().toString());
        }

        StringBuilder stringBuilder = new StringBuilder("%").append(search).append("%");
        Criterion computerName = Restrictions.like("computer.name", stringBuilder.toString());
        Criterion companyName = Restrictions.like("company.name", stringBuilder.toString());
        LogicalExpression orExp = Restrictions.or(computerName, companyName);

        return session.createCriteria(Computer.class, "computer")
                .createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
                .addOrder(order)
                .add(orExp)
                .setFirstResult(from.intValue())
                .setMaxResults(to.intValue() - from.intValue())
                .list();
    }

    @Override
    public List<Computer> getByCompanyId(Long id) {
        LOGGER.info("Computer getByCompanyId {}", id);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isIdCorrect(id)) {
            throw new DaoException(Validator.INVALID_COMPANY_ID);
        }
        return session.createCriteria(Computer.class)
                .add(Restrictions.like("company.id", id))
                .list();
    }

    @Override
    public Long getByNameNumberOfElement(String search) {
        LOGGER.info("Computer getByName : {}", search);
        Session session = sessionFactory.getCurrentSession();
        if (!Validator.isStringForSearchCorrect(search)) {
            throw new DaoException(Validator.INVALID_STRING_FOR_SEARCH);
        }

        StringBuilder stringBuilder = new StringBuilder("%").append(search).append("%");
        Criterion computerName = Restrictions.like("computer.name", stringBuilder.toString());
        Criterion companyName = Restrictions.like("company.name", stringBuilder.toString());
        LogicalExpression orExp = Restrictions.or(computerName, companyName);

        return (Long) session.createCriteria(Computer.class, "computer")
                .createCriteria("company", "company", JoinType.LEFT_OUTER_JOIN)
                .add(orExp)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
