package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
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
                .stream().map(element -> StringUtils.normalizeSpace(element.getText())).collect(Collectors.toList());
        if (headers.contains(columnName)) {
            return blazeLibrary.getElements(By.xpath(
                    String.format("//table[@id='regulation-index-browse']//td[%s]", headers.indexOf(columnName) + 1)))
                    .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
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

    public void forEachDetailPage(Consumer<DetailPage> consumer) {
        forEachPart(row -> row.onDetailPage(consumer));
    }

    public class DetailPage {
        private final RowInfo previousRowInfo, currentRowInfo, nextRowInfo;

        public DetailPage(RowInfo previousRowInfo, RowInfo currentRowInfo, RowInfo nextRowInfo) {
            this.previousRowInfo = previousRowInfo;
            this.currentRowInfo = currentRowInfo;
            this.nextRowInfo = nextRowInfo;
        }

        public RowInfo getPreviousRowInfo() {
            return previousRowInfo;
        }

        public RowInfo getCurrentRowInfo() {
            return currentRowInfo;
        }

        public RowInfo getNextRowInfo() {
            return nextRowInfo;
        }

        public String getRegulationName() {
            return blazeLibrary.getElement(By.xpath("//div[@id='main-content-internal']//img/following-sibling::span")).getText();
        }

        public String getPartNumber() {
            blazeLibrary.getElement(By.xpath("//div[contains(@class, 'part-container')] | //table[@id='regulation-index-browse']")).waitUntil(
                    BlazeWebElement.WaitCondition.ELEMENT_IS_PRESENT
            );
            return blazeLibrary.getElement(By.xpath("//div[@class='utility_icons']//ul/li[@class='active']/a"), 0).getText();
        }

        public String getTitle() {
            return null;
        }

        public List<String> getBreadcrumbs() {
            return blazeLibrary.getElements(By.xpath("//div[@id='breadcrumbs']//a"))
                    .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
        }

        public boolean hasNextPage() {
            return blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Next Page']/..")).isPresent();
        }

        public void goToNextPage() {
            blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Next Page']/..")).click(
                    blazeLibrary.clickResults().REFRESH_PAGE
            );
        }

        public boolean hasPreviousPage() {
            return blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Previous Page']/..")).isPresent();
        }

        public void goToPreviousPage() {
            blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Previous Page']/..")).click(
                    blazeLibrary.clickResults().REFRESH_PAGE
            );
        }

        public boolean hasTocLink() {
            return blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Table Of Contents']/..")).isPresent();
        }

        public void clickTocLink() {
            blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Table Of Contents']/..")).click(
                    blazeLibrary.clickResults().REFRESH_PAGE
            );
        }

        public boolean hasTopLink() {
            return blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Top Of Page']/..")).isPresent();
        }

        public void clickTopLink() {
            blazeLibrary.getElement(By.xpath("//div[@class='regnavigation']//img[@alt='Top Of Page']/..")).click();
        }

        public List<URL> getContentUrls() {
            blazeLibrary.getElement(By.xpath("//div[@class='field-items']")).waitUntil(BlazeWebElement.WaitCondition.ELEMENT_IS_PRESENT);
            return blazeLibrary.getElements(By.xpath("//div[@class='field-items']//a[@href][not(ancestor::div[@class='regnavigation'])][not(ancestor::div[@id='Table of Contents1'])]"), 0)
                    .stream().map(element -> {
                        try {
                            return new URL(element.getAttribute("href"));
                        } catch (MalformedURLException e) {
                            DetailPage page = new DetailPage(null, null, null);
                            blazeLibrary.assertion().fail(String.format("%s - %s: '%s' is a malformed url because: %s",
                                    page.getRegulationName(), page.getPartNumber(), element.getAttribute("href"), e.getMessage()));
                            return null;
                        }
                    }).filter(Objects::nonNull).collect(Collectors.toList());
        }

        public Map<String, String> getTocLinks() {
            blazeLibrary.getElement(By.xpath("//div[@id='content-wrapper']")).waitUntil(BlazeWebElement.WaitCondition.ELEMENT_IS_PRESENT);

            Map<String, String> tocLinks = new HashMap<>();
            for (BlazeWebElement tocElement : blazeLibrary.getElements(By.xpath("//a[starts-with(@href, '#_Toc')]"), 0)) {
                String[] id = tocElement.getAttribute("href").split("#");
                tocLinks.put(StringUtils.normalizeSpace(tocElement.getText()), id.length > 1 ? id[1] : id[0]);
            }
            for (Map.Entry<String, String> entry : tocLinks.entrySet()) {
                BlazeWebElement contentLink = blazeLibrary.getElement(By.xpath(String.format("//a[not(@href) and @id='%s']/..", entry.getValue())));
                if (contentLink.isPresent()) {
                    entry.setValue(StringUtils.normalizeSpace(contentLink.getText()));
                } else {
                    entry.setValue(null);
                }
            }
            return tocLinks;
        }
    }

    public PartRow getPartRow(int rowNumber) {
        return new PartRow(rowNumber);
    }

    public int numberOfSearchRows() {
        return blazeLibrary.getElements(By.xpath("//table[@id='regulation-index-browse']//tbody//tr")).size();
    }

    public class PartRow {

        private final String rowLocator;
        private final RowInfo rowInfo;
        private final int rowNumber;

        private PartRow(int rowNumber) {
            this.rowNumber = rowNumber;
            this.rowLocator = String.format("//table[@id='regulation-index-browse']//tbody//tr[%s]", rowNumber);
            String partNumber = blazeLibrary.getElement(By.xpath(rowLocator + "//td[1]")).getText();
            String title = blazeLibrary.getElement(By.xpath(rowLocator + "//td[2]")).getText();
            URL printUrl;
            try {
                printUrl = new URL(blazeLibrary.getElement(By.xpath(rowLocator + "//td[3]/a"), 0).getAttribute("href"));
            } catch (MalformedURLException e) {
                printUrl = null;
            }
            URL pdfUrl;
            try {
                pdfUrl = new URL(blazeLibrary.getElement(By.xpath(rowLocator + "//td[4]/a"), 0).getAttribute("href"));
            } catch (MalformedURLException e) {
                pdfUrl = null;
            }
            this.rowInfo = new RowInfo(partNumber, title, printUrl, pdfUrl);
        }

        public boolean hasPreviousRow() {
            return blazeLibrary.getElement(By.xpath(rowLocator + "/preceding-sibling::tr")).isPresent();
        }

        public boolean hasNextRow() {
            return blazeLibrary.getElement(By.xpath(rowLocator + "/following-sibling::tr")).isPresent();
        }

        public void onDetailPage(Consumer<DetailPage> consumer) {
            RowInfo previousRowInfo = hasPreviousRow() ? new PartRow(rowNumber - 1).info() : null;
            RowInfo nextRowInfo = hasNextRow() ? new PartRow(rowNumber + 1).info() : null;
            blazeLibrary.getElement(By.xpath(rowLocator + "//td[1]/a")).click(
                    blazeLibrary.clickResults().REFRESH_PAGE
            );
            consumer.accept(new DetailPage(previousRowInfo, info(), nextRowInfo));
            blazeLibrary.browser().navigateBack();
        }

        public RowInfo info() {
            return rowInfo;
        }
    }

    public static class RowInfo {
        private final String partNumber;
        private final String title;
        private final URL printUrl;
        private final URL pdfUrl;

        public RowInfo(String partNumber, String title, URL printUrl, URL pdfUrl) {
            this.partNumber = partNumber;
            this.title = title;
            this.printUrl = printUrl;
            this.pdfUrl = pdfUrl;
        }

        public String partNumber() {
            return partNumber;
        }

        public String title() {
            return title;
        }

        public URL printUrl() {
            return printUrl;
        }

        public URL pdfUrl() {
            return pdfUrl;
        }
    }

    public static class ContentLink {
        private final String id;
        private final String text;

        private ContentLink(String id, String text) {
            this.id = id;
            this.text = text;
        }

        public String id() {
            return id;
        }

        public String text() {
            return text;
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
