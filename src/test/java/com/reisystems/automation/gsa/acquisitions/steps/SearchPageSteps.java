package com.reisystems.automation.gsa.acquisitions.steps;

import com.reisystems.automation.gsa.acquisitions.pageobject.SearchRegulationPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.SearchSitePage;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.docstring.DocString;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.util.List;

public class SearchPageSteps {

    BlazeLibrary blazeLibrary;
    SearchRegulationPage searchRegulationPage;
    SearchSitePage searchSitePage;

    public SearchPageSteps(BlazeLibrary blazeLibrary, SearchRegulationPage searchRegulationPage, SearchSitePage searchSitePage) {
        this.blazeLibrary = blazeLibrary;
        this.searchRegulationPage = searchRegulationPage;
        this.searchSitePage = searchSitePage;
    }

    @Given("^I am on the (site|regulation) search page$")
    public void goToSearchPage(String pageType) {
        if ("site".equals(pageType)) {
            searchSitePage.goToPage();
        } else {
            searchRegulationPage.goToPage();
        }
    }

    @When("I perform site search for {string}")
    public void performSearch(String searchTerm) {
        blazeLibrary.getElement(By.xpath("//input[@id='searchkeys']")).sendKeys(searchTerm);
        blazeLibrary.getElement(By.xpath("//input[@id='searchkeys']//following-sibling::input[@value='Submit']")).click(blazeLibrary.defaults().REFRESH_PAGE);
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

    }
}
