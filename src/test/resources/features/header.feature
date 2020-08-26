@all @header
Feature: Test all header scenarios

  @coronavirus-link-goes-to-the-correct-page
  Scenario: Coronavirus link goes to the correct page
    Given I am on the homepage
    When I click on coronavirus link in the header
    Then I see the url is "https://www.acquisition.gov/coronavirus"

  @889-link-goes-to-correct-page
  Scenario: 889 Link goes to the correct page
    Given I am on the homepage
    When I click on 889 information link in the header
    Then I see the url is "https://www.acquisition.gov/FAR-Case-2019-009/889_Part_B"

  @logo-goes-to-correct-page
  Scenario: Header logo goes to the correct page
    Given I am on the homepage
    When I click on header logo
    Then I see the url is "https://www.acquisition.gov/"

  @regulation-link-goes-to-correct-page
  Scenario: Regulation link goes to correct page
    Given I am on the homepage
    When I click on header link "Regulations"
    Then I see the url is "https://www.acquisition.gov/content/regulations"

  @archives-link-goes-to-correct-page
  Scenario: Archives link goes to correct page
    Given I am on the homepage
    When I click on header link "Archives"
    Then I see the url is "https://www.acquisition.gov/archives?type=FAR"

  @policy-network-link-goes-to-correct-page
  Scenario: Policy Network link goes to correct page
    Given I am on the homepage
    When I click on header link "Policy Network"
    Then I see the url is "https://www.acquisition.gov/policy-network"

  @search-link-goes-to-correct-page
  Scenario: Search link goes to correct page
    Given I am on the homepage
    When I click on header link "Search"
    Then I see the url is "https://www.acquisition.gov/search/advanced/"

  @policy-network-dropdown-links-go-to-correct-pages
  Scenario Outline: Policy Network dropdown links goes to correct pages
    Given I am on the homepage
    When I click on header Policy Network dropdown link "<Link Text>"
    Then I see the url is "<Destination>"
    Examples:
      | Link Text                                             | Destination                                                                  |
      | CAO.gov                                               | https://www.acquisition.gov/cao-home                                         |
      | Civilian Agency Acquisition Council (CAAC)            | https://www.acquisition.gov/content/civilian-agency-acquisition-council-caac |
      | Federal Acquisition Regulatory Council                | https://www.acquisition.gov/far-council                                      |
      | Interagency Suspension and Debarment Committee (ISDC) | https://www.acquisition.gov/isdc-home                                        |

  @regulations-dropdown-links-go-to-correct-pages
  Scenario Outline: Regulation dropdown links goes to correct pages
    Given I am on the homepage
    When I click on header Regulations dropdown link "<Link Text>"
    Then I see the url is "<Destination>"
    Examples:
      | Link Text        | Destination                                   |
      | FAR              | https://www.acquisition.gov/browse/index/far  |
      | FAR Smart Matrix | https://www.acquisition.gov/far-smart-matrix  |
      | Chapter 99 (CAS) | https://www.acquisition.gov/chapter_99        |
      | DFARS            | https://www.acquisition.gov/dfars             |
      | DFARSPGI         | https://www.acquisition.gov/dfarspgi          |
      | AFARS            | https://www.acquisition.gov/afars             |
      | AFFARS           | https://www.acquisition.gov/affars            |
      | DARS             | https://www.acquisition.gov/dars              |
      | DLAD             | https://www.acquisition.gov/dlad              |
      | NMCARS           | https://www.acquisition.gov/nmcars            |
      | SOFARS           | https://www.acquisition.gov/sofars            |
      | TRANSFARS        | https://www.acquisition.gov/transfars         |
      | AGAR             | https://www.acquisition.gov/agar              |
      | AIDAR            | https://www.acquisition.gov/aidar             |
      | CAR              | https://www.acquisition.gov/car               |
      | DEARS            | https://www.acquisition.gov/dears             |
      | DIARS            | https://www.acquisition.gov/diar              |
      | DOLARS           | https://www.acquisition.gov/dolar             |
      | DOSARS           | https://www.acquisition.gov/dosar             |
      | DTAR             | https://www.acquisition.gov/dtar              |
      | EDAR             | https://www.acquisition.gov/edar              |
      | EPAAR            | https://www.acquisition.gov/epaar             |
      | FEHBAR           | https://www.acquisition.gov/fehbar            |
      | GSAM/R           | https://www.acquisition.gov/browse/index/gsam |
      | HHSAR            | https://www.acquisition.gov/hhsar             |
      | HSAR             | https://www.acquisition.gov/hsar              |
      | HUDAR            | https://www.acquisition.gov/hudar             |
      | IAAR             | https://www.acquisition.gov/iaar              |
      | JAR              | https://www.acquisition.gov/jar               |
      | LIFAR            | https://www.acquisition.gov/lifar             |
      | NFS              | https://www.acquisition.gov/nfs               |
      | NRCAR            | https://www.acquisition.gov/nrcar             |
      | TAR              | https://www.acquisition.gov/tar               |
      | VAAR             | https://www.acquisition.gov/vaar              |


