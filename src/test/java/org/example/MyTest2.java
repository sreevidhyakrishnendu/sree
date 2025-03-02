package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyTest2 extends CommonMethods {
    private static Set<String> usedProjectTitles = new HashSet<>();
    private static int projectCount = 0;


    @Test
    public void testUrl2() {
        // Use the login method from CommonMethods
        navigateAndReadConfig();
        clickProjectsLink();

        // Locate the project grid
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement projectGrid = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.project-grid")));

        // Find all project titles
        List<WebElement> projectTitles = projectGrid.findElements(By.cssSelector("li.project-grid-item div.project-title"));

        // Generate a unique project title
        String baseTitle = "My Project Title";
        String projectTitle = baseTitle;
        Set<String> existingTitles = new HashSet<>();
        for (WebElement title : projectTitles) {
            existingTitles.add(title.getText().trim());
        }

        while (existingTitles.contains(projectTitle)) {
            projectCount++;
            projectTitle = baseTitle + projectCount;
        }

        usedProjectTitles.add(projectTitle);
        System.out.println("Generated Project Title: " + projectTitle);

        // Verify if any project title matches the base title
        boolean isMatchFound = existingTitles.contains(baseTitle);

        if (isMatchFound) {
            System.out.println("Match found for project title: " + baseTitle);
        } else {
            System.out.println("No match found for project title: " + baseTitle);
        }



       createNewProject(projectTitle, "Blue");

        logout();
        System.out.println("Test 2 executed");
    }

    public void selectColorOption(String colorValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Locate the color picker container
        WebElement colorPickerContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.color-picker-container")));
        System.out.println("Color picker container found: " + colorPickerContainer);

        // Locate the input element within the color picker container
        WebElement colorInput = colorPickerContainer.findElement(By.cssSelector("input.picker__input[type='color']"));

        // Set the color value using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", colorInput, colorValue);
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('change'));", colorInput);
        System.out.println("Color value set to: " + colorValue);
    }

    public void createNewProject(String projectTitle, String color) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement newProjectButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.base-button.button.is-primary[href='/projects/new']")));
        newProjectButton.click();

        // Enter project title
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("projectTitle")));
        titleField.sendKeys(projectTitle);

        // Click on the color picker element
        WebElement colorPickerDiv = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.picker")));
        colorPickerDiv.click();
        System.out.println("Color picker div clicked");

        // Locate the datalist element
        WebElement colorPickerContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.color-picker-container")));
        System.out.println("Color picker container found: " + colorPickerContainer);

        // Retrieve all color options from the datalist
        List<WebElement> colorOptions = colorPickerContainer.findElements(By.tagName("option"));
        System.out.println("Color options retrieved: " + colorOptions.size());

                // Print the values of each color option
        for (WebElement option : colorOptions) {
            String colorValue = option.getAttribute("value");
            System.out.println("Color option value: " + colorValue);
        }

        // Set the color value using the selectColorOption method
        selectColorOption(color);

        // Find and click the "Create" button
        WebElement createButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.base-button--type-button.button.is-primary.ml-2")));
        createButton.click();
    }



}