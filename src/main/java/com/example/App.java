package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        // Setup Firefox driver automatically
        WebDriverManager.firefoxdriver().setup();

        WebDriver driver = new FirefoxDriver();

        // Maximize window
        driver.manage().window().maximize();

        // Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open website
            driver.get("https://www.saucedemo.com/");

            // Wait for username field
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));

            // Enter credentials
            driver.findElement(By.id("user-name")).sendKeys("standard_user");
            driver.findElement(By.id("password")).sendKeys("secret_sauce");

            // Click login
            driver.findElement(By.id("login-button")).click();

            // Wait for next page (validation)
            wait.until(ExpectedConditions.urlContains("inventory"));

            // Print success
            System.out.println("Login Successful in Firefox ✅");
            System.out.println("Page Title: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
