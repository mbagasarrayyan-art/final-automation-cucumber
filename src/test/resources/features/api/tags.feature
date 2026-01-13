@api
Feature: Dummy API Tags

  Scenario: Get list of tags should return 200
    When user request tag list
    Then tag list response status should be 200
