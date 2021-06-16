@pivotal
Feature: Pivotal Tracker - GET Workspaces

  @smoke @functional
  Scenario: Get workspaces
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "my/workspaces"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "examples/pivotal/getPivotalWorkspacesSchema.json" JSON schema

  @negative
  Scenario: Project is not created when using invalid authentication
    Given the user sets the following headers for "Pivotal Tracker" API request
      | X-TrackerToken | someinvalidtoken |
    When the user sends a GET request to "my/workspaces"
    Then verifies that the response should have the 403 status code
