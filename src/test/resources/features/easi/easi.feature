@all @easi @wip
Feature: EASi Features

  @test-qwe
  Scenario Outline: This is Example <Number>
    When I run a test
    Examples:
      | Number |
      | 1      |
#      | 2      |
#      | 3      |
#      | 4      |

  Scenario: Use API Call to get key
    When I perform the EASi API call
    Then I see the API response is the following:
      """

      """