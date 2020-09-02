package com.reisystems.automation.gsa.acquisitions.steps;

import com.reisystems.automation.gsa.acquisitions.pageobject.SearchPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.Given;

public class SearchPageSteps {

    BlazeLibrary blazeLibrary;
    SearchPage searchPage;

    public SearchPageSteps(BlazeLibrary blazeLibrary, SearchPage searchPage) {
        this.blazeLibrary = blazeLibrary;
        this.searchPage = searchPage;
    }

    @Given("^I am on the (site|regulation) search page$")
    public void goToSearchPage(String pageType) {
        blazeLibrary.browser().navigateToUrl(searchPage.getUrl(pageType));
    }
}
