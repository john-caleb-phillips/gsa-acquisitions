package com.reisystems.automation.gsa.acquisitions.pageobject.search;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SearchPage extends HasBlazeLibrary {

    public SearchPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public String getSearchTerm() {
        String url = blazeLibrary.browser().getCurrentUrl();
        url = url.substring(url.indexOf("search") + "search/".length());
        if (url.startsWith("advanced")) {
            url = url.substring("advanced".length());
        } else if (url.startsWith("site")) {
            url = url.substring("site".length());
        }
        if (url.contains("?")) {
            url = url.substring(0, url.indexOf("?"));
        }
        if (url.contains("/")) {
            url = url.substring(url.indexOf("/") + 1);
        }
        return url;
    }

    public int numberOfSearchRows() {
        return blazeLibrary.getElements(By.xpath("//li[@class='search-result']")).size();
    }

    public SearchResult getSearchRow(int i) {
        return new SearchResult(i);
    }

    public List<String> getFilterHeaders() {
        By headerLocator = By.xpath("//div[contains(@class, 'block-facetapi') or contains(@class, 'block-apachesolr-search')]//h2");
        return blazeLibrary.getElements(headerLocator)
                .stream().map(header -> header.getText().trim()).collect(Collectors.toList());
    }

    public List<SearchFilterOption> getFilterOptions(String filterName) {
        By optionsLocator = By.xpath(String.format("//div[(contains(@class, 'block-facetapi') or contains(@class, 'block-apachesolr-search')) and .//h2[.='%s']]//li", filterName));
        return blazeLibrary.getElements(optionsLocator).stream()
                .map(option -> Arrays.stream(option.getText().trim().split("\n"))
                        .filter(part -> (!part.startsWith("Apply") && !part.startsWith("Remove")) || !part.endsWith("filter"))
                        .collect(Collectors.toList()))
                .map(list -> new SearchFilterOption(
                        list.size() == 1  && !isInteger(list.get(0)) || list.size() > 1 ? list.get(0) : null,
                        list.size() > 1 ? list.get(1) : list.size() == 1 && isInteger(list.get(0)) ? list.get(0) : null))
                .collect(Collectors.toList());
    }

    private boolean isInteger(String toTest) {
        try {
            Integer.parseInt(toTest);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void forEachRowOnThePage(Consumer<SearchResult> consumer) {
        int numberOfSearchRows = numberOfSearchRows();
        for (int i = 1; i <= numberOfSearchRows; i++) {
            consumer.accept(getSearchRow(i));
        }
    }

    public void forEachRowInTheSearchResults(Consumer<SearchResult> consumer) {
        if (hasPreviousPage()) {
            goToFirstPage();
        }
        forEachRowOnThePage(consumer);
        while (hasNextPage()) {
            goToNextPage();
            forEachRowOnThePage(consumer);
        }
    }

    public boolean hasPreviousPage() {
        return blazeLibrary.getElement(By.xpath("//a[@title='Go to previous page']")).isPresent();
    }

    public boolean hasNextPage() {
        return blazeLibrary.getElement(By.xpath("//a[@title='Go to next page']")).isPresent();
    }

    public void goToFirstPage() {
        blazeLibrary.getElement(By.xpath("//a[@title='Go to first page']")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToPreviousPage() {
        blazeLibrary.getElement(By.xpath("//a[@title='Go to previous page']")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToNextPage() {
        blazeLibrary.getElement(By.xpath("//a[@title='Go to next page']")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToLastPage() {
        blazeLibrary.getElement(By.xpath("//a[@title='Go to last page']")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void setFarRegulationCriteria() {
        blazeLibrary.getElement(By.xpath("//label[@for='far-checkbox']")).click();
    }

    public void setOtherRegulationsCriteria(List<String> desiredOrigins) {
        blazeLibrary.getElement(By.xpath("//label[@for='other-checkbox']")).click();
        for (String desiredOrigin : desiredOrigins) {
            blazeLibrary.getElement(By.xpath(String.format("//form[@id='advanced-search-form']//label[.='%s']/preceding-sibling::input", desiredOrigin))).click();
        }
        blazeLibrary.getElement(By.xpath("//form[@id='advanced-search-form']//input[@class='ad-submit']")).click();
    }

    public class SearchResult {
        private final int rowNumber;

        private SearchResult(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public void goToDetailPage() {
            blazeLibrary.getElement(By.xpath(String.format("//li[@class='search-result'][%s]//h3/a", rowNumber))).click(blazeLibrary.clickResults().REFRESH_PAGE);
        }

        public SearchRow getInfo() {
            return new SearchRow(
                    blazeLibrary.getElement(By.xpath(String.format("//li[@class='search-result'][%s]//h3/a", rowNumber))).getText().trim(),
                    blazeLibrary.getElement(By.xpath(String.format("//li[@class='search-result'][%s]//div[@class='search-snippet-info']", rowNumber))).getText().trim(),
                    blazeLibrary.getElement(By.xpath(String.format("//li[@class='search-result'][%s]//div[@class='download']", rowNumber))).getText().trim(),
                    blazeLibrary.getElements(By.xpath(String.format("//li[@class='search-result'][%s]//div[@class='search-snippet-info']//*", rowNumber)))
                            .stream().filter(el -> el.getCssValue("background-color").equals("rgba(255, 255, 0, 1)"))
                            .map(BlazeWebElement::getText).collect(Collectors.toList())
            );
        }
    }

    public static class SearchRow {
        public final String title;
        public final String content;
        public final String origin;
        public final List<String> highlightedTerms;

        public SearchRow(String title, String content, String origin, List<String> highlightedTerms) {
            this.title = title;
            this.content = content;
            this.origin = origin;
            this.highlightedTerms = new ArrayList<>();
            this.highlightedTerms.addAll(highlightedTerms);
        }

        public String toString() {
            return String.format("SearchRow(title='%s',content='%s',origin='%s')", title, content, origin);
        }
    }

    public static class SearchFilterOption {
        public final String text;
        public final String number;

        private SearchFilterOption(String text, String number) {
            this.text = text;
            this.number = number;
        }
    }

}
