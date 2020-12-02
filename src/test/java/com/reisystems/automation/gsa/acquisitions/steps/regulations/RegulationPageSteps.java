package com.reisystems.automation.gsa.acquisitions.steps.regulations;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchivePages;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.RegulationPages;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.TablePage;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageRegistry;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class RegulationPageSteps {

    private final BlazeLibrary blazeLibrary;
    private final ArchivePages archivePages;
    private final RegulationPages regulationPages;
    private final PageRegistry pageRegistry;

    public RegulationPageSteps(BlazeLibrary blazeLibrary, ArchivePages archivePages,
                               RegulationPages regulationPages, PageRegistry pageRegistry) {
        this.blazeLibrary = blazeLibrary;
        this.archivePages = archivePages;
        this.regulationPages = regulationPages;
        this.pageRegistry = pageRegistry;
    }

    @When("I navigate to the {string} regulation page")
    public void navigateToRegulationPage(String desiredRegulation) {
        regulationPages.mainPage().clickRegulation(desiredRegulation);
    }

    @Then("I see the following regulations:")
    public void verifyRegulations(Map<String, Map<String, String>> expectedRegulations) {
        blazeLibrary.assertion().assertThat(regulationPages.mainPage().getRegulationNames())
                .as("The regulations on the Regulations main page wer not as expected")
                .containsExactlyElementsOf(expectedRegulations.keySet());
        if (blazeLibrary.assertion().wasSuccess()) {
            for (String regulation : expectedRegulations.keySet()) {
                BufferedImage fromPage = regulationPages.mainPage().getRegulationImage(regulation);
                BufferedImage fromFile = blazeLibrary.images().getFromFile(expectedRegulations.get(regulation).get("Logo Image"));
                blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPage, 0))
                        .as("Displayed image for regulation '%s' on the regulation main page did not match the validation file named '%s'", regulation, expectedRegulations.get(regulation).get("Logo Image"))
                        .isTrue();
                if (!blazeLibrary.assertion().wasSuccess()) {
                    blazeLibrary.report().attachImage(fromPage, "PNG", String.format("Actual '%s' logo from the main regulation page", regulation));
                    blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Expected '%s' logo from the file named '%s'", regulation, expectedRegulations.get(regulation).get("Logo Image")));
                    blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "Differences marked in red");
                }

                regulationPages.mainPage().clickRegulation(regulation);

                String regulationPageName = regulation + " regulation";
                if ("Chapter 99 (CAS)".equals(regulation)) {
                    regulationPageName = "chapter 99";
                } else if ("GSAM/R".equals(regulation)) {
                    regulationPageName = "GSAM regulation";
                }

                if (!pageRegistry.getPage(regulationPageName).areOnPage()) {
                    blazeLibrary.assertion().fail("Should have been on %s page, but were not.%nUrl was: %s", regulation, blazeLibrary.browser().getCurrentUrl());
                }

                blazeLibrary.assertion().assertThat(regulationPages.mainPage().getPageType())
                        .as("The page type of regulation '%s' was not as expected", regulation)
                        .isEqualTo(expectedRegulations.get(regulation).get("Page Type"));

                if (blazeLibrary.assertion().wasSuccess() && "table".equals(regulationPages.mainPage().getPageType())) {
                    BufferedImage fromPageDetail = regulationPages.tablePage().getRegulationLogo();
                    blazeLibrary.assertion().assertThat(blazeLibrary.images().compareTwoImages(fromFile, fromPageDetail, 0))
                            .as("Displayed image for regulation '%s' on the regulation detail page did not match the validation file named '%s'", regulation, expectedRegulations.get(regulation).get("Logo Image"))
                            .isTrue();
                    if (!blazeLibrary.assertion().wasSuccess()) {
                        blazeLibrary.report().attachImage(fromPageDetail, "PNG", String.format("Actual '%s' logo from the regulation detail page", regulation));
                        blazeLibrary.report().attachImage(fromFile, "PNG", String.format("Expected '%s' logo from the file named '%s'", regulation, expectedRegulations.get(regulation).get("Logo Image")));
                        blazeLibrary.report().attachImage(blazeLibrary.images().getDiff(fromPage, fromFile), "PNG", "Differences marked in red");
                    }
                    blazeLibrary.assertion().assertThat(regulationPages.tablePage().getRegulationName())
                            .as("Header title for regulation '%s' was not as expected", regulation)
                            .isEqualTo(regulation.replace("(CAS)", "").trim().replaceAll(" ", "_").toUpperCase());
                }

                blazeLibrary.browser().navigateBack();
            }
        }
    }

    @Then("I see the regulation header is the following:")
    public void verifyRegulationHeader(String expectedHeader) {
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().getHeader())
                .as("Header for regulation '%s' was not as expected", regulationPages.tablePage().getRegulationName())
                .isEqualTo(expectedHeader);
    }

    @Then("I see the regulation content is the following:")
    public void verifyRegulationContent(String expectedContent) {
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().getContent())
                .as("Content for regulation '%s' was not as expected", regulationPages.tablePage().getRegulationName())
                .isEqualToIgnoringNewLines(expectedContent);
    }

    @Then("I see the link to {string} works in the regulation content")
    public void verifyRegulationArchivesLink(String linkText) {
        String regulation = regulationPages.tablePage().getRegulationName();
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().linkIsPresent(linkText))
                .withFailMessage("Link to the Archives with text '%s' was not present for regulation '%s'",
                        linkText, regulation)
                .isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
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
        String regulation = regulationPages.tablePage().getRegulationName();

        List<BlazeWebElement> links = blazeLibrary.getElements(By.xpath("//div[@class='view-header']//a"));
        blazeLibrary.assertion().assertThat(links.size() != 0)
                .withFailMessage("Regulation '%s' did not have any links in its content", regulation)
                .isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            for (BlazeWebElement link : links) {
                try {
                    URL url = new URL(link.getAttribute("href"));
                    if (url.getProtocol().startsWith("http")) {
                        blazeLibrary.assertion().assertThat(UrlValidator.getInstance().isValid(url.toString()))
                                .withFailMessage("Regulation '%s': Content link '%s' is not a valid url address", regulation, url.toString())
                                .isTrue();
                    } else if (url.getProtocol().equals("mailto")) {
                        String file = url.getFile();
                        blazeLibrary.assertion().assertThat(EmailValidator.getInstance().isValid(file))
                                .as("Regulation '%s': Content link '%s' is not valid email address", regulation, file)
                                .isTrue();
                    } else {
                        blazeLibrary.assertion().fail("There was an unexpected issue");
                    }
                } catch (MalformedURLException e) {
                    blazeLibrary.assertion().fail("Regulation '%s': Content link '%s' threw an unexpected MalformedURLException: %s", regulation, link.getText(), e.getMessage());
                }
            }
        }
    }

    private static final Comparator<String> partComparator = (o1, o2) -> {
        String[] parts1 = o1.trim().split(" ");
        String[] parts2 = o2.trim().split(" ");
        for (int i = 0; i < Math.min(parts1.length, parts2.length); i++) {
            try {
                int int1 = Integer.parseInt(parts1[i]);
                int int2 = Integer.parseInt(parts2[i]);
                return int1 - int2;
            } catch (NumberFormatException ignored) {
                if (!parts1[i].equals(parts2[i])) {
                    break;
                }
            }
        }
        return o1.compareTo(o2);
    };

    @Then("I see the regulation table can be sorted by {string}")
    public void verifyTableSorting(String columnToSortOn) {
        String regulation = regulationPages.tablePage().getRegulationName();
        blazeLibrary.assertion().assertThat(regulationPages.tablePage().tableHasColumn(columnToSortOn))
                .withFailMessage("Regulation '%s': Could not sort the table on '%s' column because it was not present in the table", regulation, columnToSortOn)
                .isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            regulationPages.tablePage().sortTableByColumn(columnToSortOn, TablePage.SortOrder.ASCENDING);
            List<String> columnValues = regulationPages.tablePage().getColumn(columnToSortOn);
            blazeLibrary.assertion().assertThat(columnValues)
                    .as("Regulation '%s': The '%s' column was not sorted correctly in ascending order", regulation, columnToSortOn)
                    .isSortedAccordingTo(partComparator);
            regulationPages.tablePage().sortTableByColumn(columnToSortOn, TablePage.SortOrder.DESCENDING);
            columnValues = regulationPages.tablePage().getColumn(columnToSortOn);
            blazeLibrary.assertion().assertThat(columnValues)
                    .as("Regulation '%s': The '%s' column was not correctly sorted in descending order", regulation, columnToSortOn)
                    .isSortedAccordingTo(partComparator.reversed());
        }
    }

    @Then("I see that for each part in the regulation table:")
    public void verifyTableRows(List<String> thingsToCheck) {
        String regulationName = regulationPages.tablePage().getRegulationName();
        regulationPages.tablePage().sortTableByColumn("Part Number", TablePage.SortOrder.ASCENDING);
        AtomicInteger rowCount = new AtomicInteger();
        regulationPages.tablePage().forEachPart(part -> {
            rowCount.incrementAndGet();
            if (thingsToCheck.contains("there is a part number")) {
                blazeLibrary.assertion().assertThat(part.info().partNumber())
                        .withFailMessage("Regulation '%s': Row %s did not have a part number", regulationName, rowCount.get())
                        .isNotBlank();
            }
            if (thingsToCheck.contains("there is a title")) {
                blazeLibrary.assertion().assertThat(part.info().title())
                        .withFailMessage("Regulation '%s': Row %s did not have a title", regulationName, rowCount.get())
                        .isNotBlank();
            }
            if (thingsToCheck.contains("the \"Print\" icon works correctly")) {
                try {
                    HttpsURLConnection huc = (HttpsURLConnection) part.info().printUrl().openConnection();
                    huc.setRequestMethod("HEAD");
                    blazeLibrary.assertion().assertThat(huc.getResponseCode())
                            .as("%s - %s: Print file response code was incorrect", regulationName, part.info().partNumber())
                            .isEqualTo(HttpsURLConnection.HTTP_OK);
                } catch (IOException e) {
                    blazeLibrary.assertion().fail("%s - %s: Print file could not be opened: %s", regulationName, part.info().partNumber(), e.getMessage());
                } catch (NullPointerException e) {
                    part.onDetailPage(detailPage ->
                            blazeLibrary.assertion().assertThat(
                                    blazeLibrary.getElement(
                                            By.xpath(String.format("//div[contains(@class, 'field-items')]//a[normalize-space(.)='%s']", part.info().title()))
                                    ).isPresent())
                                    .withFailMessage("%s - %s: Print icon was missing, and there was no PDF link in the detail page", regulationName, part.info().partNumber(), e.getMessage())
                                    .isTrue()
                    );
                }
            }
            if (thingsToCheck.contains("the \"PDF\" icon works correctly")) {
                try {
                    HttpsURLConnection huc = (HttpsURLConnection) part.info().pdfUrl().openConnection();
                    huc.setRequestMethod("HEAD");
                    blazeLibrary.assertion().assertThat(huc.getResponseCode())
                            .as("%s - %s: PDF file response code was incorrect", regulationName, part.info().partNumber())
                            .isEqualTo(HttpsURLConnection.HTTP_OK);
                } catch (IOException e) {
                    blazeLibrary.assertion().fail("%s - %s: PDF file could not be opened: %s", regulationName, part.info().partNumber(), e.getMessage());
                } catch (NullPointerException e) {
                    blazeLibrary.assertion().fail("%s - %s: PDF icon was not present", regulationName, part.info().partNumber());
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
                        .as("%s - %s: The page regulation name is did not match the value in the regulation table", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .isEqualTo(regulationName);
            }
            if (thingsToCheck.contains("the part number matches with row value")) {
                blazeLibrary.assertion().assertThat(detailPage.getPartNumber())
                        .as("%s - %s: The displayed part number was incorrect", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .contains(detailPage.getCurrentRowInfo().partNumber().substring(5));
            }
            if (thingsToCheck.contains("the breadcrumbs are correct")) {
                List<String> expectedBreadcrumbs = Arrays.asList("Home", "Regulations", regulationName, detailPage.getCurrentRowInfo().title());
                List<String> actualBreadCrumbs = detailPage.getBreadcrumbs();
                blazeLibrary.assertion().assertThat(actualBreadCrumbs)
                        .as("%s - %s: The page breadcrumbs were incorrect", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .withFailMessage("%nExpected: %s%n  Actual: %s", expectedBreadcrumbs, actualBreadCrumbs)
                        .containsExactlyElementsOf(expectedBreadcrumbs);
            }
            if (thingsToCheck.contains("the \"Previous\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                if (detailPage.getPreviousRowInfo() != null) {
                    blazeLibrary.assertion().assertThat(detailPage.hasPreviousPage())
                            .withFailMessage("%s - %s: 'Previous Page' button was not present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .isTrue();
                    if (blazeLibrary.assertion().wasSuccess()) {
                        detailPage.goToPreviousPage();

                        String partNumber = detailPage.getPartNumber();
                        if (!detailPage.getPreviousRowInfo().partNumber().substring(5).equals(partNumber)) {
                            if (blazeLibrary.getElement(By.xpath("//table[@id='regulation-index-browse']")).isPresent()) {
                                blazeLibrary.assertion().fail("%s - %s: 'Previous Page' button went back to the regulation ToC, instead of %s",
                                        regulationName, detailPage.getCurrentRowInfo().partNumber(), detailPage.getPreviousRowInfo().partNumber());
                            } else {
                                blazeLibrary.assertion().fail("%s - %s: 'Previous Page' button went to %s instead of %s",
                                        regulationName, detailPage.getCurrentRowInfo().partNumber(),
                                        partNumber != null ? "Part " + partNumber : "a page without a displayed part number",
                                        detailPage.getPreviousRowInfo().partNumber());
                            }
                        }

                        blazeLibrary.browser().navigateBack();
                    }
                }
            }
            if (thingsToCheck.contains("the \"Next\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                if (detailPage.getNextRowInfo() != null) {
                    blazeLibrary.assertion().assertThat(detailPage.hasNextPage())
                            .withFailMessage("%s - %s: 'Next Page' button was not present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .isTrue();
                    if (blazeLibrary.assertion().wasSuccess()) {
                        detailPage.goToNextPage();

                        String partNumber = detailPage.getPartNumber();
                        if (!detailPage.getNextRowInfo().partNumber().substring(5).equals(partNumber)) {
                            if (blazeLibrary.getElement(By.xpath("//table[@id='regulation-index-browse']")).isPresent()) {
                                blazeLibrary.assertion().fail("%s - %s: 'Next Page' button went back to the regulation ToC, instead of %s",
                                        regulationName, detailPage.getCurrentRowInfo().partNumber(), detailPage.getNextRowInfo().partNumber());
                            } else {
                                blazeLibrary.assertion().fail("%s - %s: 'Next Page' button went to %s instead of %s",
                                        regulationName, detailPage.getCurrentRowInfo().partNumber(),
                                        partNumber != null ? "Part " + partNumber : "a page without a displayed part number",
                                        detailPage.getNextRowInfo().partNumber());
                            }
                        }

                        blazeLibrary.browser().navigateBack();
                    }
                }
            }
            if (thingsToCheck.contains("the \"ToC\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                blazeLibrary.assertion().assertThat(detailPage.hasTocLink())
                        .withFailMessage("%s - %s: 'ToC' button was not present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .isTrue();
                if (blazeLibrary.assertion().wasSuccess()) {
                    detailPage.clickTocLink();


                    if (!pageRegistry.getPage(regulationName + " regulation").areOnPage()) {
                        blazeLibrary.assertion().fail("%s - %s: 'ToC' button did not link back to the regulation ToC page.%nExpected URL: %s%n  Actual URL: %s",
                                regulationName, detailPage.getCurrentRowInfo().partNumber(),
                                pageRegistry.getPage(regulationName + " regulation").getExpectedUrl(),
                                blazeLibrary.browser().getCurrentUrl().split("[#?]")[0]
                        );
                    }

                    blazeLibrary.browser().navigateBack();
                }
            }
            if (thingsToCheck.contains("the \"Top\" button works correctly") && detailPage.getCurrentRowInfo().printUrl() != null) {
                blazeLibrary.assertion().assertThat(detailPage.hasTopLink())
                        .withFailMessage("%s - %s: 'Top' button was not present", regulationName, detailPage.getCurrentRowInfo().partNumber())
                        .isTrue();
                if (blazeLibrary.assertion().wasSuccess()) {
                    detailPage.clickTopLink();

                    String[] currentUrlParts = blazeLibrary.browser().getCurrentUrl().split("#");
                    blazeLibrary.assertion().assertThat(currentUrlParts.length > 1 && "TopOfPage".equals(currentUrlParts[1]))
                            .withFailMessage("%s - %s: Anchor was not 'TopOfPage' after clicking 'Top' button",
                                    regulationName, detailPage.getCurrentRowInfo().partNumber())
                            .isTrue();

                    blazeLibrary.browser().navigateBack();
                }
            }
            if (thingsToCheck.contains("the internal links work correctly")) {
                detailPage.getContentUrls().parallelStream().forEach(url -> {
                    if (url.getProtocol().startsWith("http")) {
                        blazeLibrary.assertion().assertThat(UrlValidator.getInstance().isValid(url.toString()))
                                .withFailMessage("%s - %s: Http URL '%s' was invalid",
                                        regulationName, detailPage.getCurrentRowInfo().partNumber(), url)
                                .isTrue();
                    } else if (url.getProtocol().equals("mailto")) {
                        blazeLibrary.assertion().assertThat(EmailValidator.getInstance().isValid(url.getFile()))
                                .withFailMessage("%s - %s: Mail URL '%s' was invalid",
                                        regulationName, detailPage.getCurrentRowInfo().partNumber(), url)
                                .isTrue();
                    } else {
                        blazeLibrary.assertion().fail("%s - %s: URL '%s' was neither an http URL nor a mailto URL",
                                regulationName, detailPage.getCurrentRowInfo().partNumber(), url);
                    }
                });
            }
            if (thingsToCheck.contains("the ToC links each have an anchor")) {
                Map<String, String> tocLinks = detailPage.getTocLinks();

                List<String> tocLinksWithoutContentLinks = tocLinks.entrySet().stream()
                        .filter(el -> el.getValue() == null).map(Map.Entry::getKey).collect(Collectors.toList());

                blazeLibrary.assertion().assertThat(tocLinksWithoutContentLinks.size())
                        .withFailMessage("%s - %s: The following ToC links did not have corresponding anchor links: %n%s",
                                regulationName, detailPage.getCurrentRowInfo().partNumber(),
                                String.join("\n", tocLinksWithoutContentLinks))
                        .isZero();

                List<String> tocLinksWithTheWrongText = tocLinks.entrySet().stream()
                        .filter(entry -> entry.getValue() != null)
                        .filter(entry -> !entry.getKey().equals(entry.getValue()))
                        .map(entry -> String.format("Expected: %s%n  Actual: %s%n", entry.getKey(), entry.getValue()))
                        .collect(Collectors.toList());

                blazeLibrary.assertion().assertThat(tocLinksWithTheWrongText.size())
                        .withFailMessage("%s - %s: The text for the following ToC links did not match the anchor links: %n%s",
                                regulationName, detailPage.getCurrentRowInfo().partNumber(),
                                String.join("\n", tocLinksWithTheWrongText))
                        .isZero();
            }
        });
    }
}
