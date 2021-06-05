@trello
Feature: Trello - Delete card

  @functional @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Delete one card in the default lists
    Given the user sets valid authentication headers for "Trello" API request
    When the user deletes the cards in the default list
    Then verifies that the responses should have the 200 status code

  @negative @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Try delete one card in the default lists with invalid idCard
    Given the user sets valid authentication headers for "Trello" API request
    When the user try deletes the cards in the default list with invalid idCard
    Then verifies that the responses should have the 400 status code