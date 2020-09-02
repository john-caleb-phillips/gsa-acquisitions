@all @search
Feature: Test all search scenarios

  @blank-site-search
  Scenario: Search with no terms returns nothing
    Given I am on the site search page
    When I perform site search for ""
    Then I see the site search error message saying:
      """
      Please enter some keywords.
      """

  @invalid-site-search
  Scenario: Search with unfound term returns nothing
    Given I am on the site search page
    When I perform site search for "ThisWillNotBeFound"
    Then I see the site search error message saying:
      """
      Check if your spelling is correct, or try removing filters.
      Remove quotes around phrases to match each word individually: "blue drop" will match less than blue drop.
      You can require or exclude terms using + and -: big +blue drop will require a match on blue while big blue -drop will exclude results that contain drop.
      """

  @qwe123
  Scenario:
    Given I am on the site search page
    When I perform site search for "Manager"
    Then I see the following site search results:
      | Training            | Site Content |
      | Category Management | Site Content |