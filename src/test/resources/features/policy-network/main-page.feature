@all @policy-network @main-page
Feature: Policy Network

  @info-blocks
  Scenario: Correct Info Blocks
    Given I am on the main policy network page
    Then I see the following policy network blocks:
      | Chief Acquisition Officers Council             |
      | Civilian Agency Acquisition Council            |
      | Federal Acquisition Regulatory Council         |
      | lnteragency Suspension and Debarment Committee |
      | Other Policy Resources                         |

  @caoc-layout
  Scenario: Main Page - CAO Council block layout
    Given I am on the main policy network page
    Then I see that policy network block "CAO" has header image "images/policy-network/caoc-logo.jpg"
    And I see that policy network block "CAO" has the following text:
      """
      The Chief Acquisition Officers Council (the Council) is established \
      pursuant to Section 16 of the Office of Federal Procurement \
      Policy Act, as amended, 41 USC 403, et seq.

      The Council consists of a diverse group of acquisition \
      professionals in the Executive Branch established to provide a \
      senior level forum for monitoring and improving the federal \
      acquisition system.
      """

  @caac-layout
  Scenario: Main Page - CAAC block layout
    Given I am on the main policy network page
    Then I see that policy network block "CAAC" has the following header:
      """
      Civilian Agency Acquisition Council (CAAC)
      """
    And I see that policy network block "CAAC" has the following text:
      """
      The CAAC is authorized under the Federal Acquisition Regulation \
      at 48 CFR 1.2. to assist the Administrator of General Services in \
      developing and maintaining the Federal Acquisition Regulation \
      (FAR) System by developing or reviewing all proposed changes to \
      the FAR.
      """

  @far-council-layout
  Scenario: Main Page - FAR Council block layout
    Given I am on the main policy network page
    Then I see that policy network block "FAR Council" has the following header:
      """
      Federal Acquisition Regulatory Council (FAR Council)
      """
    And I see that policy network block "FAR Council" has the following text:
      """
      The Federal Acquisition Regulatory Council was established to \
      assist in the direction and coordination of Government-wide \
      procurement policy and Government-wide procurement \
      regulatory activities in the Federal Government, in accordance \
      with Title 41, Chapter 7, Section 421 of the Office of Federal \
      Procurement Policy (OFPP) Act.
      """

  @isdc-layout
  Scenario: Main Page - ISDC block layout
    Given I am on the main policy network page
    Then I see that policy network block "ISDC" has header image "images/policy-network/isdc-logo.png"
    And I see that policy network block "ISDC" has the following text:
      """
      lnteragency Suspension and Debarment Committee (ISDC). \
      Section 4 of Executive Order 12549 on Debarment and \
      Suspension directed the establishment of the lnteragency \
      Suspension and Debarment Committee (ISDC) to monitor \
      implementation of the Order.
      """

  @other-policy-resources-layout
  Scenario: Main Page - Other Policy Resources block layout
    Given I am on the main policy network page
    Then I see that policy network block "Other Policy Resources" has the following header:
      """
      Other Policy Resources
      """
    And I see that policy network block "Other Policy Resources" has the following links:
      | Defense Acquisition Regulations System Council |
      | Chief Financial Officers Council               |
      | Chief Human Capital Officers Council           |
      | Chief Information Officers Council             |

  @verify-links
  Scenario Outline: Main Page - Links work correctly
    Given I am on the main policy network page
    When I click the header link in policy network block "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text   | Destination |
      | CAO         | CAOC        |
      | CAAC        | CAAC        |
      | FAR Council | FARC        |
      | ISDC        | ISDC        |

  @verify-other-links
  Scenario Outline: Main Page - Other Policy Resources Links work correctly
    Given I am on the main policy network page
    When I click the link for other policy resource "<Link Text>"
    Then I see the url is "<Destination Url>"
    Examples:
      | Link Text                                      | Destination Url                                                                 |
      | Defense Acquisition Regulations System Council | https://www.federalregister.gov/agencies/defense-acquisition-regulations-system |
      | Chief Financial Officers Council               | https://www.cfo.gov/                                                            |
      | Chief Human Capital Officers Council           | https://www.chcoc.gov/                                                          |
      | Chief Information Officers Council             | https://www.cio.gov/                                                            |

