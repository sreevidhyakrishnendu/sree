package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;


public class MyTest2 extends CommonMethods {

    @Test
    public void testUrl2() {
        // Use the login method from CommonMethods
        navigateAndReadConfig();
        clickProjectsLink();
        // Locate the element by its href attribute
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement newProjectLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/projects/new']")));
        newProjectLink.click();
        // Call the new method to create a project
        createNewProject("My Project Title", "Blue");

        logout();
        System.out.println("Test 2 executed");
    }

    public void createNewProject(String projectTitle, String color) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

        // Enter project title
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("projectTitle")));
        titleField.sendKeys(projectTitle);

        // Select color
        /*WebElement colorDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colorDropdown")));
        colorDropdown.click();
        WebElement colorOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[text()='" + color + "']")));
        colorOption.click();*/

        // Find and click the "Create" button
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.base-button--type-button.button.is-primary.ml-2")));
        createButton.click();
    }
}