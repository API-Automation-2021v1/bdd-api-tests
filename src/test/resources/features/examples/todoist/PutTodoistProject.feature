@todoist
Feature: Pivotal Tracker - Modify project

  @functional @createTodoistProjectPreCondition @deleteTodoistProjectPostCondition
  Scenario: Modify a project
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "projects/{id}" with the following data
      """
      {
        "name": "API Automation Project - name modified"
      }
      """
    Then verifies that the response should have the 204 status code
