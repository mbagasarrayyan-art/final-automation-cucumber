@api
Feature: Dummy API Users CRUD

  Scenario: Get user list should return 200
    When I request user list with limit 5
    Then the response status should be 200
    And the user list should not be empty

  Scenario: Create user should return 200 or 201
    When I create a new user
    Then the response status should be 200 or 201
    And print the last response

  Scenario: Update created user should return 200
    When I create a new user
    Then the response status should be 200 or 201
    When I update the created user
    Then the response status should be 200

  Scenario: Delete created user should return 200 or 204
    When I create a new user
    Then the response status should be 200 or 201
    When I delete the created user
    Then the response status should be 200 or 204
