package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

public class Header extends PageObject {

    public Header(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void clickCoronavirusLink() {
        blazeLibrary.getElement(locators.coronavirusLink()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void click889InformationLink() {
        blazeLibrary.getElement(locators.information889Link()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void clickLogo() {
        blazeLibrary.getElement(locators.logo()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void clickTopLevelLink(String linkText) {
        blazeLibrary.getElement(locators.topLevelLink(linkText)).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void clickRegulationDropdownLink(String linkText) {
        blazeLibrary.mouseAndKeyboard().moveToElement(blazeLibrary.getElement(locators.topLevelLink("Regulations"))).perform();
        blazeLibrary.getElement(locators.regulationDropdownLink(linkText)).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void clickPolicyNetworkDropdownLink(String linkText) {
        blazeLibrary.mouseAndKeyboard().moveToElement(blazeLibrary.getElement(locators.topLevelLink("Policy Network"))).perform();
        blazeLibrary.getElement(locators.policyNetworkDropdownLink(linkText)).click();
    }

    public void performSiteSearch(SearchType type, String searchTerm) {
        blazeLibrary.mouseAndKeyboard().moveToElement(blazeLibrary.getElement(locators.topLevelLink("Search"))).perform();
        blazeLibrary.getElement(locators.searchTextBox()).sendKeys(searchTerm);
        switch(type) {
            case site -> blazeLibrary.getElement(locators.searchSiteToggle()).click();
            case regulation -> blazeLibrary.getElement(locators.searchRegulationToggle()).click();
        }
        blazeLibrary.getElement(locators.searchSubmitButton()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public enum SearchType { generic, site, regulation };

    private static class locators {
        private static By coronavirusLink() {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//div[1]/a");
        }
        private static By information889Link() {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//div[2]/a");
        }
        private static By logo() {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//div[@id='header-bar']/a");
        }
        private static By topLevelLink(String linkText) {
            return By.xpath("""
                    //div[contains(@class, 'top-wrapper')]
                    //ul[contains(@class, 'level-0')]
                    /li/a[@title='%s' or normalize-space(.)='%s']
                    """.formatted(linkText, linkText));
        }
        private static By regulationDropdownLink(String linkText) {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Regulations']]//div//a[normalize-space(.)='%s']".formatted(linkText));
        }
        private static By policyNetworkDropdownLink(String linkText) {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Policy Network Home']]//li/a[normalize-space(.)='%s']".formatted(linkText));
        }
        private static By searchTextBox() {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//input[@type='text']");
        }
        private static By searchSiteToggle() {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//label[@for='website']");
        }
        private static By searchRegulationToggle() {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//label[@for='other-checkbox-2']");
        }
        private static By searchSubmitButton() {
            return By.xpath("//div[contains(@class, 'top-wrapper')]//li[.//a[@title='Search']]//input[@type='submit']");
        }
    }
}
