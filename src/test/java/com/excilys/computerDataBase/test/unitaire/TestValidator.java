/**
 * @Author Vincent Galloy
 */
package com.excilys.computerDataBase.test.unitaire;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.validation.Validator;

public class TestValidator {
	@Test
	public void testDateValidatorCorrectDate() {
		String stringDate = "2100-10-12 10:10:12";
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
		Computer computer = new Computer(null, "name", null, null, new Company(
				new Long(1), "company_name"));
		assertEquals(Validator.isComputerCorrect(computer),	true);
	}

	@Test
	public void testComputerNullCompanyId() {
		Computer computer = new Computer(null, "name", null, null, new Company(
				null, "company_name"));
		assertEquals(Validator.isComputerCorrect(computer), true);
	}

	@Test
	public void testComputerNullCompanyName() {
		Computer computer = new Computer(null, "name", null, null, new Company(
				null, null));
		assertEquals(Validator.isComputerCorrect(computer), true);
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
		assertEquals(Validator.isComputerCorrect(computer), false);
	}

	@Test
	public void testComputerNullName() {
		Computer computer = new Computer(null, null, LocalDateTime.now(),
				LocalDateTime.now(), new Company(new Long(1), "company_name"));
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
		assertEquals(Validator.isIdCorrect(new Long(4)), true);
	}
	
	@Test
	public void testComputerIdNull() {
		assertEquals(Validator.isIdCorrect(null), false);
	}
	
	@Test
	public void testComputerIdNegtif() {
		assertEquals(Validator.isIdCorrect(new Long(-4)), false);
	}
	
	@Test
	public void testComputerIdZero() {
		assertEquals(Validator.isIdCorrect(new Long(0)), false);
	}
	
	/*
	 * Test from to
	 */
	
	@Test
	public void checkComputerFromToOk() {
		assertEquals(Validator.isDateFromToCorrect(new Long(1), new Long(2)), true);
	}
	
	@Test
	public void checkComputerFromToNegatif1() {
		assertEquals(Validator.isDateFromToCorrect(new Long(-1), new Long(1)), false);
	}
	
	@Test
	public void checkComputerFromToNegatif2() {
		assertEquals(Validator.isDateFromToCorrect(new Long(1), new Long(-1)), false);
	}
	
	@Test
	public void checkComputerFromToNull1() {
		assertEquals(Validator.isDateFromToCorrect(new Long(0), null), false);
	}
	
	@Test
	public void checkComputerFromToNull2() {
		assertEquals(Validator.isDateFromToCorrect(null, new Long(0)), false);
	}
	
	@Test
	public void checkComputerFromToNotSorted() {
		assertEquals(Validator.isDateFromToCorrect(new Long(4), new Long(3)), false);
	}
	
	/*
	 * Test name for search
	 */
	
	@Test
	public void checkNameforSearchCorrect() {
		assertEquals(Validator.isNameForSearchCorrect("test"), true);
	}
	
	@Test
	public void checkNameforSearchWrong() {
		assertEquals(Validator.isNameForSearchCorrect("   "), false);
	}
	
	@Test
	public void checkNameforSearchNull() {
		assertEquals(Validator.isNameForSearchCorrect(null), false);
	}
}
