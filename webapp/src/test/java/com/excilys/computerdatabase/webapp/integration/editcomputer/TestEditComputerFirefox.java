package com.excilys.computerdatabase.webapp.integration.editcomputer;

import com.excilys.computerdatabase.mapper.impl.DateMapper;
import com.excilys.computerdatabase.webapp.integration.AbstractWebTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Vincent Galloy
 */
public class TestEditComputerFirefox extends AbstractWebTest {

    @Before
    public void init() {
        driver = new FirefoxDriver();
        login(driver);
    }

    @Test
    public void testEditCorrectElement() {
        driver.findElement(By.id("name_1")).click();

        deleteComputer(driver);
        String date = DateMapper.convertIntoString(LocalDateTime.now());
        String name = "editComputerTest3";
        enterComputer(driver, name, "", date, "Nokia");

        driver.findElement(By.id("editButton")).click();

        assertEquals(name, driver.findElement(By.id("name_1")).getText());
        assertEquals("", driver.findElement(By.id("introduced_1")).getText());
        assertEquals(date, driver.findElement(By.id("discontinued_1")).getText());

    }

    @Test
    public void testEditElementWithWrongName() {
        driver.findElement(By.id("name_1")).click();

        deleteComputer(driver);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        enterComputer(driver, "", "", date, "Nokia");

        driver.findElement(By.id("editButton")).click();

        assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/webapp/computer/view/edit"));
    }
}
