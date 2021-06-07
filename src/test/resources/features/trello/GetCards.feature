@trello
Feature: Trello - Get information card

  @functional @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Get the information of one card in the default lists
    Given the user sets valid authentication headers for "Trello" API request
    When the user gets the information of cards in the default lists
    Then verifies that the responses should have the 200 status code
    And verifies that the responses body should match with "trello/getTrelloCardSchema.json" JSON schema

  @negative @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: It is not possible to get card information in the default lists without idCard
    Given the user sets valid authentication headers for "Trello" API request
    When the user try gets the information of cards in the default lists without idCard
    Then verifies that the responses should have the 404 status code