@api
Feature: Dummy API - User CRUD

  Scenario: Create user successfully
    Given I set DummyAPI base url
    When I create a new user with firstName "Bagas" lastName "Arrayyan" email "bagas_test@mail.com"
    Then the response status should be 200
    And the response should contain "id"

  Scenario: Get user by id
    Given I set DummyAPI base url
    And I have an existing user id from previous create
    When I get user by that id
    Then the response status should be 200
    And the response should contain "id"

  Scenario: Update user by id
    Given I set DummyAPI base url
    And I have an existing user id from previous create
    When I update user firstName to "Updated"
    Then the response status should be 200
    And the response should contain "firstName"

  Scenario: Delete user by id
    Given I set DummyAPI base url
    And I have an existing user id from previous create
    When I delete user by that id
    Then the response status should be 200
