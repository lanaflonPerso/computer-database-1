package com.excilys.computerDataBase.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;

import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.entity.Computer;
import com.excilys.computerDataBase.exception.UnableToInsertElementException;

public class TestComputerDao {

	ComputerDao computerDao = ComputerDao.INSTANCE;

	@Test
	public void testInsert() {
		try {
			Computer computer = new Computer(new Long(1), "test_name",
					new Timestamp(1234000l), new Timestamp(1234000l), new Long(15));
			computerDao.create(computer);
			assertThat(computer.getId() == 1, is(false));

			Computer computer2 = computerDao.get(computer.getId());
			assertThat(computer, is(computer2));
		} catch (Exception e) {
			e.printStackTrace();
			fail("Insert or get Exception");
		}	
	}
	
	@Test
	public void testInsertWrongTimestamp() {
		try {
			Computer computer = new Computer(new Long(1), "test_name",
					new Timestamp(-5), new Timestamp(1234000l), new Long(15));
			computerDao.create(computer);
		} catch (UnableToInsertElementException e) {
			assertThat(e.getMessage(), is(UnableToInsertElementException.CAN_NOT_INSERT));
		} catch (Exception e) {
			e.printStackTrace();
			fail("unexpected error");
		}
	}
	
	@Test
	public void testInsertComputerWithNullAttributs() {
		try {
			Computer computer = new Computer();
			computerDao.create(computer);
		} catch (UnableToInsertElementException e) {
			assertThat(e.getMessage(), is(UnableToInsertElementException.NULL_COMPANY_ID));
		} catch (Exception e) {
			e.printStackTrace();
			fail("unexpected error");
		}
	}
	
	@Test
	public void testInsertNullComputer() {
		try {
			Computer computer = null;
			computerDao.create(computer);
		} catch (UnableToInsertElementException e) {
			assertThat(e.getMessage(), is(UnableToInsertElementException.NULL_COMPUTER));
		} catch (Exception e) {
			e.printStackTrace();
			fail("unexpected error");
		}
	}
	
	@Test
	public void testListComputer() {
		try {
			List<Computer> computers = computerDao.selectAll();
			assertThat(computers.get(0), is(new Computer(1l, "MacBook Pro 15.4 inch", null, null, 1l)));
		} catch (Exception e) {
			fail("List Computer Exception");
		}	
	}

}
