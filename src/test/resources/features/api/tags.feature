@api
Feature: Dummy API - Tags

  Scenario: Get list of tags
    Given I set DummyAPI base url
    When I get list of tags
    Then the response status should be 200
