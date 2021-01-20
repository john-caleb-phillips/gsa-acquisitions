package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caoc;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class CaocMainPage extends PageObject {

    public CaocMainPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "cao-home");
    }

    public void goToSubPage(String subPage) {
        BlazeWebElement toClick = blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'cao-gov-menu')]//div[contains(@class, 'dropdown')]//a[normalize-space(.)='%s']", subPage)));
        BlazeWebElement dropdownHeader = blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'cao-gov-menu')]//div[contains(@class, 'dropdown')]//a[normalize-space(.)='%s']/../..//button", subPage)));
        blazeLibrary.mouseAndKeyboard().moveToElement(dropdownHeader).perform();
        toClick.click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public BufferedImage getHeaderImage() {
        return blazeLibrary.images().getFromImgTag(By.xpath("//div[contains(@class, 'cao-title')]//img"));
    }

    public String getHeaderText() {
        return blazeLibrary.getElement(By.xpath("//div[contains(@class, 'cao-title')]")).getText().trim();
    }

    public String getContentText() {
        return blazeLibrary.getElement(By.xpath("//div[contains(@class, 'cao-title')]/following-sibling::div//div[contains(@class, 'entry') or contains(@class, 'col-lg-8')]")).getText();
    }

    public String getSideBarHeader() {
        return blazeLibrary.getElement(By.xpath("//div[@class='heading']")).getText();
    }

    public List<String> getSideBarLinks() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'caoc-resources')]//a"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public String getSideBarLinkHref(String linkText) {
        return blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'caoc-resources')]//a[normalize-space(.)='%s']", linkText)))
                .getAttribute("href");
    }

    public void clickSideBarLink(String linkText) {
        blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'caoc-resources')]//a[normalize-space(.)='%s']", linkText)))
                .click(blazeLibrary.clickResults().REFRESH_PAGE, blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    public List<String> getAgencyTableHeaders() {
        return blazeLibrary.getElements(By.xpath("//thead[contains(@class, 'cao-thead')]//th"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public List<String> getAgencyNames() {
        showAllAgencies();
        return blazeLibrary.getElements(By.xpath("//tbody//tr/td[1]"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public String getAgencyPrinciple(String agencyName) {
        showAllAgencies();
        return blazeLibrary.getElement(By.xpath(String.format("//tbody//tr/td[1][normalize-space(.)='%s']/../td[2]", agencyName))).getText();
    }

    public String getAgencyAlternate(String agencyName) {
        showAllAgencies();
        return blazeLibrary.getElement(By.xpath(String.format("//tbody//tr/td[1][normalize-space(.)='%s']/../td[3]", agencyName))).getText();
    }

    private void showAllAgencies() {
        blazeLibrary.getElement(By.xpath("//button[@id='view_all']")).click();
    }

}
