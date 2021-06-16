@pivotal
Feature: Pivotal Tracker - Create workspace

  @smoke @functional @deletePivotalWorkspacesPostCondition
  Scenario: Create a project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "my/workspaces" with the following data
      """
      {
        "name": "API Automation workspace"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "examples/pivotal/postPivotalWorkspacesSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | API Automation workspace |

  @negative
  Scenario: Project is not created when using invalid authentication
    Given the user sets the following headers for "Pivotal Tracker" API request
      | X-TrackerToken | someinvalidtoken |
    When the user sends a POST request to "my/workspaces" with the following data
      """
      {
        "name": "API Automation workspace"
      }
      """
    Then verifies that the response should have the 403 status code

  @negative
  Scenario: Create a project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "my/workspaces" with the following data
      """
      {
         "dummydata": "API Automation workspace"
      }
      """
    Then verifies that the response should have the 400 status code
