@web
Feature: Login - Saucedemo

  Scenario: Successful login
    Given I am on saucedemo login page
    When I login with username "standard_user" and password "secret_sauce"
    Then I should be redirected to inventory page
