@web
Feature: Login

  Scenario: Successful login
    Given user is on SauceDemo login page
    When user login with username "standard_user" and password "secret_sauce"
    Then inventory page should be displayed

  Scenario: Failed login with wrong password (negative)
    Given user is on SauceDemo login page
    When user login with username "standard_user" and password "wrong_password"
    Then error message should be displayed
