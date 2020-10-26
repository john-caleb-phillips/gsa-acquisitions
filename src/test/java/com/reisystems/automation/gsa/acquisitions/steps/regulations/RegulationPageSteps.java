package com.reisystems.automation.gsa.acquisitions.steps.regulations;

import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.RegulationPages;
import com.reisystems.automation.gsa.acquisitions.steps.general.GeneralSteps;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.awt.image.BufferedImage;
import java.util.Map;

public class RegulationPageSteps {

    BlazeLibrary blazeLibrary;
    GeneralSteps generalSteps;
    RegulationPages regulationPages;

    public RegulationPageSteps(BlazeLibrary blazeLibrary, GeneralSteps generalSteps, RegulationPages regulationPages) {
        this.blazeLibrary = blazeLibrary;
        this.generalSteps = generalSteps;
        this.regulationPages = regulationPages;
    }

    @Given("I am on the regulation page")
    public void goToRegulationPage() {
        regulationPages.mainPage().goToPage();
    }

    @When("I navigate to the {string} regulation page")
    public void navigateToRegulationPage(String desiredRegulation) {
        regulationPages.mainPage().clickRegulation(desiredRegulation);
    }

    @Then("I see the following regulations:")
    public void verifyRegulations(Map<String, Map<String, String>> expectedRegulations) {
        blazeLibrary.assertion().assertThat(regulationPages.mainPage().getRegulationNames())
                .as("Verifying regulations on the regulation main page")
                .containsExactlyElementsOf(expectedRegulations.keySet());
        if (blazeLibrary.assertion().wasSuccess()) {
            for (String regulation : expectedRegulations.keySet()) {
                BufferedImage fromPage = regulationPages.mainPage().getRegulationImage(regulation);
                BufferedImage fromFile = blazeLibrary.images().getFromFile(expectedRegulations.get(regulation).get("Logo Image"));
                blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                        .as("Comparing image src for regulation '%s' in the regulations page to the file named '%s'", regulation, expectedRegulations.get(regulation).get("Logo Image"))
                        .isTrue();
                if (!blazeLibrary.assertion().wasSuccess()) {
                    blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Expected '%s' logo", regulation));
                    blazeLibrary.report().attachImage(fromPage, "PNG", String.format("Actual '%s' logo from the main regulation page", regulation));
                    blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "The difference between the two logos");
                }

                regulationPages.mainPage().clickRegulation(regulation);

                if ("Chapter 99 (CAS)".equals(regulation)) {
                    generalSteps.goToPage("taken to", "chapter 99");
                } else if ("GSAM/R".equals(regulation)) {
                    generalSteps.goToPage("taken to", "GSAM regulation");
                } else {
                    generalSteps.goToPage("taken to", regulation + " regulation");
                }

                blazeLibrary.assertion().assertThat(regulationPages.mainPage().getPageType())
                        .as("Verifying the linked url for regulation '%s'", regulation)
                        .isEqualTo(expectedRegulations.get(regulation).get("Page Type"));

                if (blazeLibrary.assertion().wasSuccess() && "table".equals(regulationPages.mainPage().getPageType())) {
                    BufferedImage fromPageDetail = regulationPages.tablePage().getRegulationLogo();
                    blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPageDetail, 0))
                            .as("Comparing image src for regulation '%s' in the regulation detail page to the file named '%s'", regulation, expectedRegulations.get(regulation).get("Logo Image"))
                            .isTrue();
                    if (!blazeLibrary.assertion().wasSuccess()) {
                        blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Expected '%s' logo", regulation));
                        blazeLibrary.report().attachImage(fromPageDetail, "PNG", String.format("Actual '%s' logo from the regulation detail page", regulation));
                        blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "The difference between the two logos");
                    }
                    blazeLibrary.assertion().assertThat(regulationPages.tablePage().getRegulationName())
                            .as("Verifying header title for regulation '%s'", regulation)
                            .isEqualTo(regulation.replace("(CAS)", "").trim().replaceAll(" ", "_").toUpperCase());
                }

                blazeLibrary.browser().navigateBack();
            }
        }
    }

    @Then("I see the following logos for the following regulations:")
    public void verifyRegulationLogos(Map<String, String> expectedLogos) {
        for (Map.Entry<String, String> entry : expectedLogos.entrySet()) {
            BufferedImage fromPage = regulationPages.mainPage().getRegulationImage(entry.getKey());
            BufferedImage fromFile = blazeLibrary.images().getFromFile(entry.getValue());
            blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                    .as("Comparing image src for regulation '%s' in the header to the file named %s", entry.getKey(), entry.getValue())
                    .isTrue();
        }
    }

    @Then("I see the regulations go to the following urls:")
    public void verifyRegulationLinks(Map<String, String> expectedUrls) {
        for (Map.Entry<String, String> entry : expectedUrls.entrySet()) {
            regulationPages.mainPage().clickRegulation(entry.getKey());
            blazeLibrary.assertion().assertThat(blazeLibrary.browser().getCurrentUrl())
                    .as("Verifying the linked url for regulation '%s'", entry.getKey())
                    .isEqualTo(entry.getValue());
            blazeLibrary.browser().navigateBack();
        }
    }

    @Then("I see the regulation header is the following:")
    public void verifyRegulationHeader(String expectedHeader) {
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().getHeader())
                .as("Verifying header for regulation")
                .isEqualTo(expectedHeader);
    }

    @Then("I see the regulation content is the following:")
    public void verifyRegulationContent(String expectedContent) {
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().getContent())
                .as("Verifying content for regulation")
                .isEqualToIgnoringNewLines(expectedContent);
    }
}
