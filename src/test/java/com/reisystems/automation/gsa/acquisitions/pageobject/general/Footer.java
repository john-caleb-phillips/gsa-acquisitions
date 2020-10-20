package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

public class Footer extends HasBlazeLibrary {

    public Footer(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void clickFooterLink(String linkText) {
        blazeLibrary.getElement(locators.footerLink(linkText)).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void clickSiteInfoLink(String linkText) {
        blazeLibrary.getElement(locators.siteInfoLink(linkText)).click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB, blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void clickIcon(String iconAltText) {
        blazeLibrary.getElement(locators.icon(iconAltText)).click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB, blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void clickSignUpLink() {
        blazeLibrary.getElement(locators.signUpLink()).click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    private static class locators {
        private static By footerLink(String linkText) {
            return By.xpath(String.format("//div[contains(@class, 'footer-links')]//a[text()='%s']", linkText));
        }
        private static By siteInfoLink(String linkText) {
            return By.xpath(String.format("//p[contains(@class, 'site-info-footer')]//a[text()='%s']", linkText));
        }
        private static By icon(String linkText) {
            return By.xpath(String.format("//div[@id='footer-link']//p[.='Share the FAR']//following-sibling::div//img[contains(@src, '%s-icon')]", linkText.toLowerCase()));
        }
        private static By signUpLink() {
            return By.xpath("//div[@id='footer-link']//h4//a");
        }
    }
}
