@web
Feature: Checkout End to End

  Scenario: End to End checkout success
    Given user already logged in
    When user add backpack product to cart
    And user open cart page
    Then cart should have 1 item
    When user checkout with first name "Bagas", last name "Arrayyan", postal code "12345"
    Then checkout should be successful
