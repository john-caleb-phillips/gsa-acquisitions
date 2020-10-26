@all @footer
Feature: Footer

  @verify-main-footer-links
  Scenario Outline: "<Link Text>" link goes to the correct page
    Given I am on the home page
    And I click on footer link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text           | Destination         |
      | Useful Links        | useful links        |
      | Training            | training            |
      | FAR Resources       | far resources       |
      | Acquisition Systems | acquisition systems |

  @verify-site-info-links
  Scenario Outline: "<Link Text>" link goes to the correct page
    Given I am on the home page
    And I click on footer site info link "<Link Text>"
    Then I am taken to the <Destination> page
    Examples:
      | Link Text                   | Destination          |
      | Contact Us                  | contact us           |
      | Privacy and Security Notice | privacy and security |
      | Accessibility Aids          | accessibility aids   |

  @verify-sign-up-for-far-news-link
  Scenario: "Sign up for FAR News" link goes to the correct page
    Given I am on the home page
    When I try to sign up for FAR News in the footer
    Then I see the url is "https://www.gsa.gov/policy-regulations/policy/acquisition-policy/office-of-acquisition-policy/governmentwide-acq-policy/regulatory-secretariat-division#farnews"

  @verify-twitter-icon
  Scenario: Share to Twitter link works
    Given I am on the home page
    When I click on footer icon for "Twitter"
    Then I see the url domain is "twitter.com"
    And I see the url path is "/intent/tweet"
    And I see the url parameters are:
      | text | Home of All Federal Acquisitions. |
      | url  | {Portal:TWITTER_URL}              |

  @verify-facebook-icon
  Scenario: Share to Facebook link works
    Given I am on the home page
    When I click on footer icon for "Facebook"
    Then I see the url is "https://www.facebook.com/GSA/"

  @verify-contact-us-icon
  Scenario: "Contact Us" icon goes to the correct page
    Given I am on the home page
    When I click on footer icon for "Email"
    Then I am taken to the contact us page
