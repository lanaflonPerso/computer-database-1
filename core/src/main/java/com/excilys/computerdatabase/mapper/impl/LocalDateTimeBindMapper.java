package com.excilys.computerdatabase.mapper.impl;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeBindMapper extends XmlAdapter<String, LocalDateTime> {

	@Override
	public LocalDateTime unmarshal(String date) throws Exception {
		return DateMapper.exctractFromString(date);
	}

	@Override
	public String marshal(LocalDateTime localDateTime) throws Exception {
		return DateMapper.convertIntoString(localDateTime);
	}
}