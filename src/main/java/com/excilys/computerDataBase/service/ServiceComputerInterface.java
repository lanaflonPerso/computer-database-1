package com.excilys.computerDataBase.service;

import java.util.List;

import com.excilys.computerDataBase.model.Computer;

public interface ServiceComputerInterface extends ServiceInterface<Computer> {
	public List<Computer> getNameContains(String string, Long from, Long to);
	public Long getNameContainsElement(String string);
}
