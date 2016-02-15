package com.excilys.computerdatabase.webapp.integration.editcomputer;

import com.excilys.computerdatabase.webapp.integration.AbstractWebTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertEquals;

public class TestEditComputer extends AbstractWebTest {

    @Before
    public void init() {
        driver = new HtmlUnitDriver();
        login(driver);
    }

    @Test
    public void testEditElementWithWrongDate() {
        driver.findElement(By.id("name_1")).click();

        deleteComputer(driver);
        enterComputer(driver, "nameWrongDate", "", "2000-19-10 10:10:10", "Nokia");
        driver.findElement(By.id("editButton")).click();

        assertEquals(driver.getCurrentUrl(), "http://localhost:8080/webapp/computer/view/edit");
        assertEquals("Invalid discontinued date : respect (1) yyyy-MM-dd (2) After year : 1970 (3) Before year : 2038", driver.findElement(By.id("serviceDiscontinuedException")).getText());
    }
}
