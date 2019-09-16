# This is just a reference sample file
@login
Feature: Test all login based scenarios

  @all @validateInternalLogin
  Scenario Outline: User enters correct username and password to login and reaches homepage
    When I login as "<userType>" user
    Then I am logged in
    Examples:
    | userType |
    |  ADMIN   |
    |    PM    |
    |    PO    |
    |    FD    |
    |    FO    |


  @all @validateExternalLogin
  Scenario Outline: User enters correct username and password to login and reaches homepage
    Given I am on "Grantee" portal
    When I login as "<userType>" user
    Then I am logged in
    Examples:
    | userType |
    |   SPI    |