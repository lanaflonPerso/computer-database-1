package com.excilys.computerDataBase.test.unitaire;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.excilys.computerDataBase.test.unitaire.dao.TestCompanyDao;
import com.excilys.computerDataBase.test.unitaire.dao.TestComputerDao;
import com.excilys.computerDataBase.test.unitaire.mapper.TestCompanyMapper;
import com.excilys.computerDataBase.test.unitaire.mapper.TestComputerMapper;
import com.excilys.computerDataBase.test.unitaire.service.TestCompanyService;
import com.excilys.computerDataBase.test.unitaire.service.TestComputerService;

@RunWith(Suite.class)
@SuiteClasses({ TestCompanyDao.class, TestComputerDao.class,
		TestComputerService.class, TestValidator.class,
		TestCompanyService.class, TestComputerMapper.class,
		TestCompanyMapper.class })
public class AllUnitTests {

}
