package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            if ("edge".equalsIgnoreCase(browser)) {
                System.setProperty("webdriver.edge.driver", "C:\\Users\\sreev\\IdeaProjects\\edgedriver_win64\\msedgedriver.exe");
                driver = new EdgeDriver();
            }
            // Add more browser options if needed
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}