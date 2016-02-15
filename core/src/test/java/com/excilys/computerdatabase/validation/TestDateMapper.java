package com.excilys.computerdatabase.validation;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.excilys.computerdatabase.exception.ParsingException;
import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.util.DateFormat;

public class TestDateMapper {
	@Test
	public void testExtractEnglishOk() {
		LocalDateTime localDateTime = DateMapper.extractFromString("2000-10-10", DateFormat.ENGLISH);
		assertEquals(localDateTime.getSecond(), 0);
		assertEquals(localDateTime.getMinute(), 0);
		assertEquals(localDateTime.getHour(), 0);
		assertEquals(localDateTime.getDayOfMonth(), 10);
		assertEquals(localDateTime.getMonthValue(), 10);
		assertEquals(localDateTime.getYear(), 2000);
	}

	@Test
	public void testExtractFrenchOk() {
		LocalDateTime localDateTime = DateMapper.extractFromString("10/10/2000", DateFormat.FRENCH);
		assertEquals(localDateTime.getSecond(), 0);
		assertEquals(localDateTime.getMinute(), 0);
		assertEquals(localDateTime.getHour(), 0);
		assertEquals(localDateTime.getDayOfMonth(), 10);
		assertEquals(localDateTime.getMonthValue(), 10);
		assertEquals(localDateTime.getYear(), 2000);
	}

	@Test
	public void testExtractNull() {
		LocalDateTime localDateTime = DateMapper.extractFromString(null, DateFormat.ENGLISH);
		assertEquals(localDateTime, null);
	}

	@Test(expected = ParsingException.class)
	public void testExtractWrong() {
		DateMapper.extractFromString("azerty", DateFormat.ENGLISH);
	}

	@Test(expected = ParsingException.class)
	public void testExtractWrongFormat() {
		DateMapper.extractFromString("10/10/2000 10:10:10", DateFormat.ENGLISH);
	}
}
