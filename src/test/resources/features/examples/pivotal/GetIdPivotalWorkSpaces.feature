@pivotal
Feature: Pivotal Tracker - GET ID Workspaces

  @smoke @functional @createPivotalWorkspacePreCondition @deletePivotalWorkspacesPostCondition
  Scenario: Get ID workspaces
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a GET request to "my/workspaces/{workspaceId}"
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "examples/pivotal/getIdPivotalWorkspacesSchema.json" JSON schema

  @negative
  Scenario: Project is not created when using invalid authentication
    Given the user sets the following headers for "Pivotal Tracker" API request
      | X-TrackerToken | someinvalidtoken |
    When the user sends a GET request to "my/workspaces"
    Then verifies that the response should have the 403 status code
