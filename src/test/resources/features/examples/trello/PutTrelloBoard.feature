#@trello
#Feature: Trello - Modify board
#
#  @functional @createTrelloBoardPreCondition @deleteTrelloBoardPostCondition
#  Scenario: Modify a board
#    Given the user sets valid authentication headers for "Trello" API request
#    When the user sends a PUT request to "boards/{id}" with the following data
#      """
#      {
#        "name": "API Automation Board - Modified Name"
#      }
#      """
#    Then verifies that the response should have the 200 status code
#    And verifies that the response body should match with "examples/trello/putTrelloBoardSchema.json" JSON schema
#    And verifies that the response body should contain the following values
#      | name | API Automation Board - Modified Name |
