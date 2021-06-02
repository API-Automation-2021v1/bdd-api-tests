@trello
Feature: Trello - Create card

  @functional @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Create one card in the default lists of a board
    Given the user sets valid authentication headers for "Trello" API request
    When the user creates a card in the default lists
    Then verifies that the responses should have the 200 status code
    And verifies that the responses body should match with "trello/postTrelloCardSchema.json" JSON schema