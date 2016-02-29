package com.excilys.computerdatabase.webapp.integration.addcomputer;

import com.excilys.computerdatabase.webapp.integration.AbstractWebTest;
import com.excilys.computerdatabase.mapper.impl.DateMapper;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Vincent Galloy
 */
public class TestAddComputer extends AbstractWebTest {

    @Before
    public void init() {
        driver = new HtmlUnitDriver();
        login(driver);
    }

    @Test
    public void testAddWrongElement() {
        Long computerFoundNumber1 = getComputerNumber(driver);
        assertNotNull(computerFoundNumber1);

        driver.findElement(By.id("addComputer")).click();
        enterComputer(driver, "name", "wrongDate", "WrongDate", "Apple Inc.");
        driver.findElement(By.id("addButton")).click();
        assertEquals(driver.getCurrentUrl(), BASE_URL + "/computer/view/add");
    }

    @Test
    public void testAddRightElement() {
        Long computerFoundNumber1 = getComputerNumber(driver);
        assertEquals(computerFoundNumber1 != null, true);

        driver.findElement(By.id("addComputer")).click();
        enterComputer(driver, "name", DateMapper.convertIntoString(LocalDateTime.now()), DateMapper.convertIntoString(LocalDateTime.now()), "Apple Inc.");
        driver.findElement(By.id("addButton")).click();

        Long computerFoundNumber2 = getComputerNumber(driver);
        assertNotNull(computerFoundNumber2);
        assertEquals(computerFoundNumber1, new Long(computerFoundNumber2 - 1));
    }
}
