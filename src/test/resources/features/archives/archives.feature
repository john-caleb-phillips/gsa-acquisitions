@all @archives
Feature: Archives

  @no-blank-archive-types
  Scenario: Every archive has an Archive Type
    Given I am on the archives page
    When I perform an archive search for "- Any -"
    Then I see that all archives have an archive type

  @effective-date-filter-works-correctly
  Scenario: Effective date filter works correctly
    Given I am on the archives page
    When I set the effective date to 2020-01-01
    And I perform an archive search for "- Any -"
    Then I see that all archive effective dates are before 2020-01-01

  @unique-archive-fac-numbers
  Scenario Outline: Every "<Archive Type>" archive has a unique FAC Number
    Given I am on the archives page
    When I perform an archive search for "<Archive Type>"
    Then I see every archive has a unique fac number
    Examples:
      | Archive Type |
      | - Any -      |
      | AFARS        |
      | AFFARS       |
      | AGAR         |
      | AIDAR        |
      | CAR          |
      | DARS         |
      | DEARS        |
      | DFARS        |
      | DFARSPGI     |
      | DIAR         |
      | DLAD         |
      | DOLAR        |
      | DOSAR        |
      | DTAR         |
      | EDAR         |
      | EPAAR        |
      | FAR          |
      | FEHBAR       |
      | GSAM         |
      | HHSAR        |
      | HSAR         |
      | HUDAR        |
      | IAAR         |
      | JAR          |
      | LIFAR        |
      | NFS          |
      | NMCARS       |
      | NRCAR        |
      | SOFARS       |
      | TAR          |
      | TRANSFARS    |
      | VAAR         |

  @correct-archive-search-results
  Scenario Outline: Search for "<Archive Type>" shows only archives with "<Archive Type>" archive type
    Given I am on the archives page
    When I perform an archive search for "<Archive Type>"
    Then I see every archive has the archive type "<Archive Type>"
    Examples:
      | Archive Type |
      | AFARS        |
      | AFFARS       |
      | AGAR         |
      | AIDAR        |
      | CAR          |
      | DARS         |
      | DEARS        |
      | DFARS        |
      | DFARSPGI     |
      | DIAR         |
      | DLAD         |
      | DOLAR        |
      | DOSAR        |
      | DTAR         |
      | EDAR         |
      | EPAAR        |
      | FAR          |
      | FEHBAR       |
      | GSAM         |
      | HHSAR        |
      | HSAR         |
      | HUDAR        |
      | IAAR         |
      | JAR          |
      | LIFAR        |
      | NFS          |
      | NMCARS       |
      | NRCAR        |
      | SOFARS       |
      | TAR          |
      | TRANSFARS    |
      | VAAR         |

  @correct-archive-count
  Scenario Outline: Search for "<Archive Type>" shows every archive with "<Archive Type>" archive type
    Given I am on the archives page
    When I perform an archive search for "- Any -"
    And I count the number of archives with archive type "<Archive Type>" as "Number 1"
    And I perform an archive search for "<Archive Type>"
    And I count the number of archives with archive type "<Archive Type>" as "Number 2"
    Then I see that "Number 1" and "Number 2" are the same
    Examples:
      | Archive Type |
      | AFARS        |
      | AFFARS       |
      | AGAR         |
      | AIDAR        |
      | CAR          |
      | DARS         |
      | DEARS        |
      | DFARS        |
      | DFARSPGI     |
      | DIAR         |
      | DLAD         |
      | DOLAR        |
      | DOSAR        |
      | DTAR         |
      | EDAR         |
      | EPAAR        |
      | FAR          |
      | FEHBAR       |
      | GSAM         |
      | HHSAR        |
      | HSAR         |
      | HUDAR        |
      | IAAR         |
      | JAR          |
      | LIFAR        |
      | NFS          |
      | NMCARS       |
      | NRCAR        |
      | SOFARS       |
      | TAR          |
      | TRANSFARS    |
      | VAAR         |

  @verify-archive-details
  Scenario Outline: Check archive details for archive type "<Archive Type>"
    Given I am on the archives page
    When I perform an archive search for "<Archive Type>"
    And I gather the archive details
    Then I see the fac number in the search row matches the fac number in the detail page
    And I see the archive type in the search row matches the archive type in the detail page
    And I see the effective date in the search row matches the effective date in the detail page
    And I see the year header is present for every archive detail
    And I see the pdf file in the search row matches the pdf file in the detail page
    And I see the zip file in the search row matches the zip file in the detail page
    And I see there is at least one download link for each archive
    And I see that every download link can be downloaded
    And I see that only the following headers are present:
      | Archive Type | FAC Number | Effective Date | Year |
    And I see that only the following download links are present:
      | PDF File | Zip file | Word File | ePub | Dita Package File | Change Order |
    Examples:
      | Archive Type |
      | AFARS        |
      | AFFARS       |
      | AGAR         |
      | AIDAR        |
      | CAR          |
      | DARS         |
      | DEARS        |
      | DFARS        |
      | DFARSPGI     |
      | DIAR         |
      | DLAD         |
      | DOLAR        |
      | DOSAR        |
      | DTAR         |
      | EDAR         |
      | EPAAR        |
      | FAR          |
      | FEHBAR       |
      | GSAM         |
      | HHSAR        |
      | HSAR         |
      | HUDAR        |
      | IAAR         |
      | JAR          |
      | LIFAR        |
      | NFS          |
      | NMCARS       |
      | NRCAR        |
      | SOFARS       |
      | TAR          |
      | TRANSFARS    |
      | VAAR         |