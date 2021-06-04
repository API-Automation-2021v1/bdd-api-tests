@trello
Feature: Trello - Get an organization

  @smoke @functional @createTrelloOrganizationPreCondition @deleteTrelloOrganizationPostCondition
  Scenario: Get an organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a GET request to "organizations/{id}"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/getTrelloOrganizationSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | displayName | API Automation Organization |


  @negative
  Scenario: Get a non-existent organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a GET request to "organizations/non-existentid"
    Then verifies that the response should have the 400 status code