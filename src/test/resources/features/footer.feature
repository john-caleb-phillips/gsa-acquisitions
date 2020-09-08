@all @footer
Feature: Test all footer scenarios

  @verify-main-footer-links
  Scenario Outline: "<Link Text>" link goes to the correct page
    Given I am on the homepage
    And I click on footer link "<Link Text>"
    Then I see the url is "<Destination Url>"
    Examples:
      | Link Text           | Destination Url                                  |
      | Useful Links        | https://www.acquisition.gov/content/useful-links |
      | Training            | https://www.acquisition.gov/Training             |
      | FAR Resources       | https://www.acquisition.gov/Far_Resources        |
      | Acquisition Systems | https://www.acquisition.gov/Acquisition_Systems  |

  @verify-site-info-links
  Scenario Outline: "<Link Text>" link goes to the correct page
    Given I am on the homepage
    And I click on footer site info link "<Link Text>"
    Then I see the url is "<Destination Url>"
    Examples:
      | Link Text                   | Destination Url                                            |
      | Contact Us                  | https://www.acquisition.gov/contact-us?advagg=-1           |
      | Privacy and Security Notice | https://www.acquisition.gov/Privacy_Security               |
      | Accessibility Aids          | https://www.gsa.gov/website-information/accessibility-aids |

  @verify-sign-up-for-far-news-link
  Scenario: "Sign up for FAR News" link goes to the correct page
    Given I am on the homepage
    When I try to sign up for FAR News in the footer
    Then I see the url is "https://www.gsa.gov/policy-regulations/policy/acquisition-policy/office-of-acquisition-policy/governmentwide-acq-policy/regulatory-secretariat-division#farnews"

  @verify-twitter-icon
  Scenario: Share to Twitter link works
    Given I am on the homepage
    When I click on footer icon for "Twitter"
    Then I see the url domain is "twitter.com"
    And I see the url path is "/intent/tweet"
    And I see the url parameters are:
      | text | Home of All Federal Acquisitions. |
      | url  | http://www.acquisition.gov/       |

  @verify-facebook-icon
  Scenario: Share to Facebook link works
    Given I am on the homepage
    When I click on footer icon for "Facebook"
    Then I see the url is "https://www.facebook.com/GSA/"

  @verify-contact-us-icon
  Scenario: "Contact Us" icon goes to the correct page
    Given I am on the homepage
    When I click on footer icon for "Email"
    Then I see the url is "https://www.acquisition.gov/contact-us?advagg=-1"
