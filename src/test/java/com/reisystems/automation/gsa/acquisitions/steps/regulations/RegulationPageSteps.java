package com.reisystems.automation.gsa.acquisitions.steps.regulations;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchivePages;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.RegulationPages;
import com.reisystems.automation.gsa.acquisitions.steps.general.GeneralSteps;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class RegulationPageSteps {

    BlazeLibrary blazeLibrary;
    GeneralSteps generalSteps;
    ArchivePages archivePages;
    RegulationPages regulationPages;

    public RegulationPageSteps(BlazeLibrary blazeLibrary, GeneralSteps generalSteps, ArchivePages archivePages, RegulationPages regulationPages) {
        this.blazeLibrary = blazeLibrary;
        this.generalSteps = generalSteps;
        this.archivePages = archivePages;
        this.regulationPages = regulationPages;
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

    @Then("I see the link to {string} works in the regulation content")
    public void verifyRegulationArchivesLink(String linkText) {
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().linkIsPresent(linkText))
                .as("Link to the Archives with text '%s' is present", linkText)
                .isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            String regulation = regulationPages.tablePage().getRegulationName();
            regulationPages.tablePage().clickLink(linkText);
            blazeLibrary.assertion().assertThat(archivePages.search().areOnPage())
                    .as("Verifying the Archives link goes to the correct page")
                    .isEqualTo(true);
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat(archivePages.search().getArchiveType())
                        .as("Verifying that are on the '%s' archive page", regulation)
                        .isEqualTo(regulation);
            }
            blazeLibrary.browser().navigateBack();
        }
    }

    @Then("I see each link in the regulation content works correctly")
    public void verifyRegulationContentLinks() {
        List<BlazeWebElement> links = blazeLibrary.getElements(By.xpath("//div[@class='view-header']//a"));
        blazeLibrary.assertion().assertThat(links.size() != 0)
                .as("There must be at least on link in the contents")
                .isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            for (BlazeWebElement link : links) {
                try {
                    URL url = new URL(link.getAttribute("href"));
                    if (url.getProtocol().startsWith("http")) {
                        blazeLibrary.assertion().assertThat(UrlValidator.getInstance().isValid(url.toString()))
                                .as("Verifying that '%s' is a valid url address", url.toString())
                                .isTrue();
                    } else if (url.getProtocol().equals("mailto")) {
                        String file = url.getFile();
                        blazeLibrary.assertion().assertThat(EmailValidator.getInstance().isValid(file))
                                .as("Verifying that '%s' is a valid email address", file)
                                .isTrue();
                    } else {
                        blazeLibrary.assertion().assertThat(true).as("There was an unexpected issue").withFailMessage("TESTING").isFalse();
                    }

                } catch (MalformedURLException e) {
                    blazeLibrary.assertion().assertThat(true)
                            .as("'%s': There was an unexpected MalformedURLException: %s", link.getText(), e.getMessage())
                            .isFalse();
                } catch (IOException e) {
                    blazeLibrary.assertion().assertThat(true)
                            .as("'%s': There was an unexpected IOException: %s", link.getText(), e.getMessage())
                            .isFalse();
                }
            }
        }
    }
}
