@trello
Feature: Trello - Create card

  @functional @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Create one card in the default lists of a board
    Given the user sets valid authentication headers for "Trello" API request
    When the user creates a card in the default lists
    Then verifies that the responses should have the 200 status code
    And verifies that the responses body should match with "trello/postTrelloCardSchema.json" JSON schema

  @negative @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Card is not created when using empty idList
    Given the user sets valid authentication headers for "Trello" API request
    When the user creates a card in the default lists with empty idList
    Then verifies that the responses should have the 400 status code

  @negative @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Card is not created when using empty name for a card
    Given the user sets valid authentication headers for "Trello" API request
    When the user creates a card in the default lists with empty name for a card
    # The next step is failing because the cards is creating with a name with spaces in the body of request,
    # but by UI it is not possible to create a card with a name with spaces (Name is required by UI)
    Then verifies that the responses should have the 400 status code