package com.reisystems.automation.gsa.acquisitions.pageobject;

import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.controller.ExecutionController;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    BlazeLibrary blazeLibrary;

    public HomePage(BlazeLibrary blazeLibrary) {
        this.blazeLibrary = blazeLibrary;
    }

    public void goToHomePage() {
        blazeLibrary.browser().navigateToUrl(ExecutionController.getPortalController().getPortalUrl("INTERNAL"));
    }

    public By ovalButtonLocator(String buttonText) {
        return By.xpath(String.format("//div[contains(@class, 'button-wrapper')]/a[text()='%s']", buttonText));
    }

    public By squareButtonLocator(String buttonText) {
        return By.xpath(String.format("//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5') and .//h4[text()='%s']]", buttonText));
    }

    public List<String> getOvalButtons() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'button-wrapper')]/a[text()]")).stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public List<String> getSquareButtons() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5')]//h4[text()]")).stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }
}
