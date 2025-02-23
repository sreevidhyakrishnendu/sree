package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class MyTest1 extends CommonMethods {

    @Test
    public void testUrl1() {
        driver.get(url);

        // Read username and password from config
        ConfigReader configReader = new ConfigReader();
        String apiurl = configReader.getProperty("apiurl");
        String username = configReader.getProperty("username");
        String password = configReader.getProperty("password");

        // Use the login method from CommonMethods
        login(apiurl,username, password);
        logout();
        System.out.println("Test 1 executed");
    }

}
