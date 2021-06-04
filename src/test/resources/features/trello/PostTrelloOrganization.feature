@trello
Feature: Trello - Create organization

  @smoke @functional @deleteTrelloOrganizationPostCondition
  Scenario: Create an organization
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a POST request to "organizations" with the following data
      """
      {
        "displayName": "API Automation Organization"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/postTrelloOrganizationSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | displayName | API Automation Organization |


  @negative
  Scenario: Organization is not created when using invalid authentication
    Given the user sets the following headers for "Trello" API request
      | key | someinvalidkey |
    When the user sends a POST request to "organizations" with the following data
      """
      {
        "name": "API Automation Organization"
      }
      """
    Then verifies that the response should have the 401 status code
