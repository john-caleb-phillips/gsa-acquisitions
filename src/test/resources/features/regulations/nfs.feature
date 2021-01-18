@all @regulations @nfs-regulation
Feature: Regulations

  @validate-regulation-nfs-page-layout
  Scenario: NFS Page Layout
    Given I am on the NFS regulation page
    Then I see the regulation header is the following:
      """
      NASA Federal Acquisition Regulation Supplement
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the NFS Parts

      The NFS is issued as Chapter 18 of Title 48, Code of Federal Regulations.  \
      The NFS has been modified through PN 19-12, dated November 1, 2019.

      NFS POC: NFS Code H Responsibilities

      Last Update: DATE{D-MMM-YYYY}

      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"

  @validate-regulation-nfs-table
  Scenario: NFS Table Contents
    Given I am on the NFS regulation page
    When I save the info from the table regulation rows
    Then the part number in each row is not blank
    And the title in each row is not blank
    And the "Print" icon in each row works correctly
    And the "PDF" icon in each row works correctly

  @validate-regulation-nfs-detail-pages
  Scenario: NFS Detail Pages
    Given I am on the NFS regulation page
    When I save the info from the table regulation detail pages
    Then the regulation name on each table regulation detail page is "NFS"
    And the part number on each table regulation detail page matches the row value
    And the breadcrumbs on each table regulation detail page are formatted correctly
    And the "Previous" button on each table regulation detail page works correctly
    And the "Next" button on each table regulation detail page works correctly
    And the "ToC" button on each table regulation detail page works correctly
    And the "Top" button on each table regulation detail page works correctly
    And the internal links on each table regulation detail page work correctly
    And the ToC link on each table regulation detail page all have a corresponding anchor