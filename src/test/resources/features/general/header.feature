@all @header
Feature: Header

  @logo-goes-to-correct-page
  Scenario: Header logo goes to the correct page
    Given I am on the news page
    When I click on header logo
    Then I am taken to the home page

  @verify-header-links
  Scenario Outline: "<Link Text>" link goes to correct page
    Given I am on the home page
    When I click on header link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text      | Destination             |
      | Covid 19       | coronavirus information |
      | Section 889    | 889 information         |
      | Regulations    | main regulation         |
      | Archives       | archive search          |
      | Policy Network | main policy network     |
      | Search         | regulation search       |

  @policy-network-dropdown-links-go-to-correct-pages-qwe
  Scenario Outline: Policy Network dropdown link "<Link Text>" goes to "<Destination>"
    Given I am on the home page
    When I click on header Policy Network dropdown link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                                             | Destination |
      | CAO.gov                                               | CAOC        |
      | Civilian Agency Acquisition Council (CAAC)            | CAAC        |
      | Federal Acquisition Regulatory Council                | FARC        |
      | Interagency Suspension and Debarment Committee (ISDC) | ISDC        |

  @regulations-dropdown-links-go-to-correct-pages
  Scenario Outline: Regulation dropdown link "<Link Text>" goes to "<Destination>"
    Given I am on the home page
    When I click on header Regulations dropdown link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text        | Destination          |
      | FAR              | FAR regulation       |
      | FAR Smart Matrix | smart matrix         |
      | Chapter 99 (CAS) | chapter 99           |
      | DFARS            | DFARS regulation     |
      | DFARSPGI         | DFARSPGI regulation  |
      | AFARS            | AFARS regulation     |
      | AFFARS           | AFFARS regulation    |
      | DARS             | DARS regulation      |
      | DLAD             | DLAD regulation      |
      | NMCARS           | NMCARS regulation    |
      | SOFARS           | SOFARS regulation    |
      | TRANSFARS        | TRANSFARS regulation |
      | AGAR             | AGAR regulation      |
      | AIDAR            | AIDAR regulation     |
      | CAR              | CAR regulation       |
      | DEARS            | DEARS regulation     |
      | DIARS            | DIARS regulation     |
      | DOLARS           | DOLARS regulation    |
      | DOSARS           | DOSARS regulation    |
      | DTAR             | DTAR regulation      |
      | EDAR             | EDAR regulation      |
      | EPAAR            | EPAAR regulation     |
      | FEHBAR           | FEHBAR regulation    |
      | GSAM/R           | GSAM regulation      |
      | HHSAR            | HHSAR regulation     |
      | HSAR             | HSAR regulation      |
      | HUDAR            | HUDAR regulation     |
      | IAAR             | IAAR regulation      |
      | JAR              | JAR regulation       |
      | LIFAR            | LIFAR regulation     |
      | NFS              | NFS regulation       |
      | NRCAR            | NRCAR regulation     |
      | TAR              | TAR regulation       |
      | VAAR             | VAAR regulation      |

  @header-regulations-have-correct-logos
  Scenario: Regulation links have correct logos
    Given I am on the home page
    Then I see the following logos for the following regulations in the header:
      | FAR              | regulations/far-logo.png          |
      | FAR Smart Matrix | regulations/smart-matrix-logo.png |
      | Chapter 99 (CAS) | regulations/far-logo.png          |
      | DFARS            | regulations/dfars-logo.png        |
      | DFARSPGI         | regulations/dfars-logo.png        |
      | AFARS            | regulations/afars-logo.png        |
      | AFFARS           | regulations/affars-logo.png       |
      | DARS             | regulations/dars-logo.png         |
      | DLAD             | regulations/dlad-logo.png         |
      | NMCARS           | regulations/nmcars-logo.png       |
      | SOFARS           | regulations/sofars-logo.png       |
      | TRANSFARS        | regulations/transfars-logo.png    |
      | AGAR             | regulations/agar-logo.png         |
      | AIDAR            | regulations/aidar-logo.png        |
      | CAR              | regulations/car-logo.png          |
      | DEARS            | regulations/dears-logo.png        |
      | DIARS            | regulations/diar-logo.png         |
      | DOLARS           | regulations/dolar-logo.png        |
      | DOSARS           | regulations/dosar-logo.png        |
      | DTAR             | regulations/dtar-logo.png         |
      | EDAR             | regulations/edar-logo.png         |
      | EPAAR            | regulations/epaar-logo.png        |
      | FEHBAR           | regulations/fehbar-logo.png       |
      | GSAM/R           | regulations/gsam-logo.png         |
      | HHSAR            | regulations/hhsar-logo.png        |
      | HSAR             | regulations/hsar-logo.png         |
      | HUDAR            | regulations/hudar-logo.png        |
      | IAAR             | regulations/iaar-logo.png         |
      | JAR              | regulations/jar-logo.png          |
      | LIFAR            | regulations/lifar-logo.png        |
      | NFS              | regulations/nfs-logo.png          |
      | NRCAR            | regulations/nrcar-logo.png        |
      | TAR              | regulations/tar-logo.png          |
      | VAAR             | regulations/vaar-logo.png         |

  @header-blank-search
  Scenario: Generic search without search term leads to correct page
    Given I am on the home page
    When I perform header generic search for ""
    Then I am taken to the regulation search page
    And I see the search term is "*"

  @header-blank-search-with-term
  Scenario: Generic search with search term leads to correct page
    Given I am on the home page
    When I perform header generic search for "term"
    Then I am taken to the regulation search page
    And I see the search term is "*"

  @header-website-search
  Scenario: Site search without search term leads to correct page
    Given I am on the home page
    When I perform header site search for ""
    Then I am taken to the site search page
    And I see the search term is "*"

  @header-website-search-with-term
  Scenario: Site search with search term leads to correct page
    Given I am on the home page
    When I perform header site search for "term"
    Then I am taken to the site search page
    And I see the search term is "term"

  @header-regulation-search
  Scenario: Regulation search without search term leads to correct page
    Given I am on the home page
    When I perform header regulation search for ""
    Then I am taken to the regulation search page
    And I see the search term is ""

  @header-regulation-search-with-term
  Scenario: Regulation search with search term leads to correct page
    Given I am on the home page
    When I perform header regulation search for "term"
    Then I am taken to the regulation search page
    And I see the search term is "term"
