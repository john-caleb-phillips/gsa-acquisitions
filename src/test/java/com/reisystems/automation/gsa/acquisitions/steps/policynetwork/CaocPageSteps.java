package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.image.BufferedImage;
import java.util.List;

public class CaocPageSteps {

    private final BlazeLibrary blazeLibrary;
    private final PolicyNetworkPages policyNetworkPage;

    public CaocPageSteps(BlazeLibrary blazeLibrary, PolicyNetworkPages policyNetworkPage) {
        this.blazeLibrary = blazeLibrary;
        this.policyNetworkPage = policyNetworkPage;
    }

    @When("^I navigate to the \"((?:Large|Small) Agencies|CAOC (?:History|Charter))\" CAOC sub page$")
    public void goToCaocSubPage(String pageName) {
        policyNetworkPage.caoc().goToSubPage(pageName);
    }

    @When("I click on the CAOC sidebar link {string}")
    public void clickSideBarLink(String linkText) {
        policyNetworkPage.caoc().clickSideBarLink(linkText);
    }

    @Then("I see the CAOC header image matches the file {string}")
    public void verifyHeaderImage(String expectedImage) {
        BufferedImage fromFile = blazeLibrary.images().getFromFile(expectedImage);
        BufferedImage fromPage = policyNetworkPage.caoc().getHeaderImage();

        blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                .as("Comparing header image on the page to the file named '%s'", expectedImage)
                .isTrue();
    }

    @Then("I see the CAOC header text is {string}")
    public void verifyHeaderText(String expectedText) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getHeaderText())
                .as("Verifying header text").isEqualTo(expectedText);
    }

    @Then("I see the CAOC content is the following:")
    public void verifyContentText(String expectedText) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getContentText())
                .as("Verifying content text").isEqualToIgnoringWhitespace(expectedText);
    }

    @Then("I see the CAOC sidebar header is {string}")
    public void verifySideBarHeader(String expectedHeader) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getSideBarHeader())
                .as("Verifying sidebar header").isEqualTo(expectedHeader);
    }

    @Then("I see the CAOC sidebar has the following links:")
    public void verifySidebarLink(List<String> expectedLinks) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getSideBarLinks())
                .as("Verifying sidebar links").containsExactlyElementsOf(expectedLinks);
    }

    @Then("I see the following headers in the CAOC agency table:")
    public void verifyAgencyTableHeaders(List<String> expectedHeaders) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getAgencyTableHeaders())
                .as("Verifying agency table headers").containsExactlyElementsOf(expectedHeaders);
    }

    @Then("I see the following agencies in the CAOC agency table:")
    public void verifyAgencyTableRows(List<String> expectedAgencies) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getAgencyNames())
                .as("Verifying agency table headers").containsExactlyElementsOf(expectedAgencies);
    }
}
