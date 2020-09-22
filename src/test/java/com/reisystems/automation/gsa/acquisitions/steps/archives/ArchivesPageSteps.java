package com.reisystems.automation.gsa.acquisitions.steps.archives;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.SearchPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchivesPages;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ArchivesPageSteps {

    BlazeLibrary blazeLibrary;
    ArchivesPages archive;

    public ArchivesPageSteps(BlazeLibrary blazeLibrary, ArchivesPages archive) {
        this.blazeLibrary = blazeLibrary;
        this.archive = archive;
    }

    @Given("I am on the archives page")
    public void goToArchivesPage() {
        archive.search().goToPage();
    }

    @When("I set the archive type to {string}")
    public void setArchiveType(String archiveType) {
        archive.search().setArchiveType(archiveType);
    }

    @When("I set the fac number to {string}")
    public void setFacNumber(String facNumber) {
        archive.search().setFacNumber(facNumber);
    }

    @When("I clear the fac number")
    public void clearFacNumber() {
        archive.search().setFacNumber("");
    }

    @When("I set the effective date to {int}-{int}-{int}")
    public void setEffectiveDate(int year, int month, int day) {
        archive.search().setEffectiveDate(LocalDate.of(year, month, day));
    }

    @When("I clear the effective date")
    public void clearEffectiveDate() {
        archive.search().setEffectiveDate(null);
    }

    @When("I perform an archive search for {string}")
    public void performSearch(String archiveType) {
        archive.search().setArchiveType(archiveType).performSearch();
    }

    @Then("I see that all archives have an archive type")
    public void ensureAllArchivesHaveArchiveType() {
        archive.search().forEachRowInTheSearchResults(
                row -> blazeLibrary.assertion().assertThat(!"".equals(row.getArchiveType()))
                        .as("[%s] There is no Archive Type", row.getFacNumber())
                        .isTrue()
        );
    }

    @Then("I see every archive has the archive type {string}")
    public void ensureCorrectArchiveType(String expectedArchiveType) {
        archive.search().forEachRowInTheSearchResults(
                row -> blazeLibrary.assertion().assertThat(row.getArchiveType())
                        .as("[%s] Has the wrong archive Type", row.getFacNumber())
                        .isEqualTo(expectedArchiveType)
        );
    }

    @Then("I see every archive has a unique fac number")
    public void uniqueFacNumbers() {
        Map<String, List<String>> foundFacNumbers = new HashMap<>();

        BlazeWebElement sorter = blazeLibrary.getElement(By.xpath("//a[@title='sort by FAC Number']"));
        if (sorter.isPresent()) {
            sorter.click(blazeLibrary.defaults().REFRESH_PAGE);
        }
        archive.search().forEachRowInTheSearchResults(
                row -> {
                    String foundFacNumber = row.getFacNumber();
                    String foundArchiveType = row.getArchiveType();
                    foundFacNumbers.putIfAbsent(foundFacNumber, new ArrayList<>());
                    blazeLibrary.assertion().assertThat(!foundFacNumbers.get(foundFacNumber).contains(foundArchiveType))
                            .as("Fac Number '%s' found for archive type '%s'. It was previously found for {%s}", foundFacNumber, foundArchiveType,
                                    String.join(", ", foundFacNumbers.get(foundFacNumber)))
                            .isTrue();
                    List<String> test = foundFacNumbers.get(foundFacNumber);
                    test.add(foundArchiveType);
                    foundFacNumbers.put(foundFacNumber, test);
                }
        );
    }

    @When("I count the number of archives with archive type {string} as {string}")
    public void countArchives(String archiveType, String valueKey) {
        BlazeWebElement sorter = blazeLibrary.getElement(By.xpath("//a[@title='sort by FAC Number']"));
        if (sorter.isPresent()) {
            sorter.click(blazeLibrary.defaults().REFRESH_PAGE);
        }

        AtomicInteger numberOfInstances = new AtomicInteger(0);
        archive.search().forEachRowInTheSearchResults(row -> {
            if (row.getArchiveType().equals(archiveType)) {
                numberOfInstances.incrementAndGet();
            }
        });

        blazeLibrary.properties().setProperty(valueKey, String.valueOf(numberOfInstances));
    }

    @When("I gather the archive details")
    public void gatherArchiveDetails() {
        archive.search().forEachRowInTheSearchResults(this::getArchiveDetails);
    }

    @Then("I see that all archive effective dates are before {int}-{int}-{int}")
    public void verifyEffectiveDateFilter(int year, int month, int day) {
        LocalDate desiredDate = LocalDate.of(year, month, day);
        archive.search().forEachRowInTheSearchResults(row -> blazeLibrary.assertion().assertThat(desiredDate)
                .as("[%s:%s] Effective Date is too far in the future", row.getArchiveType(), row.getFacNumber())
                .isAfterOrEqualTo(row.getEffectiveDate())
        );
    }

    @Then("I see that {string} and {string} are the same")
    public void compareTwoValues(String value1, String value2) {
        blazeLibrary.assertion().assertThat(blazeLibrary.properties().getProperty(value1)).as("Two values should be the same").isEqualTo(blazeLibrary.properties().getProperty(value2));
    }

    @Then("I see the fac number in the search row matches the fac number in the detail page")
    public void verifyFacNumber() {
        for (ArchiveDetails detail : theSavedDetails) {
            blazeLibrary.assertion().assertThat(detail.detailFacNumber != null)
                    .as("[%s:%s] 'FAC Number' was not present in detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat(detail.rowFacNumber)
                        .as("[%s:%s] 'FAC Number' on detail page did not match 'FAC Number' in search table", detail.rowArchiveType, detail.rowFacNumber)
                        .isEqualTo(detail.rowFacNumber);
            }
        }
    }

    @Then("I see the archive type in the search row matches the archive type in the detail page")
    public void verifyArchiveType() {
        for (ArchiveDetails detail : theSavedDetails) {
            blazeLibrary.assertion().assertThat(detail.detailArchiveType != null)
                    .as("[%s:%s] 'Archive Type' was not present in detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat(detail.detailArchiveType)
                        .as("[%s:%s] 'Archive Type' on detail page did not match 'Archive Type' in search table", detail.rowArchiveType, detail.rowFacNumber)
                        .isEqualTo(detail.rowArchiveType);
            }
        }
    }

    @Then("I see the effective date in the search row matches the effective date in the detail page")
    public void verifyEffectiveDate() {
        for (ArchiveDetails detail : theSavedDetails){
            blazeLibrary.assertion().assertThat(detail.detailEffectiveDate != null)
                    .as("[%s:%s] 'Effective Date' was not present in detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat(detail.detailEffectiveDate)
                        .as("[%s:%s] 'Effective Date' on detail page did not match 'Effective Date' in search table", detail.rowArchiveType, detail.rowFacNumber)
                        .isEqualTo(detail.rowEffectiveDate);
            }
        }
    }

    @Then("I see the year header is present for every archive detail")
    public void verifyPresenceOfYearHeader() {
        for (ArchiveDetails detail : theSavedDetails) {
            blazeLibrary.assertion().assertThat(detail.detailYear != null)
                    .as("[%s:%s] 'Year' was not present in detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
        }
    }

    @Then("I see the pdf file in the search row matches the pdf file in the detail page")
    public void verifyPdfFile() {
        ArchiveDownloadLink detailPdfLink;
        for (ArchiveDetails detail : theSavedDetails) {
            // make sure either the table row or the detail page has a pdf file link
            if (!"".equals(detail.rowPdfFile.text) || detail.downloadLinks.stream().anyMatch(link -> "PDF File".equals(link.header))) {
                // make sure both the table row and the detail page have a pdf file link
                blazeLibrary.assertion().assertThat(!"".equals(detail.rowPdfFile.text))
                        .as("[%s:%s] PDF file download link was present in detail page, but not present in search table", detail.rowArchiveType, detail.rowFacNumber).isTrue();
                if (blazeLibrary.assertion().wasSuccess()) {
                    blazeLibrary.assertion().assertThat(detail.downloadLinks.stream().anyMatch(link -> "PDF File".equals(link.header)))
                            .as("[%s:%s] PDF file download link was present in search table, but not present in detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
                }

                detailPdfLink = null;
                for (ArchiveDownloadLink link : detail.downloadLinks) {
                    if ("PDF File".equals(link.header)) {
                        detailPdfLink = link;
                        break;
                    }
                }

                if (blazeLibrary.assertion().wasSuccess() && detailPdfLink != null) {
                    blazeLibrary.assertion().assertThat(detailPdfLink.text)
                            .as("[%s:%s] PDF file name on detail page did not match PDF file name in search table",
                                    detail.rowArchiveType, detail.rowFacNumber)
                            .isEqualTo(detail.rowPdfFile.text);

                    try {
                        blazeLibrary.assertion().assertThat(IOUtils.contentEquals(detailPdfLink.url.openStream(), detail.rowPdfFile.url.openStream()))
                                .as("[%s:%s] PDF file content did not match between table and detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
                    } catch (NullPointerException | IOException e) {
                        blazeLibrary.assertion().assertThat(true)
                                .as("[%s:%s] PDF file contents could not be read from either search table or detail page", detail.rowArchiveType, detail.rowFacNumber).isFalse();
                    }
                }
            }
        }
    }

    @Then("I see the zip file in the search row matches the zip file in the detail page")
    public void verifyZipFile() {
        ArchiveDownloadLink detailZipLink;
        for (ArchiveDetails detail : theSavedDetails) {
            // make sure either the table row or the detail page has a pdf file link
            if (!"".equals(detail.rowZipFile.text) || detail.downloadLinks.stream().anyMatch(link -> "Zip file".equals(link.header))) {
                // make sure both the table row and the detail page have a pdf file link
                blazeLibrary.assertion().assertThat(!"".equals(detail.rowZipFile.text))
                        .as("[%s:%s] Zip file download link was present in detail page, but not present in search table", detail.rowArchiveType, detail.rowFacNumber).isTrue();
                if (blazeLibrary.assertion().wasSuccess()) {
                    blazeLibrary.assertion().assertThat(detail.downloadLinks.stream().anyMatch(link -> "Zip file".equals(link.header)))
                            .as("[%s:%s] Zip file download link was present in search table, but not present in detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
                }

                detailZipLink = null;
                for (ArchiveDownloadLink link : detail.downloadLinks) {
                    if ("Zip file".equals(link.header)) {
                        detailZipLink = link;
                        break;
                    }
                }

                if (blazeLibrary.assertion().wasSuccess() && detailZipLink != null) {
                    blazeLibrary.assertion().assertThat(detailZipLink.text)
                            .as("[%s:%s] Zip file name on detail page did not match Zip file name in search table",
                                    detail.rowArchiveType, detail.rowFacNumber)
                            .isEqualTo(detail.rowZipFile.text);

                    try {
                        blazeLibrary.assertion().assertThat(IOUtils.contentEquals(detailZipLink.url.openStream(), detail.rowZipFile.url.openStream()))
                                .as("[%s:%s] Zip file content did not match between table and detail page", detail.rowArchiveType, detail.rowFacNumber).isTrue();
                    } catch (IOException e) {
                        blazeLibrary.assertion().assertThat(true)
                                .as("[%s:%s] Zip file contents could not be read from either search table or detail page", detail.rowArchiveType, detail.rowFacNumber).isFalse();
                    } catch (NullPointerException e) {
                        blazeLibrary.assertion().assertThat(true)
                                .as("[%s:%s] Null pointer exception: row: %s, detail: %s",
                                        detail.rowArchiveType, detail.rowFacNumber, detail.rowZipFile.url, detailZipLink.url).isFalse();
                    }
                }
            }
        }
    }

    @Then("I see there is at least one download link for each archive")
    public void verifyPresenceOfDownloadLinks() {
        for (ArchiveDetails detail : theSavedDetails) {
            blazeLibrary.assertion().assertThat(detail.downloadLinks.size() == 0)
                    .as("[%s:%s] There are no download links", detail.rowArchiveType, detail.rowFacNumber)
                    .isFalse();
        }
    }

    @Then("I see that every download link can be downloaded")
    public void verifyDownloadLinks() {
        for (ArchiveDetails detail : theSavedDetails) {
            for (ArchiveDownloadLink downloadLink : detail.downloadLinks) {
                try {
                    downloadLink.url.openStream();
                } catch (NullPointerException | IOException e) {
                    blazeLibrary.assertion().assertThat(true).as("[%s:%s] Could not open stream to URL '%s'",
                            detail.rowArchiveType, detail.rowFacNumber, downloadLink.header).isFalse();
                }
            }
        }
    }

    @Then("I see that only the following download links are present:")
    public void verifyDownloadHeaders(List<String> expectedDownloadLinks) {
        for (ArchiveDetails detail : theSavedDetails) {
            for (ArchiveDownloadLink downloadLink : detail.downloadLinks) {
                blazeLibrary.assertion().assertThat(expectedDownloadLinks)
                        .as("[%s:%s] There was an unexpected download link: %s"
                                .formatted(detail.rowArchiveType, detail.rowFacNumber, downloadLink.header))
                        .contains(downloadLink.header);
            }
        }
    }

    @Then("I see that only the following headers are present:")
    public void verifyHeaders(List<String> expectedHeaders) {
        for (ArchiveDetails detail : theSavedDetails) {
            for (String header : detail.headers) {
                blazeLibrary.assertion().assertThat(expectedHeaders)
                        .as("[%s:%s] There was an unexpected header: %s"
                                .formatted(detail.rowArchiveType, detail.rowFacNumber, header))
                        .contains(header);
            }
        }
    }

    private final List<ArchiveDetails> theSavedDetails = new ArrayList<>();

    private void getArchiveDetails(SearchPage.ArchiveSearchRow archiveRow) {

        // get archive information from row
        String rowFacNumber = archiveRow.getFacNumber();
        String rowArchiveType = archiveRow.getArchiveType();
        ArchiveDownloadLink rowPdfFile = new ArchiveDownloadLink(null, archiveRow.getPdfFileName(), archiveRow.getPdfFileUrl());
        ArchiveDownloadLink rowZipFile = new ArchiveDownloadLink(null, archiveRow.getZipFileName(), archiveRow.getZipFileUrl());

        LocalDate rowEffectiveDate = archiveRow.getEffectiveDate();

        // Go to the archive detail page
        archiveRow.clickFacNumber();

        // get archive information from detail page
        String detailArchiveType = archive.detail().getArchiveType();
        String detailFacNumber = archive.detail().getFacNumber();
        LocalDate detailEffectiveDate = archive.detail().getEffectiveDate();
        String detailYear = archive.detail().getYear();

        List<String> headers = archive.detail().getHeaders();
        List<ArchiveDownloadLink> downloadLinks = new ArrayList<>();

        for (String headerText : archive.detail().getDownloadLinkHeaders()) {
            headers.remove(headerText);
            downloadLinks.add(new ArchiveDownloadLink(headerText,
                    archive.detail().getHeaderContent(headerText),
                    archive.detail().getDownloadLinkUrl(headerText))
            );
        }

        // save archive information
        theSavedDetails.add(new ArchiveDetails(rowFacNumber, rowArchiveType, rowPdfFile, rowZipFile, rowEffectiveDate,
                detailFacNumber, detailArchiveType, detailEffectiveDate, detailYear, headers, downloadLinks));

        // return to the search page
        blazeLibrary.browser().navigateBack();
    }

    private static class ArchiveDetails {
        String rowFacNumber;
        String rowArchiveType;
        ArchiveDownloadLink rowPdfFile;
        ArchiveDownloadLink rowZipFile;
        LocalDate rowEffectiveDate;

        String detailFacNumber;
        String detailArchiveType;
        LocalDate detailEffectiveDate;
        String detailYear;

        List<String> headers;
        List<ArchiveDownloadLink> downloadLinks;

        private ArchiveDetails(String rowFacNumber, String rowArchiveType, ArchiveDownloadLink rowPdfFile, ArchiveDownloadLink rowZipFile,
                               LocalDate rowEffectiveDate, String detailFacNumber, String detailArchiveType, LocalDate detailEffectiveDate,
                               String detailYear, List<String> headers, List<ArchiveDownloadLink> downloadLinks) {

            this.rowFacNumber = rowFacNumber;
            this.rowArchiveType = rowArchiveType;
            this.rowPdfFile = rowPdfFile;
            this.rowZipFile = rowZipFile;
            this.rowEffectiveDate = rowEffectiveDate;

            this.detailFacNumber = detailFacNumber;
            this.detailArchiveType = detailArchiveType;
            this.detailEffectiveDate = detailEffectiveDate;
            this. detailYear = detailYear;

            this.headers = headers;
            this.downloadLinks = downloadLinks;
        }
    }

    private static class ArchiveDownloadLink {
        String header;
        String text;
        URL url;

        private ArchiveDownloadLink(String header, String text, URL url) {
            this.header = header;
            this.text = text;
            this.url = url;
        }

        public String toString() {
            return "ArchiveDownloadLink(header='%s',text='%s',url='%s')".formatted(header, text, url);
        }
    }

    @When("I test a {pageName} test")
    public void wer() {
    }

    @ParameterType(value = "policy network|home", useForSnippets = true)
    public String pageName(String qwe) {
        return "";
    }
}