package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SearchPage extends PageObject {

    public SearchPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void goToSitePage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/search/site/");
    }

    public void goToRegulationPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/search/advanced/");
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
        By optionsLocator = By.xpath("//div[(contains(@class, 'block-facetapi') or contains(@class, 'block-apachesolr-search')) and .//h2[.='%s']]//li".formatted(filterName));
        return blazeLibrary.getElements(optionsLocator).stream()
                .map(option -> Arrays.stream(option.getText().trim().split("\n"))
                        .filter(part -> (!part.startsWith("Apply") && !part.startsWith("Remove")) || !part.endsWith("filter"))
                        .collect(Collectors.toList()))
                .map(list -> new SearchFilterOption(list.get(0), list.size() > 1 ? list.get(1) : null))
                .collect(Collectors.toList());
    }

    public void forEachRowOnThePage(Consumer<SearchResult> consumer) {
        int numberOfSearchRows = numberOfSearchRows();
        for (int i = 1; i <= numberOfSearchRows; i++) {
            consumer.accept(getSearchRow(i));
        }
    }

    public void forEachRowInTheSearchResults(Consumer<SearchResult> consumer) {
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
        blazeLibrary.getElement(By.xpath("//a[@title='Go to first page']")).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void goToPreviousPage() {
        blazeLibrary.getElement(By.xpath("//a[@title='Go to previous page']")).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void goToNextPage() {
        blazeLibrary.getElement(By.xpath("//a[@title='Go to next page']")).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public void goToLastPage() {
        blazeLibrary.getElement(By.xpath("//a[@title='Go to last page']")).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    public class SearchResult {
        private final int rowNumber;

        private SearchResult(int rowNumber) {
            this.rowNumber = rowNumber;
        }

        public void goToDetailPage() {
            blazeLibrary.getElement(By.xpath("//li[@class='search-result'][%s]//h3/a".formatted( rowNumber))).click(blazeLibrary.defaults().REFRESH_PAGE);
        }

        public SearchRow getInfo() {
            return new SearchRow(
                    blazeLibrary.getElement(By.xpath("//li[@class='search-result'][%s]//h3/a".formatted(rowNumber))).getText().trim(),
                    blazeLibrary.getElement(By.xpath("//li[@class='search-result'][%s]//div[@class='search-snippet-info']".formatted(rowNumber))).getText().trim(),
                    blazeLibrary.getElement(By.xpath("//li[@class='search-result'][%s]//div[@class='download']".formatted(rowNumber))).getText().trim()
            );
        }
    }

    public static class SearchRow {
        public final String title;
        public final String content;
        public final String origin;

        public SearchRow(String title, String content, String origin) {
            this.title = title;
            this.content = content;
            this.origin = origin;
        }

        public String toString() {
            return "SearchRow(title='%s',content='%s',origin='%s')".formatted(title, content, origin);
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
