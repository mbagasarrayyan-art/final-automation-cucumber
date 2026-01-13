package com.bagas.finalproject.web.pages;

import com.bagas.finalproject.web.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By cartList = By.className("cart_list");
    private final By cartItems = By.className("cart_item");

    private final By removeBackpackBtn = By.id("remove-sauce-labs-backpack");
    private final By checkoutBtn = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
    }

    public boolean isLoaded() {
        try {
            wait.visible(cartList); 
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public int getItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeBackpack() {
        wait.click(removeBackpackBtn);
    }

    public void clickCheckout() {
        wait.click(checkoutBtn);
    }
}
