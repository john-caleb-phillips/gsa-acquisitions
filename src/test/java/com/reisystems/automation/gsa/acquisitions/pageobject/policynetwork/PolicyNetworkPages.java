package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork;

import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caac.CaacMainPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caoc.CaocMainPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.farc.FarcMainPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.isdc.IsdcMainPage;

public class PolicyNetworkPages {

    private final MainPolicyNetworkPage mainPolicyNetworkPage;
    private final CaacMainPage caacMainPage;
    private final CaocMainPage caocMainPage;
    private final FarcMainPage farcMainPage;
    private final IsdcMainPage isdcMainPage;

    public PolicyNetworkPages(MainPolicyNetworkPage main, CaacMainPage caac, CaocMainPage caoc, FarcMainPage farc, IsdcMainPage isdc) {
        this.mainPolicyNetworkPage = main;
        this.caacMainPage = caac;
        this.caocMainPage = caoc;
        this.farcMainPage = farc;
        this.isdcMainPage = isdc;
    }

    public MainPolicyNetworkPage main() {
        return mainPolicyNetworkPage;
    }
    public CaacMainPage caac() {
        return caacMainPage;
    }
    public CaocMainPage caoc() {
        return caocMainPage;
    }
    public FarcMainPage farc() {
        return farcMainPage;
    }
    public IsdcMainPage isdc() {
        return isdcMainPage;
    }

}
