package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CaacPageSteps {

    private final BlazeLibrary blazeLibrary;
    private final PolicyNetworkPages policyNetworkPage;

    public CaacPageSteps(BlazeLibrary blazeLibrary, PolicyNetworkPages policyNetworkPage) {
        this.blazeLibrary = blazeLibrary;
        this.policyNetworkPage = policyNetworkPage;
    }

    @When("^I navigate to the \"(Main|Letters|Members)\" CAAC page$")
    public void navigateToSubPage(String desiredPage) {
        switch (desiredPage) {
            case "Main":
                policyNetworkPage.caac().returnToMainPage();
                break;
            case "Letters":
                policyNetworkPage.caac().goToLettersPage();
                break;
            case "Members":
                policyNetworkPage.caac().goToMembersPage();
                break;
        }
    }

    @Then("I see the CAAC header is the following:")
    public void verifyPageHeader(String expectedContent) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getHeaderText())
                .as("CAAC page header was not as expected")
                .isEqualToIgnoringWhitespace(expectedContent);
    }


    @Then("I see the CAAC content is the following:")
    public void verifyPageContent(String expectedContent) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getContentText())
                .as("CAAC page content was not as expected")
                .isEqualToIgnoringWhitespace(expectedContent);
    }

    @Then("I see the CAAC letters table has the following headers:")
    public void verifyLettersTableHeader(List<String> expectedHeaders) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getLetterTableHeaders())
                .as("Headers of CAAC Letters table were not as expected")
                .containsExactlyElementsOf(expectedHeaders);
    }

    @Then("I see the letters are ordered by number")
    public void verifyLettersOrder() {
        List<LocalDate> theDates = new ArrayList<>();
        policyNetworkPage.caac().forEachLetter(el -> theDates.add(el.getLetterNumber()));
        blazeLibrary.assertion().assertThat(theDates)
                .as("CAAC Letters table was not correctly ordered by letter number")
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Then("I see each letter subject links to a valid resource")
    public void verifyLetterSubjects() {
        String currentUrl = blazeLibrary.browser().getCurrentUrl();
        policyNetworkPage.caac().forEachLetter(el -> {
            String subject = el.getSubject();
            blazeLibrary.mouseAndKeyboard().keyDown(Keys.CONTROL).perform();
            el.clickSubject();
            blazeLibrary.mouseAndKeyboard().keyUp(Keys.CONTROL).perform();
            blazeLibrary.assertion().assertThat(blazeLibrary.browser().getCurrentUrl())
                    .as("CAAC Letter '%s' did not link to a new page", subject)
                    .isNotEqualTo(currentUrl);
            blazeLibrary.browser().closeTab();
        });
    }

    @Then("I see the number of deviations for each letter is correct")
    public void verifyLetterDeviations() {
        AtomicInteger deviationCount = new AtomicInteger();
        policyNetworkPage.caac().forEachDeviation(el -> blazeLibrary.assertion().assertThat(el.getNumberOfDeviationsInRow())
                .as("Verifying number of deviations in row matches the number in the dropdown for deviation #%s", deviationCount.incrementAndGet())
                .isEqualTo(el.getNumberOfDeviationsInDropdown()));
    }

    @Then("I see the CAAC attachments table has the following headers:")
    public void verifyAttachmentHeaders(List<String> expectedHeaders) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caac().getAttachmentsTableHeaders())
                .as("Headers of CAAC Letters Attachments table were not as expected")
                .containsExactlyElementsOf(expectedHeaders);
    }

    @Then("I see each attachment name links to a valid pdf file")
    public void verifyAttachmentFiles() {
        policyNetworkPage.caac().forEachAttachment(attachment -> {
            String attachmentName = attachment.getFileName();
            blazeLibrary.assertion().assertThat(attachment.getFileUrl())
                    .withFailMessage("CAAC attachment '%s' had no download URL", attachmentName).isNotNull();
            if (blazeLibrary.assertion().wasSuccess()) {
                try {
                    attachment.getFileUrl().openStream();
                } catch (IOException e) {
                    blazeLibrary.assertion().fail("CAAC attachment '%s' could not be opened", attachmentName);
                }
            }
        });
    }
}
