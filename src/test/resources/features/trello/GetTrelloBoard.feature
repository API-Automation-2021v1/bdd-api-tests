@trello
Feature: Trello - Get board

  @functional @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
  Scenario: Get a specific board
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a GET request to "boards/{id}"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/getTrelloBoardSchema.json" JSON schema

  @negative @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
  Scenario Outline: A bord is not getting when using an invalid id
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a GET request to "boards/<invalid id>"
    Then verifies that the response should have the 404 status code
    Examples:
      | invalid id   |
      | boardid/{id} |
      | /{id}        |
      | ?{id}        |