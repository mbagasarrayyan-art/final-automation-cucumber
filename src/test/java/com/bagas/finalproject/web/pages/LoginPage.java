package com.bagas.finalproject.web.pages;

import com.bagas.finalproject.web.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By errorMsg = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
    }

    public void login(String user, String pass) {
        wait.type(username, user);
        wait.type(password, pass);
        wait.click(loginBtn);
    }

    public boolean isErrorDisplayed() {
        return wait.exists(errorMsg);
    }

    public String getErrorText() {
        if (!wait.exists(errorMsg)) return "";
        return wait.visible(errorMsg).getText().trim();
    }
}
