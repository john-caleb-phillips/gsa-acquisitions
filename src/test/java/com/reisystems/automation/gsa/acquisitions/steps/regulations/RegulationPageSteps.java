package com.reisystems.automation.gsa.acquisitions.steps.regulations;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchivePages;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.RegulationPages;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.SidebarPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.TablePage;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageRegistry;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import javax.net.ssl.HttpsURLConnection;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @When("I click sidebar link {string}")
    public void clickSidebarLink(String linkText) {
        regulationPages.sidebarPage().clickSideBarLink(linkText);
    }

    @Then("I see the following sidebar links:")
    public void verifySidebarLinks(Map<String, String> expectedLinks) {
        blazeLibrary.assertion().assertThat(new ArrayList<>(expectedLinks.keySet()))
                .as("The regulation sidebar links were not correct")
                .isEqualTo(regulationPages.sidebarPage().sidebarLinks());

        if (blazeLibrary.assertion().wasSuccess()) {
            for (String linkText : expectedLinks.keySet()) {
                blazeLibrary.mouseAndKeyboard().keyDown(Keys.CONTROL).perform();
                regulationPages.sidebarPage().clickSideBarLink(linkText);
                blazeLibrary.mouseAndKeyboard().keyUp(Keys.CONTROL).perform();

                pageRegistry.getPage(expectedLinks.get(linkText)).areOnPage();
                blazeLibrary.browser().closeTab();
            }
        }
    }

    @Then("I see the following sidebar parts:")
    public void verifySidebarParts(List<String> expectedParts) {
        expectedParts.removeIf(""::equals);
        blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().sidebarParts())
                .as("The regulation sidebar links were not correct")
                .isEqualTo(expectedParts);
    }

    @Then("I see the following full download table headers:")
    public void verifyFullDownloads(List<String> expectedFullDownloads) {
        Map<String, SidebarPage.FullDownloadTable.Cell> cells = regulationPages.sidebarPage().fullDownloadTable().getCells();
        blazeLibrary.assertion().assertThat(new ArrayList<>(cells.keySet()))
                .as("Full Download table headers were not correct")
                .containsExactlyInAnyOrderElementsOf(expectedFullDownloads);
        if (blazeLibrary.assertion().wasSuccess()) {
            for (String headerText : cells.keySet()) {
                if (headerText.contains("Number")) {
                    if (regulationPages.sidebarPage().title().equals("General Services Acquisition Manual (GSAM)")) {
                        blazeLibrary.assertion().assertThat(cells.get(headerText).href() != null)
                                .as("Could not click the '%s' cell.", headerText)
                                .isTrue();
                        if (blazeLibrary.assertion().wasSuccess()) {
                            try {
                                cells.get(headerText).href().openStream();
                            } catch (IOException e) {
                                blazeLibrary.assertion().fail(
                                        "Could not open stream to '%s' download link: %s", headerText, e.getMessage()
                                );
                            }
                        }
                    }
                } else if ("Effective Date".equals(headerText)) {
                    blazeLibrary.assertion().assertThat(cells.get(headerText).text())
                            .as("The Effective Date was not in the correct format")
                            .matches("\\d{1,2}[\\-/]\\d{1,2}[\\-/]\\d{4}");
                } else if (headerText.contains("Archive")) {
                    String regulationName = regulationPages.sidebarPage().title();

                    cells.get(headerText).click();
                    blazeLibrary.assertion().assertThat(pageRegistry.getPage("archive search").areOnPage())
                            .withFailMessage("Should have been on archives page, but were not")
                            .isTrue();
                    if (blazeLibrary.assertion().wasSuccess()) {
                        String expectedArchiveType = null;
                        if ("DFARS".equals(regulationName)) {
                            expectedArchiveType = "DFARS";
                        } else if ("DFARS PGI".equals(regulationName)) {
                            expectedArchiveType = "DFARSPGI";
                        } else if ("General Services Acquisition Manual (GSAM)".equals(regulationName)) {
                            expectedArchiveType = "GSAM";
                        }

                        blazeLibrary.assertion().assertThat(archivePages.search().getArchiveType())
                                .as("Archive link '%s' went to the wrong archive page", headerText)
                                .isEqualTo(expectedArchiveType);
                    }
                    blazeLibrary.browser().navigateBack();
                } else if ("Kindle".equals(headerText)) {
                    String regulationName = regulationPages.sidebarPage().title();
                    cells.get(headerText).click();

                    String expectedUrl = null;
                    if ("FAR".equals(regulationName)) {
                        expectedUrl = "https://www.amazon.com/dp/B07WD5P3W7";
                    } else if ("General Services Acquisition Manual (GSAM)".equals(regulationName)) {
                        expectedUrl = "https://www.amazon.com/dp/B07VF6SNHD/ref=sr_1_1?qid=1563885519&refinements=p_27%3AOffice+of+Acquisition+Policy&s=digital-text&sr=1-1&text=Office+of+Acquisition+Policy";
                    }

                    blazeLibrary.assertion().assertThat(blazeLibrary.browser().getCurrentUrl())
                            .as("Kindle URL was incorrect")
                            .isEqualTo(expectedUrl);

                    blazeLibrary.browser().closeTab();
                } else {
                    try {
                        cells.get(headerText).href().openStream();
                    } catch (IOException e) {
                        blazeLibrary.assertion().fail(
                                "Could not open stream to '%s' download link: %s", headerText, e.getMessage()
                        );
                    }
                }
            }
        }
    }

    @Then("each sidebar link goes to the correct page")
    public void verifySidebarParts() {
        String regulationName = regulationPages.sidebarPage().title();
        for (String sidebarPart : regulationPages.sidebarPage().sidebarParts()) {
            regulationPages.sidebarPage().clickSideBarPart(sidebarPart);

            blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().breadcrumbs())
                    .as("The breadcrumbs were not correct")
                    .isEqualTo(expectedBreadcrumbs(regulationName));

            if (isNumber(sidebarPart)) {
                blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().partTitle().toUpperCase())
                        .as("This is a part title: " + sidebarPart)
                        .contains("PART " + sidebarPart);

                blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().currentPart())
                        .as("This is a part: " + sidebarPart)
                        .isEqualTo("Part " + sidebarPart);

                blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().currentSubPart())
                        .as("This is a sub part: " + sidebarPart)
                        .isEqualTo("Select");

                blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().currentSection())
                        .as("This is a section: " + sidebarPart)
                        .isEqualTo("Select");

            } else {
                blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().partTitle().toUpperCase())
                        .as("This is an appendix: " + sidebarPart)
                        .startsWith("APPENDIX " + sidebarPart);
            }
        }
    }

    private boolean isNumber(String test) {
        try {
            Integer.parseInt(test);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private List<String> expectedBreadcrumbs(String regulationName) {
        List<String> expectedBreadcrumbs = new ArrayList<>();
        expectedBreadcrumbs.add("Home");
        expectedBreadcrumbs.add("Regulations");
        expectedBreadcrumbs.add("DFARS PGI".equals(regulationName) ? "DFARSPGI" : regulationName);
        expectedBreadcrumbs.add(regulationPages.sidebarPage().partTitle());
        return expectedBreadcrumbs;
    }

    @Then("I see the following ways to get the part downloads:")
    public void verifyPartDownloads(List<String> expectedPartDownloads) {
        List<SidebarPage.PartRow.Info> rows = regulationPages.sidebarPage().getRows();
        for (SidebarPage.PartRow.Info rowInfo : rows) {
            blazeLibrary.assertion().assertThat(rowInfo.downloadLinks.keySet())
                    .as("The download links were not correct")
                    .containsExactlyInAnyOrderElementsOf(expectedPartDownloads);
            if (!blazeLibrary.assertion().wasSuccess()) {
                break;
            }

            for (String linkText : rowInfo.downloadLinks.keySet()) {
                if ("Print".equals(linkText)) {
                    continue;
                }

                try {
                    rowInfo.downloadLinks.get(linkText).openStream();
                } catch (IOException e) {
                    blazeLibrary.assertion().fail(
                            "%s: Could not open stream to '%s' download link: %s",
                            rowInfo.partName, linkText, e.getMessage()
                    );
                }
            }
        }
    }

    @Then("the page title is {string}")
    public void verifyPageTitle(String expectedTitle) {
        blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().title())
                .as("The page breadcrumbs were not correct")
                .isEqualTo(expectedTitle);
    }

    @Then("the page breadcrumbs are the following:")
    public void verifyPageBreadcrumbs(List<String> expectedBreadcrumbs) {
        blazeLibrary.assertion().assertThat(regulationPages.sidebarPage().breadcrumbs())
                .as("The page breadcrumbs were not correct")
                .isEqualTo(expectedBreadcrumbs);
    }

    @Then("each part and sub-part goes to the correct page")
    public void verifyPartLinks() {
        List<String> linksWithIncorrectTitles = new ArrayList<>();
        List<String> linksGoingTo404 = new ArrayList<>();
        List<String> linksWithoutTitles = new ArrayList<>();
        regulationPages.sidebarPage().forEachRow(row -> {
            String rowTitle = row.getInfo().partName;

            row.clickTitle();

            String pageTitle = regulationPages.sidebarPage().partTitle();

            if (!rowTitle.equals(pageTitle)) {
                if (is404Page()) {
                    linksGoingTo404.add(rowTitle);
                } else if (pageTitle == null) {
                    linksWithoutTitles.add(rowTitle);
                } else {
                    linksWithIncorrectTitles.add("Row Title: " + rowTitle + ", Page Title: " + regulationPages.sidebarPage().partTitle());
                }
            }
            blazeLibrary.browser().navigateBack();
        });

        if (linksWithIncorrectTitles.size() != 0) {
            blazeLibrary.assertion().fail(
                    "The following links on the index page went to pages with different titles:%n    %s",
                    String.join("\n    ", linksWithIncorrectTitles)
            );
        }

        if (linksGoingTo404.size() != 0) {
            blazeLibrary.assertion().fail(
                    "The following links on the index page went to a 404 page:%n    %s",
                    String.join("\n    ", linksGoingTo404)
            );
        }

        if (linksWithoutTitles.size() != 0) {
            blazeLibrary.assertion().fail(
                    "The following links on the index page went to pages without titles:%n    %s",
                    String.join("\n    ", linksWithoutTitles)
            );
        }
    }

    private boolean is404Page() {
        try {
            URL url = new URL(blazeLibrary.browser().getCurrentUrl());
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            return 200 != connection.getResponseCode();
        } catch (IOException e) {
            return true;
        }
    }


    List<TablePage.RowInfo> savedRowInfo = new ArrayList<>();

    @When("^I save the info from the table regulation rows$")
    public void saveTableRegulationRowInfo() {
        regulationPages.tablePage().forEachPart(part -> savedRowInfo.add(part.info()));
    }

    List<TablePage.DetailPageInfo> savedDetailPageInfo = new ArrayList<>();

    @When("^I save the info from the table regulation detail pages$")
    public void saveTableRegulationDetailPageInfo() {
        regulationPages.tablePage().forEachDetailPage(page -> savedDetailPageInfo.add(page.getInfo()));
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

    private final static Pattern replacementString = Pattern.compile("DATE\\{([^}]+)}");
    String shortMonth = "(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Sept|Oct|Nov|Dec)";
    String longMonth = "(January|February|March|April|May|June|July|August|September|October|November|December)";

    @Then("I see the regulation content is the following:")
    public void verifyRegulationContent(String expectedContent) {

        String actualContent = StringUtils.normalizeSpace(regulationPages.tablePage().getContent());
        actualContent = actualContent.replaceAll("\n", " ");
        expectedContent = StringUtils.normalizeSpace(expectedContent);
        expectedContent = expectedContent.replaceAll("\n", " ");

        Matcher matcher = replacementString.matcher(expectedContent);

        StringBuilder builder = new StringBuilder("\\Q");
        int previousEnd = 0;
        while (matcher.find()) {
            String sanitizedGroup = matcher.group(1)
                    .replaceAll("YYYY", "\\\\E" + "\\\\d{4}" + "\\\\Q")
                    .replaceAll("YY", "\\\\E" + "\\\\d{2}" + "\\\\Q")
                    .replaceAll("MMMM", "\\\\E" + longMonth + "\\\\Q")
                    .replaceAll("MMM", "\\\\E" + shortMonth + "\\\\Q")
                    .replaceAll("MM", "\\\\E" + "\\\\d{2}" + "\\\\Q")
                    .replaceAll("(?!Mar|May)M", "\\\\E" + "\\\\d{1,2}" + "\\\\Q")
                    .replaceAll("DD", "\\\\E" + "\\\\d{2}" + "\\\\Q")
                    .replaceAll("(?!Dec)D", "\\\\E" + "\\\\d{1,2}" + "\\\\Q");
            builder.append(expectedContent, previousEnd, matcher.start());
            builder.append(sanitizedGroup);
            previousEnd = matcher.end();
        }
        builder.append(expectedContent.substring(previousEnd)).append("\\E");

        blazeLibrary.assertion().assertThat(actualContent)
                .withFailMessage("Content for regulation '%s' was not as expect%n"
                                + "Expecting:%n\"%s\"%nto match pattern:%n\"%s\"",
                        regulationPages.tablePage().getRegulationName(), actualContent, expectedContent)
                .matches(Pattern.compile(builder.toString()));

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

    @Then("the part number in each row is not blank")
    public void verifyTableRegulationRowPartNumber() {
        for (int i = 0; i < savedRowInfo.size(); i++) {
            blazeLibrary.assertion().assertThat(savedRowInfo.get(i).partNumber)
                    .withFailMessage("Row %s did not have a part number", i + 1)
                    .isNotBlank();
        }
    }

    @Then("the title in each row is not blank")
    public void verifyTableRegulationRowTitle() {
        for (int i = 0; i < savedRowInfo.size(); i++) {
            blazeLibrary.assertion().assertThat(savedRowInfo.get(i).title)
                    .withFailMessage("Part Number %s (Row %s): Row is missing title", savedRowInfo.get(i).partNumber, i + 1)
                    .isNotBlank();

        }
    }

    @Then("the \"Print\" icon in each row works correctly")
    public void verifyTableRegulationRowPrintIcon() {
        for (int i = 0; i < savedRowInfo.size(); i++) {
            try {
                HttpsURLConnection huc = (HttpsURLConnection) savedRowInfo.get(i).printUrl.openConnection();
                huc.setRequestMethod("HEAD");
                blazeLibrary.assertion().assertThat(huc.getResponseCode())
                        .as("Part Number %s (Row %s): Print file response code was incorrect", savedRowInfo.get(i).partNumber, i + 1)
                        .isEqualTo(HttpsURLConnection.HTTP_OK);
            } catch (IOException e) {
                blazeLibrary.assertion().fail("Part Number %s (Row %s): Print file could not be opened: %s",
                        savedRowInfo.get(i).partNumber, i + 1, e.getMessage());
            } catch (NullPointerException e) {
                TablePage.RowInfo qwe = savedRowInfo.get(i);
                int rowNumber = i + 1;
                regulationPages.tablePage().getPartRow(i + 1).onDetailPage(detailPage ->
                        blazeLibrary.assertion().assertThat(
                                blazeLibrary.getElement(
                                        By.xpath(String.format("//div[contains(@class, 'field-items')]//a[normalize-space(.)='%s']", qwe.title))
                                ).isPresent())
                                .withFailMessage("Row %s: Print icon was missing, and there was no PDF link in the detail page", rowNumber, e.getMessage())
                                .isTrue()
                );
            }
        }
    }

    @Then("the \"PDF\" icon in each row works correctly")
    public void verifyTableRegulationRowPdfIcon() {
        for (int i = 0; i < savedRowInfo.size(); i++) {
            try {
                HttpsURLConnection huc = (HttpsURLConnection) savedRowInfo.get(i).pdfUrl.openConnection();
                huc.setRequestMethod("HEAD");
                blazeLibrary.assertion().assertThat(huc.getResponseCode())
                        .as("Part Number %s (Row %s): PDF file response code was incorrect",
                                savedRowInfo.get(i).partNumber, i + 1)
                        .isEqualTo(HttpsURLConnection.HTTP_OK);
            } catch (IOException e) {
                blazeLibrary.assertion().fail("Part Number %s (Row %s): PDF file could not be opened: %s",
                        savedRowInfo.get(i).partNumber, i + 1, e.getMessage());
            } catch (NullPointerException e) {
                blazeLibrary.assertion().fail("Part Number %s (Row %s): PDF icon was not present",
                        savedRowInfo.get(i).partNumber, i + 1);
            }
        }
    }

    @Then("the regulation name on each table regulation detail page is {string}")
    public void verifyTableRegulationDetailPageName(String regulationName) {
        for (TablePage.DetailPageInfo pageInfo : savedDetailPageInfo) {
            blazeLibrary.assertion().assertThat(pageInfo.regulationName)
                    .as("%s - %s: The regulation name was incorrect.",
                            pageInfo.regulationName, pageInfo.rowPartNumber)
                    .isEqualTo(regulationName);
        }
    }

    @Then("the part number on each table regulation detail page matches the row value")
    public void verifyTableRegulationDetailPagePartNumber() {
        for (TablePage.DetailPageInfo pageInfo : savedDetailPageInfo) {
            blazeLibrary.assertion().assertThat(pageInfo.partNumber)
                    .as("%s - %s: The displayed part number was incorrect.",
                            pageInfo.regulationName, pageInfo.rowPartNumber)
                    .contains(pageInfo.rowPartNumber.substring(5));
        }
    }

    @Then("the breadcrumbs on each table regulation detail page are formatted correctly")
    public void verifyTableRegulationDetailPageBreadcrumbs() {
        for (TablePage.DetailPageInfo pageInfo : savedDetailPageInfo) {
            List<String> expectedBreadcrumbs = Arrays.asList("Home", "Regulations", pageInfo.regulationName, pageInfo.rowTitle);
            List<String> actualBreadCrumbs = pageInfo.breadcrumbs;
            blazeLibrary.assertion().assertThat(actualBreadCrumbs)
                    .as("%s - %s: The breadcrumbs were incorrect",
                            pageInfo.regulationName, pageInfo.rowPartNumber)
                    .withFailMessage("%nExpected: %s%n  Actual: %s", expectedBreadcrumbs, actualBreadCrumbs)
                    .containsExactlyElementsOf(expectedBreadcrumbs);
        }
    }

    @Then("the \"Previous\" button on each table regulation detail page works correctly")
    public void verifyTableRegulationDetailPagePreviousButton() {
        if (savedDetailPageInfo.size() > 0) {
            blazeLibrary.assertion().assertThat(savedDetailPageInfo.get(0).previousPagePartNumber == null)
                    .withFailMessage("%s - %s: 'Previous' button was present on the first part",
                            savedDetailPageInfo.get(0).regulationName, savedDetailPageInfo.get(0).rowPartNumber)
                    .isTrue();
        }

        for (int i = 1; i < savedDetailPageInfo.size(); i++) {
            TablePage.DetailPageInfo pageInfo = savedDetailPageInfo.get(i);
            blazeLibrary.assertion().assertThat(pageInfo.previousPagePartNumber != null)
                    .withFailMessage("%s - %s: 'Previous' button was not present",
                            pageInfo.regulationName, pageInfo.rowPartNumber)
                    .isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                if (!savedDetailPageInfo.get(i - 1).rowPartNumber.substring(5).equals(pageInfo.previousPagePartNumber)) {
                    if (blazeLibrary.getElement(By.xpath("//table[@id='regulation-index-browse']")).isPresent()) {
                        blazeLibrary.assertion().fail("%s - %s: 'Previous' button went back to the regulation ToC, instead of %s",
                                pageInfo.regulationName, pageInfo.rowPartNumber, savedDetailPageInfo.get(i - 1).rowPartNumber);
                    } else {
                        blazeLibrary.assertion().fail("%s - %s: 'Previous' button went to %s instead of %s",
                                pageInfo.regulationName, pageInfo.rowPartNumber,
                                "".equals(pageInfo.previousPagePartNumber)
                                        ? "a page without a displayed part number"
                                        : "Part " + pageInfo.previousPagePartNumber,
                                savedDetailPageInfo.get(i - 1).rowPartNumber);
                    }
                }

                blazeLibrary.browser().navigateBack();
            }
        }
    }

    @Then("the \"Next\" button on each table regulation detail page works correctly")
    public void verifyTableRegulationDetailPageNextButton() {
        if (savedDetailPageInfo.size() > 0) {
            blazeLibrary.assertion().assertThat(savedDetailPageInfo.get(savedDetailPageInfo.size() - 1).nextPagePartNumber == null)
                    .withFailMessage("%s - %s: 'Next' button was present on the last part",
                            savedDetailPageInfo.get(savedDetailPageInfo.size() - 1).regulationName,
                            savedDetailPageInfo.get(savedDetailPageInfo.size() - 1).rowPartNumber)
                    .isTrue();
        }

        for (int i = 0; i < savedDetailPageInfo.size() - 1; i++) {
            TablePage.DetailPageInfo pageInfo = savedDetailPageInfo.get(i);
            blazeLibrary.assertion().assertThat(pageInfo.nextPagePartNumber != null)
                    .withFailMessage("%s - %s: 'Next' button was not present",
                            pageInfo.regulationName, pageInfo.rowPartNumber)
                    .isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                if (!savedDetailPageInfo.get(i + 1).rowPartNumber.substring(5).equals(pageInfo.nextPagePartNumber)) {
                    if (blazeLibrary.getElement(By.xpath("//table[@id='regulation-index-browse']")).isPresent()) {
                        blazeLibrary.assertion().fail("%s - %s: 'Next' button went back to the regulation ToC, instead of %s",
                                pageInfo.regulationName, pageInfo.rowPartNumber, savedDetailPageInfo.get(i + 1).rowPartNumber);
                    } else {
                        blazeLibrary.assertion().fail("%s - %s: 'Next' button went to %s instead of %s",
                                pageInfo.regulationName, pageInfo.rowPartNumber,
                                "".equals(pageInfo.nextPagePartNumber)
                                        ? "a page without a displayed part number"
                                        : "Part " + pageInfo.nextPagePartNumber,
                                savedDetailPageInfo.get(i + 1).rowPartNumber);
                    }
                }

                blazeLibrary.browser().navigateBack();
            }
        }
    }

    @Then("the \"ToC\" button on each table regulation detail page works correctly")
    public void verifyTableRegulationDetailPageTocButton() {
        for (TablePage.DetailPageInfo pageInfo : savedDetailPageInfo) {
            blazeLibrary.assertion().assertThat(pageInfo.tocButtonLink != null)
                    .withFailMessage("%s - %s: 'ToC' button was not present",
                            pageInfo.regulationName, pageInfo.rowPartNumber)
                    .isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat(pageInfo.tocButtonLink)
                        .withFailMessage("%s - %s: 'ToC' button did not link back to the regulation ToC page.%nExpected URL: %s%n  Actual URL: %s",
                                pageInfo.regulationName, pageInfo.rowPartNumber,
                                pageRegistry.getPage(pageInfo.regulationName + " regulation").getExpectedUrl(),
                                pageInfo.tocButtonLink)
                        .isEqualTo(pageRegistry.getPage(pageInfo.regulationName + " regulation").getExpectedUrl());
            }
        }
    }

    @Then("the \"Top\" button on each table regulation detail page works correctly")
    public void verifyTableRegulationDetailPageTopButton() {
        for (TablePage.DetailPageInfo pageInfo : savedDetailPageInfo) {
            blazeLibrary.assertion().assertThat(pageInfo.topButtonLink != null)
                    .withFailMessage("%s - %s: 'Top' button was not present",
                            pageInfo.regulationName, pageInfo.rowPartNumber)
                    .isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat("TopOfPage")
                        .withFailMessage("%s - %s: Anchor was '%s' instead of 'TopOfPage' after clicking 'Top' button",
                                pageInfo.regulationName, pageInfo.rowPartNumber, pageInfo.topButtonLink)
                        .isEqualTo(pageInfo.topButtonLink);
                blazeLibrary.browser().navigateBack();
            }
        }
    }

    @Then("the internal links on each table regulation detail page work correctly")
    public void verifyTableRegulationDetailPageInternalLinks() {
        for (TablePage.DetailPageInfo pageInfo : savedDetailPageInfo) {
            pageInfo.contentUrls.parallelStream().forEach(url -> {
                if (url.getProtocol().startsWith("http")) {
                    blazeLibrary.assertion().assertThat(UrlValidator.getInstance().isValid(url.toString()))
                            .withFailMessage("%s - %s: Http URL '%s' was invalid",
                                    pageInfo.regulationName, pageInfo.rowPartNumber, url)
                            .isTrue();
                } else if (url.getProtocol().equals("mailto")) {
                    blazeLibrary.assertion().assertThat(EmailValidator.getInstance().isValid(url.getFile()))
                            .withFailMessage("%s - %s: Mail URL '%s' was invalid",
                                    pageInfo.regulationName, pageInfo.rowPartNumber, url)
                            .isTrue();
                } else {
                    blazeLibrary.assertion().fail("%s - %s: URL '%s' was neither an http URL nor a mailto URL",
                            pageInfo.regulationName, pageInfo.rowPartNumber, url);
                }
            });
        }
    }

    @Then("the ToC link on each table regulation detail page all have a corresponding anchor")
    public void verifyTableRegulationDetailPageTocLinks() {
        for (TablePage.DetailPageInfo pageInfo : savedDetailPageInfo) {
            List<String> tocLinksWithoutContentLinks = pageInfo.tocLinks.entrySet().stream()
                    .filter(el -> el.getValue() == null).map(Map.Entry::getKey).collect(Collectors.toList());

            blazeLibrary.assertion().assertThat(tocLinksWithoutContentLinks.size())
                    .withFailMessage("%s - %s: The following ToC links did not have corresponding anchor links: %n%s",
                            pageInfo.regulationName, pageInfo.rowPartNumber,
                            String.join("\n", tocLinksWithoutContentLinks))
                    .isZero();

            List<String> tocLinksWithTheWrongText = pageInfo.tocLinks.entrySet().stream()
                    .filter(entry -> entry.getValue() != null)
                    .filter(entry -> !entry.getKey().equals(entry.getValue()))
                    .map(entry -> String.format("Expected: %s%n  Actual: %s%n", entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());

            blazeLibrary.assertion().assertThat(tocLinksWithTheWrongText.size())
                    .withFailMessage("%s - %s: The text for the following ToC links did not match the anchor links: %n%s",
                            pageInfo.regulationName, pageInfo.rowPartNumber,
                            String.join("\n", tocLinksWithTheWrongText))
                    .isZero();
        }
    }
}
