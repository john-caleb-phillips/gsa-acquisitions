@all @regulations @agar-regulation
Feature: Regulations

  @validate-regulation-agar-page-layout
  Scenario: AGAR - Page Layout
    Given I am on the AGAR regulation page
    Then I see the regulation header is the following:
      """
      Agriculture Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the AGAR Parts

      Current thru: DATE{D MMM YYYY}

      AGAR POC: None
      Last Update: DATE{D-MMM-YY}
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"

  @validate-regulation-agar-table
  Scenario: AGAR - Table Contents
    Given I am on the AGAR regulation page
    When I save the info from the table regulation rows
    Then the part number in each row is not blank
    And the title in each row is not blank
    And the "Print" icon in each row works correctly
    And the "PDF" icon in each row works correctly

  @validate-regulation-agar-detail-pages
  Scenario: AGAR - Detail Pages
    Given I am on the AGAR regulation page
    When I save the info from the table regulation detail pages
    Then the regulation name on each table regulation detail page is "AGAR"
    And the part number on each table regulation detail page matches the row value
    And the breadcrumbs on each table regulation detail page are formatted correctly
    And the "Previous" button on each table regulation detail page works correctly
    And the "Next" button on each table regulation detail page works correctly
    And the "ToC" button on each table regulation detail page works correctly
    And the "Top" button on each table regulation detail page works correctly
    And the internal links on each table regulation detail page work correctly
    And the ToC link on each table regulation detail page all have a corresponding anchor