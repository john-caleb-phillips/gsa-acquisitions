@all @smart-matrix
Feature: Smart Matrix

  Scenario: Testing
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I pause execution

  Scenario: Expand/Collapse Legend Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I expand the smart matrix legend
    Then I see the smart matrix legend is visible
    And the smart matrix legend has the following text:
      """

      """
    When I collapse the smart matrix legend
    Then I see the smart matrix legend is hidden

  Scenario: Show Full Matrix Checkbox Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check the box to show the full smart matrix
    Then I see the IBR checkbox is checked
    And I see the UCF checkbox is checked
    And I see the following contract checkboxes are checked:
      | FP SUP  | CR SUP | FP R&D  | CR R&D | FP SVC  |
      | CR SVC  | FP CON | CR CON  | T&M LH | LMV     |
      | COM SCV | DDR    | A&E     | FAC    | IND DEL |
      | TRN     | SAP    | UTL SVC | CI     |         |

  Scenario: Contract Dropdowns Become Inactive Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check any of the contract checkboxes
    Then the corresponding contract dropdown becomes active
    When I uncheck any of the contract checkboxes
    Then the corresponding contract dropdown becomes inactive

  Scenario: Step 1 Filters Work Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check the IBR checkbox
    And I apply the filters
    Then I see that the "" column only has the value ""

  Scenario: Step 2 Filters Work Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check the FP CON checkbox
    And I apply the filters
    Then I see that the "" column only has the value ""

  Scenario: Step 3 Filters Work Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check the FP CON checkbox
    And I select "" in te FP CON dropdown
    And I apply the filters
    Then I see that the "" column only has the value ""

  Scenario: Search Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I search for "Decision"
    Then I see that the "" column only has values containing "Decision"

  Scenario: Table Column Sorting Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check the box to show the full smart matrix
    And I apply the filters
    Then I see that the table can be sorted on any of the columns

  Scenario: Provision or Clause Links Work Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check the box to show the full smart matrix
    And I apply the filters
    When I click any of the links in the "Provision or Clause" column
    Then I am taken to the correct page

  Scenario: Prescribed In Links Work Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I check the box to show the full smart matrix
    And I apply the filters
    When I click any of the links in the "Prescribed In" column
    Then I am taken to the correct page

  Scenario: Copy Button Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I click on the Copy button
    Then I see the system clipboard contains(or equals?):
      """

      """

  Scenario: CSV Button Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I click on the CSV button
    Then I see that a csv file has been downloaded named ""

  Scenario: PDF Button Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I click on the PDF button
    Then I see that a pdf file has been downloaded named ""

  Scenario: Print Button Works Correctly
    Given I am on the homepage
    When I click on homepage square button "Smart Matrix"
    And I click on the Print button
    Then I am taken to the print page
    Then I
    Then I see that a pdf has been downloaded named ""

