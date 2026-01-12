@api @negative
Feature: Dummy API Negative Tests

  Scenario: Get user by invalid id should return 400 or 404
    When I request user by invalid id "INVALID_ID_123"
    Then the response status should be 400 or 404
    And print the last response
