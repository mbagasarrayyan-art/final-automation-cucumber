@api
Feature: Dummy API Tags

  Scenario: Get list of tags should return 200
    When I request tags list
    Then the response status should be 200
    And the tags list should not be empty
