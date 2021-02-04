@all @policy-network @isdc-page
Feature: Policy Network

  @verify-isdc-main-page-layout
  Scenario: Interagency Suspension and Debarment Committee (ISDC) - Main Page Layout
    Given I am on the ISDC page
    Then I see the ISDC header is the following:
      """
      lnteragency Suspension and Debarment Committee (ISDC)
      """
    And I see the ISDC header image matches the file "images/policy-network/isdc-logo-hd.png"
    And I see the ISDC content is the following:
      """
      About the ISDC \

      Section 4 of Executive Order 12549 on Debarment and Suspension \
      directed the establishment of the Interagency Suspension and Debarment \
      Committee (ISDC) to monitor implementation of the Order. This Order \
      mandates Executive departments and agencies to:

      • Participate in a government-wide system for debarment and suspension \
      from programs and activities involving Federal financial and \
      nonfinancial assistance and benefits,
      • Issue regulations with government-wide criteria and minimum due \
      process procedures when debarring or suspending participants, and
      • Enter debarred and suspended participants’ identifying information \
      on the General Services Administration list of excluded persons, now \
      known as the System for Award Management (SAM). Information placed on \
      SAM is the responsibility of the Agency issuing the suspension or \
      debarment action.

      The ISDC also facilitates lead agency coordination, serves as a forum \
      to discuss current suspension and debarment related issues, and assists \
      in developing unified Federal policy. When requested by OMB, the ISDC \
      serves as a regulatory drafting body for revisions to the \
      government-wide nonprocurement suspension and debarment common rule.

      Mission
      The Interagency Suspension and Debarment Committee (ISDC) serves as a \
      Federal forum to discuss Government-wide non-procurement and procurement \
      suspension and debarment related issues (administered under 2 C.F.R. \
      Part 180 and FAR 48 C.F.R. Subpart 9.4), facilitate lead agency \
      coordination, and assist in developing unified Federal policy. The \
      ISDC, upon Office of Management and Budget (OMB) requests, provides \
      recommendations to the OMB, regarding the Government-wide suspension \
      and debarment remedy.

      Vision
      The ISDC’s vision is to promote transparency and best practices across \
      its community to address business and integrity risks within the \
      framework of the Federal suspension and debarment remedy.

      ISDC Leadership
      Acting Chair: Lori Vassar, Department of the Interior-OIG
      Vice Chair: Lori Vassar, Department of the Interior-OIG
      Vice Chair:  Monica Aquino-Thieman, NASA
      """
    And I see the following ISDC footer links:
      | Debarring Officials               |
      | Debarment Regulations             |
      | Reporting                         |
      | Compelling Reasons Determinations |

  @verify-isdc-main-page-external-links
  Scenario Outline: Interagency Suspension and Debarment Committee (ISDC) - Main Page Content Links
    Given I am on the ISDC page
    When I click on ISDC content link "<Link Text>"
    Then I see the url is "<Destination Url>"
    Examples:
      | Link Text                         | Destination Url                                                                   |
      | Executive Order 12549             | https://www.archives.gov/federal-register/codification/executive-order/12549.html |
      | System for Award Management (SAM) | https://www.sam.gov/SAM/                                                          |

  @verify-isdc-main-page-footer-links
  Scenario Outline: Interagency Suspension and Debarment Committee (ISDC) - Main Page Footer Links
    Given I am on the ISDC page
    When I click on ISDC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                         | Destination             |
      | Debarring Officials               | ISDC officials          |
      | Debarment Regulations             | ISDC debarment          |
      | Reporting                         | ISDC reporting          |
      | Compelling Reasons Determinations | ISDC compelling reasons |

  @verify-isdc-officals-page-layout
  Scenario: Interagency Suspension and Debarment Committee (ISDC) - Debarring Officials Page Layout
    Given I am on the ISDC officials page
    Then I see the ISDC header is the following:
      """
      Interagency Suspension and Debarment Committee (ISDC)
      """
    And I see the ISDC content is the following:
      """
      Debarring Officials
      """
    And I see the following table of ISDC debarring officials:
      | Agency                                                                                           | SDO                                                  | POC                   | Contact Information                             |
      | U. S. Department of Agriculture (USDA) – Agriculture Marketing Service (AMS)                     | Bruce Summers                                        | Karen Comfort         | (202) 690-0187 karen.comfort@usda.gov           |
      | USDA – Agricultural Research Service (ARS)                                                       | Chavonda Jacobs-Young                                | Kim L. Radcliff       | (301)504-1141 kim.radcliff@usda.gov             |
      | USDA – Animal and Plant Health Inspection Service (APHIS)                                        | Kevin Shea                                           | Speros Miller         | (301) 851-2673 speros.n.miller@usda.gov         |
      | USDA – Economic Research Service (ERS)                                                           | Kelly Macguire                                       | Kim L. Radcliff       | (301)504-1141 kim.radcliff@usda.gov             |
      | USDA – Farm Service Agency (FSA)                                                                 | Richard Fordyce                                      | Jack Welch            | (202) 690-3297 jack.welch@wdc.usda.gov          |
      | USDA – Food and Nutrition Service (FNS)                                                          | Pam Miller                                           | Lynn Rodgers          | (703)305-2595 lynn.rodgers@usda.gov             |
      | USDA – Food Safety and Inspection Service (FSIS)                                                 | Paul Kiecker                                         | Carlynne S. Cockrum   | (202) 720-8971 carlynne.cockrum@usda.gov        |
      | USDA – Foreign Agricultural Service (FAS)                                                        | Ken Isley                                            | Tamara Marshall-Jones | (202) 720-0885 Tama.marshall-jones@usda.gov     |
      | USDA – Forest Service (FS)                                                                       | Victoria Christiansen                                | George Sears          | (703) 605-7662 george.sears@usda.gov            |
      | USDA – National Agricultural Statistics Service (NASS)                                           | Hubert Hamer, Jr.                                    | Kim L. Radcliff       | (301)504-1141 kim.radcliff@usda.gov             |
      | USDA – National Institute of Food and Agriculture (NIFA)                                         | Parag Chitnas                                        | Joseph Perez          | (202) 401-3486 joseph.perez@usda.gov            |
      | USDA – National Resources Conservation Service                                                   | Matt Lohr                                            | Jack Welch            | (202) 690-3297 jack.welch@usda.gov              |
      | USDA – Office of the Chief Economist (OCE)                                                       | Robert Johansson                                     | Hunter Colby          | (202) 690-2477 hunter.colby@usda.gov            |
      | USDA – Office of Contracting and Procurements                                                    | Tiffany Taylor                                       | Crandall Watson       | 202) 720-0262 Crandall.watson@usa.gov           |
      | USDA – Office of Partnership/Public Engagements                                                  | Mike Beatty                                          | Phyllis Holmes        | (202) 692-0262 phyllis.holmes@usda.gov          |
      | USDA – Risk Management Agency                                                                    | Marin Barbre                                         | Jack Welch            | (202) 690-3297 jack.welch@usda.gov              |
      | USDA – Rural Business and Cooperative Service (RBCS)                                             | Rebeckah Freeman Adcock                              | Janet Stouder         | (202) 720-9728 janet.stouder@usda.gov           |
      | USDA – Rural Housing Service (RHS)                                                               | Elizabeth Green                                      | Janet Stouder         | (202) 720-9728 janet.stouder@usda.gov           |
      | USDA – Rural Utilities Service (RUS)                                                             | Chad Rupe                                            | Janet Stouder         | (202) 720-9728 janet.stouder@usda.gov           |
      | U.S. Department of Commerce                                                                      | Barry Berkowitz                                      | Gregory Coss          | (202) 482-3134 gcoss1@doc.gov                   |
      | U.S. Department of Defense (DOD) Defense Advanced Research Projects Agency                       | Peter Highnam, Acting                                | Diane Sidebottom      | (703) 526-2287 Diane.Sidebottom@darpa.mil       |
      | DOD – Army                                                                                       | Karen H. Carlisle                                    | Mark A. Rivest        | (703) 693-1152 mark.a.rivest.civ@mail.mil       |
      | DOD – Air Force                                                                                  | Derek Santos                                         | Diane M. Canzano      | (571) 256-6665 diane.canzano@us.af.mil          |
      | DOD- Navy                                                                                        | Kerry Hotopp                                         | Wayne Wisniewski      | (202) 685-5439 wayne.wisniewski@navy.mil        |
      | DOD-Defense Health Agency (DHA)                                                                  | Salvatore Maida                                      | Ian Rothfuss          | (703) 681-6012 ian.f.rothfuss.civ@mail.mil      |
      | DOD - Defense Logistics Agency (DLA)                                                             | Jon Lightner                                         | Seth D. Cohen         | (571) 767-6386 dlasdostaff@dla.mil              |
      | DOD - Defense Information Systems Agency (DISA)                                                  | William Brazis                                       | James W. Debose       | (301) 225-4059 james.w.debose.civ@mail.mil      |
      | DOD- Missile Defense Agency (MDA)                                                                | William Goves                                        | Jennifer Woehler      | (256) 450-2814 Jennifer.woehler@mda.mil         |
      | DOD-National Geospatial-Intelligence Agency (NGA)                                                | Allison Stevens                                      | Bree Ermentrout       | (571) 557 2993 Bree.a.ermentrout@nga.mil        |
      | U.S. Department of Education                                                                     | Phillip Juengst                                      | Alfreida Pettiford    | (202) 245-6110 Alfreida.Pettiford@ed.gov        |
      | U.S. Department of Energy (DOE)                                                                  | Berta Schreiber                                      | Kevin M. Smith        | (202) 287-1614 kevin.m.smith@hq.doe.gov         |
      | DOE - National Nuclear Security Agency                                                           | S, Keith Hamilton                                    | Kenneth West          | (505) 845-4337 Kenneth.west@nnsa.doe.gov        |
      | U.S. Environmental Protection Agency                                                             | Duc Nguyen                                           | Kathleen Timmins      | (202) 564-5292 timmins.kathleen@epa.gov         |
      | Export-Import Bank of the United States                                                          | Lisa Terry                                           | John G. Connor        | (202) 565-3815 john.connor@exim.gov             |
      | U.S. General Services Administration                                                             | Bill Schmidt                                         | Dylan Mooney          | (202) 316-7509 dylan.mooney@gsa.gov             |
      | U.S. Department of Health and Human Services                                                     | David R. Dasher                                      | Tiffani Redding       | (202) 205-4321 tiffani.redding@hhs.gov          |
      | U.S. Department of Homeland Security (DHS)                                                       | Denise Roberson                                      | Denise Roberson       | (202) 875-0671 denise.e.roberson@ice.dhs.gov    |
      | DHS – Federal Emergency Management Agency (FEMA)                                                 | Mike Cameron                                         | Jennifer L. Ward      | (202) 212-7772 jennifer.ward@fema.dhs.gov       |
      | DHS – Immigration and Customs Enforcement (ICE)                                                  | Denise Roberson                                      | Michael Buckingham    | (202) 732-2783 michael.buckingham@ice.dhs.gov   |
      | DHS – Customs and Border Patrol (CBP)                                                            | Mark Ziner                                           | Virginia McPherson    | (202) 579-5845 virginia.h.mcpherson@cbp.dhs.gov |
      | U.S. Department of Housing and Urban Development (HUD)                                           | Craig T. Clemmensen                                  | Carmen Trice          | (202) 402-2516 Carmen.Y.Trice@hud.gov           |
      | U.S. Department of the Interior (DOI)                                                            | Lori Vassar                                          | Stanley Stocker       | (202) 208-5659 Stanley_Stocker@doioig.gov       |
      | U.S. Department of Justice (DOJ)                                                                 | Michael H. Allen                                     | Kristen Bucher-Hahn   | (202) 514-3452 Kristen.B.Hahn@usdoj.gov         |
      | U.S. Department of Labor (DOL)                                                                   | Carl Campbell                                        | Duyen Ritchie         | (202) 693-7277 ritchie.duyen.t@dol.gov          |
      | National Aeronautics and Space Administration (NASA)                                             | William T. McMurry Jr.                               | Monica Aquino-Thieman | (202) 358-2262 monica.aquino@nasa.gov           |
      | National Science Foundation (NSF)                                                                | F. Fleming Crim                                      | Francisco J. Ruben    | (703) 292-8060 fruben@nsf.gov                   |
      | Nuclear Regulatory Commission (NRC)                                                              | Jennifer Golder                                      | Robin Baum            | (301) 287-0950 Robin.baum@nrc.gov               |
      | Office of Personnel Management (OPM) (Procurement)                                               | Mark W. Lambert                                      | Soraya King           | (202) 606-4225 soraya.king@opm.gov              |
      | OPM - Federal Employee Health Benefits Program (FEHB)                                            | Paul St. Hillaire                                    | Rosetta Younger       | (202) 606-1838 rosetta.younger@opm.gov          |
      | Pension Benefit Guaranty Corporation (PBGC)                                                      | C. Paul Chalmers (Acting)                            | Dawn Velarde          | (202) 326-4400 X3799 velarde.dawn@pbgc.gov      |
      | U.S. Postal Service                                                                              | Karen Pompanella, Acting                             | Emmanuel Niba         | (202) 288-8378 Emmanuel.Niba@usps.gov           |
      | Small Business Administration (SBA) – Financial Assistance Programs                              | Susan Streich                                        | Oussama Bachraoui     | (202) 205-6714 Oussama.bachraoui@sba.gov        |
      | SBA – All Other Programs (Procurement)                                                           | John W. Klein                                        | Edmund Bender         | (202) 205-6455 Edmund.bender@sba.gov            |
      | U.S. Department of State                                                                         | Vince Chaverini                                      | Steven C. Johnston    | (703) 875-6769 johnstonsc@state.gov             |
      | Social Security Administration (SSA)                                                             | Michelle King                                        | Dawn Caracci          | (410) 965-5499 dawn.caracci@ssa.gov             |
      | U.S. Department of Transportation (DOT) – Federal Aviation Administration (FAA) (Nonprocurement) | David F. Cushing (Airports) and Jodie Baker (Safety) | Elizabeth Newman      | (202) 267-7713 Elizabeth.newman@faa.gov         |
      | DOT FAA (Procurement)                                                                            | Mark Bury                                            | Anne Brickates        | (202) 710-1820 Anne.p.brickates@faa.gov         |
      | DOT Federal Highway Administration (FHWA) (Procurement)                                          | Thomas Everett                                       | Lisa MacPhee          | (202) 366-1392 Lisa.macphee@dot.gov             |
      | DOT FHWA (Nonprocurement)                                                                        | Hari Kalla                                           | Lisa MacPhee          | (202) 366-1392 Lisa.macphee@dot.gov             |
      | DOT Federal Motor Carrier Safety Administration (FMCSA)                                          | Meera Joshi                                          | Jean Wulff            | (202) 366-0424 jean.wulff@dot.gov               |
      | DOT Federal Railroad Administration (FRA) (procurement)                                          | Tamela Riggs                                         | Terry Weems           | (202) 493-6165 Terry.weems@dot.gov              |
      | DOT Federal Railroad Administration (FRA) (nonprocurement)                                       | Tamela Riggs                                         | Matthew Lorah         | (202)493-6186 Matthew.lorah@dot.gov             |
      | DOT Federal Transit Administration (FTA)                                                         | Reginald Allen                                       | Jerry Stenquist       | (202) 493-8020 Jerry.Stenquist@dot.gov          |
      | DOT Maritime Administration (MARAD) (Procurement)                                                | Delia Davis                                          | Ashley Amano          | (202) 366-7049 Ashley.Amano@dot.gov             |
      | DOT MARAD (Nonprocurement)                                                                       | Rand Pixa                                            | Ryan Kabacinski       | (202) 366-8750 Ryan.Kabacinski@dot.gov          |
      | DOT National Highway Traffic Safety Administration (NHTSA)                                       | Cynthia Parker                                       | Anita Barber          | (202) 366-3989 Anita.barber@dot.gov             |
      | DOT Office of the Secretary (OST)                                                                | Patrick Ingram                                       | Melinda Riddick       | (202) 366-7058 Melinda.Riddick@dot.gov          |
      | DOT Pipeline and Hazardous Materials Safety Administration (PHMSA)                               | Jon Gatti, Acting                                    | Brandon Hollingshead  | (202) 366-0845 Brandon.Hollingshead@dot.gov     |
      | DOT John A. Volpe National Transportation Systems Center (VOLPE)                                 | Patrick Ingram                                       | Felecia McBride       | (617) 494-2734 Felecia.McBride@dot.gov          |
      | DOT - Saint Lawrence Seaway (GLS)                                                                | Kristin Mulvaney                                     | Carrie Lavigne        | (315) 764-3231 Carrie.lavigne@dot.gov           |
      | U.S. Department of the Treasury                                                                  | Michelle D. Sharpe, Actg.                            | Sean McClure          | (202) 622-0903 Sean.mcclure@treasury.gov        |
      | U.S Agency for International Development (USAID)                                                 | Patrick Robinson                                     | Katie Stohs           | (571) 243 5887 kstohs@usaid.gov                 |
      | U.S. Department of Veterans Affairs (VA)                                                         | Angela Billups                                       | William A. Cox        | (703) 441-4025 vasuspension&debarment@va.gov    |
    And I see the following ISDC footer links:
      | Debarring Officials               |
      | Debarment Regulations             |
      | Reporting                         |
      | Compelling Reasons Determinations |

  @verify-isdc-officials-page-footer-links
  Scenario Outline: Interagency Suspension and Debarment Committee (ISDC) - Debarring Officials Page Footer Links
    Given I am on the ISDC officials page
    And I click on ISDC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                         | Destination             |
      | Debarring Officials               | ISDC officials          |
      | Debarment Regulations             | ISDC debarment          |
      | Reporting                         | ISDC reporting          |
      | Compelling Reasons Determinations | ISDC compelling reasons |

  @verify-isdc-debarment-page-layout
  Scenario: Interagency Suspension and Debarment Committee (ISDC) - Debarment Regulations Page Layout
    Given I am on the ISDC debarment page
    Then I see the ISDC header is the following:
      """
      Interagency Suspension and Debarment Committee (ISDC)
      """
    And I see the ISDC content is the following:
      """
      Debarment Regulations

      Regulations
      Government-wide Nonprocurement Suspension and Debarment Common Rule \
      [68 FR 66533]

      Executive Order 12549 provides for a government-wide system of \
      nonprocurement (grants and cooperative agreements) debarment and \
      suspension. A person who is debarred or suspended is excluded from \
      federal financial and nonfinancial assistance and benefits under \
      federal programs and activities. Debarment or suspension of a \
      participant in a program by one agency has government-wide, reciprocal \
      effect.

      The government-wide Nonprocurement Suspension and Debarment Common Rule \
      has been published in three parts as listed below:

      Federal Register, Nonprocurement Common Rule, Pages 66533 – 66582 (pdf)
      Federal Register, Nonprocurement Common Rule, Pages 66583 – 66632 (pdf)
      Federal Register, Nonprocurement Common Rule, Pages 66633 – 66646 (pdf)

      2 CFR Part 180, “Guidance for Governmentwide Debarment and Suspension \
      (Nonprocurement)” [70 FR 51863]

      On August 31, 2005, the Office of Management and Budget updated and \
      relocated its governmentwide nonprocurement suspension and debarment \
      guidance. Clink on the following link for the official Federal Register \
      publication.
      On November 15, 2006, the Office of Management and Budget published a \
      final rule adopting the interim final guidance with changes. [71 FR 66431]

      The chart below provides the specific Code of Federal Regulation \
      citation for each agency’s adoption of the Nonprocurement Common Rule \
      on suspension and debarment.

      Federal Acquisition Regulation Subpart 9.4 Debarment, Suspension, and \
      Ineligibility

      This subpart prescribes policies and procedures governing the debarment \
      and suspension of contractors by agencies, provides for the listing of \
      contractors debarred, suspended, proposed for debarment, and declared \
      ineligible, and sets forth the consequences of this listing. Debarment \
      or suspension of a participant in a program by one agency has \
      government-wide, reciprocal effect.

      Subpart 9.4 of the FAR can be accessed at:
      http://www.acquisition.gov/content/subpart-94-debarment-suspension-and-ineligibility
      """
    And I see the following table of ISDC regulation citations:
      | Agency                                         | Code of Federal Regulation Citation |
      | National Aeronautics and Space Administration  | 2 CFR 1880.220                      |
      | Office of Personnel Management                 | 5 CFR 919                           |
      | Department of Agriculture                      | 2 CFR 417                           |
      | Department of Energy                           | 2 CFR 901                           |
      | Export-Import Bank                             | 2 CFR 3513                          |
      | Small Business Administration                  | 2 CFR 2700                          |
      | Department of Homeland Security                | 2 CFR 3000                          |
      | Election Assistance Commission                 | 2 CFR 5800                          |
      | Nuclear Regulatory Commission                  | 2 CFR 2000                          |
      | Department of Commerce                         | 2 CFR 1326                          |
      | Social Security Administration                 | 2 CFR 2336                          |
      | Office of National Drug Control Policy         | 2 CFR 1404                          |
      | Department of State                            | 2 CFR 601                           |
      | Agency for International Development           | 2 CFR 780                           |
      | Peace Corps                                    | 2 CFR 3700                          |
      | Inter-American Foundation                      | 22 CFR 1006                         |
      | African Development Foundation                 | 22 CFR 1508                         |
      | Department of Housing and Urban Development    | 2 CFR 2424                          |
      | Department of Justice                          | 2 CFR 2867                          |
      | Department of Labor                            | 29 CFR 98                           |
      | Federal Mediation and Conciliation Service     | 29 CFR 1471                         |
      | Department of Treasury                         | 31 CFR 19                           |
      | Department of Defense                          | 2 CFR 1125                          |
      | Department of Education                        | 2 CFR 3485                          |
      | National Archives and Records Administration   | 2 CFR 2600                          |
      | Department of Veterans Affairs                 | 2 CFR 801                           |
      | Environmental Protection Agency                | 2 CFR 1532                          |
      | General Services Administration                | 41 CFR 105-68                       |
      | Department of the Interior                     | 2 CFR 1400                          |
      | Department of Health and Human Services        | 2 CFR 376                           |
      | National Science Foundation                    | 2 CFR 2520                          |
      | National Endowment for the Arts                | 2 CFR 3254                          |
      | National Endowment for the Humanities          | 2 CFR 3369                          |
      | Institute of Museum and Library Services       | 2 CFR 3185                          |
      | Corporation for National and Community Service | 2 CFR 2200                          |
      | Department of Transportation                   | 2 CFR 1200                          |
    And I see the following ISDC footer links:
      | Debarring Officials               |
      | Debarment Regulations             |
      | Reporting                         |
      | Compelling Reasons Determinations |

  @verify-isdc-debarment-page-external-links
  Scenario: Interagency Suspension and Debarment Committee (ISDC) - Debarment Regulations Page Content Links
    Given I am on the ISDC debarment page
    Then I see the ISDC content links go to the following urls:
      | Federal Register, Nonprocurement Common Rule, Pages 66533 – 66582                    | https://s3.amazonaws.com/sitesusa/wp-content/uploads/sites/272/2014/09/66582.pdf     |
      | Federal Register, Nonprocurement Common Rule, Pages 66583 – 66632                    | https://s3.amazonaws.com/sitesusa/wp-content/uploads/sites/272/2014/09/66632.pdf     |
      | Federal Register, Nonprocurement Common Rule, Pages 66633 – 66646                    | https://s3.amazonaws.com/sitesusa/wp-content/uploads/sites/272/2014/09/66646.pdf     |
      | 70 FR 51863                                                                          | https://s3.amazonaws.com/sitesusa/wp-content/uploads/sites/272/2014/09/70FR51863.pdf |
      | 71 FR 66431                                                                          | https://s3.amazonaws.com/sitesusa/wp-content/uploads/sites/272/2014/09/71FR66431.pdf |
      | http://www.acquisition.gov/content/subpart-94-debarment-suspension-and-ineligibility | {Portal:HOMEPAGE}/far/subpart-9.4                                                    |

  @verify-isdc-debarment-page-footer-links
  Scenario Outline: Interagency Suspension and Debarment Committee (ISDC) - Debarment Regulations Page Footer Links
    Given I am on the ISDC debarment page
    And I click on ISDC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                         | Destination             |
      | Debarring Officials               | ISDC officials          |
      | Debarment Regulations             | ISDC debarment          |
      | Reporting                         | ISDC reporting          |
      | Compelling Reasons Determinations | ISDC compelling reasons |

  @verify-isdc-reporting-page-layout
  Scenario: Interagency Suspension and Debarment Committee (ISDC) - Reporting Page Layout
    Given I am on the ISDC reporting page
    Then I see the ISDC header is the following:
      """
      Interagency Suspension and Debarment Committee (ISDC)
      """
    And I see the ISDC content is the following:
      """
      Reporting

      FY2019
      FY19 Report by the Interagency Suspension and Debarment Committee on Federal Agency Suspension and Debarment Activities

      FY2018
      FY18 Report by the Interagency Suspension and Debarment Committee \
      on Federal Agency Suspension and Debarment Activities

      FY2017
      FY17 Report by the Interagency Suspension and Debarment Committee on \
      Federal Agency Suspension and Debarment Activities

      FY2016
      FY16 Report by the Interagency Suspension and Debarment Committee on \
      Federal Agency Suspension and Debarment Activities

      FY2015
      FY15 Report by the Interagency Suspension and Debarment Committee on \
      Federal Agency Suspension and Debarment Activities

      FY2014
      FY14 Report by the Interagency Suspension and Debarment Committee on \
      Federal Agency Suspension and Debarment Activities

      FY2013/2012
      Searchable, color version of the FY 12 and 13 Report

      FY12 and 13 Report by the Interagency Suspension and Debarment Committee \
      on Federal Agency Suspension and Debarment Activities

      FY 2011
      FY11 Report by the Interagency Suspension and Debarment Committee on \
      Federal Agency Suspension and Debarment Activities

      FY2010/2009
      FY09 and FY10 Report by the Interagency Suspension and Debarment \
      Committee on Federal Agency Suspension and Debarment Activities
      """
    And I see that all the ISDC reporting links link to valid content
    And I see the following ISDC footer links:
      | Debarring Officials               |
      | Debarment Regulations             |
      | Reporting                         |
      | Compelling Reasons Determinations |

  @verify-isdc-debarment-page-footer-links
  Scenario Outline: Interagency Suspension and Debarment Committee (ISDC) - Reporting Page Footer Links
    Given I am on the ISDC reporting page
    And I click on ISDC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                         | Destination             |
      | Debarring Officials               | ISDC officials          |
      | Debarment Regulations             | ISDC debarment          |
      | Reporting                         | ISDC reporting          |
      | Compelling Reasons Determinations | ISDC compelling reasons |

  @verify-isdc-compelling-reasons-page-layout
  Scenario: Interagency Suspension and Debarment Committee (ISDC) - Compelling Reasons Determinations Page Layout
    Given I am on the ISDC compelling reasons page
    Then I see the ISDC header is the following:
      """
      Interagency Suspension and Debarment Committee (ISDC)
      """
    And I see the ISDC content is the following:
      """
      Compelling Reasons Determinations

      The General Services Administration (GSA), as a member of the \
      Interagency Suspension and Debarment Committee (ISDC),  posts the \
      notices described below pursuant to 10 U.S.C. § 2393

      The Department of Defense (DoD) Compelling Reasons Determinations

      Under the debarment regulation at 48 C.F.R. § 9.405, new awards shall \
      not be made to a suspended, proposed for debarment, debarred, or \
      otherwise award ineligible contractor unless the agency head or \
      designee determines in writing  that  a compelling reason exists \
      requiring the award.  As required by 10 U.S.C. § 2393 (cited below), \
      Secretaries of military departments shall provide GSA with a notice \
      describing the Compelling Reason Determination issued pursuant to \
      48 C.F.R. § 9.405.  GSA is required to maintain each such action notice \
      on a publicly accessible website. The notices contained herein, meet \
      the requirement for GSA to make such notices publicly accessible.

      NOTE: This requirement does not apply to Civilian agencies.
      NOTE: In accordance with GSA's record retention rule for publicly posted \
      documents, Compelling Reasons Determinations will not be posted more \
      than three years from the end of the calendar year in which the \
      exclusion actions associated with the Compelling Reasons Determinations \
      have been terminated.

      10 U.S.C. § 2393 - Prohibition against doing business with certain \
      offerors or contractors

      (a)(1) Except as provided in paragraph (2), the Secretary of a military \
      department may not solicit an offer from, award a contract to, extend an \
      existing contract with, or, when approval by the Secretary of the award \
      of a subcontract is required, approve the award of a subcontract to, an \
      offeror or contractor which to the Secretary’s knowledge has been \
      debarred or suspended by another Federal agency unless—

      (A) in the case of debarment, the debarment of the offeror or contractor \
      by all other agencies has been terminated or the period of time \
      specified for such debarment has expired; and

      (B) in the case of a suspension, the period of time specified by all \
      other agencies for the  suspension of the offeror or contractor has \
      expired.

      (2) Paragraph (1) does not apply in any case in which the Secretary \
      concerned determines that there is a compelling reason to solicit an \
      offer from, award a contract to, extend a contract with, or approve a \
      subcontract with such offeror or contractor.

      (b) Whenever the Secretary concerned makes a determination described in \
      subsection (a)(2), he shall, at the time of the determination, transmit \
      a notice to the Administrator of General Services describing the \
      determination. The Administrator of General Services shall maintain \
      each such notice on a publicly accessible website to themaximum extent \
      practicable.
      """
    And I see the following table of ISDC compelling reasons determinations:
      | Army Compelling Reasons Determination Sep 2003 (1).pdf                     |
      | Army Compelling Reasons Determination Sep 2003 (2).pdf                     |
      | Army Compelling Reasons Determination Jan 2004.pdf                         |
      | Army Compelling Reasons Determination Mar 2004.pdf                         |
      | Army Compelling Reasons Determination Jul 2007.pdf                         |
      | Army Compelling Reasons Determination Nov 2007.pdf                         |
      | Army Compelling Reasons Determination Apr 2008.pdf                         |
      | Army Compelling Reasons Determination Dec 2009 (1).pdf                     |
      | Army Compelling Reasons Determination Dec 2009 (2).pdf                     |
      | Army Compelling Reasons Determination Dec 2009 (3).pdf                     |
      | Army Compelling Reasons Determination Dec 2009 (4).pdf                     |
      | Army Compelling Reasons Determination Dec 2009 (5).pdf                     |
      | Army Compelling Reasons Determination Dec 2009 (6).pdf                     |
      | Army Compelling Reasons Determination May 2010.pdf                         |
      | Army Compelling Reasons Determination June 2015 (1).pdf                    |
      | Army Compelling Reasons Determination June 2015 (2).pdf                    |
      | Army Compelling Reasons Determination June 2015 (3).pdf                    |
      | Army Compelling Reasons Determination June 2018.pdf                        |
      | Defense Logistics Agency Compelling Reasons Determination Nov 2009 (1).pdf |
      | Defense Logistics Agency Compelling Reasons Determination Nov 2009 (2).pdf |
      | Defense Logistics Agency Compelling Reasons Determination Jan 2010.pdf     |
      | Defense Logistics Agency Compelling Reasons Determination Apr 2010.pdf     |
      | Defense Logistics Agency Compelling Reasons Determination Mar 2010.pdf     |
      | Defense Logistics Agency Compelling Reasons Determination May 2010.pdf     |
      | Defense Logistics Agency Compelling Reasons Determination June 2010.pdf    |
      | Defense Logistics Agency Compelling Reasons Determination Nov 2010.pdf     |
      | Defense Logistics Agency Compelling Reasons Determination Dec 2014.pdf     |
    And I see that all the ISDC compelling reasons determinations link to valid content
    And I see the following ISDC footer links:
      | Debarring Officials               |
      | Debarment Regulations             |
      | Reporting                         |
      | Compelling Reasons Determinations |

  @verify-isdc-compelling-reasons-page-external-links
  Scenario: Interagency Suspension and Debarment Committee (ISDC) - Compelling Reasons Determinations Page Content Links
    Given I am on the ISDC compelling reasons page
    Then I see the ISDC content links go to the following urls:
      | 10 U.S.C. § 2393                             | https://uscode.house.gov/view.xhtml?req=granuleid:USC-prelim-title10-section2393&num=0&edition=prelim               |
      | 48 C.F.R. § 9.405                            | {Portal:HOMEPAGE}/far/subpart-4.11#i1122064                                                                         |
      | 10 U.S.C. § 2393                             | https://uscode.house.gov/view.xhtml?req=granuleid:USC-prelim-title10-section2393&num=0&edition=prelim               |
      | 48 C.F.R. § 9.405                            | {Portal:HOMEPAGE}/far/subpart-4.11#i1122064                                                                         |
      | retention rule for publicly posted documents | https://www.archives.gov/files/records-mgmt/rcs/schedules/independent-agencies/rg-0352/daa-0352-2016-0001_sf115.pdf |
      | 10 U.S.C. § 2393                             | https://uscode.house.gov/view.xhtml?req=granuleid:USC-prelim-title10-section2393&num=0&edition=prelim               |

  @verify-isdc-compelling-reasons-page-footer-links
  Scenario Outline: Interagency Suspension and Debarment Committee (ISDC) - Compelling Reasons Determinations Page Footer Links
    Given I am on the ISDC compelling reasons page
    And I click on ISDC footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                         | Destination             |
      | Debarring Officials               | ISDC officials          |
      | Debarment Regulations             | ISDC debarment          |
      | Reporting                         | ISDC reporting          |
      | Compelling Reasons Determinations | ISDC compelling reasons |