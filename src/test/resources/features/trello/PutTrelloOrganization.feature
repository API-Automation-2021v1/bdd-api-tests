@trello
Feature: Trello - Modify organization

  @smoke @functional @createTrelloOrganizationPreCondition @deleteTrelloOrganizationPostCondition
  Scenario: Modify an organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a PUT request to "organizations/{id}" with the following data
      """
      {
        "displayName": "API Automation Organization - Modified Name"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/putTrelloOrganizationSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | displayName | API Automation Organization - Modified Name |

  @negative
  Scenario: Modify a non-existent organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a PUT request to "organizations/non-existentid" with the following data
      """
      {
        "displayName": "API Automation Organization - Modified Name"
      }
      """
    Then verifies that the response should have the 400 status code
