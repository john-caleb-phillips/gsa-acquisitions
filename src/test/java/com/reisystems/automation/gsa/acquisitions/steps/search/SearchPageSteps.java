package com.reisystems.automation.gsa.acquisitions.steps.search;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.DetailPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.search.SearchPage;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SearchPageSteps {

    BlazeLibrary blazeLibrary;
    SearchPage searchPage;
    DetailPage detailPage;

    public SearchPageSteps(BlazeLibrary blazeLibrary, SearchPage searchPage, DetailPage detailPage) {
        this.blazeLibrary = blazeLibrary;
        this.searchPage = searchPage;
        this.detailPage = detailPage;
    }

    @Given("^I am on the (site|regulation) search page$")
    public void goToSearchPage(String pageType) {
        if ("site".equals(pageType)) {
            searchPage.goToSitePage();
        } else {
            searchPage.goToRegulationPage();
        }
    }

    @When("I click the link to go to the regulation search page")
    public void navigateToRegulationSearchPage() {
        blazeLibrary.getElement(By.xpath("//p[contains(@class, 'search-snippet')]/a")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    @When("I perform search for {string}")
    public void performSearch(String searchTerm) {
        blazeLibrary.getElement(By.xpath("//form[@id='advanced-search-form']//div[contains(@class, 'form-item-keys')]//input[@id='searchkeys']")).sendKeys(searchTerm);
        blazeLibrary.getElement(By.xpath("//form[@id='advanced-search-form']//div[contains(@class, 'form-item-keys')]//input[@type='submit']")).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    @When("I expand all sidebar options")
    public void expandSidebars() {
        By expandLocator = By.xpath("//div[(contains(@class, 'block-facetapi') or contains(@class, 'block-apachesolr-search'))]//a[.='Show more']");
        while (blazeLibrary.getElement(expandLocator).isPresent()) {
            blazeLibrary.getElement(expandLocator).click();
        }
    }

    @When("I set the regulation search criteria to the following origins:")
    public void setRegulationSearchCriteria(List<String> desiredOrigins) {
        if (desiredOrigins.contains("FAR")) {
            searchPage.setFarRegulationCriteria();
            desiredOrigins.removeIf(el -> el.equals("FAR"));
        }
        if (desiredOrigins.size() != 0) {
            searchPage.setOtherRegulationsCriteria(desiredOrigins.stream().distinct().collect(Collectors.toList()));
        }
    }

    @Then("I see the site search error message saying:")
    public void checkErrorMessage(String theMessage) {
        String actualMessage = blazeLibrary.getElement(By.xpath("//div[@id='main-content-wrapper']")).getText().replace("ERROR MESSAGE\n", "");
        blazeLibrary.assertion().assertThat(actualMessage)
                .as("Checking site search error message")
                .isEqualTo(theMessage);
    }

    @Then("I see every search result contains at least one of the following terms:")
    public void verifySearchResults(List<String> searchTerms) {
        searchPage.forEachRowInTheSearchResults(result -> {
            SearchPage.SearchRow contents = result.getInfo();
            blazeLibrary.assertion().assertThat(searchTerms.stream().anyMatch(searchTerm -> contents.content.toUpperCase().contains(searchTerm.toUpperCase())))
                    .as("Search result did not contain any of '%s'.%nSearch result:%n%s%n%s%n", searchTerms, contents.title, contents.content)
                    .isTrue();
        });
    }

    @Then("I see that at least one of the following are highlighted in each search result:")
    public void verifyCorrectTermsAreHighlighted(List<String> expectedTerms) {
        searchPage.forEachRowInTheSearchResults(result -> {
            SearchPage.SearchRow theInfo = result.getInfo();
            blazeLibrary.assertion().assertThat(theInfo.highlightedTerms)
                    .as("Verifying that highlighted term is in the search results", theInfo.title)
                    .anyMatch(el -> expectedTerms.stream().anyMatch(el::equalsIgnoreCase));
        });
    }

    @Then("I see that only the following are highlighted in each search result:")
    public void verifyOnlyCorrectTermsAreHighlighted(List<String> expectedTerms) {
        searchPage.forEachRowInTheSearchResults(result -> {
            SearchPage.SearchRow theInfo = result.getInfo();
            blazeLibrary.assertion().assertThat(theInfo.highlightedTerms)
                    .as("Verifying that all highlighted terms are in the search results", theInfo.title)
                    .allMatch(el -> expectedTerms.stream().anyMatch(el::equalsIgnoreCase));
        });
    }

    @Then("I see nothing highlighted in the search result details")
    public void verifyNoHighlightingOnDetailPage() {
        searchPage.forEachRowInTheSearchResults(result -> {
            result.goToDetailPage();
            blazeLibrary.assertion().assertThat(blazeLibrary.getElements(By.xpath("//*[not(ancestor::div[@class='top-wrapper' or @id='footer-link'])]"))
                    .stream().filter(el -> {
                        String color = el.getCssValue("background-color");
                        return color.equals("yellow") || color.equals("rgba(255, 255, 0, 1)") || color.equals("rgb(255, 255, 0)");
                    })
                    .collect(Collectors.toList()))
                    .as("Verifying number of highlighted elements on the detail page")
                    .hasSize(0);
            blazeLibrary.browser().navigateBack();
        });
    }

    @Then("I see something highlighted in the search result details")
    public void verifyHighlightingOnDetailPage() {
        searchPage.forEachRowInTheSearchResults(result -> {
            result.goToDetailPage();
            List<BlazeWebElement> highlightedElements = blazeLibrary.getElements(By.xpath("//*[not(ancestor::div[@class='top-wrapper' or @id='footer-link'])][contains(@style, 'background-color: rgb(255, 255, 0)')]"));
            if (highlightedElements.size() == 0) {
                String currentUrl = blazeLibrary.browser().getCurrentUrl();
                blazeLibrary.assertion().assertThat(highlightedElements.size() != 0)
                        .as("Verifying number of highlighted elements on the detail page.%nURL was: %s", currentUrl)
                        .isTrue();
            }
            blazeLibrary.browser().navigateBack();
        });
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
                .containsExactlyElementsOf(expectedSidebarOptions.stream().filter(Predicate.not(String::isEmpty)).collect(Collectors.toList()));
    }

    @Then("I see every search result is from {string} archive")
    public void verifySearchResultsbyArchiveType(String expectedArchiveType) {
        searchPage.forEachRowInTheSearchResults(
                row -> {
                    blazeLibrary.assertion().assertThat(row.getInfo().origin).isEqualTo("ARCHIVES");
                    if (blazeLibrary.assertion().wasSuccess()) {
                        row.goToDetailPage();
                        blazeLibrary.assertion().assertThat(detailPage.getArchiveType())
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
                        blazeLibrary.assertion().assertThat(detailPage.getYear())
                                .as("Testing year").isEqualTo(expectedYear);
                        blazeLibrary.browser().navigateBack();
                    }
                });
    }

    @Then("I see the search results are sorted by title")
    public void verifySortByTitle() {
        List<String> titles = new ArrayList<>();
        searchPage.forEachRowInTheSearchResults(row -> titles.add(row.getInfo().title.toUpperCase()));
        blazeLibrary.assertion().assertThat(titles).as("Table should be ordered").isSorted();
    }

    @When("I filter by archive type {string}")
    public void filterByArchiveType(String desiredArchiveType) {
        expandSidebars();
        blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'block-facetapi') and .//h2[.='Filter by archive type:']]//li/a/text()[normalize-space(.)='%s']/..", desiredArchiveType)))
                .click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    @When("I filter by year {string}")
    public void filterByYear(String desiredYear) {
        expandSidebars();
        blazeLibrary.getElement(By.xpath(String.format("//div[contains(@class, 'block-facetapi') and .//h2[.='Filter by year:']]//li/a/text()[normalize-space(.)='%s']/..", desiredYear)))
                .click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    @When("I sort by title")
    public void sortByTitle() {
        blazeLibrary.getElement(By.xpath("//div[contains(@class, 'block-apachesolr') and .//h2[.='Sort by']]//li/a/text()[normalize-space(.)='Title']/.."))
                .click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    @When("I count the number of search results with archive type {string} as {string}")
    public void countArchiveTypes(String desiredArchiveType, String savedValueKey) {
        AtomicInteger count = new AtomicInteger(0);
        searchPage.forEachRowInTheSearchResults(row -> {
            row.goToDetailPage();
            if (desiredArchiveType.equals(detailPage.getArchiveType())) {
                count.incrementAndGet();
            }
            blazeLibrary.browser().navigateBack();
        });
        blazeLibrary.properties().setProperty(savedValueKey, String.valueOf(count.get()));
    }

    @Then("I see the origin of every regulation is one of the following:")
    public void verifyRegulationOrigins(List<String> expectedOrigins) {
        searchPage.forEachRowInTheSearchResults(el -> {
            SearchPage.SearchRow row = el.getInfo();
            blazeLibrary.assertion().assertThat(expectedOrigins)
                    .as("Verifying origin of row '%s'", row.title)
                    .contains(row.origin);
        });
    }
}
