package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends PageObject {

    public HomePage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void goToPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/");
    }

    public By ovalButtonLocator(String buttonText) {
        return By.xpath("//div[contains(@class, 'button-wrapper')]/a[text()='%s']".formatted(buttonText));
    }

    public By squareButtonLocator(String buttonText) {
        return By.xpath("//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5') and .//h4[text()='%s']]".formatted(buttonText));
    }

    public List<String> getOvalButtons() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'button-wrapper')]/a[text()]")).stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public List<String> getSquareButtons() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'middle-content')]//div[contains(@class, 'col-lg-5')]//h4[text()]")).stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }
}
