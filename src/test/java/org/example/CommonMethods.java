package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonMethods extends BaseTest {


    public void login(String apiurl, String username, String password)  {

        // Maximize the browser window
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Locate the api-url field, clear it, and enter the new apiurl
        WebElement apiurlField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("api-url")));
        apiurlField.clear();
        apiurlField.sendKeys(apiurl);

        // Locate and click the button
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".base-button.base-button--type-button.button.is-primary")));
        button.click();


        WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));

        usernameField.sendKeys(username);
        passwordField.sendKeys(password);

        // Locate and click the button with label "Login"
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']")));
        loginButton.click();

        // Verify that the next page is shown by waiting for a unique element on the next page
        WebElement welcomeText = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),username)]")));
    }

    public void logout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate and click the username element using class name
        WebElement svgElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".svg-inline--fa.fa-chevron-down")));
        svgElement.click();

        // Locate and click the logout option from the dropdown using span element
        WebElement logoutOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Logout']")));
        logoutOption.click();
    }
}