package org.example;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;
    protected String url;

    @BeforeClass
    public void setUp() {
        ConfigReader configReader = new ConfigReader();
        String browser = configReader.getProperty("browser");
        url = configReader.getProperty("url");
        driver = DriverFactory.getDriver(browser);
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
