package com.excilys.computerDataBase.mapper.impl;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.excilys.computerDataBase.exception.ParsingException;
import com.excilys.computerDataBase.mapper.MapperInterface;
import com.excilys.computerDataBase.model.Company;
import com.excilys.computerDataBase.model.Computer;

/**
 * The Enum ComputerMapper.
 */
public enum DaoMapper implements MapperInterface<Computer>{
	
	/** The instance. */
	INSTANCE;
	
	/** The Constant COMPA_NAME. */
	private static final String COMPA_NAME = "compa.name";

	/** The Constant PARAM_ID. */
	private static final String PARAM_ID = "id";

	/** The Constant PARAM_NAME. */
	private static final String PARAM_NAME = "name";

	/** The Constant PARAM_INTRODUCED. */
	private static final String PARAM_INTRODUCED = "introduced";

	/** The Constant PARAM_DISCONTINUED. */
	private static final String PARAM_DISCONTINUED = "discontinued";

	/** The Constant PARAM_COMPANY_ID. */
	private static final String PARAM_COMPANY_ID = "company_id";
	
	/* (non-Javadoc)
	 * @see com.excilys.computerDataBase.mapper.MapperInterface#map(java.sql.ResultSet)
	 */
	@Override
	public Computer map(ResultSet resultSet) {
		try {
			Long id = resultSet.getLong(PARAM_ID);
			String name = resultSet.getString(PARAM_NAME);
			Timestamp t1 = resultSet.getTimestamp(PARAM_INTRODUCED);
			LocalDateTime introduced = null;
			if (t1 != null) {
				introduced = t1.toLocalDateTime();
			}
			
			Timestamp t2 = resultSet.getTimestamp(PARAM_DISCONTINUED);
			LocalDateTime discontinued = null;
			if (t2 != null) {
				discontinued = t2.toLocalDateTime();
			}
			
			Long companyId = resultSet.getLong(PARAM_COMPANY_ID);
			String companyName = resultSet.getString(COMPA_NAME);
			Company company = new Company(companyId, companyName);
			
			return new Computer(id, name, introduced, discontinued,	company);
		} catch (Exception e) {
			throw new ParsingException(ParsingException.CAN_NOT_MAP_RESULT_SET);
		}
	}

}
