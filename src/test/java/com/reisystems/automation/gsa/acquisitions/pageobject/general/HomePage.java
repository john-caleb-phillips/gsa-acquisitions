package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends PageObject {
    public HomePage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "");
    }

    public void clickOvalButton(String buttonText) {
        blazeLibrary.getElement(locators.ovalButton(buttonText)).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void clickSquareButton(String buttonText) {
        blazeLibrary.getElement(locators.squareButton(buttonText)).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public List<String> getOvalButtons() {
        return blazeLibrary.getElements(locators.ovalButtons()).stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public List<String> getSquareButtons() {
        return blazeLibrary.getElements(locators.squareButtons()).stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public BufferedImage getSquareButtonImage(String buttonText) {
        return blazeLibrary.images().getFromImgTag(locators.squareButtonImage(buttonText));
    }

    private static class locators {
        private static By ovalButton(String buttonText) {
            return By.xpath(String.format("//div[contains(@class, 'button-wrapper')]/a[normalize-space(.)='%s']", buttonText));
        }

        private static By ovalButtons() {
            return By.xpath("//div[contains(@class, 'button-wrapper')]/a[text()]");
        }

        private static By squareButton(String buttonText) {
            return By.xpath(String.format("//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5') and .//h4[text()='%s']]", buttonText));
        }

        private static By squareButtons() {
            return By.xpath("//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5')]//h4[text()]");
        }

        private static By squareButtonImage(String buttonText) {
            return By.xpath(
                    String.format("//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5') and .//h4[text()='%s']]//img", buttonText)
            );
        }
    }
}
