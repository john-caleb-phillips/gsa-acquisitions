package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends HasBlazeLibrary {
    public MainPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void goToPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/content/regulations");
    }

    public String getPageType() {
        if (blazeLibrary.getElement(By.xpath("//table[@id='regulation-index-browse']")).isPresent()) {
            return "table";
        } else if (blazeLibrary.getElement(By.xpath("//div[@id='parts-column']")).isPresent()) {
            return "sidebar";
        } else {
            return "unknown";
        }
    }

    public void clickRegulation(String regulationName) {
        blazeLibrary.getElement(locators.regulationLink(regulationName)).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public List<String> getRegulationNames() {
        return blazeLibrary.getElements(locators.regulationLinks())
                .stream().map(BlazeWebElement::getText)
                .map(StringUtils::normalizeSpace).collect(Collectors.toList());
    }

    public BufferedImage getRegulationImage(String regulationName) {
        return blazeLibrary.images().getFromImgTag(locators.regulationImage(regulationName));
    }

    private static class locators {
        private static By regulationLink(String regulationName) {
            return By.xpath("//div[@class='field-items']//a[normalize-space(.)='%s']".formatted(regulationName));
        }
        private static By regulationLinks() {
            return By.xpath("//div[@class='field-items']//a");
        }
        private static By regulationImage(String regulationName) {
            return By.xpath("//div[@class='field-items']//a[normalize-space(.)='%s']//img".formatted(regulationName));
        }
    }
}
