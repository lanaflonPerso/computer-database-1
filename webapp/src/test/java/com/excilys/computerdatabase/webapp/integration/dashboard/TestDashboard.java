package com.excilys.computerdatabase.webapp.integration.dashboard;

import com.excilys.computerdatabase.webapp.integration.AbstractWebTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Vincent Galloy
 */
public class TestDashboard extends AbstractWebTest {

    @Before
    public void init() {
        driver = new HtmlUnitDriver();
        login(driver);
    }

    @Test
    public void testFirstElement() {
        WebElement query = driver.findElement(By.id("companyName_0"));
        assertNotNull(query);
        assertNotNull(query.getText());

        query = driver.findElement(By.id("name_0"));
        assertNotNull(query);
        assertNotNull(query.getText());
    }

    @Test
    public void testGoToAddElementUrl() {
        driver.findElement(By.id("addComputer")).click();
        assertEquals(BASE_URL + "/computer/view/add", driver.getCurrentUrl());
    }

    @Test
    public void testGoToEditElementUrl() {
        driver.findElement(By.id("name_0")).click();
        assertEquals(driver.getCurrentUrl().contains(BASE_URL + "/computer/view/edit?computerId="), true);
    }
}
