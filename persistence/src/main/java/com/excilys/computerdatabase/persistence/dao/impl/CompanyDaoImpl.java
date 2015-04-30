/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.persistence.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.dao.CompanyDao;
import com.excilys.computerdatabase.persistence.exception.DaoException;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;

@Repository
@SuppressWarnings("unchecked")
public class CompanyDaoImpl implements CompanyDao {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SessionFactory sf;

	@Override
	public void create(Company t) {
		Session session = sf.getCurrentSession();
		if (!Validator.isCompanyCorrect(t)) {
			log.warn(Validator.INVALID_COMPANY);
			throw new DaoException(Validator.INVALID_COMPANY);
		}
		Long id = (Long) session.save(t);
		t.setId(id);
	}

	@Override
	public List<Company> getAll(SortCriteria sortCriteria) {
		Session session = sf.getCurrentSession();
		return session.createCriteria(Company.class).addOrder(Order.asc("name")).list();
	}

	@Override
	public List<Company> getAll(Long from, Long to, SortCriteria sortCriteria) {
		Session session = sf.getCurrentSession();
		if (!Validator.isDateFromToCorrect(from, to)) {
			log.warn(Validator.INVALID_BOUND);
			throw new DaoException(Validator.INVALID_BOUND);
		}
		return session.createCriteria(Company.class)
				.setFirstResult(from.intValue())
				.setMaxResults(to.intValue() - from.intValue())
				.list();
	}

	@Override
	public Long getNumberOfElement() {
		Session session = sf.getCurrentSession();
		return (Long) session.createCriteria(Company.class)
				.setProjection(Projections.rowCount())
				.list()
				.get(0);
	}

	@Override
	public void delete(Long id) {
		Session session = sf.getCurrentSession();
		if (!Validator.isIdCorrect(id)) {
			log.warn(Validator.INVALID_COMPANY_ID);
			throw new DaoException(Validator.INVALID_COMPANY_ID);
		}
		Company company = (Company) session.get(Company.class, id);
		if(company == null) {
			return;
		}
		session.delete(company);
	}

	@Override
	public Company getById(Long id) {
		Session session = sf.getCurrentSession();
		if (!Validator.isIdCorrect(id)) {
			log.warn(Validator.INVALID_COMPANY_ID);
			throw new DaoException(Validator.INVALID_COMPANY_ID);
		}
		return (Company) session.get(Company.class, id);
	}

}
