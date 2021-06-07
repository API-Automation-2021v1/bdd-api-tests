@trello
Feature: Trello - Delete an organization

  @smoke @functional @createTrelloOrganizationPreCondition
  Scenario: Delete an organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a DELETE request to "organizations/{id}"
    Then verifies that the response should have the 200 status code


  @negative
  Scenario: Delete a non-existent organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a DELETE request to "organizations/non-existentid"
    Then verifies that the response should have the 400 status code