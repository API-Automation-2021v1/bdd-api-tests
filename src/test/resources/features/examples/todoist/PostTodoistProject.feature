@todoist
Feature: Todoist - Create project

  @smoke @functional @deleteTodoistProjectPostCondition
  Scenario: Create a project
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "projects" with the following data
      """
      {
        "name": "API Automation Project"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "examples/todoist/postTodoistProjectSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | API Automation Project |

  @negative
  Scenario: Project is not created when using invalid authentication
    Given the user sets the following headers for "Todoist" API request
      | Authorization | someinvalidtoken |
    When the user sends a POST request to "projects" with the following data
      """
      {
        "name": "API Automation Project"
      }
      """
    Then verifies that the response should have the 400 status code
