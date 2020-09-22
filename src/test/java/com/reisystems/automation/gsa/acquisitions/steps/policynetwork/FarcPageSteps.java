package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class FarcPageSteps {

    BlazeLibrary blazeLibrary;
    PolicyNetworkPages policyNetworkPage;

    public FarcPageSteps(BlazeLibrary blazeLibrary, PolicyNetworkPages policyNetworkPage) {
        this.blazeLibrary = blazeLibrary;
        this.policyNetworkPage = policyNetworkPage;
    }

    @Given("I am on the FARC page")
    public void goToFarcPage() {
        policyNetworkPage.farc().goToPage();
    }

    @When("I click on FARC footer link {string}")
    public void clickFooterLink(String linkText) {
        policyNetworkPage.farc().clickFooterLink(linkText);
    }

    @When("I click on FARC memoranda link {string}")
    public void clickMemorandaLink(String linkText) {
        policyNetworkPage.farc().clickFarcMemorandaLink(linkText);
    }

    @Then("I see the FARC header is the following:")
    public void verifyPageHeader(String expectedHeader) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.farc().getHeader())
                .as("Verifying header")
                .isEqualTo(expectedHeader);
    }

    @Then("I see the FARC content is the following:")
    public void verifyPageContent(String expectedContent) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.farc().getContent())
                .as("Verifying content")
                .isEqualToIgnoringWhitespace(expectedContent);
    }

    @Then("I see the following FARC footer links:")
    public void verifyFooterLinks(List<String> expectedLinks) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.farc().getFooterLinks())
                .as("Verifying header")
                .containsExactlyElementsOf(expectedLinks);
    }


}
