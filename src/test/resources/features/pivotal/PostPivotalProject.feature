@pivotal
Feature: Pivotal Tracker - Create project

  @smoke @functional @deletePivotalProjectPostCondition
  Scenario: Create a project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "projects" with the following data
      """
      {
        "name": "API Automation Project"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "pivotal/postPivotalProjectSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | API Automation Project |

  @negative
  Scenario: Project is not created when using invalid authentication
    Given the user sets the following headers for "Pivotal Tracker" API request
      | X-TrackerToken | someinvalidtoken |
    When the user sends a POST request to "projects" with the following data
      """
      {
        "name": "API Automation Project"
      }
      """
    Then verifies that the response should have the 403 status code
