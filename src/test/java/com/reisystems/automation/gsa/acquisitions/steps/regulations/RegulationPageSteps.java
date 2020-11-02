package com.reisystems.automation.gsa.acquisitions.steps.regulations;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchivePages;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.RegulationPages;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.TablePage;
import com.reisystems.automation.gsa.acquisitions.steps.general.GeneralSteps;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.By;

import javax.net.ssl.HttpsURLConnection;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RegulationPageSteps {

    private final BlazeLibrary blazeLibrary;
    private final GeneralSteps generalSteps;
    private final ArchivePages archivePages;
    private final RegulationPages regulationPages;

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
                }
            }
        }
    }

    @Then("I see the regulation table can be sorted by {string}")
    public void verifyTableSorting(String columnToSortOn) {
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().tableHasColumn(columnToSortOn))
                .as("The '%s' column is present in the table", columnToSortOn)
                .isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            regulationPages.tablePage().sortTableByColumn(columnToSortOn, TablePage.SortOrder.ASCENDING);
            List<String> columnValues = regulationPages.tablePage().getColumn(columnToSortOn);
            blazeLibrary.assertion().assertThat(columnValues)
                    .as("The '%s' column is sorted in ascending order", columnToSortOn)
                    .isSorted();
            regulationPages.tablePage().sortTableByColumn(columnToSortOn, TablePage.SortOrder.DESCENDING);
            columnValues = regulationPages.tablePage().getColumn(columnToSortOn);
            blazeLibrary.assertion().assertThat(columnValues)
                    .as("The '%s' column is sorted in descending order", columnToSortOn)
                    .isSortedAccordingTo(Comparator.reverseOrder());
        }
    }

    @Then("I see that for each part in the regulation table:")
    public void verifyTableRows(List<String> thingsToCheck) {
        regulationPages.tablePage().sortTableByColumn("Part Number", TablePage.SortOrder.ASCENDING);
        regulationPages.tablePage().forEachPart(part -> {
            if (thingsToCheck.contains("there is a part number")) {
                blazeLibrary.assertion().assertThat(part.info().partNumber())
                        .as("There should be a part number")
                        .isNotBlank();
            }
            if (thingsToCheck.contains("there is a title")) {
                blazeLibrary.assertion().assertThat(part.info().title())
                        .as("There should be a title")
                        .isNotBlank();
            }
            if (thingsToCheck.contains("the \"Print\" icon works correctly")) {
                try {
                    HttpsURLConnection huc = (HttpsURLConnection) part.info().printUrl().openConnection();
                    huc.setRequestMethod("HEAD");
                    blazeLibrary.assertion().assertThat(huc.getResponseCode())
                            .as("Verifying the Print icon")
                            .isEqualTo(HttpsURLConnection.HTTP_OK);
                } catch (IOException e) {
                    blazeLibrary.assertion().assertThat(true)
                            .as("Print file could not be opened")
                            .isFalse();
                } catch (NullPointerException e) {
                    part.onDetailPage(detailPage ->
                            blazeLibrary.assertion().assertThat(
                                    blazeLibrary.getElement(
                                            By.xpath(String.format("//div[contains(@class, 'field-items')]//a[normalize-space(.)='%s']", part.info().title()))
                                    ).isPresent())
                                    .as("Print icon was missing, but there was a link in the detail page")
                                    .isTrue()
                    );
                }
            }
            if (thingsToCheck.contains("the \"PDF\" icon works correctly")) {
                try {
                    HttpsURLConnection huc = (HttpsURLConnection) part.info().pdfUrl().openConnection();
                    huc.setRequestMethod("HEAD");
                    blazeLibrary.assertion().assertThat(huc.getResponseCode())
                            .as("Verifying the Print icon")
                            .isEqualTo(HttpsURLConnection.HTTP_OK);
                } catch (IOException e) {
                    blazeLibrary.assertion().assertThat(true)
                            .as("Print file could not be opened")
                            .isFalse();
                } catch (NullPointerException e) {
                    blazeLibrary.assertion().assertThat(true)
                            .as("Print icon was not present")
                            .isFalse();
                }
            }

        });
    }

    @Then("I see that for each part detail in the regulation table:")
    public void verifyRegulationDetailPages(List<String> thingsToCheck) {
        String regulationName = regulationPages.tablePage().getRegulationName();
        regulationPages.tablePage().sortTableByColumn("Part Number", TablePage.SortOrder.ASCENDING);
        regulationPages.tablePage().forEachDetailPage(detailPage -> {
            if (thingsToCheck.contains("the regulation name matches with row value")) {
                blazeLibrary.assertion().assertThat(detailPage.getRegulationName())
                        .as("%s - %s: Verifying the page regulation name is correct", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .isEqualTo(regulationName);
            }
            if (thingsToCheck.contains("the part number matches with row value")) {
                blazeLibrary.assertion().assertThat(detailPage.getPartNumber())
                        .as("%s - %s: Verifying the part number is correct", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .contains(detailPage.getCurrentRowInfo().partNumber().substring(5));
            }
            if (thingsToCheck.contains("the breadcrumbs are correct")) {
                blazeLibrary.assertion().assertThat(detailPage.getBreadcrumbs())
                        .as("%s - %s: Verifying the breadcrumbs", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .containsExactlyElementsOf(Arrays.asList("Home", "Regulations", regulationName, detailPage.getCurrentRowInfo().title()));
            }
            if (thingsToCheck.contains("the \"Previous\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                if (detailPage.getPreviousRowInfo() != null) {
                    blazeLibrary.assertion().assertThat(detailPage.hasPreviousPage())
                            .as("%s - %s: Verifying that the Previous page button is present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .isTrue();
                    if (blazeLibrary.assertion().wasSuccess()) {
                        detailPage.goToPreviousPage();

                        blazeLibrary.assertion().assertThat(detailPage.getPartNumber())
                                .as("%s - %s: Verifying the part number is correct on the previous page", regulationName, detailPage.getCurrentRowInfo().partNumber())
                                .isEqualTo(detailPage.getPreviousRowInfo().partNumber().substring(5));

                        blazeLibrary.browser().navigateBack();
                    }
                }
            }
            if (thingsToCheck.contains("the \"Next\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                if (detailPage.getNextRowInfo() != null) {
                    blazeLibrary.assertion().assertThat(detailPage.hasNextPage())
                            .as("%s - %s: Verifying that the Next page button is present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .isTrue();
                    if (blazeLibrary.assertion().wasSuccess()) {
                        detailPage.goToNextPage();

                        blazeLibrary.assertion().assertThat(detailPage.getPartNumber())
                                .as("%s - %s: Verifying the part number is correct on the next page", regulationName, detailPage.getCurrentRowInfo().partNumber())
                                .isEqualTo(detailPage.getNextRowInfo().partNumber().substring(5));

                        blazeLibrary.browser().navigateBack();
                    }
                }
            }
            if (thingsToCheck.contains("the \"ToC\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                blazeLibrary.assertion().assertThat(detailPage.hasTocLink())
                        .as("%s - %s: Verifying that the ToC page button is present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .isTrue();
                if (blazeLibrary.assertion().wasSuccess()) {
                    detailPage.clickTocLink();

                    generalSteps.goToPage("taken to", regulationName + " regulation");

                    blazeLibrary.browser().navigateBack();
                }
            }
            if (thingsToCheck.contains("the \"Top\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                blazeLibrary.assertion().assertThat(detailPage.hasTopLink())
                        .as("%s - %s: Verifying that the Top page button is present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .isTrue();

                if (blazeLibrary.assertion().wasSuccess()) {
                    detailPage.clickTopLink();

                    String[] currentUrlParts = blazeLibrary.browser().getCurrentUrl().split("#");
                    blazeLibrary.assertion().assertThat(currentUrlParts.length > 1 && "TopOfPage".equals(currentUrlParts[1]))
                            .as("%s - %s: Verifying the anchor after clicking the Top button", regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .isTrue();

                    blazeLibrary.browser().navigateBack();
                }
            }
            if (thingsToCheck.contains("the internal links work correctly")) {
                blazeLibrary.assertion().assertThat(detailPage.getContentUrls())
                        .as("%s - %s: Verifying the content urls", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .allMatch(url -> {
                            if (url.getProtocol().startsWith("http")) {
                                return UrlValidator.getInstance().isValid(url.toString());
                            } else if (url.getProtocol().equals("mailto")) {
                                return EmailValidator.getInstance().isValid(url.getFile());
                            }
                            return false;
                        });
            }
            if (thingsToCheck.contains("all ToC links have an anchor")
                    || thingsToCheck.contains("all anchors have a ToC link")) {
                List<String> tocAnchorLinks = detailPage.getTocAnchorLinks();
                List<String> contentAnchors = detailPage.getContentAnchors();

                if (thingsToCheck.contains("all ToC links have an anchor")) {
                    blazeLibrary.assertion().assertThat(contentAnchors)
                            .as("%s - %s: Verifying the ToC links have corresponding anchors", regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .containsAll(tocAnchorLinks);
                }

                if (thingsToCheck.contains("all anchors have a ToC link")) {
                    blazeLibrary.assertion().assertThat(tocAnchorLinks)
                            .as("%s - %s: Verifying the anchors have corresponding ToC links", regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .containsAll(contentAnchors);
                }
            }
        });
    }
}
