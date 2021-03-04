@all @policy-network @caac-page
Feature: Policy Network

  Scenario: Civilian Agency Acquisition Council (CAAC) - Main Page Layout
    Given I am on the CAAC page
    Then I see the CAAC header is the following:
      """
      Civilian Agency Acquisition Council (CAAC)
      """
    Then I see the CAAC content is the following:
      """
      CAAC Regulatory Authorization
      The CAAC is authorized under the Federal Acquisition Regulation at 48 CFR 1.2.

      The Council performs the following functions
      Assists the Administrator of General Services in developing and \
      maintaining the Federal Acquisition Regulation (FAR) System by \
      developing or reviewing all proposed changes to the FAR.

      Solicits the views of agencies, associations, and other interested \
      parties on those proposed changes to the FAR that involve issues of \
      interest to them. Solicitations need not be published in the Federal \
      Register; however, notice of the availability of proposed changes may \
      be published in the Federal Register if requested by a member of the Council.

      Coordinates its activities with the Defense Acquisition Regulations \
      Council (DARC). Each Council shall review the FAR changes proposed by \
      the other Council.

      (1) Disagreements between the two Councils (DARC and CAAC) shall be \
      resolved by the Chairpersons with the assistance of interested members.
      (2) If the Council Chairpersons cannot resolve a disagreement, the \
      Councils will refer the matter to successively higher levels in GSA, \
      National Aeronautics and Space Administration (NASA), and Department of \
      Defense (DOD) until agreement is reached.
      (3) If the Administrator of General Services, the Administrator of NASA, \
      and the Secretary of Defense cannot resolve a Council disagreement, the \
      disagreement will be referred to the Office of Federal Procurement Policy.
      (4) Makes decisions on FAR issues by majority vote. Each agency \
      represented on the Council has one vote. FAR changes will not be drafted \
      during Council meetings.


      Membership

      The members of the Council are senior procurement professionals with \
      substantial experience in Federal procurement. The Council is comprised \
      of a representative, or an alternate, designated by each of the \
      following departments and agencies:

      Department of Agriculture
      Department of Commerce
      Department of Energy
      Department of Health and Human Services
      Department of Homeland Security
      Department of the Interior
      Department of Labor
      Department of State
      Department of Transportation
      Department of Treasury
      Department of Veterans Affairs
      Environmental Protection Agency
      General Services Administration
      Small Business Administration
      Social Security Administration

      The Chairperson of the Council is a senior procurement professional \
      with recognized expertise. The Chairperson is designated by the Deputy \
      Assistant Administrator for Acquisition Policy, GSA. GSA provides an \
      Executive Secretary, a legal counsel, and any other necessary staff and \
      administrative support.

      Responsibilities
      Administrator of General Services. The Administrator of General Services
      (1) Develops and maintains the FAR in conjunction with the Secretary of \
      Defense and the Administrator of NASA, under the provisions of sections \
      201(a) and 205(c) of the Federal Property and Administrative Services \
      Act of 1949, as amended (40 U.S.C. 481(a) and 486(c)).
      (2) Publishes the FAR in the Federal Register and in a loose-leaf \
      edition. The loose-leaf edition is distributed to procurement \
      agencies at a prorated cost.

      Chairperson of the Council. The Chairperson of the Council
      (1) Calls meetings of the Council at least monthly and is responsible \
      for the operation of the Council and its staff.
      (2) Determines which matters will be referred to the Council and which \
      will be handled by the staff. All matters are subject to review by \
      the Council.
      (3) Assigns work to agency representatives according to an agency's \
      interest, but only with the concurrence of its representative.
      (4) Assigns a staff member to provide assistance to each project handled \
      by the Council.

      Members of the Council
      (1) Present the views of their agencies on proposed changes to the FAR.
      (2) Provide staff and administrative support for projects assigned to \
      them. Members may organize subgroups to assist in the project.

      Meetings are held at the call of the Chairperson. Any member may request \
      the Chairperson to call a meeting.
      """

  @verify-caac-page-links
  Scenario Outline: Civilian Agency Acquisition Council (CAAC) - Verify main page links work correctly
    Given I am on the CAAC page
    When I navigate to the "<Link Text>" CAAC page
    Then I am taken to the <Destination> page
    Examples:
      | Link Text | Destination  |
      | Letters   | CAAC letters |
      | Members   | CAAC members |

  @caac-letters-page-layout
  Scenario: Civilian Agency Acquisition Council (CAAC) - Verify Letters page layout
    Given I am on the CAAC letters page
    Then I see the CAAC header is the following:
      """
      CAAC Letters
      """
    And I see the CAAC content is the following:
      """
      Following is a list of letters issued by the Chairperson of the Civilian \
      Agency Acquisition Council (CAAC). The letter serves as consultation in \
      accordance with FAR 1.404 for an authorized agency official to issue a \
      class deviation as provided in the letter. Click on the letter subject \
      to view the CAAC letter.

      Associated with each fiscal year 2018 CAAC letter is a list of agencies \
      which have informed us they issued a class deviation associated with the \
      CAAC letter. For agencies that have approved the posting of their class \
      deviation, simply click on the agency's name to view the class \
      deviation. For agencies that have not approved the posting of their \
      class deviation there is an envelope icon next to their agency name to \
      be used to email the agency for information.

      Posting of previous fiscal year agency information regarding class \
      deviations is being considered.

      If you have any questions please contact GSARegSec@gsa.gov (link sends e-mail).

      Acrobat Reader is required to read these documents
      """

  @verify-caac-letters-page-links
  Scenario Outline: Civilian Agency Acquisition Council (CAAC) - Verify letters page links work correctly
    Given I am on the CAAC letters page
    And I navigate to the "<Link Text>" CAAC page
    Then I am taken to the <Destination> page
    Examples:
      | Link Text | Destination  |
      | Members   | CAAC members |
      | Main      | CAAC         |

  @caac-letters-page-table
  Scenario: Civilian Agency Acquisition Council (CAAC) - Verify letters table
    Given I am on the CAAC letters page
    When I navigate to the "Letters" CAAC page
    Then I see the CAAC letters table has the following headers:
      | Letter# | Subject | Date |
    And I see the letters are ordered by number
    And I see each letter subject links to a valid resource
    And I see the number of deviations for each letter is correct
    And I see the CAAC attachments table has the following headers:
      | Attachment | Size |
    And I see each attachment name links to a valid pdf file

  @caac-members-page-layout
  Scenario: Civilian Agency Acquisition Council (CAAC) - Verify Members page layout
    Given I am on the CAAC members page
    Then I see the CAAC header is the following:
      """
      List of CAAC Members
      """
    And I see the CAAC content is the following:
      """
      CIVILIAN AGENCY ACQUISITION COUNCIL

      MEMBERS' ADDRESSES AND PHONE NUMBERS

      (Updated: 3/2/2021)
      """

  @verify-caac-members-page-links
  Scenario Outline: Civilian Agency Acquisition Council (CAAC) - Verify Members page links work correctly
    Given I am on the CAAC members page
    And I navigate to the "<Link Text>" CAAC page
    Then I am taken to the <Destination> page
    Examples:
      | Link Text | Destination  |
      | Letters   | CAAC letters |
      | Main      | CAAC         |