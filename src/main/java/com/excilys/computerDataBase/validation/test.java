package com.excilys.computerDataBase.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

import org.apache.log4j.Logger;

public class test {
	public static void main(String ... args ){
		LocalDateTime l = LocalDateTime.now();
		DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd hh:mm:ss");
	
	System.out.println(l.format(dateTimeFormatterBuilder.toFormatter()));
				
	Logger log = Logger.getLogger(test.class.getName());
	
	
	
	 log.info("Hello this is a debug message");		
		System.out.println(l);
		//"dd-MMM-yy hh:mm:ss
	}
}
