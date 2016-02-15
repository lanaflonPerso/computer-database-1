package com.excilys.computerdatabase.webapp.integration.dashboard;

import com.excilys.computerdatabase.webapp.integration.AbstractWebTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

/**
 * @author Vincent Galloy
 */
public class TestDashboardFirefox extends AbstractWebTest {

    @Before
    public void init() {
        driver = new FirefoxDriver();
        login(driver);
    }

    @Test
    public void testDeleteElement() {
        Long numberOfElement = getComputerNumber(driver);
        driver.findElement(By.id("editComputer")).click();
        driver.findElement(By.id("selected_3")).click();
        driver.findElement(By.id("deleteSelected")).click();

        driver.switchTo().alert().accept();

        Long numberOfElement2 = getComputerNumber(driver);
        assertEquals(numberOfElement, new Long(numberOfElement2 + 1));
    }
}
