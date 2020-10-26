package com.reisystems.automation.gsa.acquisitions.pageobject.search;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.PageObject;

public class SiteSearchPage extends PageObject {
    public SiteSearchPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "search/site");
    }

    @Override
    public boolean areOnPage() {
        return blazeLibrary.browser().getCurrentUrl().startsWith(url);
    }
}
