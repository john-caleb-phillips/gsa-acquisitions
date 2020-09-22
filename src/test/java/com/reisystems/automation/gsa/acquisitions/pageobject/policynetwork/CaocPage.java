package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class CaocPage extends PageObject {

    public CaocPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void goToPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/cao-home");
    }

    public void goToSubPage(String subPage) {
        BlazeWebElement toClick = blazeLibrary.getElement(By.xpath("//div[contains(@class, 'cao-gov-menu')]//div[contains(@class, 'dropdown')]//a[normalize-space(.)='%s']".formatted(subPage)));
        BlazeWebElement dropdownHeader = blazeLibrary.getElement(By.xpath("//div[contains(@class, 'cao-gov-menu')]//div[contains(@class, 'dropdown')]//a[normalize-space(.)='%s']/../..//button".formatted(subPage)));
        int xOffset = dropdownHeader.getLocation().getX() + (dropdownHeader.getSize().getWidth() / 2);
        int yOffset = dropdownHeader.getLocation().getY() + (dropdownHeader.getSize().getHeight() / 2);
        blazeLibrary.mouseAndKeyboard().moveByOffset(xOffset, yOffset).perform();
        toClick.click(blazeLibrary.defaults().REFRESH_PAGE);
        blazeLibrary.mouseAndKeyboard().moveByOffset(-1 * xOffset, -1 * yOffset).perform();
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

    public void clickSideBarLink(String linkText) {
        blazeLibrary.getElement(By.xpath("//div[contains(@class, 'caoc-resources')]//a[normalize-space(.)='%s']".formatted(linkText)))
                .click(blazeLibrary.defaults().REFRESH_PAGE, blazeLibrary.defaults().OPEN_WINDOW_OR_TAB);
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
        return blazeLibrary.getElement(By.xpath("//tbody//tr/td[1][normalize-space(.)='%s']/../td[2]".formatted(agencyName))).getText();
    }

    public String getAgencyAlternate(String agencyName) {
        showAllAgencies();
        return blazeLibrary.getElement(By.xpath("//tbody//tr/td[1][normalize-space(.)='%s']/../td[3]".formatted(agencyName))).getText();
    }

    private void showAllAgencies() {
        blazeLibrary.getElement(By.xpath("//button[@id='view_all']")).click();
    }

}