package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

public class RegulationPages {

    RegulationMainPage regulationMainPage;
    TablePage tablePage;

    public RegulationPages(RegulationMainPage regulationMainPage, TablePage tablePage) {
        this.regulationMainPage = regulationMainPage;
        this.tablePage = tablePage;
    }

    public RegulationMainPage mainPage() {
        return regulationMainPage;
    }

    public TablePage tablePage() {
        return tablePage;
    }
}
