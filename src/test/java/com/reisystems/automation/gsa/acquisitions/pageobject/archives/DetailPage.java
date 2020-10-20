package com.reisystems.automation.gsa.acquisitions.pageobject.archives;

import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DetailPage extends HasBlazeLibrary {

    public DetailPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    private void waitForPageToLoad() {
        blazeLibrary.getElement(locators.pageContent()).waitUntil(BlazeWebElement.WaitCondition.ELEMENT_IS_PRESENT);
    }

    public List<String> getHeaders() {
        waitForPageToLoad();
        return blazeLibrary.getElements(locators.headers(), 0).stream().map(BlazeWebElement::getText).map(String::trim).collect(Collectors.toList());
    }

    public List<String> getDownloadLinkHeaders() {
        waitForPageToLoad();
        return blazeLibrary.getElements(locators.downloadLinkHeaders(), 0)
                .stream().map(BlazeWebElement::getText).map(String::trim).collect(Collectors.toList());
    }

    public boolean isHeaderPresent(String headerText) {
        waitForPageToLoad();
        return blazeLibrary.getElements(locators.header(headerText), 0).size() != 0;
    }

    public String getHeaderContent(String headerText) {
        return isHeaderPresent(headerText) ? blazeLibrary.getElement(locators.headerContent(headerText)).getText() : null;
    }

    public String getDownloadLinkType(String headerText) {
        return isHeaderPresent(headerText) ? blazeLibrary.getElement(locators.downloadLink(headerText)).getAttribute("type") : null;
    }

    public URL getDownloadLinkUrl(String headerText) {
        try {
            return isHeaderPresent(headerText) ? new URL(blazeLibrary.getElement(locators.downloadLink(headerText)).getAttribute("href")) : null;
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public boolean isArchiveTypePresent() {
        return isHeaderPresent("Archive Type");
    }

    public String getArchiveType() {
        return getHeaderContent("Archive Type");
    }

    public boolean isFacNumberPresent() {
        return isHeaderPresent("FAC Number");
    }

    public String getFacNumber() {
        return getHeaderContent("FAC Number");
    }

    public boolean isEffectiveDatePresent() {
        return isHeaderPresent("Effective Date");
    }

    public LocalDate getEffectiveDate() {
        return LocalDate.parse(getHeaderContent("Effective Date"), DateTimeFormatter.ofPattern("EEEE, MMMM d, uuuu"));
    }

    public boolean isYearPresent() {
        return isHeaderPresent("Year");
    }

    public String getYear() {
        return getHeaderContent("Year");
    }

    private static class locators {
        private static By header(String headerText) {
            return By.xpath(String.format("//div[@class='field-label' and contains(., '%s')]", headerText));
        }
        private static By headers() {
            return By.xpath("//div[@class='field-label']");
        }
        private static By downloadLinkHeaders() {
            return By.xpath("//div[contains(@class, 'field-label-above') and .//a]//div[@class='field-label']");
        }
        private static By pageContent() {
            return By.xpath("//div[@id='middlecontent']");
        }
        private static By headerContent(String headerText) {
            return By.xpath(String.format("//div[@class='field-label' and contains(., '%s')]//following-sibling::div", headerText));
        }
        private static By downloadLink(String headerText) {
            return By.xpath(String.format("//div[@class='field-label' and contains(., '%s')]//following-sibling::div//a", headerText));
        }
    }

}
