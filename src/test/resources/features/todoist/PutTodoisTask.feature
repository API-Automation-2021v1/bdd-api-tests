@todoist
Feature: Todoist - Modify Task


  @smoke @functional
  Scenario: Modify a task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "tasks/4886190853" with the following data
     """
      {
        "content": "API Automation Task - modified"
      }
      """
    Then  verifies that the response should have the 204 status code

  @negative
  Scenario: Modify a non-existent task
    Given the user sets valid authentication headers for "Todoist" API request
    When the user sends a POST request to "tasks/1" with the following data
      """
      {
        "displayName": "API Automation Task - modified"
      }
      """
    Then verifies that the response should have the 400 status code