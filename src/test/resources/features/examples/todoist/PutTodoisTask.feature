@todoist
Feature: Todoist - Modify Task


  @smoke @functional@createTodoistTaskPreCondition @deleteTodoistTaskPostCondition
  Scenario: Modify a task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "tasks/{id}" with the following data
     """
      {
        "content": "API Automation Task - modified",

      }
      """
    And verifies that the response body should match with "todoist/putTodoistTaskSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | displayName | API Automation Task - modified |

  @negative
  Scenario: Modify a non-existent task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "tasks/non-existentid" with the following data
      """
      {
        "displayName": "API Automation Task - modified"
      }
      """
    Then verifies that the response should have the 400 status code