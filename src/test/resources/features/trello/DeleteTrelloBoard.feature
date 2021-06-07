@trello
Feature: Trello - Delete board

  @functional @createTrelloBoardPreCondition
  Scenario: Delete a specific board
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a DELETE request to "boards/{id}"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "trello/deleteTrelloBoardSchema.json" JSON schema

  @negative @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
 Scenario Outline:  Verify no board is deleted with invalid id
    Given the user sets valid authentication headers for "Trello" API request
    When the user sends a DELETE request to "boards/<board invalid id>"
    Then verifies that the response should have the 404 status code
    Examples:
      | board invalid id |
      | ?{id}            |
      | /{id}            |
      | board/{id}       |
