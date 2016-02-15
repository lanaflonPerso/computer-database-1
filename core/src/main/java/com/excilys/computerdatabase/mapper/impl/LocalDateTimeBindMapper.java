package com.excilys.computerdatabase.mapper.impl;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

/**
 * @author Vincent Galloy
 */
public class LocalDateTimeBindMapper extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String date) throws Exception {
        return DateMapper.extractFromString(date);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return DateMapper.convertIntoString(localDateTime);
    }
}