package com.excilys.computerDataBase.test.unitaire;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;

import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;
import com.excilys.computerDataBase.validation.Validator;

public class TestValidator {
	@Test
	public void testDateValidatorCorrectDate() {
		String stringDate = "2100-10-12 10:10:12";
		assertThat(Validator.INSTANCE.validateDate(stringDate), is(true));
	}

	@Test
	public void testDateValidator() {
		String stringDate = "null";
		assertThat(Validator.INSTANCE.validateDate(stringDate), is(false));
	}

	@Test
	public void testDateValidator2() {
		String stringDate = null;
		assertThat(Validator.INSTANCE.validateDate(stringDate), is(false));
	}

	@Test
	public void testDateValidator3() {
		String stringDate = "2100-34-12 35:45:12";
		assertThat(Validator.INSTANCE.validateDate(stringDate), is(false));
	}

	/*
	 * Test computer validation
	 */

	@Test
	public void testComputerCorrectComputer() {
		Computer computer = new Computer(null, "name", null, null, new Company(
				new Long(1), "company_name"));
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(true));
	}

	@Test
	public void testComputerNullCompanyId() {
		Computer computer = new Computer(null, "name", null, null, new Company(
				null, "company_name"));
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(true));
	}

	@Test
	public void testComputerNullCompanyName() {
		Computer computer = new Computer(null, "name", null, null, new Company(
				null, null));
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(true));
	}

	@Test
	public void testComputerNullAttribute() {
		Computer computer = new Computer();
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(false));
	}

	@Test
	public void testComputerNullComputer() {
		Computer computer = null;
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(false));
	}

	@Test
	public void testComputerNullCompany() {
		Computer computer = new Computer(null, "name", null, null, null);
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(false));
	}

	@Test
	public void testComputerNullName() {
		Computer computer = new Computer(null, null, LocalDateTime.now(),
				LocalDateTime.now(), new Company(new Long(1), "company_name"));
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(false));
	}

	@Test
	public void testComputerEmptyName() {
		Computer computer = new Computer(null, "", null, null, null);
		assertThat(Validator.INSTANCE.checkComputer(computer),
				is(false));
	}
	
	/*
	 * Test computer Id
	 */

	@Test
	public void testComputerIdOk() {
		assertThat(Validator.INSTANCE.checkComputerId(new Long(4)),
				is(true));
	}
	
	@Test
	public void testComputerIdNull() {
		assertThat(Validator.INSTANCE.checkComputerId(null),
				is(false));
	}
	
	@Test
	public void testComputerIdNegtif() {
		assertThat(Validator.INSTANCE.checkComputerId(new Long(-4)),
				is(false));
	}
	
	@Test
	public void testComputerIdZero() {
		assertThat(Validator.INSTANCE.checkComputerId(new Long(0)),
				is(false));
	}
	
	/*
	 * Test from to
	 */
	
	@Test
	public void checkComputerFromToOk() {
		assertThat(Validator.INSTANCE.checkFromTo(new Long(1), new Long(2)),
		is(true));
	}
	
	@Test
	public void checkComputerFromToNegatif1() {
		assertThat(Validator.INSTANCE.checkFromTo(new Long(-1), new Long(1)),
		is(false));
	}
	
	@Test
	public void checkComputerFromToNegatif2() {
		assertThat(Validator.INSTANCE.checkFromTo(new Long(1), new Long(-1)),
		is(false));
	}
	
	@Test
	public void checkComputerFromToNull1() {
		assertThat(Validator.INSTANCE.checkFromTo(new Long(0), null),
		is(false));
	}
	
	@Test
	public void checkComputerFromToNull2() {
		assertThat(Validator.INSTANCE.checkFromTo(null, new Long(0)),
		is(false));
	}
	
	@Test
	public void checkComputerFromToNotSorted() {
		assertThat(Validator.INSTANCE.checkFromTo(new Long(4), new Long(3)),
		is(false));
	}
}
