@trello
Feature: Trello - Modify organization

  @createTrelloOrganizationPreCondition @deleteTrelloOrganizationPostCondition
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
