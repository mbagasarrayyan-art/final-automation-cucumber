package com.bagas.finalproject.web.pages;

import com.bagas.finalproject.web.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WaitUtils wait;

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");

    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, 20);
    }

    public void fillInfoAndContinue(String fn, String ln, String zip) {
        wait.type(firstName, fn);
        wait.type(lastName, ln);
        wait.type(postalCode, zip);
        wait.click(continueBtn);
    }

    public void finishCheckout() {
        wait.click(finishBtn);
    }

    public String getCompleteHeader() {
        return wait.visible(completeHeader).getText().trim();
    }
}
