@all @header
Feature: Test all header scenarios

  Scenario: Coronavirus link goes to the correct page
    Given I am on the homepage
    When I click on coronavirus link in the header
    Then I see the url is "https://www.acquisition.gov/coronavirus"

  Scenario: 889 Link goes to the correct page
    Given I am on the homepage
    When I click on 889 information link in the header
    Then I see the url is "https://www.acquisition.gov/FAR-Case-2019-009/889_Part_B"

  Scenario: Header logo goes to the correct page
    Given I am on the homepage
    When I click on header logo
    Then I see the url is "https://www.acquisition.gov/"

  @qwe123
  Scenario: Regulation link goes to correct page
    Given I am on the homepage
    When I click on header link "Regulations"
    Then I see the url is "https://www.acquisition.gov/content/regulations"

  @qwe123
  Scenario: Archives link goes to correct page
    Given I am on the homepage
    When I click on header link "Archives"
    Then I see the url is "https://www.acquisition.gov/archives?type=FAR"

  @qwe123
  Scenario: Policy Network link goes to correct page
    Given I am on the homepage
    When I click on header link "Policy Network"
    Then I see the url is "https://www.acquisition.gov/policy-network"

  @qwe123
  Scenario: Search link goes to correct page
    Given I am on the homepage
    When I click on header link "Search"
    Then I see the url is "https://www.acquisition.gov/search/advanced/"
