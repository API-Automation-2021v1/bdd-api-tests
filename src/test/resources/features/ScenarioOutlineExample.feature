Feature: Outline examples

  Scenario: test 1
    When I login to the app
    And I set the username ")8%%"
    And I set the code 432

  Scenario: test 2
    When I login to the app
    And I set the username ""
    And I set the code 222

  Scenario: test 3
    When I login to the app
    And I set the username "____"
    And I set the code 333

  Scenario Outline: negative tests
    When I login to the app
    And I set the username "<username>"
    And I set the code <code>
    Examples:
      | username | code |
      | )8%%     | 432  |
      |          | 222  |
      | ____     | 333  |
