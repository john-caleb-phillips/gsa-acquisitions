package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CaacPageSteps {

    BlazeLibrary blazeLibrary;
    PolicyNetworkPages policyNetworkPage;

    public CaacPageSteps(BlazeLibrary blazeLibrary, PolicyNetworkPages policyNetworkPage) {
        this.blazeLibrary = blazeLibrary;
        this.policyNetworkPage = policyNetworkPage;
    }

    @When("^I navigate to the \"(Main|Letters|Members)\" CAAC page$")
    public void navigateToSubPage(String desiredPage) {
        switch (desiredPage) {
            case "Main": policyNetworkPage.caac().returnToMainPage(); break;
            case "Letters": policyNetworkPage.caac().goToLettersPage(); break;
            case "Members": policyNetworkPage.caac().goToMembersPage(); break;
        }
    }

    @Then("I see the CAAC header is the following:")
    public void verifyPageHeader(String expectedContent) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getHeaderText())
                .as("Verifying header of CAAC page")
                .isEqualToIgnoringWhitespace(expectedContent);
    }


    @Then("I see the CAAC content is the following:")
    public void verifyPageContent(String expectedContent) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getContentText())
                .as("Verifying content of CAAC page")
                .isEqualToIgnoringWhitespace(expectedContent);
    }

    @Then("I see the CAAC letters table has the following headers:")
    public void verifyLettersTableHeader(List<String> expectedHeaders) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getLetterTableHeaders())
                .as("Verifying Letters table headers")
                .containsExactlyElementsOf(expectedHeaders);
    }

    @Then("I see the letters are ordered by number")
    public void verifyLettersOrder() {
        List<LocalDate> theDates = new ArrayList<>();
        policyNetworkPage.caac().forEachLetter(el -> theDates.add(el.getLetterNumber()));
        blazeLibrary.assertion().assertThat(theDates).isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Then("I see each letter subject links to a valid resource")
    public void verifyLetterSubjects() {
        String currentUrl = blazeLibrary.browser().getCurrentUrl();
        policyNetworkPage.caac().forEachLetter(el -> {
            el.clickSubject();
            blazeLibrary.assertion().assertThat(blazeLibrary.browser().getCurrentUrl()).isNotEqualTo(currentUrl);
            blazeLibrary.browser().closeTab();
        });
    }

    @Then("I see the number of deviations for each letter is correct")
    public void verifyLetterDeviations() {
        policyNetworkPage.caac().forEachDeviation(el -> blazeLibrary.assertion().assertThat(el.getNumberOfDeviationsInRow())
                .as("Verifying number of deviations in row")
                .isEqualTo(el.getNumberOfDeviationsInDropdown()));
    }

    @Then("I see the CAAC attachments table has the following headers:")
    public void verifyAttachmentHeaders(List<String> expectedHeaders) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getAttachmentsTableHeaders())
                .as("Verifying Letters table headers")
                .containsExactlyElementsOf(expectedHeaders);
    }

    @Then("I see each attachment name links to a valid pdf file")
    public void verifyAttachmentFiles() {
        policyNetworkPage.caac().forEachAttachment(el -> {
            blazeLibrary.assertion().assertThat(el.getFileUrl())
                    .as("Verifying file download ofr '%s'", el.getFileName()).isNotNull();
            if (blazeLibrary.assertion().wasSuccess()) {
                try {
                    el.getFileUrl().openStream();
                } catch (IOException e) {
                    blazeLibrary.assertion().assertThat(true)
                            .as("File could not be opened")
                            .isFalse();
                }
            }
        });
    }
}
