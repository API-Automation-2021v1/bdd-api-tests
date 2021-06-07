# new feature
# Tags: optional

@pivotal
Feature: Pivotal - Delete label

  @smoke @functional @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario: Delete label from the project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a DELETE request to "projects/{id}/labels/{label_id}"
    Then verifies that the response should have the 204 status code

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Label is deleted using a invalid label id
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a DELETE request to "projects/{id}/labels/<label invalid id>"
    Then verifies that the response should have the 400 status code
    Examples:
      | label invalid id |
      | 89.{label_id}    |
      | {id}             |
      | label-is         |

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Label is not deleted using a invalid project id
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a DELETE request to "projects/<invalid id>/labels/{label_id}"
    Then verifies that the response should have the 404 status code
    Examples:
      | invalid id |
      | {label_id} |
      | 0.{id}     |
      | label-is   |



