package com.reisystems.automation.gsa.acquisitions.pageobject;

import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ArchiveDetailPage {

    BlazeLibrary blazeLibrary;

    public ArchiveDetailPage(BlazeLibrary blazeLibrary) {
        this.blazeLibrary = blazeLibrary;
    }

    public List<String> getHeaders() {
        blazeLibrary.getElement(By.xpath("//div[@id='middlecontent']")).waitUntil(BlazeWebElement.WaitCondition.ELEMENT_IS_PRESENT);
        return blazeLibrary.getElements(By.xpath("//div[@class='field-label']"), 0).stream().map(BlazeWebElement::getText).map(String::trim).collect(Collectors.toList());
    }

    public List<String> getDownloadLinkHeaders() {
        blazeLibrary.getElement(By.xpath("//div[@id='middlecontent']")).waitUntil(BlazeWebElement.WaitCondition.ELEMENT_IS_PRESENT);
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'field-label-above') and .//a]//div[@class='field-label']"), 0)
                .stream().map(BlazeWebElement::getText).map(String::trim).collect(Collectors.toList());
    }

    public boolean isHeaderPresent(String headerText) {
        blazeLibrary.getElement(By.xpath("//div[@id='middlecontent']")).waitUntil(BlazeWebElement.WaitCondition.ELEMENT_IS_PRESENT);
        return blazeLibrary.getElements(By.xpath(String.format("//div[@class='field-label' and contains(., '%s')]", headerText)), 0).size() != 0;
    }

    public String getHeaderContent(String headerText) {
        if (!isHeaderPresent(headerText)) {
            return null;
        }
        return blazeLibrary.getElement(By.xpath(String.format("//div[@class='field-label' and contains(., '%s')]//following-sibling::div", headerText))).getText();
    }

    public String getDownloadLinkType(String headerText) {
        if (!isHeaderPresent(headerText)) {
            return null;
        }
        return blazeLibrary.getElement(By.xpath(String.format("//div[@class='field-label' and contains(., '%s')]//following-sibling::div//a", headerText))).getAttribute("type");
    }

    public URL getDownloadLinkUrl(String headerText) {
        if (!isHeaderPresent(headerText)) {
            return null;
        }
        try {
            return new URL(blazeLibrary.getElement(By.xpath(String.format("//div[@class='field-label' and contains(., '%s')]//following-sibling::div//a", headerText))).getAttribute("href"));
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

}
