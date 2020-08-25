@footer @all
Feature: Test all footer scenarios

  @verify-useful-links-link
  Scenario: "Useful Links" link goes to the correct page
    Given I am on the homepage
    And I click on footer link "Useful Links"
    Then I see the url is "https://www.acquisition.gov/content/useful-links"

  @verify-training-link
  Scenario: "Training" link goes to the correct page
    Given I am on the homepage
    And I click on footer link "Training"
    Then I see the url is "https://www.acquisition.gov/Training"

  @verify-far-resources-link
  Scenario: "FAR Resources" link goes to the correct page
    Given I am on the homepage
    And I click on footer link "FAR Resources"
    Then I see the url is "https://www.acquisition.gov/Far_Resources"

  @verify-acquisitions-systems-link
  Scenario: "Acquisition Systems" link goes to the correct page
    Given I am on the homepage
    And I click on footer link "Acquisition Systems"
    Then I see the url is "https://www.acquisition.gov/Acquisition_Systems"

  @verify-contact-us-link
  Scenario: "Contact Us" link goes to the correct page
    Given I am on the homepage
    And I click on footer site info link "Contact Us"
    Then I see the url is "https://www.acquisition.gov/contact-us?advagg=-1"

  @verify-privacy-and-security-notice-link
  Scenario: "Privacy and Security Notice" link goes to the correct page
    Given I am on the homepage
    And I click on footer site info link "Privacy and Security Notice"
    Then I see the url is "https://www.acquisition.gov/Privacy_Security"

  @verify-accessibility-aids-link
  Scenario: "Accessibility Aids" link goes to the correct page
    Given I am on the homepage
    And I click on footer site info link "Accessibility Aids"
    Then I see the url is "https://www.gsa.gov/website-information/accessibility-aids"

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
