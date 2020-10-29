package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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

    public boolean linkIsPresent(String linkText) {
        return blazeLibrary.getElement(By.xpath(String.format(".//a[normalize-space(.)='%s']", linkText))).isPresent();
    }

    public void clickLink(String linkText) {
        blazeLibrary.getElement(By.xpath(String.format(".//a[normalize-space(.)='%s']", linkText))).click(
                blazeLibrary.clickResults().REFRESH_PAGE
        );
    }

    public boolean tableHasColumn(String columnName) {
        return blazeLibrary.getElement(By.xpath(
                String.format(
                        "//table[@id='regulation-index-browse']//th[normalize-space(.)='%s']",
                        columnName
                )
        )).isPresent();
    }

    public void sortTableByColumn(String columnName, SortOrder ascendingOrDescending) {
        BlazeWebElement columnHeader = blazeLibrary.getElement(By.xpath(String.format(
                "//table[@id='regulation-index-browse']//th[normalize-space(.)='%s']",
                columnName
        )));
        if (ascendingOrDescending == SortOrder.ASCENDING
                && !columnHeader.getAttribute("class").contains("sorting_asc")) {
            columnHeader.click(() -> columnHeader.getAttribute("class").contains("sorting_asc")
                    ? String.format("Sorted column '%s' in ascending order", columnName) : null);
        } else if (ascendingOrDescending == SortOrder.DESCENDING
                && !columnHeader.getAttribute("class").contains("sorting_desc")) {
            columnHeader.click(() -> columnHeader.getAttribute("class").contains("sorting_desc")
                    ? String.format("Sorted column '%s' in descending order", columnName) : null);
        }
    }

    public List<String> getColumn(String columnName) {
        List<String> headers = blazeLibrary.getElements(By.xpath("//table[@id='regulation-index-browse']//th"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
        if (headers.contains(columnName)) {
            return blazeLibrary.getElements(By.xpath(
                    String.format("//table[@id='regulation-index-browse']//td[%s]", headers.indexOf(columnName) + 1)))
                    .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
        } else {
            return List.of();
        }
    }

    public enum SortOrder {
        ASCENDING,
        DESCENDING
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
            blazeLibrary.getElement(By.xpath(String.format(
                    "//table[@id='regulation-index-browse']//tbody//tr[%s]//td[1]/a", rowNumber
            ))).click(blazeLibrary.clickResults().REFRESH_PAGE);
        }

        public URL getPrintUrl() {
            try {
                return new URL(blazeLibrary.getElement(By.xpath(
                        String.format("//table[@id='regulation-index-browse']//tbody//tr[%s]//td[3]/a", rowNumber)
                )).getAttribute("href"));
            } catch (MalformedURLException e) {
                return null;
            }
        }

        public URL getPdfUrl() {
            try {
                return new URL(blazeLibrary.getElement(By.xpath(
                        String.format("//table[@id='regulation-index-browse']//tbody//tr[%s]//td[4]/a", rowNumber)
                )).getAttribute("href"));
            } catch (MalformedURLException e) {
                return null;
            }
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
