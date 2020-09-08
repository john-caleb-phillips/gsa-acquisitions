@all @search
Feature: Test all search scenarios

  #Site Searches

  @blank-site-search
  Scenario: Search with no terms gives an error message
    Given I am on the site search page
    When I perform search for ""
    Then I see the site search error message saying:
      """
      Please enter some keywords.
      """

  @invalid-site-search
  Scenario: Search with term that is not found returns nothing
    Given I am on the site search page
    When I perform search for "ThisWillNotBeFound"
    Then I see the site search error message saying:
      """
      Check if your spelling is correct, or try removing filters.
      Remove quotes around phrases to match each word individually: "blue drop" will match less than blue drop.
      You can require or exclude terms using + and -: big +blue drop will require a match on blue while big blue -drop will exclude results that contain drop.
      """

#  @site-search-with-valid-value
#  Scenario: Site search works correctly
#    Given I am on the site search page
#    When I perform search for "Manager"
#    Then I see the following site search results:
#      | Training            | Site Content |
#      | Category Management | Site Content |

  @site-search-correct-sidebar
  Scenario: Correct Site Sidebar
    Given I am on the site search page
    When I perform search for "*"
    And I expand all sidebar options
    Then I see the following filter headers:
      | Filter by archive type: |
      | Filter by year:         |
      | Sort by                 |
      | Type                    |
    And I see the following options under filter "Filter by archive type:":
      | DFARS | FAR       | NFS   | DLAD  | GSAM  | AFFARS | AFARS | NMCARS | DEARS | DARS  |
      | EPAAR | TRANSFARS | VAAR  | DOSAR | DTAR  | HSAR   | HUDAR | FEHBAR | HHSAR | DIAR  |
      | CAR   | AGAR      | AIDAR | EDAR  | LIFAR | TAR    | DOLAR | IAAR   | JAR   | NRCAR |
    And I see the following options under filter "Filter by year:":
      | 2014 | 2018 | 2017 | 2016 | 2013 | 2012 | 2006 | 2015 | 2011 | 2008 | 2010 | 1970 | 2004 |
      | 2009 | 2019 | 2005 | 2007 | 2002 | 2003 | 1999 | 2000 | 1998 | 2020 | 2001 | 1997 | 1996 |
    And I see the following options under filter "Sort by":
      | Title     |
      | Relevancy |
    And I see the following options under filter "Type":
      | Archives               |
      | Basic page             |
      | News and Announcements |
      | CAO.gov Page           |
      | Acquisition Pages      |

  @filter-by-archive-type-shows-only-correct-archive-types
  Scenario Outline: Filter by "<Archive Type>" archive type shows only the correct archive types
    Given I am on the site search page
    When I perform search for "*"
    And I filter by archive type "<Archive Type>"
    Then I see every search result is from "<Archive Type>" archive
    Examples:
      | Archive Type |
      | DFARS        |
      | FAR          |
      | NFS          |
      | DLAD         |
      | GSAM         |
      | AFFARS       |
      | AFARS        |
      | NMCARS       |
      | DEARS        |
      | DARS         |
      | EPAAR        |
      | TRANSFARS    |
      | VAAR         |
      | DOSAR        |
      | DTAR         |
      | HSAR         |
      | HUDAR        |
      | FEHBAR       |
      | HHSAR        |
      | DIAR         |
      | CAR          |
      | AGAR         |
      | AIDAR        |
      | EDAR         |
      | LIFAR        |
      | TAR          |
      | DOLAR        |
      | IAAR         |
      | JAR          |
      | NRCAR        |

  @filter-by-archive-type-shows-all-archives
  Scenario Outline: Filter by archive type shows all the correct archive types
    Given I am on the archives page
    And I perform an archive search for "<Archive Type>"
    And I count the number of archives with archive type "<Archive Type>" as "Number 1"
    When I am on the site search page
    And I perform search for "*"
    And I filter by archive type "<Archive Type>"
    And I count the number of search results with archive type "<Archive Type>" as "Number 2"
    Then I see that "Number 1" and "Number 2" are the same
    Examples:
      | Archive Type |
      | DFARS        |
      | FAR          |
      | NFS          |
      | DLAD         |
      | GSAM         |
      | AFFARS       |
      | AFARS        |
      | NMCARS       |
      | DEARS        |
      | DARS         |
      | EPAAR        |
      | TRANSFARS    |
      | VAAR         |
      | DOSAR        |
      | DTAR         |
      | HSAR         |
      | HUDAR        |
      | FEHBAR       |
      | HHSAR        |
      | DIAR         |
      | CAR          |
      | AGAR         |
      | AIDAR        |
      | EDAR         |
      | LIFAR        |
      | TAR          |
      | DOLAR        |
      | IAAR         |
      | JAR          |
      | NRCAR        |

  @site-search-filter-by-year-works-correctly
  Scenario: Filter by years shows the only correct archives
    Given I am on the site search page
    When I perform search for "*"
    And I filter by year "2019"
    Then I see every search result is from year "2019"

  @site-search-sort-by-title-works-correctly
  Scenario: Sort by title orders the records correctly
    Given I am on the site search page
    When I perform search for "Manage"
    And I sort by title
    Then I see the search results are sorted by title

  @site-search-term-is-in-search-results
  Scenario: Search term is in search result
    Given I am on the site search page
    When I perform search for "Manage"
    Then I see every search result contains "Manag"

  @site-search-highlights-search-result
  Scenario: Searched words are highlighted in the search page
    Given I am on the site search page
    When I perform search for "Manage"
    Then I see that "Manage" is highlighted in the search results

  @site-search-does-not-highlight-detail-page
  Scenario: Searched words on the site are not highlighted in the actual page
    Given I am on the site search page
    When I perform search for "Manage"
    Then I see that "Manage" is not highlighted in the search result details

  #Regulation searches

  @regulation-search-correct-sidebar
  Scenario: Correct Site Sidebar
    Given I am on the regulation search page
    When I perform search for "*"
    And I expand all sidebar options
    Then I see the following filter headers:
      | Filter by part number: |
      | Sort by                |
      | Type                   |
      | Document Type          |
      | Tags                   |
    And I see the following options under filter "Filter by part number:":
      | 52  | 252 | 225 | 22  | 552 | 32  | 19  |
      | 4   | 204 | 3   | 49  | 217 | 47  | 232 |
      | 9   | 42  | 227 | 215 | 31  | 237 | 25  |
      | 222 | 219 | 15  | 36  | 14  | 8   | 16  |
      | 53  | 208 | 242 | 209 | 23  | 46  | 570 |
      | 28  | 1   | 27  | 239 | 216 | 245 | 246 |
      | 504 | 13  | 37  | 17  | 519 | 223 | 536 |
    And I see the following options under filter "Sort by":
      | Title     |
      | Relevancy |
    And I see the following options under filter "Type":
      | FAR        | DFARS                  | GSAM                   | DFARSPGI                 | AFFARS            |
      | NMCARS     | HSAR                   | AFARS                  | DIAR                     | DARS              |
      | NFS        | TAR                    | CAR                    | EPAAR                    | VAAR              |
      | DOSAR      | AGAR                   | SOFARS                 | DEARS                    | AIDAR             |
      | DLAD       | HUDAR                  | HHSAR                  | DOLAR                    | JAR               |
      | EDAR       | FEHBAR                 | LIFAR                  | NRCAR                    | DTAR              |
      | IAAR       | TRANSFARS              | Archives               | Regulation Change Notice | Current Uploads   |
      | Basic page | News and Announcements | Regulation Information | Procurement Forecasts    | CAO.gov Page      |
      | mpt&sat    | Chapter 99             | Fan Episodes           | Webform                  | Acquisition Pages |
    And I see the following options under filter "Document Type":
      | subtopic   | part     | subpart    | PGI Part    |
      | subchapter | annex    | SUBCHAPTER | MP Part     |
      | toc        | appendix | index      | volume      |
      | CHAPTER    | foreword | Foreword   | corrections |
    And I see the following options under filter "Tags":
      | Part     | AFFARS            | NMCARS   | HSAR   | AFARS | DIAR      | DARS  |
      | NFS      | TAR               | CAR      | EPAAR  | VAAR  | DOSAR     | AGAR  |
      | SOFARS   | DEARS             | AIDAR    | DLAD   | HUDAR | HHSAR     | DOLAR |
      | PGI Part | JAR               | EDAR     | FEHBAR | LIFAR | Annex     | NRCAR |
      | MP Part  | Table of Contents | Appendix | DTAR   | IAAR  | TRANSFARS | 2019  |

  @regulation-search-works-correctly
  Scenario: Regulation search works correctly
    Given I am on the regulation search page
    When I perform search for ""
    Then I see that FAR search is enabled
    And I see that all regular
    And I see there are 9637 search results

  @qwe123
  Scenario: Site search works correctly

  @qwe123
  Scenario: Site search works correctly

  @qwe123
  Scenario: Site search works correctly

  @qwe123
  Scenario: Site search works correctly

  @qwe123
  Scenario: Site search works correctly