package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.isdc.IsdcMainPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class IsdcPageSteps {

    private final BlazeLibrary blazeLibrary;
    private final PolicyNetworkPages policyNetworkPage;

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

    @Then("I see the ISDC content links go to the following urls:")
    public void verifyContentLinks(List<List<String>> contentLinks) {
        for (List<String> link : contentLinks) {
            if (link.size() != 2) {
                blazeLibrary.assertion().fail("Table must have exactly two columns.");
                break;
            }

            String href = policyNetworkPage.isdc().getContentLinkHref(link.get(0));

            if (href.endsWith(".pdf")) {
                blazeLibrary.assertion().assertThat(href)
                        .withFailMessage("URL for ISDC content link '%s' did not go to the expected URL.%nExpected: %s%nActual: %s", link.get(0), link.get(1), href)
                        .isEqualTo(link.get(1));
                if (blazeLibrary.assertion().wasSuccess()) {
                    try {
                        new URL(href).openStream();
                    } catch (MalformedURLException e) {
                        blazeLibrary.assertion().fail("ISDC content link '%s' href attribute was not a valid URL. It was '%s'", link.get(0), href);
                    } catch (IOException e) {
                        blazeLibrary.assertion().fail("ISDC content link '%s' could not be opened.", link.get(0));
                    }
                }
            } else {
                blazeLibrary.mouseAndKeyboard().keyUp(Keys.CONTROL).perform();
                policyNetworkPage.isdc().clickContentLink(link.get(0));
                blazeLibrary.mouseAndKeyboard().keyDown(Keys.CONTROL).perform();

                blazeLibrary.assertion().assertThat(blazeLibrary.browser().getCurrentUrl())
                        .withFailMessage("URL for ISDC content link '%s' did not go to the expected URL.%nExpected: %s%nActual: %s", link.get(0), link.get(1), href)
                        .isEqualTo(link.get(1));

                blazeLibrary.browser().closeTab();
            }
        }
    }

    @Then("I see the ISDC header is the following:")
    public void verifyPageHeader(String expectedHeader) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getHeader())
                .as("ISDC header was not as expected")
                .isEqualTo(expectedHeader);
    }

    @Then("I see the ISDC header image matches the file {string}")
    public void verifyHeaderImage(String expectedImage) {
        BufferedImage fromFile = blazeLibrary.images().getFromFile(expectedImage);
        BufferedImage fromPage = policyNetworkPage.isdc().getHeaderImage();

        blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                .withFailMessage("IDC header image did not match the validation image named '%s'", expectedImage)
                .isTrue();

        if (!blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.report().attachImage(fromPage, "PNG", "Image from the ISDC header");
            blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Image for from file '%s'", expectedImage));
            blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "Differences marked in red");
        }
    }

    @Then("I see the ISDC content is the following:")
    public void verifyPageContent(String expectedContent) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getContent())
                .as("ISDC content was not as expected")
                .isEqualToIgnoringWhitespace(expectedContent);
    }

    @Then("I see the following table of ISDC debarring officials:")
    public void verifyDebarringOfficialsTable(List<List<String>> expectedTable) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getDebarringOfficialsTable())
                .as("ISDC Debarring Officials table was not as expected")
                .isEqualTo(expectedTable);
    }

    @Then("I see the following table of ISDC regulation citations:")
    public void verifyRegulationCitationsTable(List<List<String>> expectedTable) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getRegulationsCitationsTable())
                .as("ISDC Regulation Citations table was not as expected")
                .isEqualTo(expectedTable);
    }

    @Then("I see the following table of ISDC compelling reasons determinations:")
    public void verifyCompellingReasonsDeterminations(List<List<String>> expectedTable) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getCompellingReasonsDeterminationsTable())
                .as("ISDC Compelling Reasons Determinations table was not as expected")
                .isEqualTo(expectedTable);
    }

    @Then("I see that all the ISDC reporting links link to valid content")
    public void verifyReportingLinks() {
        for (IsdcMainPage.DownloadLink link : policyNetworkPage.isdc().getReportingLinks()) {
            try {
                link.url.openStream();
            } catch (NullPointerException | IOException e) {
                blazeLibrary.assertion().fail("Problem with ISDC reporting link '%s': Could not open stream to URL '%s'",
                        link.name, link.url);
            }
        }
    }

    @Then("I see that all the ISDC compelling reasons determinations link to valid content")
    public void verifyCompellingReasonsDeterminationLinks() {
        for (IsdcMainPage.DownloadLink link : policyNetworkPage.isdc().getCompellingReasonsDeterminationLinks()) {
            try {
                link.url.openStream();
            } catch (NullPointerException | IOException e) {
                blazeLibrary.assertion().fail("Problem with ISDC reporting link '%s': Could not open stream to URL '%s'",
                        link.name, link.url);
            }
        }
    }

    @Then("I see the following ISDC footer links:")
    public void verifyFooterLinks(List<String> expectedLinks) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.isdc().getFooterLinks())
                .as("ISDC footer links were not as expected")
                .containsExactlyElementsOf(expectedLinks);
    }
}
