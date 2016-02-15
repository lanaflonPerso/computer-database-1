package com.excilys.computerdatabase.webapp.integration;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public abstract class AbstractWebTest {
    private static final String ADMIN_USERNAME = "all";
    private static final String ADMIN_PASSWORD = "all";

    protected WebDriver driver;

    @After
    public void close() {
        if(driver != null) {
            driver.close();
        }
    }

    protected void login(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(5000L, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(5000L, TimeUnit.MILLISECONDS);
        driver.get("http://localhost:8080/webapp/computer/view/dashboard?language=en");

        driver.findElement(By.id("username")).sendKeys(ADMIN_USERNAME);
        driver.findElement(By.id("password")).sendKeys(ADMIN_PASSWORD);
        driver.findElement(By.id("login")).click();

        if (driver.getCurrentUrl().contains("customLogin")) {
            fail("Unable to login");
        }
    }

    protected void deleteComputer(WebDriver webDriver) {
        WebElement webElement = webDriver.findElement(By.id("computerName"));
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        webElement.sendKeys(Keys.DELETE);
        webElement = webDriver.findElement(By.id("introduced"));
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        webElement.sendKeys(Keys.DELETE);
        webElement = webDriver.findElement(By.id("discontinued"));
        webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        webElement.sendKeys(Keys.DELETE);

    }

    protected void enterComputer(WebDriver webDriver, String name, String introduced, String discontinued, String companyName) {
        WebElement webElement = webDriver.findElement(By.id("computerName"));
        webElement.sendKeys(name);
        webElement = webDriver.findElement(By.id("introduced"));
        webElement.sendKeys(introduced);
        webElement = webDriver.findElement(By.id("discontinued"));
        webElement.sendKeys(discontinued);

        webElement = webDriver.findElement(By.id("companyId"));
        List<WebElement> webElements = webElement.findElements(By.tagName("option"));
        for (WebElement webElement2 : webElements) {
            if (companyName != null) {
                if (companyName.equals(webElement2.getText())) {
                    webElement2.click();
                }
            }
        }
    }

    protected Long getComputerNumber(WebDriver driver) {
        try {
            WebElement webElement = driver.findElement(By.id("homeTitle"));
            String s = webElement.getText().split(" ")[0];
            return Long.valueOf(s);
        } catch (Exception e) {
            return null;
        }
    }
}
