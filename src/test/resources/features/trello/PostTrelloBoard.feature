@trello
Feature: Trello - Create board

  @smoke @functional @deleteTrelloBoardPostCondition
  Scenario: Create a board
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a POST request to "boards" with the following data
      """
      {
        "name": "Testing Board"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/postTrelloBoardSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | Testing Board |

    @smoke @functional @deleteTrelloBoardPostCondition
    Scenario: Create a Public Board where just invited users can join
      Given the user sets valid authentication headers for "Trello" API request
      When the user sends a POST request to "boards" with the following data
      """
      {
        "name": "Public Board-01AU6",
        "prefs_permissionLevel": "public"
      }
      """
      Then verifies that the response should have the 200 status code
      And verifies that the response body should contain the following values
      | name                   | Public Board-01AU6 |
      | prefs.permissionLevel  | public             |

  @negative
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

  @negative
  Scenario: Board is not created when name is empty
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a POST request to "boards" with  an empty data in name
      """
      {
        "name": ""
      }
      """
    Then verifies that the response should have the 400 status code

