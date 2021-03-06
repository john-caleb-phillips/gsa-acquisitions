package com.reisystems.automation.gsa.acquisitions.pageobject.archives;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class ArchiveSearchPage extends PageObject {

    public ArchiveSearchPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "archives");
    }

    @Override
    public boolean areOnPage() {
        return blazeLibrary.browser().getCurrentUrl().startsWith(url);
    }

    public ArchiveSearchPage setArchiveType(String archiveType) {
        blazeLibrary.getElement(By.xpath("//form[@id='views-exposed-form-archives-page']//select[@id='edit-type']")).asDropdown().selectByVisibleText(archiveType);
        return this;
    }

    public String getArchiveType() {
        return blazeLibrary.getElement(By.xpath("//form[@id='views-exposed-form-archives-page']//select[@id='edit-type']")).asDropdown().getFirstSelectedOption().getText();
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
        textBox.sendKeys(date != null ? date.format(DateTimeFormatter.ofPattern("MM/dd/uuuu")) : "");
        return this;
    }

    public void performSearch() {
        blazeLibrary.getElement(By.xpath("//form[@id='views-exposed-form-archives-page']//input[@id='edit-submit-archives']")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void forEachRowOnThePage(Consumer<ArchiveSearchRow> consumer) {
        int numberOfSearchRows = numberOfSearchRows();
        for (int i = 1; i <= numberOfSearchRows; i++) {
            consumer.accept(getSearchRow(i));
        }
    }

    public void forEachRowInTheSearchResults(Consumer<ArchiveSearchRow> consumer) {
        if (hasPreviousPage()) {
            goToFirstPage();
        }
        forEachRowOnThePage(consumer);
        while (hasNextPage()) {
            goToNextPage();
            forEachRowOnThePage(consumer);
        }
    }

    public int numberOfSearchRows() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'view-archives')]//tbody/tr")).size();
    }

    public ArchiveSearchRow getSearchRow(int rowNumber) {
        return new ArchiveSearchRow(rowNumber);
    }

    public boolean hasPreviousPage() {
        return blazeLibrary.getElement(By.cssSelector("ul.pager > li.pager-previous")).isPresent();
    }

    public boolean hasNextPage() {
        return blazeLibrary.getElement(By.cssSelector("ul.pager > li.pager-next")).isPresent();
    }

    public void goToFirstPage() {
        blazeLibrary.getElement(By.cssSelector("ul.pager > li.pager-first")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToPreviousPage() {
        blazeLibrary.getElement(By.cssSelector("ul.pager > li.pager-previous")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToNextPage() {
        blazeLibrary.getElement(By.cssSelector("ul.pager > li.pager-next")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToLastPage() {
        blazeLibrary.getElement(By.cssSelector("ul.pager > li.pager-last")).click(blazeLibrary.clickResults().REFRESH_PAGE);
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

        public LocalDate getEffectiveDate() {
            return LocalDate.parse(
                    blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[4]", rowNumber))).getText(),
                    DateTimeFormatter.ofPattern("MMM dd,uuuu")
            );
        }

        public String getZipFileName() {
            BlazeWebElement zipFileLink = blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[5]//a", rowNumber)));
            if (zipFileLink.isPresent()) {
                return zipFileLink.getAttribute("aria-label");
            } else {
                return "";
            }
        }

        public URL getZipFileUrl() {
            try {
                BlazeWebElement zipFileLink = blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[5]//a", rowNumber)));
                if (zipFileLink.isPresent()) {
                    return new URL(zipFileLink.getAttribute("href"));
                } else {
                    return null;
                }
            } catch (MalformedURLException e) {
                return null;
            }
        }

        public String getPdfFileName() {
            BlazeWebElement pdfFileLink = blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[6]//a", rowNumber)));
            if (pdfFileLink.isPresent()) {
                return pdfFileLink.getAttribute("aria-label");
            } else {
                return "";
            }
        }

        public URL getPdfFileUrl() {
            try {
                BlazeWebElement pdfFileLink = blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[6]//a", rowNumber)));
                if (pdfFileLink.isPresent()) {
                    return new URL(pdfFileLink.getAttribute("href"));
                } else {
                    return null;
                }
            } catch (MalformedURLException e) {
                return null;
            }
        }

        public void clickFacNumber() {
            blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'view-archives')]//tbody/tr[%s]/td[1]/a", rowNumber))).click(blazeLibrary.clickResults().REFRESH_PAGE);
        }
    }
}
