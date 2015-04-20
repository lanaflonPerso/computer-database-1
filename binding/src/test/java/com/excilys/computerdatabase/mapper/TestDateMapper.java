package com.excilys.computerdatabase.mapper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.excilys.computerdatabase.dto.mapper.impl.DateMapper;
import com.excilys.computerdatabase.exception.ParsingException;
import com.excilys.computerdatabase.util.DateFormat;

public class TestDateMapper {
	@Test
	public void testExtractEnglishOk(){
		LocalDateTime localDateTime = DateMapper.exctractFromString("2000-10-10 10:10:10", DateFormat.ENGLISH);
		assertEquals(localDateTime.getSecond(), 10);
		assertEquals(localDateTime.getMinute(), 10);
		assertEquals(localDateTime.getHour(), 10);
		assertEquals(localDateTime.getDayOfMonth(), 10);
		assertEquals(localDateTime.getMonthValue(), 10);
		assertEquals(localDateTime.getYear(), 2000);
	}
	
	@Test
	public void testExtractFrenchOk(){
		LocalDateTime localDateTime = DateMapper.exctractFromString("10/10/2000 10:10:10", DateFormat.FRENCH);
		assertEquals(localDateTime.getSecond(), 10);
		assertEquals(localDateTime.getMinute(), 10);
		assertEquals(localDateTime.getHour(), 10);
		assertEquals(localDateTime.getDayOfMonth(), 10);
		assertEquals(localDateTime.getMonthValue(), 10);
		assertEquals(localDateTime.getYear(), 2000);
	}
	
	@Test
	public void testExtractNull(){
		LocalDateTime localDateTime = DateMapper.exctractFromString(null, DateFormat.ENGLISH);
		assertEquals(localDateTime, null);
	}
	
	@Test(expected=ParsingException.class)
	public void testExtractWrong(){
		DateMapper.exctractFromString("azerty", DateFormat.ENGLISH);
	}
	
	@Test(expected=ParsingException.class)
	public void testExtractWrongFormat(){
		DateMapper.exctractFromString("10/10/2000 10:10:10", DateFormat.ENGLISH);
	}
}
