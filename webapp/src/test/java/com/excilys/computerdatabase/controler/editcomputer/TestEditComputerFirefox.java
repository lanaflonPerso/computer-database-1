/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.editcomputer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.excilys.computerdatabase.controler.AbstractWebTest;

public class TestEditComputerFirefox extends AbstractWebTest {
	private WebDriver driver;

	@Before
	public void init() {
		driver = new FirefoxDriver();
		login(driver);
	}

	@After
	public void close() {
		driver.close();
	}

	@Test
	public void testEditCorrectElement() throws Exception {
		driver.findElement(By.id("name_1")).click();

		deleteComputer(driver);
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String name = "editComputerTest3";
		enterComputer(driver, name, "", date, "Nokia");

		driver.findElement(By.id("editButton")).click();

		assertEquals(name, driver.findElement(By.id("name_1")).getText());
		assertEquals("", driver.findElement(By.id("introduced_1")).getText());
		assertEquals(date, driver.findElement(By.id("discontinued_1")).getText());

	}

	@Test
	public void testEditElementWithWrongName() throws Exception {
		driver.findElement(By.id("name_1")).click();

		deleteComputer(driver);
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		enterComputer(driver, "", "", date, "Nokia");

		driver.findElement(By.id("editButton")).click();

		assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/webapp/computer/view/edit"));
	}

}
