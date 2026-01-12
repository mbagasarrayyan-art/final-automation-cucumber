package com.bagas.finalproject.web.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver, int seconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public WebElement visible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement clickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement present(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean exists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    /**
     * Click yang tahan banting untuk headless:
     * - tunggu clickable
     * - coba click biasa
     * - kalau ke-intercept / stale, scroll + JS click fallback
     */
    public void click(By locator) {
        WebElement el = clickable(locator);

        try {
            el.click();
        } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'center'});", el
                );
            } catch (Exception ignored) {}

            // re-find element biar gak stale
            el = clickable(locator);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
        }
    }

    public void type(By locator, String text) {
        WebElement el = visible(locator);
        el.clear();
        el.sendKeys(text);
    }

    public void waitText(By locator, String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }
}
