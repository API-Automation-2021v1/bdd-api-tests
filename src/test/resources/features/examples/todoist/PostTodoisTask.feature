@todoist
Feature: Todoist - Create task

  @smoke @functional @deleteTodoisTaskPostCondition
  Scenario: Create a Task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "task" with the following data
      """
      {
        "content": "API Automation Task"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "todoist/postTodoistTaskSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | API Automation Task |

  @negative
  Scenario: Task is not created when using invalid authentication
    Given the user sets the following headers for "Todoist" API request
      | Authorization | someinvalidtoken |
    When the user sends a POST request to "tasks" with the following data
      """
      {
        "name": "API Automation Task"
      }
      """
    Then verifies that the response should have the 400 status code
