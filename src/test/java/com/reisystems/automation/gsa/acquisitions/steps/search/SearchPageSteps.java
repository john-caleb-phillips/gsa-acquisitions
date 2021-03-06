package com.reisystems.automation.gsa.acquisitions.steps.search;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchiveDetailPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.search.SearchPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SearchPageSteps {

    private final BlazeLibrary blazeLibrary;
    private final SearchPage searchPage;
    private final ArchiveDetailPage archiveDetailPage;

    public SearchPageSteps(BlazeLibrary blazeLibrary, SearchPage searchPage, ArchiveDetailPage archiveDetailPage) {
        this.blazeLibrary = blazeLibrary;
        this.searchPage = searchPage;
        this.archiveDetailPage = archiveDetailPage;
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
            try {
                blazeLibrary.getElement(expandLocator).click();
            } catch (ElementClickInterceptedException ignored) {}
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
                .as("Site search error message was not as expected")
                .isEqualTo(theMessage);
    }

    @Then("I see every search result contains at least one of the following terms:")
    public void verifySearchResults(List<String> searchTerms) {
        searchPage.forEachRowInTheSearchResults(result -> {
            SearchPage.SearchRow contents = result.getInfo();
            blazeLibrary.assertion().assertThat(searchTerms.stream().anyMatch(searchTerm -> contents.content.toUpperCase().contains(searchTerm.toUpperCase())))
                    .withFailMessage("Search result did not contain any of '%s'.%nSearch result:%n%s%n%s%n", searchTerms, contents.title, contents.content)
                    .isTrue();
        });
    }

    @Then("I see that at least one of the following are highlighted in each search result:")
    public void verifyCorrectTermsAreHighlighted(List<String> expectedTerms) {
        searchPage.forEachRowInTheSearchResults(result -> {
            SearchPage.SearchRow theInfo = result.getInfo();
            blazeLibrary.assertion().assertThat(theInfo.highlightedTerms)
                    .withFailMessage("Search term was not highlighted in the search result with title '%s'", theInfo.title)
                    .anyMatch(el -> expectedTerms.stream().anyMatch(el::equalsIgnoreCase));
        });
    }

    @Then("I see that only the following are highlighted in each search result:")
    public void verifyOnlyCorrectTermsAreHighlighted(List<String> expectedTerms) {
        searchPage.forEachRowInTheSearchResults(result -> {
            SearchPage.SearchRow theInfo = result.getInfo();
            blazeLibrary.assertion().assertThat(theInfo.highlightedTerms)
                    .withFailMessage("An unexpected term was highlighted in the search result with title '%s'", theInfo.title)
                    .allMatch(el -> expectedTerms.stream().anyMatch(el::equalsIgnoreCase));
        });
    }

    @Then("I see nothing highlighted in the search result details")
    public void verifyNoHighlightingOnDetailPage() {
        searchPage.forEachRowInTheSearchResults(result -> {
            String title = result.getInfo().title;
            result.goToDetailPage();
            blazeLibrary.assertion().assertThat(blazeLibrary.getElements(By.xpath("//*[not(ancestor::div[@class='top-wrapper' or @id='footer-link'])]"))
                    .stream().filter(el -> {
                        String color = el.getCssValue("background-color");
                        return color.equals("yellow") || color.equals("rgba(255, 255, 0, 1)") || color.equals("rgb(255, 255, 0)");
                    })
                    .collect(Collectors.toList()))
                    .withFailMessage("Search result was incorrectly highlighted in the detail page for search result with title '%s'%nURL was: %s", title, blazeLibrary.browser().getCurrentUrl())
                    .hasSize(0);
            blazeLibrary.browser().navigateBack();
        });
    }

    @Then("I see something highlighted in the search result details")
    public void verifyHighlightingOnDetailPage() {
        searchPage.forEachRowInTheSearchResults(result -> {
            String title = result.getInfo().title;
            result.goToDetailPage();
            List<BlazeWebElement> highlightedElements = blazeLibrary.getElements(By.xpath("//*[not(ancestor::div[@class='top-wrapper' or @id='footer-link'])][contains(@style, 'background-color: rgb(255, 255, 0)')]"));
            blazeLibrary.assertion().assertThat(highlightedElements.size() != 0)
                    .withFailMessage("Nothing was highlighted in the detail page of the search result with title '%s'%nURL was: %s", title, blazeLibrary.browser().getCurrentUrl())
                    .isTrue();
            blazeLibrary.browser().navigateBack();
        });
    }

    @Then("I see the following filter headers:")
    public void checkSidebarHeaders(List<String> expectedSidebarHeaders) {
        blazeLibrary.assertion().assertThat(searchPage.getFilterHeaders())
                .as("Search sidebar filter headers were not as expected")
                .containsExactlyInAnyOrderElementsOf(expectedSidebarHeaders);
    }

    @Then("I see the following options under filter {string}:")
    public void checkSidebarOptions(String sidebarHeader, List<String> expectedSidebarOptions) {
        List<String> actualSideBarOptions = searchPage.getFilterOptions(sidebarHeader).stream().map(option -> option.text).collect(Collectors.toList());
        blazeLibrary.assertion().assertThat(actualSideBarOptions)
                .as("Search sidebar filter options under header '%s' were not as expected", sidebarHeader)
                .containsExactlyInAnyOrderElementsOf(expectedSidebarOptions.stream()
                        .filter(el -> el != null && !el.isEmpty()).collect(Collectors.toList())
                );
    }

    @Then("I see every search result is from {string} archive")
    public void verifySearchResultsByArchiveType(String expectedArchiveType) {
        searchPage.forEachRowInTheSearchResults(
                row -> {
                    String title = row.getInfo().title;
                    blazeLibrary.assertion().assertThat(row.getInfo().origin)
                            .withFailMessage("Search result with title '%s' did not have 'ARCHIVES' as its origin", title)
                            .isEqualTo("ARCHIVES");
                    if (blazeLibrary.assertion().wasSuccess()) {
                        row.goToDetailPage();
                        blazeLibrary.assertion().assertThat(archiveDetailPage.getArchiveType())
                                .withFailMessage("Search result with title '%s' did not link to the expected Archive Type")
                                .isEqualTo(expectedArchiveType);
                        blazeLibrary.browser().navigateBack();
                    }
                });
    }

    @Then("I see every search result is from year {string}")
    public void verifySearchResultsByYear(String expectedYear) {
        searchPage.forEachRowInTheSearchResults(
                row -> {
                    String title = row.getInfo().title;
                    blazeLibrary.assertion().assertThat(row.getInfo().origin)
                            .withFailMessage("Search result with title '%s' did not have 'ARCHIVES' as its origin", title)
                            .isEqualTo("ARCHIVES");
                    if (blazeLibrary.assertion().wasSuccess()) {
                        row.goToDetailPage();
                        blazeLibrary.assertion().assertThat(archiveDetailPage.getYear())
                                .as("Search result with title '%s' did not link to an archive with the expected year")
                                .isEqualTo(expectedYear);
                        blazeLibrary.browser().navigateBack();
                    }
                });
    }

    @Then("I see the search results are sorted by title")
    public void verifySortByTitle() {
        List<String> titles = new ArrayList<>();
        searchPage.forEachRowInTheSearchResults(row -> titles.add(row.getInfo().title.toUpperCase()));
        blazeLibrary.assertion().assertThat(titles)
                .as("Search table was not correctly sorted by title")
                .isSorted();
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
            if (desiredArchiveType.equals(archiveDetailPage.getArchiveType())) {
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
                    .as("Origin search result with title '%s' was not as expected", row.title)
                    .contains(row.origin);
        });
    }

    @Then("I see the search term is {string}")
    public void verifySearchPageTerm(String expectedSearchTerm) {
        blazeLibrary.assertion().assertThat(searchPage.getSearchTerm())
                .as("The search page search term was not as expected")
                .isEqualTo(expectedSearchTerm);
    }
}
