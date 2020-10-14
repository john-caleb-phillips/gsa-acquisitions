@all @regulations @main-page
Feature: Regulations - Main Page

  @correct-regulations-are-present
  Scenario: Correct regulations are present
    Given I am on the regulation page
    Then I see the following regulations:
      |                  | Logo Image                     | Destination Url                               | Page Type |
      | FAR              | regulations/far-logo.png       | https://www.acquisition.gov/browse/index/far  | sidebar   |
      | Chapter 99 (CAS) | regulations/far-logo.png       | https://www.acquisition.gov/chapter_99        | table     |
      | DFARS            | regulations/dfars-logo.png     | https://www.acquisition.gov/dfars             | sidebar   |
      | DFARSPGI         | regulations/dfars-logo.png     | https://www.acquisition.gov/dfarspgi          | sidebar   |
      | AFARS            | regulations/afars-logo.png     | https://www.acquisition.gov/afars             | table     |
      | AFFARS           | regulations/affars-logo-2.png  | https://www.acquisition.gov/affars            | table     |
      | DARS             | regulations/dars-logo.png      | https://www.acquisition.gov/dars              | table     |
      | DLAD             | regulations/dlad-logo.png      | https://www.acquisition.gov/dlad              | table     |
      | NMCARS           | regulations/nmcars-logo.png    | https://www.acquisition.gov/nmcars            | table     |
      | SOFARS           | regulations/sofars-logo.png    | https://www.acquisition.gov/sofars            | table     |
      | TRANSFARS        | regulations/transfars-logo.png | https://www.acquisition.gov/transfars         | table     |
      | AGAR             | regulations/agar-logo.png      | https://www.acquisition.gov/agar              | table     |
      | AIDAR            | regulations/aidar-logo.png     | https://www.acquisition.gov/aidar             | table     |
      | CAR              | regulations/car-logo.png       | https://www.acquisition.gov/car               | table     |
      | DEARS            | regulations/dears-logo.png     | https://www.acquisition.gov/dears             | table     |
      | DIARS            | regulations/diar-logo.png      | https://www.acquisition.gov/diar              | table     |
      | DOLARS           | regulations/dolar-logo.png     | https://www.acquisition.gov/dolar             | table     |
      | DOSARS           | regulations/dosar-logo.png     | https://www.acquisition.gov/dosar             | table     |
      | DTAR             | regulations/dtar-logo.png      | https://www.acquisition.gov/dtar              | table     |
      | EDAR             | regulations/edar-logo.png      | https://www.acquisition.gov/edar              | table     |
      | EPAAR            | regulations/epaar-logo.png     | https://www.acquisition.gov/epaar             | table     |
      | FEHBAR           | regulations/fehbar-logo.png    | https://www.acquisition.gov/fehbar            | table     |
      | GSAM/R           | regulations/gsam-logo.png      | https://www.acquisition.gov/browse/index/gsam | sidebar   |
      | HHSAR            | regulations/hhsar-logo.png     | https://www.acquisition.gov/hhsar             | table     |
      | HSAR             | regulations/hsar-logo.png      | https://www.acquisition.gov/hsar              | table     |
      | HUDAR            | regulations/hudar-logo.png     | https://www.acquisition.gov/hudar             | table     |
      | IAAR             | regulations/iaar-logo.png      | https://www.acquisition.gov/iaar              | table     |
      | JAR              | regulations/jar-logo.png       | https://www.acquisition.gov/jar               | table     |
      | LIFAR            | regulations/lifar-logo.png     | https://www.acquisition.gov/lifar             | table     |
      | NFS              | regulations/nfs-logo.png       | https://www.acquisition.gov/nfs               | table     |
      | NRCAR            | regulations/nrcar-logo.png     | https://www.acquisition.gov/nrcar             | table     |
      | TAR              | regulations/tar-logo.png       | https://www.acquisition.gov/tar               | table     |
      | VAAR             | regulations/vaar-logo.png      | https://www.acquisition.gov/vaar              | table     |