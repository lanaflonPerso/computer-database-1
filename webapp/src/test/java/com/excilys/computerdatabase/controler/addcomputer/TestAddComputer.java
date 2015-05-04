/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.addcomputer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.excilys.computerdatabase.controler.AbstractWebTest;
import com.excilys.computerdatabase.mapper.impl.DateMapper;

public class TestAddComputer extends AbstractWebTest {
	private WebDriver driver;
	
	@Before
	public void init() {
		driver = new HtmlUnitDriver();
		login(driver);
	}
	
	@After
	public void close() {
		driver.close();
	}

	@Test
	public void testAddWrongElement() throws Exception {
		Long computerFoundNumber1 = getComputerNumber(driver);
		assertNotNull(computerFoundNumber1);

		driver.findElement(By.id("addComputer")).click();
		enterComputer(driver, "name", "wrongDate", "WrongDate", "Apple Inc.");
		driver.findElement(By.id("addButton")).click();
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/webapp/computer/view/add");
	}

	@Test
	public void testAddRightElement() throws Exception {
		Long computerFoundNumber1 = getComputerNumber(driver);
		assertEquals(computerFoundNumber1 != null, true);

		driver.findElement(By.id("addComputer")).click();
		enterComputer(driver, "name", DateMapper.convertIntoString(LocalDateTime.now()), DateMapper.convertIntoString(LocalDateTime.now()), "Apple Inc.");
		driver.findElement(By.id("addButton")).click();

		Long computerFoundNumber2 = getComputerNumber(driver);
		assertNotNull(computerFoundNumber2);
		assertEquals(computerFoundNumber1, new Long(computerFoundNumber2 - 1));
	}

	private Long getComputerNumber(WebDriver driver) {
		try {
			WebElement webElement = driver.findElement(By.id("homeTitle"));
			String s = webElement.getText().split(" ")[0];
			return Long.valueOf(s);
		} catch (Exception e) {
			return null;
		}
	}
}
