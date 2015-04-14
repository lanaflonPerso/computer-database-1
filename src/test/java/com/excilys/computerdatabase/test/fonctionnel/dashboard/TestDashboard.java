/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.test.fonctionnel.dashboard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TestDashboard {
	private WebDriver driver;

	@Before
	public void init() {
		driver = new HtmlUnitDriver();
		driver.get("http://localhost:8080/computer-database/dashboard");
	}
	
	@After
	public void close() {
		driver.close();
	}

	@Test
	public void testFirstElement() {
		WebElement query = driver.findElement(By.id("companyName_0"));
		assertNotNull(query);
		assertEquals("Apple Inc.", query.getText());

		query = driver.findElement(By.id("name_0"));
		assertNotNull(query);
		assertEquals("MacBook Pro 15.4 inch", query.getText());
	}
	
	@Test
	public void testGoToAddElementUrl() {
		driver.findElement(By.id("addComputer")).click();
		assertEquals("http://localhost:8080/computer-database/addComputer", driver.getCurrentUrl());
	}
	
	@Test
	public void testGoToEditElementUrl() {
		driver.findElement(By.id("name_0")).click();
		assertEquals(driver.getCurrentUrl().contains("http://localhost:8080/computer-database/editComputer?computerId="), true);
	}
	
}
