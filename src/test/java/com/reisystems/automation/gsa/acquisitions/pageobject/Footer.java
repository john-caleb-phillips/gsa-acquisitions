package com.reisystems.automation.gsa.acquisitions.pageobject;

import org.openqa.selenium.By;

public class Footer {
    public By footerLinkLocator(String linkText) {
        return By.xpath(String.format("//div[contains(@class, 'footer-links')]//a[text()='%s']", linkText));
    }

    public By siteInfoLinkLocator(String linkText) {
        return By.xpath(String.format("//p[contains(@class, 'site-info-footer')]//a[text()='%s']", linkText));
    }

    public By iconLocator(String iconAltText) {
        return By.xpath(String.format("//div[@id='footer-link']//p[.='Share the FAR']//following-sibling::div//img[contains(@src, '%s-icon')]", iconAltText.toLowerCase()));
    }

    public By signUpLink() {
        return By.xpath("//div[@id='footer-link']//h4//a");
    }
}
