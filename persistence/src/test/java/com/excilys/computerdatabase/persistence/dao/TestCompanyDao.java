package com.excilys.computerdatabase.persistence.dao;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.exception.DaoException;
import com.excilys.computerdatabase.sort.SortCriteria;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author Vincent Galloy
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-persistence-context.xml"})
public class TestCompanyDao extends AbstractTestDao {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private DataSource dataSource;

    @Before
    public void setUpDatabase() {
        super.setUpDatabase(dataSource);
    }

    @Test
    public void testCreateCompany() {
        Company company = new Company(0L, "company_test");

        companyDao.create(company);
        Company company2 = companyDao.getById(company.getId());

        assertNotEquals(company.getId(), new Long(0));
        assertEquals(company2, company);
    }

    @Test
    public void testListCompany() {
        List<Company> companies = companyDao.getAll(new SortCriteria());
        assertEquals(companies.get(0), new Company(1L, "Apple Inc."));
        assertEquals(companies.size(), 2);
    }

    @Test
    public void testDeleteOk() {
        Company company = new Company(0L, "company_test");
        companyDao.create(company);
        assertNotEquals(company.getId(), new Long(0));
        companyDao.delete(company.getId());
        Company c = companyDao.getById(company.getId());
        assertNull(c);
    }

    @Test(expected = DaoException.class)
    public void testUpdateWrong() {
        companyDao.update(null);
    }

    @Test
    public void testUpdateOk() {
        Company company = new Company(0L, "name");
        companyDao.create(company);
        assertNotEquals((long) company.getId(), 0L);
        assertEquals(company, companyDao.getById(company.getId()));
        company.setName("name2");
        companyDao.update(company);
        assertEquals(company, companyDao.getById(company.getId()));
    }

    @Test(expected = DaoException.class)
    public void testGetByIdWithNullCompany() {
        assertEquals(companyDao.getById(null), null);
    }

    @Test
    public void testGetByIdOk() {
        Company company = companyDao.getById(2L);
        assertEquals(company.getId(), new Long(2));
    }

    @Test(expected = DaoException.class)
    public void testGetAllFromToWithZero() {
        companyDao.getAll(0L, 0L, new SortCriteria());
    }

    @Test
    public void testGetAllFromTo() {
        List<Company> companies = companyDao.getAll(0L, 1L,
                new SortCriteria());
        assertNotNull(companies);
        assertEquals(companies.size(), 1);
        assertEquals(companies.get(0).getName(), "Apple Inc.");
    }

    @Test(expected = DaoException.class)
    public void testGetAllFromToWrong() {
        companyDao.getAll(null, 1L, new SortCriteria());
    }

    @Test(expected = DaoException.class)
    public void testGetAllFromToWrong2() {
        companyDao.getAll(4L, 1L, new SortCriteria());
    }

    @Test
    public void testGetNumberOfElement() {
        List<Company> companies = companyDao.getAll(new SortCriteria());
        Long total = companyDao.getNumberOfElement();
        assertEquals(total, new Long(companies.size()));
        assertEquals(total, new Long(2));
    }

    @Test
    public void testDeleteElementOk() {
        Company company = new Company(0L, "company_test");
        companyDao.create(company);
        Long total = companyDao.getNumberOfElement();
        companyDao.delete(company.getId());
        assertEquals(new Long(total - 1), companyDao.getNumberOfElement());
    }

    @Test
    public void testDeleteElementWrong() {
        Company company = new Company(0L, "company_test");
        companyDao.create(company);
        Long total = companyDao.getNumberOfElement();
        companyDao.delete(company.getId() + 1);
        assertEquals(total, companyDao.getNumberOfElement());
    }
}
