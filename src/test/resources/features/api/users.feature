@api
Feature: Dummy API Users CRUD

  Scenario: Get user list should return 200
    When user request user list
    Then user list response status should be 200

  Scenario: Create user should return 200 or 201
    Given prepare valid user payload
    When user create new user
    Then create user response status should be 200 or 201

  Scenario: Update created user should return 200
    Given prepare valid user payload
    When user create new user
    Then create user response status should be 200 or 201
    When user update created user
    Then update user response status should be 200

  Scenario: Delete created user should return 200 or 204
    Given prepare valid user payload
    When user create new user
    Then create user response status should be 200 or 201
    When user delete created user
    Then delete user response status should be 200 or 204
