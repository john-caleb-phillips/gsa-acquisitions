package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork;

public class PolicyNetworkPages {

    private final MainPage mainPage;
    private final CaacPage caacPage;
    private final CaocPage caocPage;
    private final FarcPage farcPage;
    private final IsdcPage isdcPage;

    public PolicyNetworkPages(MainPage main, CaacPage caac, CaocPage caoc, FarcPage farc, IsdcPage isdc) {
        this.mainPage = main;
        this.caacPage = caac;
        this.caocPage = caoc;
        this.farcPage = farc;
        this.isdcPage = isdc;
    }

    public MainPage main() {
        return mainPage;
    }

    public CaacPage caac() {
        return caacPage;
    }

    public CaocPage caoc() {
        return caocPage;
    }

    public FarcPage farc() {
        return farcPage;
    }

    public IsdcPage isdc() {
        return isdcPage;
    }

}
