@trello
Feature: Trello - Create board

  @deleteTrelloBoardPostCondition
  Scenario: Create a board
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a POST request to "boards" with the following data
      """
      {
        "name": "API Automation Board"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "examples/trello/postTrelloBoardSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | API Automation Board |

  Scenario: Board is not created when using invalid authentication
    Given the user sets the following headers for "Trello" API request
      | key | someinvalidkey |
    When the user sends a POST request to "boards" with the following data
      """
      {
        "name": "API Automation Project"
      }
      """
    Then verifies that the response should have the 401 status code
