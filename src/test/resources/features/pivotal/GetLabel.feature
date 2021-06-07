# new feature
@pivotal
Feature: Pivotal - Get a specific label

  @smoke @functional @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario: Get a specific label of the project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "projects/{id}/labels/{label_id}"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "pivotal/getPivotalALabelSchema.json" JSON schema

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Label is not returned using a invalid label id
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "projects/{id}/labels/<label invalid id>"
    Then verifies that the response should have the 404 status code
    Examples:
      | label invalid id |
      | 89.{label_id}    |
      | {id}             |
      | label-is         |

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Label is not retrieved using a invalid project id
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "projects/<invalid id>/labels/{label_id}"
    Then verifies that the response should have the 404 status code
    Examples:
      | invalid id |
      | {label_id} |
      | 0.{id}     |
      | label-is   |

