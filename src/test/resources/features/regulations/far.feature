@all @regulations @far-regulation
Feature: Regulations

  @validate-far-layout
  Scenario: FAR - Page Layout
    Given I am on the FAR regulation page
    Then the page title is "FAR"
    And the page breadcrumbs are the following:
      | Home | Regulations | FAR |
    And I see the following sidebar links:
      | Index                     | FAR regulation |
      | List of Sections Affected | updates        |
      | DOD Deviations            | DOD deviations |
      | CAAC Deviations           | CAAC letters   |
      | Chapter 99 (CAS)          | chapter 99     |
    And I see the following sidebar parts:
      | 1  | 2  | 3  | 4  | 5  | 6  | 7  | 8  |
      | 9  | 10 | 11 | 12 | 13 | 14 | 15 | 16 |
      | 17 | 18 | 19 | 20 | 21 | 22 | 23 | 24 |
      | 25 | 26 | 27 | 28 | 29 | 30 | 31 | 32 |
      | 33 | 34 | 35 | 36 | 37 | 38 | 39 | 40 |
      | 41 | 42 | 43 | 44 | 45 | 46 | 47 | 48 |
      | 49 | 50 | 51 | 52 | 53 |    |    |    |
    And I see the following full download table headers:
      | FAC Number | Effective Date | HTML | XML | PDF | Word | EPub | ITunes | Kindle |
    And each part and sub-part goes to the correct page
    And I see the following ways to get the part downloads:
      | HTML | XML | Print |
    And each sidebar link goes to the correct page