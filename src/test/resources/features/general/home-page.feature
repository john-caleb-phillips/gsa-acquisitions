@all @home-page
Feature: Home Page

  @correct-oval-buttons-are-present
  Scenario: Correct oval buttons are present
    Given I am on the home page
    Then I see the following oval buttons:
      | Browse the FAR | See all updates | Browse all news |

  @correct-square-buttons-are-present
  Scenario: Correct square buttons are present
    Given I am on the home page
    Then I see the following square buttons:
      | Smart Matrix | PSC Manual            |
      | FAR Archives | Procurement Forecasts |

  @square-buttons-have-correct-pictures
  Scenario: Square buttons have the correct pictures
    Given I am on the home page
    Then I see the following square button pictures:
      | Smart Matrix          | images/home-page/smart-matrix.png |
      | PSC Manual            | images/home-page/psc-manual.png   |
      | FAR Archives          | images/home-page/far-archives.png |
      | Procurement Forecasts | images/home-page/forecasts.png    |

  @verify-oval-buttons
  Scenario Outline: "<Button Text>" link goes to the correct page
    Given I am on the home page
    When I click on homepage oval button "<Button Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Button Text     | Destination    |
      | Browse the FAR  | FAR regulation |
      | See all updates | updates        |
      | Browse all news | news           |

  @verify-square-buttons
  Scenario Outline: "<Button Text>" link goes to the correct page
    Given I am on the home page
    When I click on homepage square button "<Button Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Button Text           | Destination           |
      | Smart Matrix          | smart matrix          |
      | PSC Manual            | psc manual            |
      | FAR Archives          | archive search        |
      | Procurement Forecasts | procurement forecasts |

  @verify-list-of-updates-matches-update-page
  Scenario: The list of updates matches the update page
    Given I am on the home page
    When I click on homepage oval button "See all updates"
    And I save the top 4 updates as "updates"
    And I navigate back
    Then I see the homepage updates match the updates saved as "updates"

  @verify-list-of-news-items-matches-news-page
  Scenario: The list of new items matches the news page
    Given I am on the home page
    When I click on homepage oval button "Browse all news"
    And I save the top 3 news items as "news.items"
    And I navigate back
    Then I see the homepage news items match the news items saved as "news.items"



