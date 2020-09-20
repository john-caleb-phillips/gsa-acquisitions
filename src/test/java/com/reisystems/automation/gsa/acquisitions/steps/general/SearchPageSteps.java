package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchiveDetailPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.SearchPage;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SearchPageSteps {

    BlazeLibrary blazeLibrary;
    SearchPage searchPage;
    ArchiveDetailPage archiveDetailPage;

    public SearchPageSteps(BlazeLibrary blazeLibrary, SearchPage searchPage, ArchiveDetailPage archiveDetailPage) {
        this.blazeLibrary = blazeLibrary;
        this.searchPage = searchPage;
        this.archiveDetailPage = archiveDetailPage;
    }

    @Given("^I am on the (site|regulation) search page$")
    public void goToSearchPage(String pageType) {
        if ("site".equals(pageType)) {
            searchPage.goToSitePage();
        } else {
            searchPage.goToRegulationPage();
        }
    }

    @When("I perform search for {string}")
    public void performSearch(String searchTerm) {
        blazeLibrary.getElement(By.xpath("//form[@id='advanced-search-form']//div[contains(@class, 'form-item-keys')]//input[@id='searchkeys']")).sendKeys(searchTerm);
        blazeLibrary.getElement(By.xpath("//form[@id='advanced-search-form']//div[contains(@class, 'form-item-keys')]//input[@type='submit']")).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I expand all sidebar options")
    public void expandSidebars() {
        By expandLocator = By.xpath("//div[(contains(@class, 'block-facetapi') or contains(@class, 'block-apachesolr-search'))]//a[.='Show more']");
        while (blazeLibrary.getElement(expandLocator).isPresent()) {
            blazeLibrary.getElement(expandLocator).click();
        }
    }

    @Then("I see the site search error message saying:")
    public void checkErrorMessage(String theMessage) {
        String actualMessage = blazeLibrary.getElement(By.xpath("//div[@id='main-content-wrapper']")).getText().replace("ERROR MESSAGE\n", "");
        blazeLibrary.assertion().assertThat(actualMessage)
                .as("Checking site search error message")
                .isEqualTo(theMessage);
    }

    @Then("I see the following site search results:")
    public void verifySiteSearchResults(List<List<String>> expectedResults) {
        List<SearchPage.SearchRow> expectedSearchResults = expectedResults.stream().map(expectedResult ->
                new SearchPage.SearchRow(
                        expectedResult.get(0),
                        "",
                        expectedResult.get(1).toUpperCase()
                )).collect(Collectors.toList()
        );

        List<SearchPage.SearchRow> actualSearchResults = new ArrayList<>();
        searchPage.forEachRowOnThePage(row -> actualSearchResults.add(row.getInfo()));

        blazeLibrary.assertion().assertThat(actualSearchResults).as("Comparing search results")
                .usingElementComparator((o1, o2) -> (o1.origin.equals(o2.origin) && o1.title.equals(o2.title)) ? 0 : 1)
                .containsAll(expectedSearchResults);
    }

    @Then("I see every search result contains {string}")
    public void verifySearchResults(String searchTerm) {
        searchPage.forEachRowInTheSearchResults(el -> {
            blazeLibrary.assertion().assertThat(el.getInfo().content)
                    .as("Verifying that the search results contain '%s'", searchTerm)
                    .containsIgnoringCase(searchTerm);
        });
    }

    @Then("I see that {string} is highlighted in the search results")
    public void searchResultIsHighlighted(String searchTerm) {
        List<BlazeWebElement> snippets = blazeLibrary.getElements(By.xpath("//div[@class='search-snippet-info']"));
        for (BlazeWebElement snippet : snippets) {
            blazeLibrary.assertion()
                    .assertThat(snippet.findElement(
                            By.xpath(".//strong[" + blazeLibrary.xpath().contains(".", searchTerm) + "]")).isPresent()
                    )
                    .as("Should be present")
                    .isTrue();
            if (blazeLibrary.assertion().wasSuccess()) {
                for (BlazeWebElement el : snippet.findElements(
                        By.xpath(".//strong[" + blazeLibrary.xpath().contains(".", searchTerm) + "]"))
                ) {
                    String color = el.getCssValue("background-color").substring(5);
                    StringTokenizer tokenizer = new StringTokenizer(color);
                    blazeLibrary.assertion()
                            .assertThat(new Color(
                                    Integer.parseInt(tokenizer.nextToken(",").trim()),
                                    Integer.parseInt(tokenizer.nextToken(",").trim()),
                                    Integer.parseInt(tokenizer.nextToken(",").trim())
                            ))
                            .as("Should be yellow")
                            .isEqualTo(Color.YELLOW);
                }
            }
        }
    }

    @Then("I see the following filter headers:")
    public void checkSidebarHeaders(List<String> expectedSidebarHeaders) {
        blazeLibrary.assertion().assertThat(searchPage.getFilterHeaders())
                .as("Checking sidebar headers")
                .containsExactlyElementsOf(expectedSidebarHeaders);
    }

    @Then("I see the following options under filter {string}:")
    public void checkSidebarOptions(String sidebarHeader, List<String> expectedSidebarOptions) {
        List<String> actualSideBarOptions = searchPage.getFilterOptions(sidebarHeader).stream().map(option -> option.text).collect(Collectors.toList());
        blazeLibrary.assertion().assertThat(actualSideBarOptions)
                .as("Checking options under sidebar header '%s'", sidebarHeader)
                .containsExactlyElementsOf(expectedSidebarOptions);
    }

    @Then("I see every search result is from {string} archive")
    public void verifySearchResultsbyArchiveType(String expectedArchiveType) {
        searchPage.forEachRowInTheSearchResults(
                row -> {
                    blazeLibrary.assertion().assertThat(row.getInfo().origin).isEqualTo("ARCHIVES");
                    if (blazeLibrary.assertion().wasSuccess()) {
                        row.goToDetailPage();
                        blazeLibrary.assertion().assertThat(archiveDetailPage.getArchiveType())
                                .as("Testing archive type").isEqualTo(expectedArchiveType);
                        blazeLibrary.browser().navigateBack();
                    }
                });
    }

    @Then("I see every search result is from year {string}")
    public void verifySearchResultsByYear(String expectedYear) {
        searchPage.forEachRowInTheSearchResults(
                row -> {
                    blazeLibrary.assertion().assertThat(row.getInfo().origin).isEqualTo("ARCHIVES");
                    if (blazeLibrary.assertion().wasSuccess()) {
                        row.goToDetailPage();
                        blazeLibrary.assertion().assertThat(archiveDetailPage.getYear())
                                .as("Testing year").isEqualTo(expectedYear);
                        blazeLibrary.browser().navigateBack();
                    }
                });
    }

    @Then("I see the search results are sorted by title")
    public void verifySortByTitle() {
        List<String> titles = new ArrayList<>();
        searchPage.forEachRowInTheSearchResults(row -> titles.add(row.getInfo().title));
        blazeLibrary.assertion().assertThat(titles).as("Table should be ordered").isSorted();
    }

    @When("I filter by archive type {string}")
    public void filterByArchiveType(String desiredArchiveType) {
        expandSidebars();
        blazeLibrary.getElement(By.xpath("//div[contains(@class, 'block-facetapi') and .//h2[.='Filter by archive type:']]//li/a/text()[normalize-space(.)='%s']/..".formatted(desiredArchiveType)))
                .click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I filter by year {string}")
    public void filterByYear(String desiredYear) {
        expandSidebars();
        blazeLibrary.getElement(By.xpath("//div[contains(@class, 'block-facetapi') and .//h2[.='Filter by year:']]//li/a/text()[normalize-space(.)='%s']/..".formatted(desiredYear)))
                .click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I sort by title")
    public void sortByTitle() {
        blazeLibrary.getElement(By.xpath("//div[contains(@class, 'block-apachesolr') and .//h2[.='Sort by']]//li/a/text()[normalize-space(.)='Title']/.."))
                .click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I count the number of search results with archive type {string} as {string}")
    public void countArchiveTypes(String desiredArchiveType, String savedValueKey) {
        AtomicInteger count = new AtomicInteger(0);
        searchPage.forEachRowInTheSearchResults(row -> {
            row.goToDetailPage();
            if (desiredArchiveType.equals(archiveDetailPage.getArchiveType())) {
                count.incrementAndGet();
            }
            blazeLibrary.browser().navigateBack();
        });
        blazeLibrary.properties().setProperty(savedValueKey, String.valueOf(count.get()));
    }
}
