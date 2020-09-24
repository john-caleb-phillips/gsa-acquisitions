package com.reisystems.automation.gsa.acquisitions.pageobject.archives;

public class ArchivesPages {

    private final SearchPage search;
    private final DetailPage detail;

    public ArchivesPages(SearchPage search, DetailPage detail) {
        this.search = search;
        this.detail = detail;
    }

    public SearchPage search() {
        return search;
    }
    public DetailPage detail() {
        return detail;
    }
}
