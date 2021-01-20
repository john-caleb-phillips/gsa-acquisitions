@all @policy-network @caoc-page
Feature: Policy Network

  @caoc-main-page-layout
  Scenario: Chief Acquisition Officers Council (CAOC) - Main Page Layout
    Given I am on the CAOC page
    Then I see the CAOC header image matches the file "images/policy-network/caoc-logo.jpg"
    And I see the CAOC header text is "CHIEF ACQUISITION OFFICERS COUNCIL"
    And I see the CAOC content is the following:
      """
      Welcome to the home page of the Chief Acquisition Officers Council (CAOC)

      The Chief Acquisition Officers Council (the Council) is established \
      pursuant to Section 16 of the Office of Federal Procurement Policy Act, \
      as amended, 41 USC 403, et seq.

      The Council consists of a diverse group of acquisition professionals in \
      the Executive Branch established to provide a senior level forum for \
      monitoring and improving the federal acquisition system. The Council \
      promotes effective business practices that ensure the timely delivery of \
      best value products and services to the agencies, achieve public policy \
      objectives, and further integrity, fairness, competition, and openness \
      in the federal acquisition system. The Council works closely with the \
      Administrator, Office of Federal Procurement Policy, and the Federal \
      Acquisition Regulatory Council to promote these business practices in \
      the acquisition system.

      Public Law 93-400, “The Office of Federal Procurement Policy Act”, as \
      amended, created the Office of Federal Procurement Policy (OFPP) in \
      1974, and placed it in the Office of Management and Budget (OMB). The \
      OFPP was created, among other purposes, to provide Government-wide \
      procurement policies “…which shall be followed by Executive agencies…” \
      in the procurement activities.

      “To further achieve effective, efficient, and economic administration \
      of the Federal procurement system, (Public Law 98-191, dated December 1, \
      1983) the head of each executive agency ‘shall designate a senior \
      procurement executive who shall be responsible for management direction \
      of the procurement system of the executive agency'” In 1999, an \
      interagency council, consisting of agency procurement executives was \
      chartered and named the Procurement Executives Council (PEC). Its \
      mission was to monitor and improve the Federal Acquisition System \
      consisting of the Federal Acquisition Regulation (FAR), which is the \
      primary document, and agency acquisition regulations that implement or \
      supplement the FAR.

      OFPP issued a memorandum on December 14, 2014 detailing its top three \
      priorities which are listed below. The memorandum, “Transforming the \
      Marketplace: Simplifying Federal Procurement to Improve Performance, \
      Drive Innovation, and Increase Savings,” can be found here.

      Buying as One Through Category Management
      Deploying Talent and Tools Across Agencies & Growing TalentWithin \
      Agencies to Drive Innovation
      Building Stronger Vendor Relations
      On January 16, 2003, a subsequent Charter was introduced at the PEC \
      Executive Committee Meeting, to be effective February 20, 2003. The \
      charter, in addition to some membership changes, includes a name change \
      from Procurement Executive Council to Federal Acquisition Council (FAC) \
      to provide greater flexibility and a more inclusive reach beyond \
      procurement.

      On June 1, 2004, a new Charter was effected further consolidating \
      Council purpose, membership, liaison, and working groups. This charter \
      renamed the Council as the Chief Acquisition Officers Council to better \
      address end-to-end acquisition issues.
      """
    And I see the CAOC sidebar header is "CAOC RESOURCES"
    And I see the CAOC sidebar has the following links:
      | Circulars                                                            |
      | Guides                                                               |
      | Memoranda                                                            |
      | Policy Letters                                                       |
      | Reports                                                              |
      | Other                                                                |
      | By Topical Areas                                                     |
      | Acquisition, Program Management and Small Business Excellence Awards |
      | DATA Act Section 5 Pilot                                             |
      | Procurement Data Clarification                                       |

  @caoc-main-page-side-bar-links
  Scenario: Chief Acquisition Officers Council (CAOC) - Verify side bar links
    Given I am on the CAOC page
    Then I see the CAOC sidebar links go to the following urls:
      | Circulars                                                            | https://www.whitehouse.gov/omb/information-for-agencies/circulars/                                                                                                        |
      | Guides                                                               | https://www.whitehouse.gov/omb/management/office-federal-procurement-policy/#_Office_of_Federal/                                                                          |
      | Memoranda                                                            | https://www.whitehouse.gov/omb/information-for-agencies/memoranda/                                                                                                        |
      | Policy Letters                                                       | https://www.whitehouse.gov/omb/management/office-federal-procurement-policy/#_Office_of_Federal_2                                                                         |
      | Reports                                                              | https://www.whitehouse.gov/omb/management/office-federal-procurement-policy/#_Office_of_Federal_3                                                                         |
      | Other                                                                | https://www.whitehouse.gov/omb/management/office-federal-procurement-policy/#_Office_of_Federal_4                                                                         |
      | By Topical Areas                                                     | https://www.whitehouse.gov/omb/management/office-federal-procurement-policy/#_Office_of_Federal_2                                                                         |
      | Acquisition, Program Management and Small Business Excellence Awards | https://www.fai.gov/resources/acquisition-program-management-and-small-business-excellence-awards                                                                         |
      | DATA Act Section 5 Pilot                                             | {Portal:HOMEPAGE}/data-act-pilot                                                                                                                                          |
      | Procurement Data Clarification                                       | {Portal:HOMEPAGE}/sites/default/files/page_file_uploads/Clarifying%20Procurement%20Data%20Information%20Based%20on%20Lessons%20Learned%20During%20DATA%20Act%20Audits.pdf |

  @verify-caoc-large-agencies-page-layout
  Scenario: Chief Acquisition Officers Council (CAOC) - Verify Large Agencies Page
    Given I am on the CAOC page
    When I navigate to the "Large Agencies" CAOC sub page
    Then I am taken to the CAOC large agencies page
    And I see the CAOC header image matches the file "images/policy-network/caoc-logo.jpg"
    And I see the CAOC header text is "LARGE AGENCIES"
    And I see the following headers in the CAOC agency table:
      | Agencies | Principal | Alternate |
    And I see the following agencies in the CAOC agency table:
      | Defense Contract Audit Agency                 |
      | Defense Contract Management Agency            |
      | Defense Logistics Agency                      |
      | Department of Agriculture                     |
      | Department of the Air Force                   |
      | Department of the Army                        |
      | Department of Commerce                        |
      | Department of Defense                         |
      | Department of Education                       |
      | Department of Energy                          |
      | Department of Health and Human Services       |
      | Department of Homeland Security               |
      | Department of Housing and Urban Development   |
      | Department of the Interior                    |
      | Department of Justice                         |
      | Department of Labor                           |
      | Department of the Navy                        |
      | Department of State                           |
      | Department of Transportation                  |
      | Department of the Treasury                    |
      | Department of Veterans Affairs                |
      | Environmental Protection Agency               |
      | General Services Administration               |
      | National Aeronautics and Space Administration |
      | National Science Foundation                   |
      | Nuclear Regulatory Commission                 |
      | Office of Personnel Management                |
      | Small Business Administration                 |
      | Social Security Administration                |
      | U.S. Agency for International Development     |

  @verify-caoc-small-agencies-page-layout
  Scenario: Chief Acquisition Officers Council (CAOC) - Verify Small Agencies Page
    Given I am on the CAOC page
    When I navigate to the "Small Agencies" CAOC sub page
    Then I am taken to the CAOC small agencies page
    And I see the CAOC header image matches the file "images/policy-network/caoc-logo.jpg"
    And I see the CAOC header text is "SMALL AGENCIES"
    And I see the following headers in the CAOC agency table:
      | Agencies | Principal |
    And I see the following agencies in the CAOC agency table:
      | Administrative Office of the US Courts                | Advisory Council on Historic Preservation                     |
      | American Battle Monuments Commission                  | Appalachian Regional Commission                               |
      | U.S. Agency for Global Media                          | Christopher Columbus Foundation                               |
      | Commission on Civil Rights                            | Commission on Fine Arts                                       |
      | Commodity Futures Trading Commission                  | Congressional Budget Office                                   |
      | Consumer Financial Protection Bureau                  | Consumer Product Safety Commission                            |
      | Corporation for National & Community Service          | Council of the Inspectors General on Integrity and Efficiency |
      | Court Services and Offender Supervision Agency        | Defense Nuclear Facilities Safety Board                       |
      | Delta Regional Authority                              | Denali Commission                                             |
      | Export-Import Bank of the US                          | Farm Credit Agency                                            |
      | Federal Communications Commission                     | Federal Deposit Insurance Corporation                         |
      | Federal Elections Commission                          | Federal Energy Regulatory Commission                          |
      | Federal Housing Finance Agency                        | Federal Labor Relations Authority                             |
      | Federal Maritime Commission                           | Federal Mediation and Conciliation Service                    |
      | Federal Mine Safety and Health Review Commission      | Federal Reserve Board                                         |
      | Federal Trade Commission                              | Government Publishing Office                                  |
      | Institute of Museum and Library Services              | Inter-American Foundation                                     |
      | James Madison Memorial Fellowship Foundation          | Japan-US Friendship Commission                                |
      | John F. Kennedy Center for Performing Arts            | Legal Services Corporation                                    |
      | Marine Mammal Commission                              | Medicare Payment Advisory Commission                          |
      | Merit Systems Protection Board                        | Millennium Challenge Corporation                              |
      | Morris K. Udall Foundation                            | National Assessment Governing Board                           |
      | National Capital Planning Commission                  | National Commission on Libraries & Info. Science              |
      | National Credit Union Administration                  | National Endowment for the Arts                               |
      | National Endowment for the Humanities                 | National Gallery of Art                                       |
      | National Labor Relations Board                        | National Mediation Board                                      |
      | National Nuclear Security Administration              | National Transportation Safety Board                          |
      | Nuclear Waste Technical Review Board                  | Occupational Safety & Health Review Commission                |
      | Office of Government Ethics                           | Office of Navajo & Hopi Indian Relocation                     |
      | U.S. International Development Finance Corporation    | Pension Benefit Guaranty Corporation                          |
      | Pretrial Services Agency for the District of Columbia | Securities and Exchange Commission                            |
      | Smithsonian Institution                               | Tennessee Valley Authority                                    |
      | US Access Board                                       | US Arctic Research Commission                                 |
      | US Equal Employment Opportunity Commission            | US Holocaust Memorial Museum                                  |
      | US Institute of Peace                                 | U. S. International Trade Commission                          |
      | US Postal Service                                     | US Postal Service Inspector General                           |
      | US Railroad Retirement Board                          | US Trade and Development Agency                               |

  @verify-caoc-history-page-layout
  Scenario: Chief Acquisition Officers Council (CAOC) - Verify CAOC History Page
    Given I am on the CAOC page
    When I navigate to the "CAOC History" CAOC sub page
    Then I am taken to the CAOC history page
    And I see the CAOC header image matches the file "images/policy-network/caoc-logo.jpg"
    And I see the CAOC header text is "CAOC HISTORY"
    And I see the CAOC content is the following:
      """
      Public Law 93-400, “The Office of Federal Procurement Policy Act”, as \
      amended, created the Office of Federal Procurement Policy (OFPP) in 1974 \
      and placed it in the Office of Management and Budget (OMB). The OFPP was \
      created, among other purposes, to provide Government-wide procurement \
      policies “…which shall be followed by Executive agencies…” in the \
      procurement activities.

      “To further achieve effective, efficient, and economic administration of \
      the Federal procurement system, (Public Law 98-191, dated December 1, \
      1983) the head of each executive agency … shall designate a senior \
      procurement executive who shall be responsible for management direction \
      of the procurement system of the executive agency…” In 1999, an \
      interagency council, consisting of agency procurement executives was \
      chartered and named the Procurement Executives Council (PEC). Its \
      mission was to monitor and improve the Federal Acquisition System \
      consisting of the Federal Acquisition Regulation (FAR), which is the \
      primary document, and agency acquisition regulations that implement or \
      supplement the FAR.

      Entering the year 2000, the PEC focused its vision on the following \
      strategic priorities:

      Create an acquisition workforce of mission-focused business leaders
      Optimize technology as a key business enabler
      Lead collaboration to achieve desired business results
      Effectively integrate socio-economic programs in the Federal Procurement System
      Transform the Federal Acquisition System for better business results

      On January 16, 2003, a new Charter was introduced at the PEC Executive \
      Committee Meeting, to be effective February 20, 2003. The charter, in \
      addition to some membership changes, includes a name change from \
      Procurement Executive Council to Federal Acquisition Council (FAC) to \
      provide greater flexibility and a more inclusive reach beyond procurement.

      Effective Date
      This Charter is effective as of June 1, 2004.
      """

  @verify-caoc-charter-page-layout
  Scenario: Chief Acquisition Officers Council (CAOC) - Verify CAOC Charter Page
    Given I am on the CAOC page
    When I navigate to the "CAOC Charter" CAOC sub page
    Then I am on the CAOC charter page
    And I see the CAOC header image matches the file "images/policy-network/caoc-logo.jpg"
    And I see the CAOC header text is "CAOC CHARTER"
    And I see the CAOC content is the following:
        """
        Authority
        The Chief Acquisition Officers (CAOs) Council (the Council) is \
        established pursuant to Section 16A of the Office of Federal \
        Procurement Policy (OFPP) Act, as amended, 41 U.S.C. 403, et seq.

        Purpose
        The Council is the principal interagency forum for monitoring and \
        improving the Federal acquisition system.

        The Council:
        – Develops recommendations for the Director of the Office of \
        Management and Budget (OMB) on acquisition policies and requirements; \
        Shares experiences, ideas, best practices, and innovative approaches;
        – Assists the OFPP Administrator in identifying, developing, and \
        coordinating multi- agency projects and other innovative initiatives;
        – Promotes effective business practices that ensure the timely \
        delivery of best value products and services and achieve public \
        policy objectives, working with the OFPP Administrator and the Federal \
        Acquisition Regulatory Council as necessary;
        – Furthers integrity, fairness, competition, openness, and efficiency; \
        and Along with the Office of Personnel Management, assesses and \
        addresses the hiring, training, and professional development needs of \
        the acquisition workforce.

        The Council also is focused on promoting the President’s Management \
        Agenda in all aspects of the acquisition system, as well as the \
        President’s specific acquisition-related initiatives and policies.

        Membership
        The Council is chaired by OMB’s Deputy Director for Management (DDM). \
        The OFPP Administrator leads the Council’s activities on behalf of the \
        DDM. The Vice-Chair shall be selected by the Council from among its \
        members. The Vice-Chair shall serve a one-year term, and may serve \
        multiple terms. Members consist of agency CAOs, the Under Secretary of \
        Defense for Acquisition, Logistics and Technology, and the Senior
        Procurement Executives of each military department. In addition, \
        members may include other senior agency officers appointed by the \
        heads of agencies in consultation with the Chair.

        Meetings
        The Council shall meet every three months, or as needed, at the \
        discretion of the Chair. Administrative support to the Council shall \
        be provided by the General Services Administration.

        Working Groups
        The Council may use working groups to address particular issues and \
        initiatives, as needed. Working groups consist of Council members or \
        other agency personnel with the requisite expertise to effectively \
        address the issues and initiatives. Working group members and tasks \
        are assigned by the Chair or Vice-Chair.

        Liasons
        The Council may appoint liaisons with the Chief Information Officers \
        Council, the Chief Financial Officers Council, the Human Resources \
        Management Council, the Small Business Procurement Advisory Council, \
        and other councils or organizations, as appropriate.

        Effective Date
        This Charter is effective as of June 1, 2004.

        Signed
        Clay Johnson III
        Deputy Director for Management
        Office of Management and Budget
        """