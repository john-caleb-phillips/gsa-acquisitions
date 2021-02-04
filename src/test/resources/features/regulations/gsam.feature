@all @regulations @gsam-regulation
Feature: Regulations

  @validate-gsam-layout
  Scenario: GSAM - Page Layout
    Given I am on the GSAM regulation page
    Then the page title is "General Services Acquisition Manual (GSAM)"
    And the page breadcrumbs are the following:
      | Home | Regulations | General Services Acquisition Manual (GSAM) |
    And I see the following sidebar links:
      | Index | GSAM regulation |
    And I see the following sidebar parts:
      | 501 | 502 | 503 | 504 | 505 | 506 | 507 | 508 |
      | 509 | 510 | 511 | 512 | 513 | 514 | 515 | 516 |
      | 517 | 518 | 519 | 520 | 521 | 522 | 523 | 524 |
      | 525 | 526 | 527 | 528 | 529 | 530 | 531 | 532 |
      | 533 | 534 | 535 | 536 | 537 | 538 | 539 | 540 |
      | 541 | 542 | 543 | 544 | 545 | 546 | 547 | 548 |
      | 549 | 550 | 551 | 552 | 553 | 570 | 571 |     |
    And I see the following full download table headers:
      | Change Number | Effective Date | GSAM/R Archive | HTML | DITA | PDF | Word | iBook | Kindle |
    And each part and sub-part goes to the correct page
    And I see the following ways to get the part downloads:
      | HTML | DITA | Print |
    And each sidebar link goes to the correct page