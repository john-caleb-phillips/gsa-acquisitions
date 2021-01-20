package com.reisystems.automation.gsa.acquisitions.steps.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.PolicyNetworkPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

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

    @When("I see the CAOC sidebar links go to the following urls:")
    public void clickSideBarLink(Map<String, String> sidebarLinks) {
        for (String link : sidebarLinks.keySet()) {
            String href = policyNetworkPage.caoc().getSideBarLinkHref(link);

            if (href.endsWith(".pdf")) {
                blazeLibrary.assertion().assertThat(href)
                        .withFailMessage("URL for CAOC sidebar link '%s' did not go to the expected URL.%nExpected: %s%nActual: %s", link, sidebarLinks.get(link), href)
                        .isEqualTo(sidebarLinks.get(link));
                if (blazeLibrary.assertion().wasSuccess()) {
                    try {
                        new URL(href).openStream();
                    } catch (MalformedURLException e) {
                        blazeLibrary.assertion().fail("CAOC sidebar link '%s' href attribute was not a valid URL. It was '%s'", link, href);
                    } catch (IOException e) {
                        blazeLibrary.assertion().fail("CAOC sidebar link '%s' could not be opened.", link);
                    }
                }
            } else {
                blazeLibrary.mouseAndKeyboard().keyUp(Keys.CONTROL).perform();
                policyNetworkPage.isdc().clickContentLink(link);
                blazeLibrary.mouseAndKeyboard().keyDown(Keys.CONTROL).perform();

                blazeLibrary.assertion().assertThat(blazeLibrary.browser().getCurrentUrl())
                        .withFailMessage("URL for CAOC sidebar link '%s' did not go to the expected URL.%nExpected: %s%nActual: %s", link, sidebarLinks.get(link), href)
                        .isEqualTo(sidebarLinks.get(link));

                blazeLibrary.browser().closeTab();
            }
        }
    }

    @Then("I see the CAOC header image matches the file {string}")
    public void verifyHeaderImage(String expectedImage) {
        BufferedImage fromFile = blazeLibrary.images().getFromFile(expectedImage);
        BufferedImage fromPage = policyNetworkPage.caoc().getHeaderImage();

        blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                .withFailMessage("CAOC header image did not match the validation image named '%s'", expectedImage)
                .isTrue();

        if (!blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.report().attachImage(fromPage, "PNG", "Image from the CAOC header");
            blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Image for from file '%s'", expectedImage));
            blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "Differences marked in red");
        }
    }

    @Then("I see the CAOC header text is {string}")
    public void verifyHeaderText(String expectedText) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getHeaderText())
                .as("CAOC header text was not as expected").isEqualTo(expectedText);
    }

    @Then("I see the CAOC content is the following:")
    public void verifyContentText(String expectedText) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getContentText())
                .as("CAOC content text was not as expected").isEqualToIgnoringWhitespace(expectedText);
    }

    @Then("I see the CAOC sidebar header is {string}")
    public void verifySideBarHeader(String expectedHeader) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getSideBarHeader())
                .as("CAOC sidebar header was not as expected").isEqualTo(expectedHeader);
    }

    @Then("I see the CAOC sidebar has the following links:")
    public void verifySidebarLink(List<String> expectedLinks) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getSideBarLinks())
                .as("CAOC sidebar links were not as expected").containsExactlyElementsOf(expectedLinks);
    }

    @Then("I see the following headers in the CAOC agency table:")
    public void verifyAgencyTableHeaders(List<String> expectedHeaders) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getAgencyTableHeaders())
                .as("Headers in the CAOC Agency table were not as expected").containsExactlyElementsOf(expectedHeaders);
    }

    @Then("I see the following agencies in the CAOC agency table:")
    public void verifyAgencyTableRows(List<String> expectedAgencies) {
        blazeLibrary.assertion().assertThat(policyNetworkPage.caoc().getAgencyNames())
                .as("Agencies in the CAOC Agency table were not as expected").containsExactlyElementsOf(expectedAgencies);
    }
}
