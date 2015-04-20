package com.excilys.computerdatabase.controler.impl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractWebTest {

	public static final String ADMIN_USERNAME = "admin";
	public static final String ADMIN_PASSWORD = "admin";

	protected void login(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(5000L, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(5000L, TimeUnit.MILLISECONDS);
		driver.get("http://localhost:8080/webapp/dashboard?language=en");
		
		driver.findElement(By.id("username")).sendKeys(ADMIN_USERNAME);
		driver.findElement(By.id("password")).sendKeys(ADMIN_PASSWORD);
		driver.findElement(By.id("login")).click();
	
		if(driver.getCurrentUrl().contains("customLogin")){
			throw new RuntimeException("Unable to login");
		}
	}
	
}
