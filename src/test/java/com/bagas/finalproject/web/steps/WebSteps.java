package com.bagas.finalproject.web.steps;

import com.bagas.finalproject.web.pages.InventoryPage;
import com.bagas.finalproject.web.pages.LoginPage;
import io.cucumber.java.en.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WebSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("I am on saucedemo login page")
    public void openLogin() {
        loginPage = new LoginPage(Hooks.driver);
        inventoryPage = new InventoryPage(Hooks.driver);
        loginPage.open();
    }

    @When("I login with username {string} and password {string}")
    public void login(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("I should be redirected to inventory page")
    public void verifyInventory() {
        assertThat(inventoryPage.isOpened()).isTrue();
    }
}
