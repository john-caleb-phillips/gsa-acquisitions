package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.general.Footer;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.When;

public class FooterSteps {

    private final Footer footer;

    public FooterSteps(Footer footer) {
        this.footer = footer;
    }

    @When("I click on footer link {string}")
    public void clickFooterMiddleLink(String linkName) {
        footer.clickFooterLink(linkName);
    }

    @When("I click on footer site info link {string}")
    public void clickFooterBottomLink(String linkName) {
        footer.clickSiteInfoLink(linkName);
    }

    @When("I click on footer icon for {string}")
    public void clickFooterIcon(String iconAltText) {
        footer.clickIcon(iconAltText);
    }

    @When("I try to sign up for FAR News in the footer")
    public void signupForFarNews() {
        footer.clickSignUpLink();
    }
}
