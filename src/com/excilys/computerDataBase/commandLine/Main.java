package com.excilys.computerDataBase.commandLine;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.excilys.computerDataBase.dao.impl.ComputerDao;
import com.excilys.computerDataBase.entity.Computer;

public class Main {
	public static void main(String ... args) {

		
		ComputerDao computerDao = ComputerDao.getInstance();
		try {
			computerDao.openConnection("computer-database-db", "root", "root");
			List<Computer> computers = computerDao.selectAll();
			for (Computer computer : computers) {
				System.out.println(computer.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("get" + computerDao.get(570));
		System.out.println("crfte " + computerDao.create(new Computer(new Long(571), "nom", new Timestamp(19000), new Timestamp(200000), new Long(3))));
		computerDao.delete(new Computer(new Long(580), "nom", new Timestamp(19000), new Timestamp(200000), new Long(3)));
	}
}
