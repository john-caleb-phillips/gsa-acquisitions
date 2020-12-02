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
      | FAR              | images/regulations/far-logo.png          |
      | FAR Smart Matrix | images/regulations/smart-matrix-logo.png |
      | Chapter 99 (CAS) | images/regulations/far-logo.png          |
      | DFARS            | images/regulations/dfars-logo.png        |
      | DFARSPGI         | images/regulations/dfars-logo.png        |
      | AFARS            | images/regulations/afars-logo.png        |
      | AFFARS           | images/regulations/affars-logo.png       |
      | DARS             | images/regulations/dars-logo.png         |
      | DLAD             | images/regulations/dlad-logo.png         |
      | NMCARS           | images/regulations/nmcars-logo.png       |
      | SOFARS           | images/regulations/sofars-logo.png       |
      | TRANSFARS        | images/regulations/transfars-logo.png    |
      | AGAR             | images/regulations/agar-logo.png         |
      | AIDAR            | images/regulations/aidar-logo.png        |
      | CAR              | images/regulations/car-logo.png          |
      | DEARS            | images/regulations/dears-logo.png        |
      | DIARS            | images/regulations/diar-logo.png         |
      | DOLARS           | images/regulations/dolar-logo.png        |
      | DOSARS           | images/regulations/dosar-logo.png        |
      | DTAR             | images/regulations/dtar-logo.png         |
      | EDAR             | images/regulations/edar-logo.png         |
      | EPAAR            | images/regulations/epaar-logo.png        |
      | FEHBAR           | images/regulations/fehbar-logo.png       |
      | GSAM/R           | images/regulations/gsam-logo.png         |
      | HHSAR            | images/regulations/hhsar-logo.png        |
      | HSAR             | images/regulations/hsar-logo.png         |
      | HUDAR            | images/regulations/hudar-logo.png        |
      | IAAR             | images/regulations/iaar-logo.png         |
      | JAR              | images/regulations/jar-logo.png          |
      | LIFAR            | images/regulations/lifar-logo.png        |
      | NFS              | images/regulations/nfs-logo.png          |
      | NRCAR            | images/regulations/nrcar-logo.png        |
      | TAR              | images/regulations/tar-logo.png          |
      | VAAR             | images/regulations/vaar-logo.png         |

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
