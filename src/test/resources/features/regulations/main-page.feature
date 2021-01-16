@all @regulations @main-page
Feature: Regulations - Main Page

  @correct-regulations-are-present
  Scenario: Correct regulations are present
    Given I am on the main regulation page
    Then I see the following regulations:
      |                  | Logo Image                            | Page Type |
      | FAR              | images/regulations/far-logo.png       | sidebar   |
      | Chapter 99 (CAS) | images/regulations/far-logo.png       | table     |
      | DFARS            | images/regulations/dfars-logo.png     | sidebar   |
      | DFARSPGI         | images/regulations/dfars-logo.png     | sidebar   |
      | AFARS            | images/regulations/afars-logo.png     | table     |
      | AFFARS           | images/regulations/affars-logo.png    | table     |
      | DARS             | images/regulations/dars-logo.png      | table     |
      | DLAD             | images/regulations/dlad-logo.png      | table     |
      | NMCARS           | images/regulations/nmcars-logo.png    | table     |
      | SOFARS           | images/regulations/sofars-logo.png    | table     |
      | TRANSFARS        | images/regulations/transfars-logo.png | table     |
      | AGAR             | images/regulations/agar-logo.png      | table     |
      | AIDAR            | images/regulations/aidar-logo.png     | table     |
      | CAR              | images/regulations/car-logo.png       | table     |
      | DEAR             | images/regulations/dear-logo.png      | table     |
      | DIAR             | images/regulations/diar-logo.png      | table     |
      | DOLAR            | images/regulations/dolar-logo.png     | table     |
      | DOSAR            | images/regulations/dosar-logo.png     | table     |
      | DTAR             | images/regulations/dtar-logo.png      | table     |
      | EDAR             | images/regulations/edar-logo.png      | table     |
      | EPAAR            | images/regulations/epaar-logo.png     | table     |
      | FEHBAR           | images/regulations/fehbar-logo.png    | table     |
      | GSAM/R           | images/regulations/gsam-logo.png      | sidebar   |
      | HHSAR            | images/regulations/hhsar-logo.png     | table     |
      | HSAR             | images/regulations/hsar-logo.png      | table     |
      | HUDAR            | images/regulations/hudar-logo.png     | table     |
      | IAAR             | images/regulations/iaar-logo.png      | table     |
      | JAR              | images/regulations/jar-logo.png       | table     |
      | LIFAR            | images/regulations/lifar-logo.png     | table     |
      | NFS              | images/regulations/nfs-logo.png       | table     |
      | NRCAR            | images/regulations/nrcar-logo.png     | table     |
      | TAR              | images/regulations/tar-logo.png       | table     |
      | VAAR             | images/regulations/vaar-logo.png      | table     |