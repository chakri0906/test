package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();

        // Read headless property passed from Maven
        String headless = System.getProperty("headless");
        boolean isHeadless = headless == null || headless.equalsIgnoreCase("true");

        if (isHeadless) {
            options.addArguments("--headless=new");
        } else {
            System.out.println("Running Chrome in headed mode (UI visible)...");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        try {
            // Step 1: Navigate to Google
            driver.get("https://www.google.com");
            System.out.println("Page title: " + driver.getTitle());

            // Step 2: Perform a search
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Azure DevOps Selenium demo");
            searchBox.submit();

            // Step 3: Wait briefly (simple sleep for demo; better to use WebDriverWait in real tests)
            Thread.sleep(2000);

            // Step 4: Print the titles of the first few search results
            List<WebElement> results = driver.findElements(By.cssSelector("h3"));
            System.out.println("Top search results:");
            for (int i = 0; i < Math.min(results.size(), 5); i++) {
                System.out.println(" - " + results.get(i).getText());
            }

            // Step 5: Click the first result (if available)
            if (!results.isEmpty()) {
                results.get(0).click();
                Thread.sleep(2000);
                System.out.println("Navigated to: " + driver.getCurrentUrl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
