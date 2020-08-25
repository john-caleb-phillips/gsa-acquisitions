package com.reisystems.automation.gsa.acquisitions.pageobject;

import com.reisystems.blaze.controller.BlazeDriver;
import org.openqa.selenium.By;

public class Header {
    public By headerLinks = By.xpath("//div[contains(@class, 'region-main-nav')]//ul[contains(@class, 'level-0')]/li[@data-type='menu_item']/a");

    public By link(String linkText) {
        return By.xpath(String.format("//div[contains(@class, 'top-wrapper')]//ul[contains(@class, 'level-0')]/li/a[%s or %s]",
                BlazeDriver.xpath().contains("@title", linkText), BlazeDriver.xpath().contains(".", linkText))
        );
    }
}
