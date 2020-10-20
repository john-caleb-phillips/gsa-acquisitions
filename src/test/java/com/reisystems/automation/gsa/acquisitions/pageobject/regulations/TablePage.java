package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TablePage extends HasBlazeLibrary {

    public TablePage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public BufferedImage getRegulationLogo() {
        return blazeLibrary.images().getFromImgTag(locators.regulationLogo());
    }

    public String getRegulationName() {
        return blazeLibrary.getElement(locators.regulationName()).getText().trim();
    }

    public String getHeader() {
        return blazeLibrary.getElement(locators.header()).getText().trim();
    }

    public String getContent() {
        return blazeLibrary.getElements(locators.content())
                .stream().map(BlazeWebElement::getText).collect(Collectors.joining("\n"));
    }

    public void forEachPart(Consumer<PartRow> consumer) {
        int numberOfSearchRows = numberOfSearchRows();
        for (int i = 1; i <= numberOfSearchRows; i++) {
            consumer.accept(getPartRow(i));
        }
    }

    public PartRow getPartRow(int rowNumber) {
        return new PartRow(rowNumber);
    }

    public int numberOfSearchRows() {
        return blazeLibrary.getElements(By.xpath("//table[@id='regulation-index-browse']//tbody//tr")).size();
    }

    public class PartRow {

        int rowNumber;

        private PartRow(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public void goToPart() {

        }

        public void getPrint() {

        }

        public void getPdf() {

        }

        public String getNumber() {
            return blazeLibrary.getElement(By.xpath(String.format("//table[@id='regulation-index-browse']//tbody//tr[%s]//td[1]", rowNumber))).getText();
        }

        public String getTitle() {
            return blazeLibrary.getElement(By.xpath(String.format("//table[@id='regulation-index-browse']//tbody//tr[%s]//td[2]", rowNumber))).getText();
        }
    }

    private static class locators {
        private static By regulationLogo() {
            return By.xpath("//div[@id='middlecontent']//div[@class='heading']//img");
        }
        private static By regulationName() {
            return By.xpath("//div[@id='middlecontent']//div[@class='heading']//img/following-sibling::span");
        }
        private static By header() {
            return By.xpath("//div[@class='heading']//h1");
        }
        private static By content() {
            return By.xpath("//div[@class='heading']//div[.//h1]//following-sibling::*");
        }
    }
}
