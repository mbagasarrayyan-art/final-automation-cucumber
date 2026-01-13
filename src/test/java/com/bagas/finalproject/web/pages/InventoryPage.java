package com.bagas.finalproject.web.pages;

import com.bagas.finalproject.web.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private final WebDriver driver;
    private final WaitUtils wait;

    private final By inventoryContainer = By.id("inventory_container");

    private final By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.className("shopping_cart_link");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 15);
    }

    public boolean isLoaded() {
        try {
            wait.visible(inventoryContainer); 
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void addBackpackToCart() {
        wait.click(addBackpackBtn);
       
        wait.visible(cartBadge);
        wait.waitText(cartBadge, "1");
    }

    public void openCart() {
        wait.click(cartLink);
    }

    public boolean isCartBadgePresent() {
        return wait.exists(cartBadge);
    }

    public String getCartBadgeText() {
        return wait.visible(cartBadge).getText().trim();
    }
}
