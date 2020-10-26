package com.reisystems.automation.gsa.acquisitions.pageobject.archives;

public class ArchivePages {

    private final ArchiveSearchPage search;
    private final ArchiveDetailPage detail;

    public ArchivePages(ArchiveSearchPage search, ArchiveDetailPage detail) {
        this.search = search;
        this.detail = detail;
    }

    public ArchiveSearchPage search() {
        return search;
    }
    public ArchiveDetailPage detail() {
        return detail;
    }
}
