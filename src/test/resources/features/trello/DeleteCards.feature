@trello
Feature: Trello - Delete card

  @functional @createTrelloBoardPreCondition @createTrelloCardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Delete one card in the default lists
    Given the user sets valid authentication headers for "Trello" API request
    When the user deletes the cards in the default list
    Then verifies that the responses should have the 200 status code