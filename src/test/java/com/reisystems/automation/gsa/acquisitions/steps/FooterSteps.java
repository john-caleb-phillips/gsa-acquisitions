package com.reisystems.automation.gsa.acquisitions.steps;

import com.reisystems.automation.gsa.acquisitions.pageobject.Footer;
import com.reisystems.blaze.controller.BlazeDriver;
import com.reisystems.blaze.interfaces.ClickResult;
import com.reisystems.blaze.report.LogLevel;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.When;

public class FooterSteps {

    private final Footer footer;

    public FooterSteps(Footer footer) {
        this.footer = footer;
    }


    @When("I click on footer link {string}")
    public void clickFooterMiddleLink(String linkName) {
        BlazeDriver.getElement(footer.footerLinkLocator(linkName)).click(ClickResult.REFRESH_PAGE);
    }

    @When("I click on footer site info link {string}")
    public void clickFooterBottomLink(String linkName) {
        BlazeDriver.getElement(footer.siteInfoLinkLocator(linkName)).click(ClickResult.OPEN_WINDOW_OR_TAB, ClickResult.REFRESH_PAGE);
    }

    @When("I click on footer icon for {string}")
    public void clickFooterIcon(String linkName) {
        BlazeDriver.getElement(footer.iconLocator(linkName)).click(ClickResult.OPEN_WINDOW_OR_TAB, ClickResult.REFRESH_PAGE);
    }

    @When("I try to sign up for FAR News in the footer")
    public void signupForFarNews() {
        BlazeDriver.getElement(footer.signUpLink()).click(ClickResult.OPEN_WINDOW_OR_TAB);
    }
}
