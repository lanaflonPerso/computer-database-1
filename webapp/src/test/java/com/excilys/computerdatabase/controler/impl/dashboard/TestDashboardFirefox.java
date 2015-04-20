package com.excilys.computerdatabase.controler.impl.dashboard;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.excilys.computerdatabase.controler.impl.AbstractWebTest;

public class TestDashboardFirefox extends AbstractWebTest {

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
