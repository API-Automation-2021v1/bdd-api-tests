# new feature
@pivotal
Feature: Pivotal - Modify label

  @smoke @functional @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Modify the label of the project
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "projects/{id}/labels/{label_id}" with the following data
    """
      {
        "name": "<label-name>"
      }
    """
    Then verifies that the response should have the 200 status code
    And verifies that the response body should match with "pivotal/putPivotalLabelSchema.json" JSON schema
    And verifies that the response body should contain the following values
      | id         | {label_id}   |
      | project_id | {id}         |
      | name       | <label-name> |
    Examples:
      | label-name           |
      | {label_name}-updated |
      | m                    |
      | 456                  |
      | @##$%^&              |

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario: Label name is not modified with a duplicated name
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a POST request to "projects/{id}/labels" with the following data
    """
      {
        "name": "duplicate-label-name"
      }
    """
    When the user sends a PUT request to "projects/{id}/labels/{label_id}" with the following data
    """
      {
        "name": "duplicate-label-name"
      }
    """
    Then verifies that the response should have the 400 status code

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Label is not modified sending invalid values for name
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "projects/{id}/labels/{label_id}" with the following data
    """
      {
        "name": <label-name>
      }
    """
    Then verifies that the response should have the 400 status code
    Examples:
      | label-name |
      | ""         |
      | "    "     |

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Label is not modified using invalid label id
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "projects/{id}/labels/<label invalid id>" with the following data
    """
      {
        "name": "{label_name}modified"
      }
    """
    Then verifies that the response should have the 400 status code
    Examples:
      | label invalid id |
      | 89.{label_id}    |
      | {id}             |
      | label-is         |

  @negative @createPivotalProjectWithLabelPreCondition @deletePivotalProjectPostCondition
  Scenario Outline: Label is not modified using invalid project id
    Given the user sets valid authentication headers for "Pivotal Tracker" API request
    When the user sends a PUT request to "projects/<invalid id>/labels/{label_id}" with the following data
    """
      {
        "name": "{label_name}modified"
      }
    """
    Then verifies that the response should have the 404 status code
    Examples:
      | invalid id |
      | {label_id} |
      | 0.{id}     |
      | label-is   |
