/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.controler.impl.editcomputer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestEditComputerFirefox {
	private WebDriver driver;

	@Before
	public void init() {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/webapp/dashboard?language=en");
	}
	
	@After
	public void close() {
		driver.close();
	}

	@Test
	public void testEditCorrectElement() {
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
	public void testEditElementWithWrongName() {
		driver.findElement(By.id("name_1")).click();
		
		deleteComputer(driver);
		String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		enterComputer(driver, "", "", date, "Nokia");
		
		driver.findElement(By.id("editButton")).click();
		
		assertTrue(driver.getCurrentUrl().contains("http://localhost:8080/webapp/editComputer?computerId="));
	}
	
	@Test
	public void testEditElementWithWrongDate() {
		driver.findElement(By.id("name_1")).click();
	
		
		deleteComputer(driver);
		enterComputer(driver, "nameWrongDate", "", "2000-19-10 10:10:10", "Nokia");
		driver.findElement(By.id("editButton")).click();
		
		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/webapp/editComputer");
		assertEquals(driver.findElement(By.id("serviceDiscontinuedException")).getText(), "Invalid discontinued date : respect yyyy-MM-dd HH:mm:ss");
		try {
			driver.findElement(By.id("serviceNameException"));
			fail("element serviceNameException must not appear in this case");
		} catch (NoSuchElementException ignored) {}
		try {
			driver.findElement(By.id("serviceIntroducedException"));
			fail("element serviceNameException must not appear in this case");
		} catch (NoSuchElementException ignored) {}	
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
