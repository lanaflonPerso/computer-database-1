package com.excilys.computerdatabase.service.services.impl;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.dao.ComputerDao;
import com.excilys.computerdatabase.service.exception.ServiceException;
import com.excilys.computerdatabase.sort.SortCriteria;
import com.excilys.computerdatabase.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Vincent Galloy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-service-application-context.xml"})
public class TestComputerService {
    private static final Long COMPUTER_ID = 45L;
    private static final Long NUMBER_OF_ELEMENT = 101L;
    private final Computer computer1 = new Computer(1L, "myName", LocalDateTime.now(), LocalDateTime.now(), new Company(3L, "myCompany"));
    @Autowired
    private ComputerDao computerDao2;

    private ComputerServiceImpl computerServiceImpl;
    private ComputerDao computerDao = Mockito.mock(ComputerDao.class);

    @Before
    public void setUp() {
        computerServiceImpl = new ComputerServiceImpl();
        computerServiceImpl.setComputerDao(computerDao);
        Mockito.when(computerDao.getById(COMPUTER_ID)).thenReturn(computer1);
        List<Computer> computers = new ArrayList<>();
        computers.add(computer1);
        Mockito.when(computerDao.getAll(Mockito.any())).thenReturn(computers);
        Mockito.when(computerDao.getNumberOfElement()).thenReturn(NUMBER_OF_ELEMENT);
        Mockito.doThrow(new RuntimeException()).when(computerDao).create(computer1);
        computerServiceImpl.setComputerDao(computerDao);
    }

    @After
    public void setDown() {
        computerServiceImpl.setComputerDao(computerDao2);
    }

    @Test
    public void testGetByIdOk() {
        assertEquals(computerServiceImpl.getById(COMPUTER_ID).getId(), new Long(1));
    }

    @Test
    public void testGetByIdWrong() {
        try {
            computerServiceImpl.getById(0L);
            fail("no exception occurred");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), Validator.INVALID_COMPUTER_ID);
        }
    }

    @Test
    public void testAll() {
        List<Computer> computers = computerServiceImpl.list(new SortCriteria());
        assertEquals(computers.size(), 1);
        assertEquals(computers.get(0), computer1);
    }

    @Test(expected = RuntimeException.class)
    public void testCreateOk() {
        computerServiceImpl.create(computer1);
    }

    @Test
    public void testCreateWrongComputer() {
        try {
            computerServiceImpl.create(new Computer());
            fail("no exception occurred");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
        }
    }

    @Test
    public void testCreateNameWithSpace1() {
        try {
            Computer computer = new Computer(1L, "   ", LocalDateTime.now(), LocalDateTime.now(), new Company(15L, "Canon"));
            computerServiceImpl.create(computer);
            fail("no exception occurred");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
        }
    }

    @Test
    public void testUpdateOk() {
        computerServiceImpl.update(computer1);
    }

    @Test
    public void testUpdateWrongComputer() {
        Computer computer = new Computer(0L, "myName", LocalDateTime.now(), LocalDateTime.now(), null);
        try {
            computerServiceImpl.create(computer);
            fail("no exception occurred");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
        }
    }

    @Test
    public void testDeleteOk() {
        computerServiceImpl.delete(computer1.getId());
    }

    @Test
    public void testDeleteWrongComputer() {
        try {
            computerServiceImpl.delete((long) -1);
            fail("no exception occurred");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), Validator.INVALID_COMPUTER);
        }
    }

    @Test
    public void testNumberOfElementOk() {
        Long numberForElement = computerServiceImpl.getNumberOfElement();
        assertEquals(numberForElement, NUMBER_OF_ELEMENT);
    }

    @Test
    public void testListFromToOk() {
        computerServiceImpl.list(0L, 1L, new SortCriteria());
    }

    @Test
    public void testListFromToWrong() {
        try {
            computerServiceImpl.list(0L, null, new SortCriteria());
            fail("no exception occurred");
        } catch (ServiceException e) {
            assertEquals(e.getMessage(), Validator.INVALID_BOUND);
        }
    }
}
