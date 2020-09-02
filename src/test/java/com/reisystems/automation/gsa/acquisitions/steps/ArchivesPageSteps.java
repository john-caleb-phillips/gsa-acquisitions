package com.reisystems.automation.gsa.acquisitions.steps;

import com.reisystems.automation.gsa.acquisitions.pageobject.ArchiveDetailPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.ArchiveSearchPage;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
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
    ArchiveSearchPage archiveSearchPage;
    ArchiveDetailPage archiveDetailPage;

    public ArchivesPageSteps(BlazeLibrary blazeLibrary, ArchiveSearchPage archiveSearchPage, ArchiveDetailPage archiveDetailPage) {
        this.blazeLibrary = blazeLibrary;
        this.archiveSearchPage = archiveSearchPage;
        this.archiveDetailPage = archiveDetailPage;
    }

    @Given("I am on the archives page")
    public void goToArchivesPage() {
        blazeLibrary.browser().navigateToUrl(archiveSearchPage.getUrl());
    }

    @When("I set the archive type to {string}")
    public void setArchiveType(String archiveType) {
        archiveSearchPage.setArchiveType(archiveType);
    }

    @When("I set the fac number to {string}")
    public void setFacNumber(String facNumber) {
        archiveSearchPage.setFacNumber(facNumber);
    }

    @When("I clear the fac number")
    public void clearFacNumber() {
        archiveSearchPage.setFacNumber("");
    }

    @When("I set the effective date to {int}-{int}-{int}")
    public void setEffectiveDate(int year, int month, int day) {
        archiveSearchPage.setEffectiveDate(LocalDate.of(year, month, day));
    }

    @When("I clear the effective date")
    public void clearEffectiveDate() {
        archiveSearchPage.setEffectiveDate(null);
    }

    @When("I perform an archive search for {string}")
    public void performSearch(String archiveType) {
        archiveSearchPage.setArchiveType(archiveType).performSearch();
    }

    @Then("I see that all archives have an archive type")
    public void ensureAllArchivesHaveArchiveType() {
        archiveSearchPage.forEachRowInTheSearchResults(
                el -> blazeLibrary.assertion().assertThat(!"".equals(el.getArchiveType()))
                        .as("[%s] There is no Archive Type", el.getFacNumber())
                        .isTrue()
        );
    }

    @Then("I see every archive has the archive type {string}")
    public void ensureCorrectArchiveType(String expectedArchiveType) {
        archiveSearchPage.forEachRowInTheSearchResults(
                el -> blazeLibrary.assertion().assertThat(el.getArchiveType())
                        .as("[%s] Has the wrong archive Type", el.getFacNumber())
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
        archiveSearchPage.forEachRowInTheSearchResults(
                el -> {
                    String foundFacNumber = el.getFacNumber();
                    String foundArchiveType = el.getArchiveType();
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
    public void qwe(String archiveType, String valueKey) {
        BlazeWebElement sorter = blazeLibrary.getElement(By.xpath("//a[@title='sort by FAC Number']"));
        if (sorter.isPresent()) {
            sorter.click(blazeLibrary.defaults().REFRESH_PAGE);
        }

        AtomicInteger numberOfInstances = new AtomicInteger(0);
        archiveSearchPage.forEachRowInTheSearchResults(el -> {
            if (el.getArchiveType().equals(archiveType)) {
                numberOfInstances.incrementAndGet();
            }
        });

        blazeLibrary.properties().setProperty(valueKey, String.valueOf(numberOfInstances));
    }

    @Then("I see that {string} and {string} are the same")
    public void compareTwoValues(String value1, String value2) {
        blazeLibrary.assertion().assertThat(blazeLibrary.properties().getProperty(value1)).as("This is a boring test").isEqualTo(blazeLibrary.properties().getProperty(value2));
    }

    @Then("I see all archive details are correct")
    public void testArchiveDetails() {
        archiveSearchPage.forEachRowInTheSearchResults(this::parseArchive);
    }

    private void parseArchive(ArchiveSearchPage.ArchiveSearchRow archiveRow) {

        // get archive information from row

        String rowFacNumber = archiveRow.getFacNumber();
        String rowArchiveType = archiveRow.getArchiveType();
        String rowPdfFile = archiveRow.getPdfFileName();
        String rowZipFile = archiveRow.getZipFileName();
        LocalDate rowEffectiveDate = archiveRow.getEffectiveDate();

        URL rowPdfUrl = archiveRow.getPdfFileUrl();
        URL rowZipUrl = archiveRow.getZipFileUrl();

        // Go to the archive detail page
        archiveRow.clickFacNumber();

        blazeLibrary.assertion().assertThat(archiveDetailPage.isArchiveTypePresent())
                .as("[%s:%s] 'Archive Type' was not present in detail page", rowArchiveType, rowFacNumber).isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.assertion().assertThat(archiveDetailPage.getArchiveType())
                    .as("[%s:%s] 'Archive Type' on detail page did not match 'Archive Type' in search table", rowArchiveType, rowFacNumber)
                    .isEqualTo(rowArchiveType);
        }

        blazeLibrary.assertion().assertThat(archiveDetailPage.isFacNumberPresent())
                .as("[%s:%s] 'FAC Number' was not present in detail page", rowArchiveType, rowFacNumber).isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.assertion().assertThat(archiveDetailPage.getFacNumber())
                    .as("[%s:%s] 'FAC Number' on detail page did not match 'FAC Number' in search table", rowArchiveType, rowFacNumber)
                    .isEqualTo(rowFacNumber);
        }

        blazeLibrary.assertion().assertThat(archiveDetailPage.isYearPresent())
                .as("[%s:%s] 'Year' was not present in detail page", rowArchiveType, rowFacNumber).isTrue();

        blazeLibrary.assertion().assertThat(archiveDetailPage.isEffectiveDatePresent())
                .as("[%s:%s] 'Effective Date' was not present in detail page", rowArchiveType, rowFacNumber).isTrue();
        if (blazeLibrary.assertion().wasSuccess()) {
            blazeLibrary.assertion().assertThat(archiveDetailPage.getEffectiveDate())
                    .as("[%s:%s] 'Effective Date' on detail page did not match 'Effective Date' in search table", rowArchiveType, rowFacNumber)
                    .isEqualTo(rowEffectiveDate);
        }

        if (!"".equals(rowPdfFile) || archiveDetailPage.isHeaderPresent("PDF File")) {
            blazeLibrary.assertion().assertThat(archiveDetailPage.isHeaderPresent("PDF File"))
                    .as("[%s:%s] 'PDF File' was present in search table, but not present in detail page", rowArchiveType, rowFacNumber).isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat(archiveDetailPage.getHeaderContent("PDF File"))
                        .as("[%s:%s] 'PDF File' on detail page did not match 'PDF File' in search table", rowArchiveType, rowFacNumber)
                        .isEqualTo(rowPdfFile);
            }
        }

        if (!"".equals(rowZipFile) || archiveDetailPage.isHeaderPresent("Zip file")) {
            blazeLibrary.assertion().assertThat(archiveDetailPage.isHeaderPresent("Zip file"))
                    .as("[%s:%s] 'Zip file' was present in search table, but not present in detail page", rowArchiveType, rowFacNumber).isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                blazeLibrary.assertion().assertThat(archiveDetailPage.getHeaderContent("Zip file"))
                        .as("[%s:%s] 'Zip file' on detail page did not match 'Zip file' in search table", rowArchiveType, rowFacNumber)
                        .isEqualTo(rowZipFile);
            }
        }

        blazeLibrary.assertion().assertThat(archiveDetailPage.getDownloadLinkHeaders().size() != 0)
                .as("[%s:%s] There are download links: ", rowArchiveType, rowFacNumber)
                .isTrue();
        for (String headerText : archiveDetailPage.getDownloadLinkHeaders()) {
            try {
                if (rowPdfUrl != null && "application/pdf".equals(archiveDetailPage.getDownloadLinkType(headerText))) {
                    blazeLibrary.assertion().assertThat(IOUtils.contentEquals(archiveDetailPage.getDownloadLinkUrl(headerText).openStream(), rowPdfUrl.openStream()))
                            .as("[%s:%s] PDF File content did not match between table and detail page", rowArchiveType, rowFacNumber).isTrue();
                }
                if (rowZipUrl != null && "application/zip".equals(archiveDetailPage.getDownloadLinkType(headerText))) {
                    blazeLibrary.assertion().assertThat(IOUtils.contentEquals(archiveDetailPage.getDownloadLinkUrl(headerText).openStream(), rowZipUrl.openStream()))
                            .as("[%s:%s] Zip file content did not match between table and detail page", rowArchiveType, rowFacNumber).isTrue();
                }
            } catch (IOException e) {
                blazeLibrary.assertion().assertThat(true).as("[%s:%s] URL: %s", rowArchiveType, rowFacNumber, archiveDetailPage.getDownloadLinkUrl(headerText)).isFalse();
            }

            blazeLibrary.assertion().assertThat(Set.of("PDF File", "Zip file", "Word File", "ePub", "Dita Package File", "Change Order"))
                    .as(String.format("[%s:%s] There was an unexpected download link: %s", rowArchiveType, rowFacNumber, headerText)).contains(headerText);
        }

        List<String> headers = archiveDetailPage.getHeaders();
        headers.removeAll(archiveDetailPage.getDownloadLinkHeaders());
        blazeLibrary.assertion().assertThat(headers)
                .as(String.format("[%s:%s] There was an unexpected header", rowArchiveType, rowFacNumber))
                .allMatch(el -> Set.of("Archive Type", "FAC Number", "Effective Date", "Year").contains(el));

        blazeLibrary.browser().navigateBack();
    }
}
