@pivotal
  Feature: Pivotal - Get Project
    @smoke @functional
      Scenario: Get Projects from Pivotal account
      Given the user sets valid authentication headers for "Pivotal" API request
      When the user sends GET request to "projects/{projects_id}/stories/{id}"
      Then verifies that response should have the 200 status code
      And verifies that the response body should match with "pivotal/postPivotalStoriesSchema.json" JSON schema
      And verifies that the response body should contain the following values
      | name | [API Automation Story] |

