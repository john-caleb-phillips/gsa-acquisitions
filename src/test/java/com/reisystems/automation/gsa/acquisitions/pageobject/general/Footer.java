package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

public class Footer extends PageObject {

    public Footer(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public By footerLinkLocator(String linkText) {
        return By.xpath("//div[contains(@class, 'footer-links')]//a[text()='%s']".formatted(linkText));
    }

    public By siteInfoLinkLocator(String linkText) {
        return By.xpath("//p[contains(@class, 'site-info-footer')]//a[text()='%s']".formatted(linkText));
    }

    public By iconLocator(String iconAltText) {
        return By.xpath("//div[@id='footer-link']//p[.='Share the FAR']//following-sibling::div//img[contains(@src, '%s-icon')]".formatted(iconAltText.toLowerCase()));
    }

    public By signUpLink() {
        return By.xpath("//div[@id='footer-link']//h4//a");
    }
}
