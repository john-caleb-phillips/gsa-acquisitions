package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

public class RegulationPages {

    MainPage mainPage;
    TablePage tablePage;

    public RegulationPages(MainPage mainPage, TablePage tablePage) {
        this.mainPage = mainPage;
        this.tablePage = tablePage;
    }

    public MainPage mainPage() {
        return mainPage;
    }

    public TablePage tablePage() {
        return tablePage;
    }
}
