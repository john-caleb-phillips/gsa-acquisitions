package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

public class Header extends PageObject {

    public Header(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    //public By headerLinks = By.xpath("//div[contains(@class, 'region-main-nav')]//ul[contains(@class, 'level-0')]/li[@data-type='menu_item']/a");
    public By coronavirusLinkLocator() {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//div[1]/a");
    }

    public By information889Locator() {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//div[2]/a");
    }

    public By logoLocator() {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//div[@id='header-bar']/a");
    }

    public By linkLocator(String linkText) {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//ul[contains(@class, 'level-0')]/li/a[@title='%s' or normalize-space(.)='%s']"
                .formatted(linkText, linkText));
    }

    public By regulationsDropdownLink(String linkText) {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Regulations']]//div//a[normalize-space(.)='%s']".formatted(linkText));
    }

    public By policyNetworkDropdownLink(String linkText) {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Policy Network Home']]//li/a[normalize-space(.)='%s']".formatted(linkText));
    }

    public By searchTextBox() {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//input[@type='text']");
    }

    public By searchSiteToggle() {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//label[@for='website']");
    }

    public By searchRegulationsToggle() {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//label[@for='other-checkbox-2']");
    }

    public By searchSubmitButton() {
        return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//input[@type='submit']");
    }
}
