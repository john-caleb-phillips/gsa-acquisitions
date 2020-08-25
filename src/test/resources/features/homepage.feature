@all @home-page
Feature: Test all homepage scenarios

  @correct-oval-buttons-are-present
  Scenario: Correct oval buttons are present
    Given I am on the homepage
    Then I see the following oval buttons:
      | Browse the FAR | See all updates | Browse all news |

  @correct-square-buttons-are-present
  Scenario: Correct square buttons are present
    Given I am on the homepage
    Then I see the following square buttons:
      | Smart Matrix | PSC Manual            |
      | FAR Archives | Procurement Forecasts |

  @square-buttons-have-correct-pictures
  Scenario: Square buttons have the correct pictures
    Given I am on the homepage
    Then I see the following square button pictures:
      | Smart Matrix          | smart-matrix.png |
      | PSC Manual            | psc-manual.png   |
      | FAR Archives          | far-archives.png |
      | Procurement Forecasts | forecasts.png    |

  @verify-browse-the-far-link
  Scenario: "Browse the FAR" link goes to the correct page
    Given I am on the homepage
    When I click on homepage oval button "Browse the FAR"
    Then I see the url is "https://www.acquisition.gov/browse/index/far"

  @verify-see-all-updates-link
  Scenario: "See all Updates" link goes to the correct page
    Given I am on the homepage
    When I click on homepage oval button "See all updates"
    Then I see the url is "https://www.acquisition.gov/content/list-sections-affected"

  @verify-list-of-updates-matches-update-page
  Scenario: The list of updates matches the update page
    Given I am on the homepage
    When I click on homepage oval button "See all updates"
    And I save the top 4 updates as "updates"
    And I navigate back
    Then I see the homepage updates match the updates saved as "updates"

  @verify-smart-matrix-link
  Scenario: "Smart Matrix" link goes to the correct page
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    Then I see the url is "https://www.acquisition.gov/far-smart-matrix"

  @verify-psc-manual-link
  Scenario: "PSC Manual" link goes to the correct page
    Given I am on the homepage
    When I click on homepage square button "PSC Manual"
    Then I see the url is "https://www.acquisition.gov/psc-manual"

  @verify-far-archives-link
  Scenario: "FAR Archives" link goes to the correct page
    Given I am on the homepage
    When I click on homepage square button "FAR Archives"
    Then I see the url is "https://www.acquisition.gov/archives/far"

  @verify-procurement-forecasts-link
  Scenario: "Procurement Forecasts" link goes to the correct page
    Given I am on the homepage
    When I click on homepage square button "Procurement Forecasts"
    Then I see the url is "https://www.acquisition.gov/procurement-forecasts"

  @verify-browse-all-news-link
  Scenario: "Browse all news" link goes to the correct page
    Given I am on the homepage
    When I click on homepage oval button "Browse all news"
    Then I see the url is "https://www.acquisition.gov/news"

  @verify-list-of-news-items-matches-news-page
  Scenario: The list of new items matches the news page
    Given I am on the homepage
    When I click on homepage oval button "Browse all news"
    And I save the top 3 news items as "news.items"
    And I navigate back
    Then I see the homepage news items match the news items saved as "news.items"



