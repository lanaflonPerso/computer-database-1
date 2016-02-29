package com.excilys.computerdatabase.console;

import com.excilys.computerdatabase.console.exception.ConsoleException;
import com.excilys.computerdatabase.console.service.ConsoleService;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-console-context.xml"})
public class TestConsoleService {
    @Autowired
    private ConsoleService consoleService;

    @Test(expected = ConsoleException.class)
    public void testAddComputerWithNullCompanyId() {
        Computer computer = new Computer(0L, "name-test-2", null, null, new Company());
        consoleService.createComputer(computer);
    }
}
