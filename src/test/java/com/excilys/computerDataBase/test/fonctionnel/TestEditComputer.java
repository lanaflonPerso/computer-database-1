package com.excilys.computerDataBase.test.fonctionnel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TestEditComputer {
	private HtmlUnitDriver driver;

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
	public void testEditElement() {
		driver.findElement(By.id("name_1")).click();
		assertThat(driver.getCurrentUrl(), is("http://localhost:8080/computer-database/editComputer?computerId=104"));
		
		enterComputer(driver, "editComputerTest", "", "2014-03-10 10:09:08", "Nokia");
		
		driver.findElement(By.id("editButton")).click();
		
		assertThat(driver.findElement(By.id("introduced_1")).getText(), is("2014-03-10 10:09:08"));
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
