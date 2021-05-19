Feature: Background Step definitions

  Background:
    When I login to the app

  @createUserPrecondition
  Scenario: test 1
    When I set the username "marcos-x86"

  Scenario: test 2
    When I set the username "marcos-x64"

  Scenario: test 3
    When I set the username "marcos-x8086"
