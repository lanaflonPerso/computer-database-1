package com.excilys.computerdatabase.validation;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.validation.Validator;

public class TestValidator {
	@Test
	public void testDateValidatorCorrectDate() {
		String stringDate = "2100-10-12";
		assertEquals(Validator.isDateValid(stringDate), true);
	}

	@Test
	public void testDateValidator() {
		String stringDate = "null";
		assertEquals(Validator.isDateValid(stringDate), false);
	}

	@Test
	public void testDateValidator2() {
		String stringDate = null;
		assertEquals(Validator.isDateValid(stringDate), false);
	}

	@Test
	public void testDateValidator3() {
		String stringDate = "2100-34-12 35:45:12";
		assertEquals(Validator.isDateValid(stringDate), false);
	}

	/*
	 * Test computer validation
	 */

	@Test
	public void testComputerCorrectComputer() {
		Computer computer = new Computer(null, "name", null, null, new Company(1L, "company_name"));
		assertEquals(Validator.isComputerCorrect(computer), true);
	}

	@Test
	public void testComputerNullCompanyId() {
		Computer computer = new Computer(null, "name", null, null, new Company(null, "company_name"));
		assertEquals(Validator.isComputerCorrect(computer), false);
	}

	@Test
	public void testComputerNullCompanyName() {
		Computer computer = new Computer(null, "name", null, null, new Company(null, null));
		assertEquals(Validator.isComputerCorrect(computer), false);
	}

	@Test
	public void testComputerNullAttribute() {
		Computer computer = new Computer();
		assertEquals(Validator.isComputerCorrect(computer), false);
	}

	@Test
	public void testComputerNullComputer() {
		Computer computer = null;
		assertEquals(Validator.isComputerCorrect(computer), false);
	}

	@Test
	public void testComputerNullCompany() {
		Computer computer = new Computer(null, "name", null, null, null);
		assertEquals(Validator.isComputerCorrect(computer), true);
	}

	@Test
	public void testComputerNullName() {
		Computer computer = new Computer(null, null, LocalDateTime.now(), LocalDateTime.now(), new Company(1L, "company_name"));
		assertEquals(Validator.isComputerCorrect(computer), false);
	}

	@Test
	public void testComputerEmptyName() {
		Computer computer = new Computer(null, "", null, null, null);
		assertEquals(Validator.isComputerCorrect(computer), false);
	}

	/*
	 * Test computer Id
	 */

	@Test
	public void testComputerIdOk() {
		assertEquals(Validator.isIdCorrect(4L), true);
	}

	@Test
	public void testComputerIdNull() {
		assertEquals(Validator.isIdCorrect(null), false);
	}

	@Test
	public void testComputerIdNegatif() {
		assertEquals(Validator.isIdCorrect((long) -4), false);
	}

	@Test
	public void testComputerIdZero() {
		assertEquals(Validator.isIdCorrect(0L), false);
	}

	/*
	 * Test from to
	 */

	@Test
	public void checkComputerFromToOk() {
		assertEquals(Validator.isDateFromToCorrect(1L, 2L), true);
	}

	@Test
	public void checkComputerFromToNegatif1() {
		assertEquals(Validator.isDateFromToCorrect((long) -1, 1L), false);
	}

	@Test
	public void checkComputerFromToNegatif2() {
		assertEquals(Validator.isDateFromToCorrect(1L, (long) -1), false);
	}

	@Test
	public void checkComputerFromToNull1() {
		assertEquals(Validator.isDateFromToCorrect(0L, null), false);
	}

	@Test
	public void checkComputerFromToNull2() {
		assertEquals(Validator.isDateFromToCorrect(null, 0L), false);
	}

	@Test
	public void checkComputerFromToNotSorted() {
		assertEquals(Validator.isDateFromToCorrect(4L, 3L), false);
	}

	/*
	 * Test name for search
	 */

	@Test
	public void checkNameForSearchCorrect() {
		assertEquals(Validator.isStringForSearchCorrect("test"), true);
	}

	@Test
	public void checkNameForSearchWrong() {
		assertEquals(Validator.isStringForSearchCorrect("   "), false);
	}

	@Test
	public void checkNameForSearchNull() {
		assertEquals(Validator.isStringForSearchCorrect(null), false);
	}
}
