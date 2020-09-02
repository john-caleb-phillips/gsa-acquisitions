package com.reisystems.automation.gsa.acquisitions.pageobject;

import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class ArchiveSearchPage {

    BlazeLibrary blazeLibrary;

    public ArchiveSearchPage(BlazeLibrary blazeLibrary) {
        this.blazeLibrary = blazeLibrary;
    }

    public void goToPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/archives");
    }

    public ArchiveSearchPage setArchiveType(String archiveType) {
        blazeLibrary.getElement(By.xpath("//form[@id='views-exposed-form-archives-page']//select[@id='edit-type']")).asDropdown().selectByVisibleText(archiveType);
        return this;
    }

    public ArchiveSearchPage setFacNumber(String facNumber) {
        BlazeWebElement textBox = blazeLibrary.getElement(By.xpath("//form[@id='views-exposed-form-archives-page']//input[@id='edit-title']"));
        textBox.clear();
        textBox.sendKeys(facNumber);
        return this;
    }

    public ArchiveSearchPage setEffectiveDate(LocalDate date) {
        BlazeWebElement textBox = blazeLibrary.getElement(By.xpath("//form[@id='views-exposed-form-archives-page']//input[@id='edit-date-value-datepicker-popup-0']"));
        textBox.clear();
        textBox.sendKeys(date != null ? date.format(DateTimeFormatter.ofPattern("uuuu-MM-dd")) : "");
        return this;
    }

    public void performSearch() {
        blazeLibrary.getElement(By.xpath("//form[@id='views-exposed-form-archives-page']//input[@id='edit-submit-archives']")).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void forEachRowOnThePage(Consumer<ArchiveSearchRow> consumer) {
        int numberOfSearchRows = numberOfSearchRows();
        for (int i = 1; i <= numberOfSearchRows; i++) {
            consumer.accept(getSearchRow(i));
        }
    }

    public void forEachRowInTheSearchResults(Consumer<ArchiveSearchRow> consumer) {
        forEachRowOnThePage(consumer);
        while (blazeLibrary.getElement(By.xpath("//a[@title='Go to next page']")).isPresent()) {
            blazeLibrary.getElement(By.xpath("//a[@title='Go to next page']")).click(blazeLibrary.defaults().REFRESH_PAGE);
            forEachRowOnThePage(consumer);
        }
    }

    public int numberOfSearchRows() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'view-archives')]//tbody/tr")).size();
    }

    public ArchiveSearchRow getSearchRow(int rowNumber) {
        return new ArchiveSearchRow(rowNumber);
    }

    public class ArchiveSearchRow {

        private final int rowNumber;

        private ArchiveSearchRow(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public String getFacNumber() {
            return blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[1]", rowNumber))).getText();
        }

        public String getArchiveType() {
            return blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[2]", rowNumber))).getText();
        }

        public String getPdfFileName() {
            return blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[3]", rowNumber))).getText();
        }

        public URL getPdfFileUrl() {
            try {
                return new URL(blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[3]", rowNumber))).getAttribute("href"));
            } catch (MalformedURLException e) {
                return null;
            }
        }

        public String getZipFileName() {
            return blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[4]", rowNumber))).getText();
        }

        public URL getZipFileUrl() {
            try {
                return new URL(blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[4]", rowNumber))).getAttribute("href"));
            } catch (MalformedURLException e) {
                return null;
            }
        }

        public LocalDate getEffectiveDate() {
            return LocalDate.parse(
                    blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[5]", rowNumber))).getText(),
                    DateTimeFormatter.ofPattern("MM/dd/uuuu")
            );
        }

        public void clickFacNumber() {
            blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[1]/a", rowNumber))).click(blazeLibrary.defaults().REFRESH_PAGE);
        }
    }
}
