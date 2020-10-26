@all @regulations @main-page
Feature: Regulations - Main Page

  @correct-regulations-are-present
  Scenario: Correct regulations are present
    Given I am on the main regulation page
    Then I see the following regulations:
      |                  | Logo Image                     | Page Type |
      | FAR              | regulations/far-logo.png       | sidebar   |
      | Chapter 99 (CAS) | regulations/far-logo.png       | table     |
      | DFARS            | regulations/dfars-logo.png     | sidebar   |
      | DFARSPGI         | regulations/dfars-logo.png     | sidebar   |
      | AFARS            | regulations/afars-logo.png     | table     |
      | AFFARS           | regulations/affars-logo.png    | table     |
      | DARS             | regulations/dars-logo.png      | table     |
      | DLAD             | regulations/dlad-logo.png      | table     |
      | NMCARS           | regulations/nmcars-logo.png    | table     |
      | SOFARS           | regulations/sofars-logo.png    | table     |
      | TRANSFARS        | regulations/transfars-logo.png | table     |
      | AGAR             | regulations/agar-logo.png      | table     |
      | AIDAR            | regulations/aidar-logo.png     | table     |
      | CAR              | regulations/car-logo.png       | table     |
      | DEARS            | regulations/dears-logo.png     | table     |
      | DIARS            | regulations/diar-logo.png      | table     |
      | DOLARS           | regulations/dolar-logo.png     | table     |
      | DOSARS           | regulations/dosar-logo.png     | table     |
      | DTAR             | regulations/dtar-logo.png      | table     |
      | EDAR             | regulations/edar-logo.png      | table     |
      | EPAAR            | regulations/epaar-logo.png     | table     |
      | FEHBAR           | regulations/fehbar-logo.png    | table     |
      | GSAM/R           | regulations/gsam-logo.png      | sidebar   |
      | HHSAR            | regulations/hhsar-logo.png     | table     |
      | HSAR             | regulations/hsar-logo.png      | table     |
      | HUDAR            | regulations/hudar-logo.png     | table     |
      | IAAR             | regulations/iaar-logo.png      | table     |
      | JAR              | regulations/jar-logo.png       | table     |
      | LIFAR            | regulations/lifar-logo.png     | table     |
      | NFS              | regulations/nfs-logo.png       | table     |
      | NRCAR            | regulations/nrcar-logo.png     | table     |
      | TAR              | regulations/tar-logo.png       | table     |
      | VAAR             | regulations/vaar-logo.png      | table     |