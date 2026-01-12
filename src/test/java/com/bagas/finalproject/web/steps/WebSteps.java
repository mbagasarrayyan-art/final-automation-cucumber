package com.bagas.finalproject.web.steps;

import com.bagas.finalproject.web.pages.CartPage;
import com.bagas.finalproject.web.pages.CheckoutPage;
import com.bagas.finalproject.web.pages.InventoryPage;
import com.bagas.finalproject.web.pages.LoginPage;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class WebSteps {

    private WebDriver driver;

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("user is on SauceDemo login page")
    public void userIsOnLoginPage() {
        // ✅ ambil driver setelah @Before hook jalan
        driver = Hooks.driver;
        Assertions.assertNotNull(driver, "Driver null. Hooks @Before belum jalan / tag @web tidak kebaca.");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @When("user login with username {string} and password {string}")
    public void userLogin(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("inventory page should be displayed")
    public void inventoryPageShouldBeDisplayed() {
        Assertions.assertTrue(inventoryPage.isLoaded(), "Inventory page harus tampil setelah login sukses");
    }

    @Then("error message should be displayed")
    public void errorMessageShouldBeDisplayed() {
        Assertions.assertTrue(loginPage.isErrorDisplayed(), "Error message harus tampil untuk login gagal");
    }

    @Given("user already logged in")
    public void userAlreadyLoggedIn() {
        userIsOnLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        Assertions.assertTrue(inventoryPage.isLoaded(), "Login gagal, inventory tidak tampil");
    }

    @When("user add backpack product to cart")
    public void userAddBackpackToCart() {
        inventoryPage.addBackpackToCart();
        Assertions.assertEquals("1", inventoryPage.getCartBadgeText(), "Badge cart harus 1 setelah add");
    }

    @And("user open cart page")
    public void userOpenCartPage() {
        inventoryPage.openCart();
        Assertions.assertTrue(cartPage.isLoaded(), "Cart page tidak kebuka");
    }

    @Then("cart should have {int} item")
    public void cartShouldHaveItem(int expected) {
        int actual = cartPage.getItemsCount();
        Assertions.assertEquals(expected, actual, "Jumlah item di cart tidak sesuai");
    }

    @When("user remove backpack product from cart")
    public void userRemoveBackpackFromCart() {
        cartPage.removeBackpack();
    }

    @Then("cart should be empty")
    public void cartShouldBeEmpty() {
        int actual = cartPage.getItemsCount();
        Assertions.assertEquals(0, actual, "Cart harus kosong setelah remove");
    }

    @When("user checkout with first name {string}, last name {string}, postal code {string}")
    public void userCheckout(String fn, String ln, String zip) {
        cartPage.clickCheckout();
        checkoutPage.fillInfoAndContinue(fn, ln, zip);
        checkoutPage.finishCheckout();
    }

    @Then("checkout should be successful")
    public void checkoutShouldBeSuccessful() {
        String header = checkoutPage.getCompleteHeader();
        Assertions.assertTrue(header.toLowerCase().contains("thank you"),
                "Checkout sukses harus muncul thank you");
    }
}
