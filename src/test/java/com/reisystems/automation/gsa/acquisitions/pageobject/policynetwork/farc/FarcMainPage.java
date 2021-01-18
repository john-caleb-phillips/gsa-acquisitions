package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.farc;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class FarcMainPage extends PageObject {

    public FarcMainPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "far-council");
    }

    public String getHeader() {
        return blazeLibrary.getElement(By.xpath("//section//h1")).getText();
    }

    public String getContent() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'field-items')]/div/*[local-name() = 'p' or local-name() = 'ul' or local-name() = 'h1'][not(.//preceding-sibling::h3)]"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.joining("\n"));
    }

    public List<String> getFooterLinks() {
        return blazeLibrary.getElements(By.xpath("//div[@id='block-menu-menu-about-the-far-council']//li"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public void clickFooterLink(String linkText) {
        blazeLibrary.getElement(By.xpath(String.format("//div[@id='block-menu-menu-about-the-far-council']//li//a[.='%s']", linkText))).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void clickFarcMemorandaLink(String linkText) {
        blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'field-items')]//a[.='%s']", linkText))).click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    public void verifyFarcMemorandaLink(String linkText, String destinationUrl) {
        String href = blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'field-items')]//a[.='%s']", linkText))).getAttribute("href");
        blazeLibrary.assertion().assertThat(href)
                .withFailMessage("URL for FARC memoranda link '%s' was not was expected.%nExpected: %s%nActual: %s", linkText, destinationUrl, href)
                .isEqualTo(destinationUrl);
        if (blazeLibrary.assertion().wasSuccess()) {
            try {
                new URL(href).openStream();
            } catch (MalformedURLException e) {
                blazeLibrary.assertion().fail("FARC memoranda link '%s' href attribute was not a valid URL. It was '%s'", linkText, href);
            } catch (IOException e) {
                blazeLibrary.assertion().fail("FARC memoranda link '%s' could not be opened.", linkText);
            }
        }
    }

}
