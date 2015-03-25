package com.excilys.computerDataBase.test.fonctionnel;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestEditComputer {
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
	public void testEditElement() {
		driver.get("http://localhost:8080/computer-database/editComputer?computerId=104");
	
		
		deleteComputer(driver);
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String name = "editComputerTest3";
		enterComputer(driver, name, "", date, "Nokia");
		
		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		driver.findElement(By.id("editButton")).click();
		
		assertEquals(name, driver.findElement(By.id("name_1")).getText());
		assertEquals("", driver.findElement(By.id("introduced_1")).getText());
		assertEquals(date, driver.findElement(By.id("discontinued_1")).getText());	

	}

	
	private void deleteComputer(WebDriver driver2) {
		WebElement webElement = null;
		webElement = driver.findElement(By.id("computerName"));
		webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		webElement.sendKeys(Keys.DELETE);
		webElement = driver.findElement(By.id("introduced"));
		webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		webElement.sendKeys(Keys.DELETE);
		webElement = driver.findElement(By.id("discontinued"));
		webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		webElement.sendKeys(Keys.DELETE);
		
	}

	private void enterComputer(WebDriver driver2, String name,
			String introduced, String discontinued, String companyName) {
		WebElement webElement = null;
		webElement = driver.findElement(By.id("computerName"));
		webElement.sendKeys(name);
		webElement = driver.findElement(By.id("introduced"));
		webElement.sendKeys(introduced);
		webElement = driver.findElement(By.id("discontinued"));
		webElement.sendKeys(discontinued);
		
		webElement = driver.findElement(By.id("companyId"));
		List<WebElement> webElements = webElement.findElements(By.tagName("option"));
		for(WebElement webElement2 : webElements) {
			if( companyName != null ) {
				if (companyName.equals(webElement2.getText())){
					webElement2.click();
				}
			}
		}

	}

}
