package com.excilys.computerdatabase.controler.editcomputer;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.excilys.computerdatabase.controler.AbstractWebTest;

public class TestEditComputer extends AbstractWebTest {
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
	public void testEditElementWithWrongDate() throws Exception {
		driver.findElement(By.id("name_1")).click();

		deleteComputer(driver);
		enterComputer(driver, "nameWrongDate", "", "2000-19-10 10:10:10", "Nokia");
		driver.findElement(By.id("editButton")).click();

		assertEquals(driver.getCurrentUrl(), "http://localhost:8080/webapp/computer/view/edit");
		assertEquals("Invalid discontinued date : respect yyyy-MM-dd HH:mm:ss", driver.findElement(By.id("serviceDiscontinuedException")).getText());
	}

}
