Feature: Step definitions

  @createUserPrecondition @createUser2Precondition @cleanDBPostcondition
  Scenario: Step definitions generation examples
    When I login to the app
    And I set the username "marcos-x86"
    And I set the code 204
    And I set the text
    """
    {
      "name" : "Walter",
      "lastname" : "White"
    }
    """
    And I set the data
      | header1 | value 1 |
      | header2 | value 2 |
      | header3 | value 3 |
