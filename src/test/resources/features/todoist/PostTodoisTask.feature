@todoist
Feature: Todoist - Create task

  @smoke @functional @deleteTodoistTaskPostCondition
  Scenario: Create a Task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "tasks" with the following data
      """
      {
        "content": "API Automation Task",
        "due_string": "tomorrow at 12:00",
        "due_lang": "en",
        "priority": 4
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "todoist/postTodoisTaskSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | content | API Automation Task |

  @negative
  Scenario: Task is not created when using invalid authentication
    Given the user sets the following headers for "Todoist" API request
      | Authorization | someinvalidtoken |
    When the user sends a POST request to "tasks" with the following data
      """
      {
        "content": "API Automation Task"
      }
      """
    Then verifies that the response should have the 400 status code
