@all @regulations @table-regulations
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
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-affars
  Scenario: Table Regulation - AFFARS
    Given I am on the AFFARS regulation page
    Then I see the regulation header is the following:
      """
      Air Force Federal Acquisition Regulation Supplement
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives
      MP TOC
      PGI TOC

      Current to AFAC 2019-1001 (Effective: 1 October 2019)
      AFFARS POC: SAF/AQCP
      Last Update: 29-Sep-20
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-dars
  Scenario: Table Regulation - DARS
    Given I am on the DARS regulation page
    Then I see the regulation header is the following:
      """
      DISA Acquisition Regulation Supplement
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the DARS Parts

      Current to 2020 Edition Parts 1, 2, 4, 6, 7, 15, 17, 19, 39, and 43
      Effective: 11-May-2020

      DARS POC: None
      Last Update: 29-May-2020
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-dlad
  Scenario: Table Regulation - DLAD
    Given I am on the DLAD regulation page
    Then I see the regulation header is the following:
      """
      Defense Logistics Acquisition Directive
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the DLAD Parts

      DLAD Update Effective October 15, 2020 through PROCLTR 2021-01
      (Revised October 15, 2020)

      DLAD POC: Anne Burleigh
      Last Update: 27-October-2020
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-nmcars
  Scenario: Table Regulation - NMCARS
    Given I am on the NMCARS regulation page
    Then I see the regulation header is the following:
      """
      Navy Marine Corps Acquisition Regulation Supplement
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the NMCARS Parts

      2018 Edition, Current to 18-11
      Effective: 11 May 2020

      NMCARS POC: Denise L. Randolph
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-sofars
  Scenario: Table Regulation - SOFARS
    Given I am on the SOFARS regulation page
    Then I see the regulation header is the following:
      """
      Special Operations Federal Acquisition Regulations Supplement
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the SOFARS Parts

      (USSOCOM) Current to SCN201811 [Edition]

      SOFARS POC: James (Trey) Goodwin
      Last Update: 28-Sept-20
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-transfars
  Scenario: Table Regulation - TRANSFARS
    Given I am on the TRANSFARS regulation page
    Then I see the regulation header is the following:
      """
      USTRANSCOM
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the TRANSFARS Parts

      (US Transportation Command)
      Current version: (2007-14)

      TRANSFARS POC: USTRANSCOM/TCAQ-P
      Last Update: 23-Sep-20
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-agar
  Scenario: Table Regulation - AGAR
    Given I am on the AGAR regulation page
    Then I see the regulation header is the following:
      """
      Agriculture's Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the AGAR Parts

      Current thru: 14 Mar 2016

      AGAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-aidar
  Scenario: Table Regulation - AIDAR
    Given I am on the AIDAR regulation page
    Then I see the regulation header is the following:
      """
      USAID Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our RegulationArchives

      A quick reference of the AIDAR Parts

      Current to: effective 16 March 2015

      AIDAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-car
  Scenario: Table Regulation - CAR
    Given I am on the CAR regulation page
    Then I see the regulation header is the following:
      """
      Department of Commerce Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the CAR Parts

      Current thru: 13 May 2015

      CAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the most \
      current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-dears
  Scenario: Table Regulation - DEAR
    Given I am on the DEAR regulation page
    Then I see the regulation header is the following:
      """
      Department of Energy Acquisition Regulations
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the DEAR Parts

      (Effective 15 Junl, 2016)

      DEAR POC: Barbara Binney
      Last Update: 24-Feb-17
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-diars
  Scenario: Table Regulation - DIAR
    Given I am on the DIAR regulation page
    Then I see the regulation header is the following:
      """
      Department of the Interior Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the DIAR Parts

      Current to: (84 FR 69344, Dec. 18, 2019)

      DIAR POC: Tiffany Harvey
      Last Update: Dec. 18, 2019
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-dolars
  Scenario: Table Regulation - DOLAR
    Given I am on the DOLAR regulation page
    Then I see the regulation header is the following:
      """
      Department of Labor Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the DOLAR Parts

      Current to: 27 May 2004

      DOLAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-dosars
  Scenario: Table Regulation - DOSAR
    Given I am on the DOSAR regulation page
    Then I see the regulation header is the following:
      """
      Department of State Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the DOSAR Parts

      Current to: 02 Sep, 2016

      DOSAR POC: Barbara Latvanas
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-dtar
  Scenario: Table Regulation - DTAR
    Given I am on the DTAR regulation page
    Then I see the regulation header is the following:
      """
      Department of the Treasury Acquisition Regulations
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the DTAR Parts

      Current to: DTAR, 16 Dec 2016 Edition

      DTAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-edar
  Scenario: Table Regulation - EDAR
    Given I am on the EDAR regulation page
    Then I see the regulation header is the following:
      """
      Education Department Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the EDAR Parts

      Current to: 08 March 2011

      EDAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-epaar
  Scenario: Table Regulation - EPAAR
    Given I am on the EPAAR regulation page
    Then I see the regulation header is the following:
      """
      Environmental Protection Agency Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the EPAAR Parts

      Current to: Effective 25 Jul 2016

      EPAAR POC: None
      Last Update: 28-Sep-20
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-fehbar
  Scenario: Table Regulation - FEHBAR
    Given I am on the FEHBAR regulation page
    Then I see the regulation header is the following:
      """
      Federal Employees Health Benefits Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the FEHBAR Parts

      Current to: 10 Jun 2015

      FEHBAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-hhsar
  Scenario: Table Regulation - HHSAR
    Given I am on the HHSAR regulation page
    Then I see the regulation header is the following:
      """
      Health & Human Services Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the HHSAR Parts

      Current to: 18 Dec 2015

      HHSAR POC: None
      Last Update: 16-Apr-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-hsar
  Scenario: Table Regulation - HSAR
    Given I am on the HSAR regulation page
    Then I see the regulation header is the following:
      """
      Department of Homeland Security Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the HSAR Parts

      Effective Date: 28 Jan 2019

      HSAR POC: Nancy Harvey
      Last Update: 26-May-2020
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-hudar
  Scenario: Table Regulation - HUDAR
    Given I am on the HUDAR regulation page
    Then I see the regulation header is the following:
      """
      US Department of Housing Development and Urban Development
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the HUDAR Parts

      Current to: 14 Apr 2016

      HUDAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-iaar
  Scenario: Table Regulation - IAAR
    Given I am on the IAAR regulation page
    Then I see the regulation header is the following:
      """
      Broadcasting Board of Governors
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the IAAR Parts

      (Broadcasting Board of Governors)
      Current to: 03 Apr 1985

      IAAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-jar
  Scenario: Table Regulation - JAR
    Given I am on the JAR regulation page
    Then I see the regulation header is the following:
      """
      Justice Acquisition Regulations
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the JAR Parts

      Current to: JAC 99-1

      JAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-lifar
  Scenario: Table Regulation - LIFAR
    Given I am on the LIFAR regulation page
    Then I see the regulation header is the following:
      """
      Life Insurance Federal Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the LIFAR Parts

      Current to: Oct 08, 2008

      LIFAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-nfs
  Scenario: Table Regulation - NFS
    Given I am on the NFS regulation page
    Then I see the regulation header is the following:
      """
      NASA Federal Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the NFS Parts

      The NFS is issued as Chapter 18 of Title 48, Code of Federal Regulations.  \
      The NFS has been modified through PN 19-12, dated November 1, 2019.

      NFS POC: NFS Code H Responsibilities

      Last Update: 1-Nov-2019

      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-nrcar
  Scenario: Table Regulation - NRCAR
    Given I am on the NRCAR regulation page
    Then I see the regulation header is the following:
      """
      Nuclear Regulatory Commission Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the NRCAR Parts

      Current to: 10 Sep 1999

      NRCAR POC: None
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-tar
  Scenario: Table Regulation - TAR
    Given I am on the TAR regulation page
    Then I see the regulation header is the following:
      """
      The Department of Transportation (DOT) Acquisition Regulation (TAR)
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the TAR Parts

      Current thru: 12 Sep 2014

      TAR POC: Jennifer Johnson
      Last Update: 7-Sep-18
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |

  @validate-regulation-vaar
  Scenario: Table Regulation - VAAR
    Given I am on the VAAR regulation page
    Then I see the regulation header is the following:
      """
      Veterans Affairs Acquisition Regulation
      """
    And I see the regulation content is the following:
      """
      Looking for older versions of regulations? Check our Regulation Archives

      A quick reference of the VAAR Parts

      Current to: 2008-25
      Effective: 26 Oct 2020

      VAAR POC: Procurement Policy Service Office
      Last Update: 02-Nov-20
      This is not an official version of the regulation. You can confirm the \
      most current version of the regulation on the eCFR.
      """
    And I see the link to "Regulation Archives" works in the regulation content
    And I see each link in the regulation content works correctly
    And I see the regulation table can be sorted by "Part Number"
    And I see the regulation table can be sorted by "Title"
    And I see that for each part in the regulation table:
      | there is a part number           |
      | there is a title                 |
      | the "Print" icon works correctly |
      | the "PDF" icon works correctly   |
    And I see that for each part detail in the regulation table:
      | the regulation name matches with row value |
      | the part number matches with row value     |
      | the breadcrumbs are correct                |
      | the "Next" button works correctly          |
      | the "Previous" button works correctly      |
      | the "ToC" button works correctly           |
      | the "Top" button works correctly           |
      | the internal links work correctly          |
      | the ToC links each have an anchor          |