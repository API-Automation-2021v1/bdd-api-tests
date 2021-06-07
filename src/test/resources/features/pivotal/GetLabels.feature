# new feature
@pivotal
Feature: Pivotal - Get labels

  @functional @createPivotalProjectPreCondition @deletePivotalProjectPostCondition
  Scenario: Execute get labels for a project without labels
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "projects/{id}/labels"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should empty

  @smoke @functional @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario: Get the label of the project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "projects/{id}/labels"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "pivotal/getPivotalLabelsSchema.json" JSON schema

  @negative @createPivotalProjectPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Get the label of the project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "projects/<project-id>/labels"
    Then verifies that the response should have the 404 status code
    Examples:
      | project-id |
      | 0.0        |
      |            |
      | test       |
      | 0          |