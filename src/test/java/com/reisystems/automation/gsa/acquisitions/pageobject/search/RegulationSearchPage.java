package com.reisystems.automation.gsa.acquisitions.pageobject.search;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.PageObject;

public class RegulationSearchPage extends PageObject {
    public RegulationSearchPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "search/advanced");
    }

    @Override
    public boolean areOnPage() {
        return blazeLibrary.browser().getCurrentUrl().startsWith(url);
    }
}
