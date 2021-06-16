@pivotal
Feature: Pivotal - Modify workspaces

  @smoke @functional @createPivotalWorkspacePreCondition @deletePivotalWorkspacesPostCondition @createPivotalProjectPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Modify a workspaces
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "my/workspaces/{workspaceId}" with the following data
      """
        {
        "project_ids":[<id>]
        }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "examples/pivotal/putPivotalWorkspacesSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | project_ids | [<id>] |
    Examples:
      | id   |
      | {id} |

  @negative
  Scenario: Project is not created when using invalid authentication
    Given the user sets the following headers for "Pivotal Tracker" API request
      | X-TrackerToken | someinvalidtoken |
    When the user sends a PUT request to "my/workspaces/{workspaceId}" with the following data
      """
      {
      "project_ids":[2501362]
      }
      """
    Then verifies that the response should have the 403 status code

  @negative @createPivotalWorkspacePreCondition @deletePivotalWorkspacesPostCondition
  Scenario: Modify a workspaces
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "my/workspaces/{workspaceId}" with the following data
      """

      """
    Then verifies that the response should have the 500 status code

  @negative @createPivotalWorkspacePreCondition @deletePivotalWorkspacesPostCondition
  Scenario: Modify a workspaces
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "my/workspaces/{workspaceId}" with the following data
      """
      {"project_ids":[784]}
      """
    Then verifies that the response should have the 400 status code
