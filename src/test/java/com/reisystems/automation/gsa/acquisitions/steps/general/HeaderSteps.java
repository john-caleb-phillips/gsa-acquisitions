package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.general.Header;
import com.reisystems.automation.gsa.acquisitions.pageobject.smartmartix.SmartMatrixPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.image.BufferedImage;
import java.util.Map;


public class HeaderSteps {

    Header header;
    private final BlazeLibrary blazeLibrary;

    public HeaderSteps(BlazeLibrary blazelibrary, Header header) {
        this.blazeLibrary = blazelibrary;
        this.header = header;
    }

    @When("I click on coronavirus link in the header")
    public void clickCoronavirusLink() {
        header.clickCoronavirusLink();
    }

    @When("I click on 889 information link in the header")
    public void click889InformationLink() {
        header.click889InformationLink();
    }

    @When("I click on header logo")
    public void clickHeaderLogo() {
        header.clickLogo();
    }

    @When("I click on header link {string}")
    public void clickHeaderLink(String headerLinkText) {
        header.clickTopLevelLink(headerLinkText);
    }

    @When("I click on header Regulations dropdown link {string}")
    public void clickRegulationsDropdownLink(String linkText) {
        header.clickRegulationDropdownLink(linkText);
    }

    @When("I click on header Policy Network dropdown link {string}")
    public void clickPolicyNNetworkDropdownLink(String linkText) {
        header.clickPolicyNetworkDropdownLink(linkText);
    }

    @When("^I perform header (generic|site|regulation) search for \"([^\"]*)\"$")
    public void performSearch(Header.SearchType searchType, String searchTerm) {
        header.performSiteSearch(searchType, searchTerm);
    }

    @Then("I see the following logos for the following regulations in the header:")
    public void verifyHeaderRegulationLogos(Map<String, String> expectedLogos) {
        for (Map.Entry<String, String> entry : expectedLogos.entrySet()) {
            BufferedImage fromPage = header.getRegulationImage(entry.getKey());
            BufferedImage fromFile = blazeLibrary.images().getFromFile(entry.getValue());
            blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                    .as("Comparing image src for regulation '%s' in the header to the file named %s", entry.getKey(), entry.getValue())
                    .isTrue();
        }
    }
}
