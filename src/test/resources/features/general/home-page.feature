@all @home-page
Feature: Home Page

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
      | Smart Matrix          | home-page/smart-matrix.png |
      | PSC Manual            | home-page/psc-manual.png   |
      | FAR Archives          | home-page/far-archives.png |
      | Procurement Forecasts | home-page/forecasts.png    |

  @verify-oval-buttons
  Scenario Outline: "<Button Text>" link goes to the correct page
    Given I am on the homepage
    When I click on homepage oval button "<Button Text>"
    Then I see the url is "<Destination Url>"
    Examples:
      | Button Text     | Destination Url                                            |
      | Browse the FAR  | https://www.acquisition.gov/browse/index/far               |
      | See all updates | https://www.acquisition.gov/content/list-sections-affected |
      | Browse all news | https://www.acquisition.gov/news                           |

  @verify-square-buttons
  Scenario Outline: "<Button Text>" link goes to the correct page
    Given I am on the homepage
    When I click on homepage square button "<Button Text>"
    Then I see the url is "<Destination Url>"
    Examples:
      | Button Text           | Destination Url                                   |
      | Smart Matrix          | https://www.acquisition.gov/far-smart-matrix      |
      | PSC Manual            | https://www.acquisition.gov/psc-manual            |
      | FAR Archives          | https://www.acquisition.gov/archives/far          |
      | Procurement Forecasts | https://www.acquisition.gov/procurement-forecasts |

  @verify-list-of-updates-matches-update-page
  Scenario: The list of updates matches the update page
    Given I am on the homepage
    When I click on homepage oval button "See all updates"
    And I save the top 4 updates as "updates"
    And I navigate back
    Then I see the homepage updates match the updates saved as "updates"

  @verify-list-of-news-items-matches-news-page
  Scenario: The list of new items matches the news page
    Given I am on the homepage
    When I click on homepage oval button "Browse all news"
    And I save the top 3 news items as "news.items"
    And I navigate back
    Then I see the homepage news items match the news items saved as "news.items"



