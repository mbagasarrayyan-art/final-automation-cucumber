@api
Feature: Dummy API Negative Tests

  Scenario: Get user by invalid id should return 400 or 404
    Given user has invalid user id
    When user request user by invalid id
    Then get user invalid id response status should be 400 or 404
