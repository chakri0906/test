package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();

        // Read headless property passed from Maven
        String headless = System.getProperty("headless");
        boolean isHeadless = headless == null || headless.equalsIgnoreCase("true");

        if (isHeadless) {
            options.addArguments("--headless=new"); // Run in headless mode
        } else {
            System.out.println("Running Chrome in headed mode (UI visible)...");
        }

        // Common Chrome options for CI stability
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        System.out.println("Page title: " + driver.getTitle());

        driver.quit();
    }
}
