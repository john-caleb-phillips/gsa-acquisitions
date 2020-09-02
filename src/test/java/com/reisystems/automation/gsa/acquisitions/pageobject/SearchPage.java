package com.reisystems.automation.gsa.acquisitions.pageobject;

public class SearchPage {

    public String getUrl(String pageType) {
        if ("site".equals(pageType)) {
            return "https://www.acquisition.gov/search/site/";
        } else {
            return "https://www.acquisition.gov/search/advanced/";
        }
    }
}
