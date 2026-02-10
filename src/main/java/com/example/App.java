package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();

        // Read HEADLESS env variable
        String headless = System.getenv("HEADLESS");

        // Default to headless if HEADLESS not set or true
        boolean isHeadless = headless == null || headless.equalsIgnoreCase("true");

        // Only add headless option if needed
        if (isHeadless) {
            options.addArguments("--headless=new"); // Use new headless mode
        } else {
            System.out.println("Running Chrome in headed mode (UI visible)...");
        }

        // Common options for CI stability
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Optional: prevent crash on Mac/Linux headless agents
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        System.out.println("Page title: " + driver.getTitle());

        driver.quit();
    }
}
