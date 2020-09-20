package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.general.Footer;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.When;

public class FooterSteps {

    private final Footer footer;
    private final BlazeLibrary blazeLibrary;

    public FooterSteps(BlazeLibrary blazelibrary, Footer footer) {
        this.blazeLibrary = blazelibrary;
        this.footer = footer;
    }

    @When("I click on footer link {string}")
    public void clickFooterMiddleLink(String linkName) {
        blazeLibrary.getElement(footer.footerLinkLocator(linkName)).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I click on footer site info link {string}")
    public void clickFooterBottomLink(String linkName) {
        blazeLibrary.getElement(footer.siteInfoLinkLocator(linkName)).click(blazeLibrary.defaults().OPEN_WINDOW_OR_TAB, blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I click on footer icon for {string}")
    public void clickFooterIcon(String linkName) {
        blazeLibrary.getElement(footer.iconLocator(linkName)).click(blazeLibrary.defaults().OPEN_WINDOW_OR_TAB, blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I try to sign up for FAR News in the footer")
    public void signupForFarNews() {
        blazeLibrary.getElement(footer.signUpLink()).click(blazeLibrary.defaults().OPEN_WINDOW_OR_TAB);
    }
}
