@pivotal
Feature: Pivotal - Modify project

  @functional @createPivotalProjectPreCondition @deletePivotalProjectPostCondition
  Scenario: Modify a project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "projects/{id}" with the following data
      """
      {
        "name": "API Automation Project - name modified"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "pivotal/putPivotalProjectSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | API Automation Project - name modified |
