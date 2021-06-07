@pivotal
Feature: Pivotal Tracker - Create story

  @smoke @functional @deletePivotalProjectPostCondition
  Scenario: Create a story
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "stories" with the following data
      """
      {
        "name": "API Automation Story"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "pivotal/postPivotalStoriesSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | name | API Automation Story |

  @negative
  Scenario: Story is not created when using invalid authentication
    Given the user sets the following headers for "Pivotal Tracker" API request
      | X-TrackerToken | someinvalidtoken |
    When the user sends a POST request to "stories" with the following data
      """
      {
        "name": "API Automation Story"
      }
      """
    Then verifies that the response should have the 403 status code
