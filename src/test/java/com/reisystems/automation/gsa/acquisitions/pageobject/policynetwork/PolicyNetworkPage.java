package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.PageObject;
import com.reisystems.blaze.controller.BlazeLibrary;

public class PolicyNetworkPage extends PageObject {

    private final MainPage mainPage;
    private final CaacPage caacPage;
    private final CaocPage caocPage;
    private final FarcPage farcPage;
    private final IsdcPage isdcPage;

    public PolicyNetworkPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
        this.mainPage = new MainPage(blazeLibrary);
        this.caacPage = new CaacPage(blazeLibrary);
        this.caocPage = new CaocPage(blazeLibrary);
        this.farcPage = new FarcPage(blazeLibrary);
        this.isdcPage = new IsdcPage(blazeLibrary);
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
