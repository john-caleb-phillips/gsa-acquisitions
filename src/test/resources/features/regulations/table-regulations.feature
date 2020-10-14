@all @regulations @main-page
Feature: Regulations - Table Format

  @validate-regulation-afars
  Scenario: Table Regulation - AFARS
    Given I am on the regulation page
    When I navigate to the "AFARS" regulation page
    And I pause execution
    Then I see the regulation header is the following:
      """
      Army Federal Acquisition Regulation Supplement
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the AFARS Parts

      Last Updated: September 17, 2020

      AFARS POC: Office of the Deputy Assistant Secretary of the Army
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation at \
      https://procurement.army.mil/afars.

      (Please note: this link requires CAC and is not available to all users)
      """
#    And I see the link to "Regulation Archives" works in the regulation content
#    And I see each link in the regulation content works correctly
#    And I see the regulation table can be sorted by "Part Number"
#    And I see the regulation table can be sorted by "Title"
#    And I see "Print" works for each regulation part in the table
#    And I see "PDF" works for each regulation part in the table
#    And I see each part number has a title in the table
#    And I see the link to each regualtion part in the table works correctly
#    And I see for each part the "Next" button works correctly
#    And I see for each part the "Previous" button works correctly
#    And I see for each part the "ToC" button works correctly
#    And I see for each part the "Top" button works correctly
#    And I see for each part the internal links work correctly
#    And I see for each part all ToC links have an anchor
#    And I see for each part all anchors have a ToC link

  @validate-regulation-affars
  Scenario: Table Regulation - AFFARS

  @validate-regulation-dars
  Scenario: Table Regulation - DARS

  @validate-regulation-dlad
  Scenario: Table Regulation - DLAD

  @validate-regulation-nmcars
  Scenario: Table Regulation - NMCARS

  @validate-regulation-sofars
  Scenario: Table Regulation - SOFARS

  @validate-regulation-transfars
  Scenario: Table Regulation - TRANSFARS

  @validate-regulation-agar
  Scenario: Table Regulation - AGAR

  @validate-regulation-aidar
  Scenario: Table Regulation - AIDAR

  @validate-regulation-car
  Scenario: Table Regulation - CAR

  @validate-regulation-dears
  Scenario: Table Regulation - DEARS

  @validate-regulation-diars
  Scenario: Table Regulation - DIARS

  @validate-regulation-dolars
  Scenario: Table Regulation - DOLARS

  @validate-regulation-dosars
  Scenario: Table Regulation - DOSARS

  @validate-regulation-dtar
  Scenario: Table Regulation - DTAR

  @validate-regulation-edar
  Scenario: Table Regulation - EDAR

  @validate-regulation-epaar
  Scenario: Table Regulation - EPAAR

  @validate-regulation-fehbar
  Scenario: Table Regulation - FEHBAR

  @validate-regulation-hhsar
  Scenario: Table Regulation - HHSAR

  @validate-regulation-hsar
  Scenario: Table Regulation - HSAR

  @validate-regulation-hudar
  Scenario: Table Regulation - HUDAR

  @validate-regulation-iaar
  Scenario: Table Regulation - IAAR

  @validate-regulation-jar
  Scenario: Table Regulation - JAR

  @validate-regulation-lifar
  Scenario: Table Regulation - LIFAR

  @validate-regulation-nfs
  Scenario: Table Regulation - NFS

  @validate-regulation-nrcar
  Scenario: Table Regulation - NRCAR

  @validate-regulation-tar
  Scenario: Table Regulation - TAR

  @validate-regulation-vaar
  Scenario: Table Regulation - VAAR

  @validate-regulation-smart-matrix
  Scenario: Table Regulation - Smart Matrix