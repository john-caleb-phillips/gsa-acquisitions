package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class FarcPage extends PageObject {

    public FarcPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void goToPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/far-council");
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
        blazeLibrary.getElement(By.xpath("//div[@id='block-menu-menu-about-the-far-council']//li//a[.='%s']".formatted(linkText))).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void clickFarcMemorandaLink(String linkText) {
        blazeLibrary.getElement(By.xpath("//div[contains(@class, 'field-items')]//a[.='%s']".formatted(linkText))).click(blazeLibrary.defaults().OPEN_WINDOW_OR_TAB);
    }

}
