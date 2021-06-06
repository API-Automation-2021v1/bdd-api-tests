@todoist
Feature: Todoist - Delete task

  @smoke @functional @createTodoistTaskPostCondition
  Scenario: Delete a Task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a DELETE request to "tasks/{id}"
    Then verifies that the response should have the 204 status code


  @negative
  Scenario: Delete a not existent task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a DELETE request to "tasks/4"
    Then verifies that the response should have the 400 status code
