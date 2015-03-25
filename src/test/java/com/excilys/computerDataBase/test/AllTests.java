package com.excilys.computerDataBase.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.excilys.computerDataBase.test.fonctionnel.TestAddComputer;
import com.excilys.computerDataBase.test.fonctionnel.TestDashboard;
import com.excilys.computerDataBase.test.fonctionnel.TestEditComputer;
import com.excilys.computerDataBase.test.unitaire.TestMapper;
import com.excilys.computerDataBase.test.unitaire.TestValidator;
import com.excilys.computerDataBase.test.unitaire.dao.TestCompanyDao;
import com.excilys.computerDataBase.test.unitaire.dao.TestComputerDao;
import com.excilys.computerDataBase.test.unitaire.service.TestCompanyService;
import com.excilys.computerDataBase.test.unitaire.service.TestComputerService;

@RunWith(Suite.class)
@SuiteClasses({ TestCompanyDao.class, TestComputerDao.class,
		TestComputerService.class, TestValidator.class,
		TestCompanyService.class, TestAddComputer.class, TestDashboard.class,
		TestMapper.class, TestEditComputer.class })
public class AllTests {

}
