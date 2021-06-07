@pivotal
Feature: Pivotal - Create label

  @smoke @functional @createPivotalProjectPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Create a label with different values
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "projects/{id}/labels" with the following data
      """
      {
        "name": "<label-name>"
      }
      """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "pivotal/postPivotalLabelSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | project_id | {id}         |
      | name | <label-name> |
    Examples:
      | label-name                                       |
      | mp-label-01                                      |
      | mp-@#-01                                         |
      | 111                                              |
      | this the test for labels name with a long string |
      | @#$%^&*                                          |

  @negative @createPivotalProjectPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Negative Create a label with different values
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "projects/{id}/labels" with the following data
      """
      {
        "name": <label-name>
      }
      """
    Then verifies that the response should have the 400 status code
    Examples:
      | label-name                                                                                                                                                                                                                                                         |
      | ""                                                                                                                                                                                                                                                                 |
      | "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, con" |
      | "      "                                                                                                                                                                                                                                                           |

  @functional @createPivotalProjectPreCondition @deletePivotalProjectPostCondition
  Scenario: Create more than on label for the project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "projects/{id}/labels" with the following data
    """
      {
        "name": "first-label-name"
      }
      """
      Then verifies that the response should have the 200 status code
      And verifies that the response body should match with "pivotal/postPivotalLabelSchema.json" JSON schema
      And verifies that the response body should contain the following values
        | project_id | {id}             |
        | name       | first-label-name |
    When the user sends a POST request to "projects/{id}/labels" with the following data
      """
      {
        "name": "second-label-name"
      }
      """
      Then verifies that the response should have the 200 status code
     And verifies that the response body should match with "pivotal/postPivotalLabelSchema.json" JSON schema
     And verifies that the response body should contain the following values
       | project_id | {id}              |
       | name       | second-label-name |

  @functional @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario: Label is not created using a duplicate name
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "projects/{id}/labels" with the following data
      """
      {
        "name": "{label_name}"
      }
      """
    Then verifies that the response should have the 400 status code