@all @archives
Feature: Test all archives scenarios

  @no-blank-archive-types
  Scenario: Every archive has an Archive Type
    Given I am on the archives page
    When I perform an archive search for "- Any -"
    Then I see that all archives have an archive type

  @unique-archive-fac-numbers
  Scenario Outline: Searching for "<Archive Type>" shows that each record has a unique FAC Number
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

  Scenario Outline: Something something
    When I am on the archives page
    And I perform an archive search for "<Archive Type>"
    And I see all archive details are correct
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