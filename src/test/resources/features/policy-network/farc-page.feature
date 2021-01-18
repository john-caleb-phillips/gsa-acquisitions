@all @policy-network @farc-page
Feature: Policy Network - Federal Acquisition Regulatory Council (FARC)

  Scenario: Main Page Layout
    Given I am on the FARC page
    Then I see the FARC header is the following:
      """
      Federal Acquisition Regulatory Council
      """
    And I see the FARC content is the following:
      """
      About the FAR Council

      The Federal Acquisition Regulatory Council was established to assist in \
      the direction and coordination of Government-wide procurement policy \
      and Government-wide procurement regulatory activities in the Federal \
      Government, in accordance with Title 41,  Chapter 7, Section 421 of \
      the Office of Federal Procurement Policy (OFPP) Act. The Administrator, \
      in consultation with the Council, shall ensure that procurement \
      regulations, promulgated by executive agencies, are consistent with \
      the Federal Acquisition Regulation (FAR) and in accordance with any \
      policies issued pursuant to Section 405 of Title 41. The Council \
      manages coordinates controls and monitors the maintenance and \
      issuance of changes in the FAR.
      """
    And I see the following FARC footer links:
      | Council Members          |
      | Council Meeting Schedule |
      | Council Memoranda        |

  @verify-farc-main-page-links
  Scenario Outline: Main Page Footer Links
    Given I am on the FARC page
    When I click on FARC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                | Destination    |
      | Council Members          | FARC members   |
      | Council Meeting Schedule | FARC meeting   |
      | Council Memoranda        | FARC memoranda |

  @verify-farc-members-page-layout
  Scenario: Council Members Page Layout
    Given I am on the FARC members page
    Then I see the FARC header is the following:
      """
      Federal Acquisition Regulatory Council
      """
    And I see the FARC content is the following:
      """
      FAR Council Members

      In accordance with the OFPP Act, the FAR Council membership consists of: \
      the Administrator for Federal Procurement Policy and — (A) the Secretary \
      of Defense, (B) the Administrator of National Aeronautics and Space; and \
      (C) the Administrator of General Services.
      """
    Then I see the following FARC footer links:
      | Council Members          |
      | Council Meeting Schedule |
      | Council Memoranda        |

  @verify-farc-members-page-links
  Scenario Outline: Council Members Page Footer Links
    Given I am on the FARC members page
    And I click on FARC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                | Destination    |
      | Council Members          | FARC members   |
      | Council Meeting Schedule | FARC meeting   |
      | Council Memoranda        | FARC memoranda |

  @verify-farc-meeting-page-layout
  Scenario: Meeting Schedule Page Layout
    Given I am on the FARC meeting page
    Then I see the FARC header is the following:
      """
      Federal Acquisition Regulatory Council
      """
    And I see the FARC content is the following:
      """
      FAR Council Meeting Schedule

      The FAR  Council meets quarterly or as needed, during the fiscal year, \
      to discuss complex and controversial acquisition issues and to resolve \
      any disagreements between FAR members on implementation of FAR changes \
      and to provide direction to the eight FAR Teams.  The FAR Teams are

      1) Acquisition Environmental and Labor Law
      2) Acquisition Ethics and International Law
      3) Acquisition Finance
      4) Acquisition Implementation
      5) Acquisition Planning  and Methods
      6) Acquisition Small Business
      7) Acquisition Strategy
      8) Acquisition Technology
      """
    And I see the following FARC footer links:
      | Council Members          |
      | Council Meeting Schedule |
      | Council Memoranda        |

  @verify-farc-meeting-page-links
  Scenario Outline: Meeting Schedule Page Footer Links
    Given I am on the FARC meeting page
    And I click on FARC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                | Destination    |
      | Council Members          | FARC members   |
      | Council Meeting Schedule | FARC meeting   |
      | Council Memoranda        | FARC memoranda |

  @verify-farc-memoranda-page-layout
  Scenario: Council Memoranda Page Layout
    Given I am on the FARC memoranda page
    Then I see the FARC header is the following:
      """
      Federal Acquisition Regulatory Council
      """
    And I see the FARC content is the following:
      """
      FAR Council Memoranda

      Establishment of the Federal Acquisition Regulation Small Business Team (March 2, 2007) (1 page, 33 kb)

      Revised FAR Process (March 11, 2004) (1 page, 44 kb)
      """
    And I see the following FARC footer links:
      | Council Members          |
      | Council Meeting Schedule |
      | Council Memoranda        |

  @verify-farc-memoranda-page-external-links
  Scenario: Council Memoranda Page Links
    Given I am on the FARC memoranda page
    Then I see the FARC memoranda links go to the following urls:
      | Establishment of the Federal Acquisition Regulation Small Business Team | qwehttps://obamawhitehouse.archives.gov/sites/default/files/omb/assets/procurement_memo/small_bus_team_memo.pdf |
      | Revised FAR Process                                                     | qwehttps://obamawhitehouse.archives.gov/sites/default/files/omb/assets/procurement/memo_revised_far.pdf         |

  @verify-farc-memoranda-page-link
  Scenario Outline: Council Memoranda Page Footer Links
    Given I am on the FARC memoranda page
    And I click on FARC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                | Destination    |
      | Council Members          | FARC members   |
      | Council Meeting Schedule | FARC meeting   |
      | Council Memoranda        | FARC memoranda |
