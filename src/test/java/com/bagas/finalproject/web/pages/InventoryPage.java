package com.bagas.finalproject.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private final WebDriver driver;
    private final By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOpened() {
        return driver.findElements(inventoryContainer).size() > 0;
    }
}
