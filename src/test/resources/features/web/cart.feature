@web
Feature: Cart

  Scenario: Add item to cart
    Given user already logged in
    When user add backpack product to cart
    And user open cart page
    Then cart should have 1 item

  Scenario: Remove item from cart
    Given user already logged in
    When user add backpack product to cart
    And user open cart page
    When user remove backpack product from cart
    Then cart should be empty
