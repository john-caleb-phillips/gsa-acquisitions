package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.isdc.IsdcMainPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class IsdcPageSteps {

    BlazeLibrary blazeLibrary;
    PolicyNetworkPages policyNetworkPage;

    public IsdcPageSteps(BlazeLibrary blazeLibrary, PolicyNetworkPages policyNetworkPage) {
        this.blazeLibrary = blazeLibrary;
        this.policyNetworkPage = policyNetworkPage;
    }

    @When("I click on ISDC content link {string}")
    public void clickContentLink(String linkText) {
        policyNetworkPage.isdc().clickContentLink(linkText);
    }

    @When("I click on ISDC footer link {string}")
    public void clickFooterLink(String linkText) {
        policyNetworkPage.isdc().clickFooterLink(linkText);
    }

    @Then("I see the ISDC header is the following:")
    public void verifyPageHeader(String expectedHeader) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getHeader())
                .as("Verifying header")
                .isEqualTo(expectedHeader);
    }

    @Then("I see the ISDC header image matches the file {string}")
    public void verifyHederImage(String expectedImage) {
        BufferedImage fromFile = blazeLibrary.images().getFromFile(expectedImage);
        BufferedImage fromPage = policyNetworkPage.isdc().getHeaderImage();

        blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                .as("Comparing header image on the page to the file named '%s'", expectedImage)
                .isTrue();
    }

    @Then("I see the ISDC content is the following:")
    public void verifyPageContent(String expectedContent) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getContent())
                .as("Verifying content")
                .isEqualToIgnoringWhitespace(expectedContent);
    }

    @Then("I see the following table of ISDC debarring officials:")
    public void verifyDebarringOfficialsTable(List<List<String>> expectedTable) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getDebarringOfficialsTable())
                .as("Verifying debarring officials table")
                .isEqualTo(expectedTable);
    }

    @Then("I see the following table of ISDC regulation citations:")
    public void verifyRegulationCitationsTable(List<List<String>> expectedTable) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getRegulationsCitationsTable())
                .as("Verifying regulation citations table")
                .isEqualTo(expectedTable);
    }

    @Then("I see the following table of ISDC compelling reasons determinations:")
    public void verifyCompellingReasonsDeterminations(List<List<String>> expectedTable) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getCompellingReasonsDeterminationsTable())
                .as("Verifying regulation citations table")
                .isEqualTo(expectedTable);
    }

    @Then("I see that all the ISDC reporting links link to valid content")
    public void verifyReportingLinks() {
        for (IsdcMainPage.DownloadLink link : policyNetworkPage.isdc().getReportingLinks()) {
            try {
                link.url.openStream();
            } catch (NullPointerException | IOException e) {
                blazeLibrary.assertion().assertThat(true).as("File '%s': Could not open stream to URL '%s'",
                        link.name, link.url).isFalse();
            }
        }
    }

    @Then("I see that all the ISDC compelling reasons determinations link to valid content")
    public void verifyCompellingReasonsDeterminationLinks() {
        for (IsdcMainPage.DownloadLink link : policyNetworkPage.isdc().getCompellingReasonsDeterminationLinks()) {
            try {
                link.url.openStream();
            } catch (NullPointerException | IOException e) {
                blazeLibrary.assertion().assertThat(true).as("File '%s': Could not open stream to URL '%s'",
                        link.name, link.url).isFalse();
            }
        }
    }



    @Then("I see the following ISDC footer links:")
    public void verifyFooterLinks(List<String> expectedLinks) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getFooterLinks())
                .as("Verifying header")
                .containsExactlyElementsOf(expectedLinks);
    }
}
