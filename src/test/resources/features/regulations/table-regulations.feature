@all @regulations @main-page
Feature: Regulations - Table Format

  @validate-regulation-afars
  Scenario: Table Regulation - AFARS
    Given I am on the AFARS regulation page
    Then I see the regulation header is the following:
      """
      Army Federal Acquisition Regulation Supplement
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the AFARS Parts

      Last Updated: September 23, 2020

      AFARS POC: Office of the Deputy Assistant Secretary of the Army
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation at \
      https://procurement.army.mil/afars.

      (Please note: this link requires CAC and is not available to all users)
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
#    And I see the regulation table can be sorted by "Part Number"
#    And I see the regulation table can be sorted by "Title"
#    And I see that for each part in the regulation table:
#        | there is a part number                          |
#        | there is a title                                |
#        | the "Print" icon works correctly                |
#        | the "PDF" icon works correctly                  |
#        | the link to the regulation part works correctly |
#   And I see that for each part detail in the regulation table:
#        | the "Next" button works correctly     |
#        | the "Previous" button works correctly |
#        | the "ToC" button works correctly      |
#        | the "Top" button works correctly      |
#        | the internal links work correctly     |
#        | all ToC links have an anchor          |
#        | all anchors have a ToC link           |

  @validate-regulation-affars @wip
  Scenario: Table Regulation - AFFARS

  @validate-regulation-dars @wip
  Scenario: Table Regulation - DARS

  @validate-regulation-dlad @wip
  Scenario: Table Regulation - DLAD

  @validate-regulation-nmcars @wip
  Scenario: Table Regulation - NMCARS

  @validate-regulation-sofars @wip
  Scenario: Table Regulation - SOFARS

  @validate-regulation-transfars @wip
  Scenario: Table Regulation - TRANSFARS

  @validate-regulation-agar @wip
  Scenario: Table Regulation - AGAR

  @validate-regulation-aidar @wip
  Scenario: Table Regulation - AIDAR

  @validate-regulation-car @wip
  Scenario: Table Regulation - CAR

  @validate-regulation-dears @wip
  Scenario: Table Regulation - DEARS

  @validate-regulation-diars @wip
  Scenario: Table Regulation - DIARS

  @validate-regulation-dolars @wip
  Scenario: Table Regulation - DOLARS

  @validate-regulation-dosars @wip
  Scenario: Table Regulation - DOSARS

  @validate-regulation-dtar @wip
  Scenario: Table Regulation - DTAR

  @validate-regulation-edar @wip
  Scenario: Table Regulation - EDAR

  @validate-regulation-epaar @wip
  Scenario: Table Regulation - EPAAR

  @validate-regulation-fehbar @wip
  Scenario: Table Regulation - FEHBAR

  @validate-regulation-hhsar @wip
  Scenario: Table Regulation - HHSAR

  @validate-regulation-hsar @wip
  Scenario: Table Regulation - HSAR

  @validate-regulation-hudar @wip
  Scenario: Table Regulation - HUDAR

  @validate-regulation-iaar @wip
  Scenario: Table Regulation - IAAR

  @validate-regulation-jar @wip
  Scenario: Table Regulation - JAR

  @validate-regulation-lifar @wip
  Scenario: Table Regulation - LIFAR

  @validate-regulation-nfs @wip
  Scenario: Table Regulation - NFS

  @validate-regulation-nrcar @wip
  Scenario: Table Regulation - NRCAR

  @validate-regulation-tar @wip
  Scenario: Table Regulation - TAR

  @validate-regulation-vaar @wip
  Scenario: Table Regulation - VAAR