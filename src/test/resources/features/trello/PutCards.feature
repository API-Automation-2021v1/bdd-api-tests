@trello
Feature: Trello - Update card

  @functional @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Update one card in the default lists
    Given the user sets valid authentication headers for "Trello" API request
    When the user updates the cards in the default list
    Then verifies that the responses should have the 200 status code
    And verifies that the responses body should match with "trello/putTrelloCardSchema.json" JSON schema

  @functional @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Update only one card in the default lists
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a PUT request to "cards/{idFirstCard}" with the following data
      """
      {
        "name": "Updating name a card",
        "desc": "API Automation Card - Modified Description",
        "closed": "false"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/putTrelloCardSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name   | Updating name a card                       |
      | desc   | API Automation Card - Modified Description |
      | closed | false                                      |

  @negative @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Cards is not updated without idCard
    Given the user sets valid authentication headers for "Trello" API request
    When the user updates the cards in the default lists without idCard
    Then verifies that the responses should have the 404 status code

  @negative @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Card is not updated without idCard
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a PUT request to "cards/" with the following data
      """
      {
        "desc": "API Automation Card - Modified Description"
      }
      """
    Then verifies that the response should have the 404 status code