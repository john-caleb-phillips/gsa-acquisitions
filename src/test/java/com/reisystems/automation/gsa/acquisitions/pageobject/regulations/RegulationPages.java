package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

public class RegulationPages {

    private final RegulationMainPage regulationMainPage;
    private final TablePage tablePage;
    private final SidebarPage sidebarPage;

    public RegulationPages(RegulationMainPage regulationMainPage, TablePage tablePage,
                           SidebarPage sidebarPage) {
        this.regulationMainPage = regulationMainPage;
        this.tablePage = tablePage;
        this.sidebarPage = sidebarPage;
    }

    public RegulationMainPage mainPage() {
        return regulationMainPage;
    }

    public TablePage tablePage() {
        return tablePage;
    }

    public SidebarPage sidebarPage() {
        return sidebarPage;
    }
}
