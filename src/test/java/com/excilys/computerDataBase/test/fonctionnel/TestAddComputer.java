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

public class TestAddComputer {
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
	public void testAddWrongElement() {
		Long computerFoundNumber1 = getComputerNumber(driver);
		assertThat(computerFoundNumber1 != null, is(true));

		driver.findElement(By.id("addComputer")).click();
		enterComputer(driver, "name", "wrongDate", "WrongDate", "Apple Inc.");
		driver.findElement(By.id("addButton")).click();

		Long computerFoundNumber2 = getComputerNumber(driver);
		//assertThat(computerFoundNumber2, nullValue());

		assertThat(computerFoundNumber1, is(computerFoundNumber2));
	}

	@Test
	public void testAddRightElement() throws Exception {
		Long computerFoundNumber1 = getComputerNumber(driver);
		assertThat(computerFoundNumber1 != null, is(true));

		driver.findElement(By.id("addComputer")).click();
		enterComputer(driver, "name", "1999-10-09 01:02:03", "1999-10-09 01:02:03", "Apple Inc.");
		driver.findElement(By.id("addButton")).click();

		Long computerFoundNumber2 = getComputerNumber(driver);
		assertThat(computerFoundNumber2 != null, is(true));

		assertThat(computerFoundNumber1, is(computerFoundNumber2 - 1));
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
