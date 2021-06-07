@todoist
Feature: Todoist - Get list for Task

  @smoke @functional
  Scenario: Get List for Task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a GET request to "tasks"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should contain at least the following value
      | content | API Automation Task |

  @smoke @functional
  Scenario: Get a task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a GET request to "tasks/{id}"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "todoist/getTodoistTaskSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | content | API Automation Task |


  @negative
  Scenario: Get a non-existent task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a GET request to "tasks/non-existentid"
    Then verifies that the response should have the 404 status code