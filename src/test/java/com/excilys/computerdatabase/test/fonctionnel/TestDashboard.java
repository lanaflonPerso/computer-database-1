/**
 * @Author Vincent Galloy
 */
package com.excilys.computerdatabase.test.fonctionnel;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
		assertThat(query != null, is(true));
		assertThat(query.getText(), is("Apple Inc."));

		query = driver.findElement(By.id("name_0"));
		assertThat(query != null, is(true));
		assertThat(query.getText(), is("MacBook Pro 15.4 inch"));
	}
	
	@Test
	public void testGoToAddElementUrl() {
		driver.findElement(By.id("addComputer")).click();
		assertThat(driver.getCurrentUrl(), is("http://localhost:8080/computer-database/addComputer"));
	}
	
	@Test
	public void testGoToEditElementUrl() {
		driver.findElement(By.id("name_0")).click();
		assertThat(driver.getCurrentUrl(), is("http://localhost:8080/computer-database/editComputer?computerId=4"));
	}
	
}
