package com.excilys.computerdatabase.test.fonctionnel.dashboard;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDashboardFirefox {

	private WebDriver driver;

	@Before
	public void init() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/computer-database/dashboard");
	}

	@After
	public void close() {
		driver.close();
	}
	
	@Test
	public void testdeleteElement() {
		Long numberofElement = getComputerNumber(driver);
		driver.findElement(By.id("editComputer")).click();
		driver.findElement(By.id("selected_3")).click();
		driver.findElement(By.id("deleteSelected")).click();

		driver.switchTo().alert().accept();

		Long numberofElement2 = getComputerNumber(driver);
		assertEquals(numberofElement, new Long(numberofElement2 + 1));
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
