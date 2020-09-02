package com.reisystems.automation.gsa.acquisitions.pageobject;

import com.reisystems.blaze.controller.BlazeLibrary;

public class SearchSitePage {

    private BlazeLibrary blazeLibrary;

    public SearchSitePage(BlazeLibrary blazeLibrary) {
        this.blazeLibrary = blazeLibrary;
    }

    public void goToPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/search/site/");
    }

}
