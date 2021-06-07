@trello
Feature: Trello - Get Board from an organization

  @smoke @functional @createTrelloOrganizationPreCondition @createTrelloBoardOnOrganizationPreCondition
  @deleteTrelloBoardPostCondition @deleteTrelloOrganizationPostCondition
  Scenario: Get Board from an organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a GET request to "organizations/{id}/boards"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/getTrelloBoardFromOrganizationSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | [API Automation Board on Org] |

  @negative
  Scenario: Get Board from a non-existent organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a GET request to "organizations/non-existentid/boards"
    Then verifies that the response should have the 400 status code
