@all @smart-matrix
Feature: Smart Matrix

  Background: Go to the smart matrix page
    Given I am on the smart matrix page

  @expand-and-collapse-smart-matrix-legend
  Scenario: Expand/Collapse Legend Works Correctly
    When I expand the smart matrix legend
    Then the smart matrix legend is visible
    And the smart matrix legend has the following text:
      """
      Key:

      General:
      P or C = Provision or Clause                               UCF = Uniform Contract Format Section, when Applicable
      IBR = Is Incorporation by Reference Authorized? (See FAR 52.102)

      Type of Contract:
      DDR = Dismantling, Demolition, or Removal of Improvements  A&E = Architect-Engineering
      FP SUP = Fixed-Price Supply                                FAC = Facilities
      CR SUP = Cost-Reimbursement Supply                         IND DEL = Indefinite Delivery
      FP R&D = Fixed-Price Research & Development                TRN = Transportation
      CR R&D = Cost Reimbursement Research & Development         SAP = Simplified Acquisition Procedures (excluding micro-purchase)
      FP SVC = Fixed-Price Service                               UTL SVC = Utility Services
      CR SVC = Cost Reimbursement Service                        CI = Commercial Items
      FP CON = Fixed-Price Construction
      CR CON = Cost Reimbursement Construction                   Usage:
      T&M LH = Time & Material/Labor Hours                       R = Required
      LMV = Leasing of Motor Vehicles                            A = Required when Applicable
      COM SVC = Communication Services                           O = Optional
      """
    When I collapse the smart matrix legend
    Then the smart matrix legend is not visible

  @show-full-matrix-checkbox
  Scenario: Show Full Matrix Checkbox Works Correctly
    When I check the box to show the complete matrix
    Then the IBR checkbox is checked
    And the UCF checkbox is checked
    And the following contract checkboxes are checked:
      | FP SUP  | CR SUP | FP R&D  | CR R&D | FP SVC  |
      | CR SVC  | FP CON | CR CON  | T&M LH | LMV     |
      | COM SVC | DDR    | A&E     | FAC    | IND DEL |
      | TRN     | SAP    | UTL SVC | CI     |         |
    When I uncheck the box to show the complete matrix
    Then the IBR checkbox is not checked
    And the UCF checkbox is not checked
    And the following contract checkboxes are not checked:
      | FP SUP  | CR SUP | FP R&D  | CR R&D | FP SVC  |
      | CR SVC  | FP CON | CR CON  | T&M LH | LMV     |
      | COM SVC | DDR    | A&E     | FAC    | IND DEL |
      | TRN     | SAP    | UTL SVC | CI     |         |

  @contract-dropdowns-are-activated-and-inactivated
  Scenario: Contract Dropdowns Become Inactive Correctly
    When I check any of the contract checkboxes
    Then the corresponding contract dropdown becomes active
    When I uncheck any of the contract checkboxes
    Then the corresponding contract dropdown becomes inactive

  @step-1-filter-works-correctly
  Scenario: Step 1 Filters Work Correctly
    When I check the IBR checkbox
    And I apply the filters
    Then the smart matrix contains the following headers:
      | Provision or Clause | Prescribed in | P or C | IBR |
    When I check the UCF checkbox
    And I apply the filters
    Then the smart matrix contains the following headers:
      | Provision or Clause | Prescribed in | P or C | IBR | UCF |
    When I uncheck the IBR checkbox
    And I apply the filters
    Then the smart matrix contains the following headers:
      | Provision or Clause | Prescribed in | P or C | UCF |
    When I uncheck the UCF checkbox
    And I apply the filters
    Then the smart matrix contains the following headers:
      | Provision or Clause | Prescribed in | P or C |

  @step-2-filter-works-correctly
  Scenario: Step 2 Filters Work Correctly
    When I check any of the contract checkboxes
    Then applying the filters adds the contract column to the table
    When I uncheck any of the contract checkboxes
    Then applying the filters removes the contract column from the table

  @step-3-filter-works-correctly
  Scenario: Step 3 Filters Work Correctly
    When I check any of the contract checkboxes
    And I choose any of the contract dropdown options
    Then applying the filters results in the corresponding column showing only the selected option

  @search-works-correctly
  Scenario: Search Works Correctly
    When I search for "Certificate"
    Then I see every value in the "Provision or Clause" column contains "Certificate"

  @table-sorting-works-correctly
  Scenario: Table Column Sorting Works Correctly
    When I check the box to show the complete matrix
    And I apply the filters
    Then the table can be sorted on any of the columns

  @provision-or-clause-column-format
  Scenario: 'Provision or Clause' column format
    When I check the box to show the complete matrix
    And I apply the filters
    Then all values under the "Provision or Clause" column are in the right format

  @prescribed-in-column-format
  Scenario: 'Prescribed in' Column Format
    When I check the box to show the complete matrix
    And I apply the filters
    Then all values under the "Prescribed in" column are in the right format

  @provision-or-clause-links-work-correctly
  Scenario: 'Provision or Clause' Links Work Correctly
    When I check the box to show the complete matrix
    And I apply the filters
    When I click any of the links in the "Provision or Clause" column
    Then I am taken to the correct page

  @prescribed-in-links-work-correctly
  Scenario: 'Prescribed in' Links Work Correctly
    When I check the box to show the complete matrix
    And I apply the filters
    When I click any of the links in the "Prescribed in" column
    Then I am taken to the correct page

  @wip
  Scenario: Copy Button Works Correctly
    When I click on the Copy button
    Then I see the system clipboard contains:
      """

      """

  @wip
  Scenario: CSV Button Works Correctly
    When I click on the CSV button
    Then I see that a csv file has been downloaded named ""

  @wip
  Scenario: PDF Button Works Correctly
    When I click on the PDF button
    Then I see that a pdf file has been downloaded named ""

  @wip
  Scenario: Print Button Works Correctly
    When I click on the Print button
    Then I am taken to the print page
    Then I
    Then I see that a pdf has been downloaded named ""

