package com.excilys.computerdatabase.test.unitaire;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.excilys.computerdatabase.test.unitaire.dao.TestCompanyDao;
import com.excilys.computerdatabase.test.unitaire.dao.TestComputerDao;
import com.excilys.computerdatabase.test.unitaire.mapper.TestCompanyMapper;
import com.excilys.computerdatabase.test.unitaire.mapper.TestComputerMapper;
import com.excilys.computerdatabase.test.unitaire.mapper.TestDateMapper;
import com.excilys.computerdatabase.test.unitaire.service.TestCompanyService;
import com.excilys.computerdatabase.test.unitaire.service.TestComputerService;
import com.excilys.computerdatabase.test.unitaire.validator.TestValidator;

@RunWith(Suite.class)
@SuiteClasses({ TestCompanyDao.class, TestComputerDao.class,
		TestComputerService.class, TestValidator.class,
		TestCompanyService.class, TestComputerMapper.class,
		TestCompanyMapper.class, TestDateMapper.class })
public class AllUnitTests {

}
