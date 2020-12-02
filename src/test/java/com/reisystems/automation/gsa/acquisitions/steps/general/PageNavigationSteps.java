package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.archives.ArchiveSearchPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.footer.*;
import com.reisystems.automation.gsa.acquisitions.pageobject.general.*;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.MainPolicyNetworkPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caac.CaacLettersPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caac.CaacMainPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caac.CaacMembersPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caoc.*;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.farc.FarcMainPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.farc.FarcMeetingPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.farc.FarcMembersPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.farc.FarcMemorandaPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.isdc.*;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.RegulationMainPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.regulation.*;
import com.reisystems.automation.gsa.acquisitions.pageobject.search.RegulationSearchPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.search.SiteSearchPage;
import com.reisystems.automation.gsa.acquisitions.pageobject.smartmartix.SmartMatrixPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageRegistry;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PageNavigationSteps extends HasBlazeLibrary {
    PageRegistry pageRegistry;

    public PageNavigationSteps(BlazeLibrary blazeLibrary, PageRegistry pageRegistry) {
        super(blazeLibrary);
        this.pageRegistry = pageRegistry;
    }

    @When("^I am (on|taken to) the (home) page$")
    @When("^I am (on|taken to) the (archive search) page$")
    @When("^I am (on|taken to) the (main regulation) page$")
    @Given("^I am (on|taken to) the (main policy network) page$")
    @Given("^I am (on|taken to) the (CAAC) page$")
    @Given("^I am (on|taken to) the (CAAC letters) page$")
    @Given("^I am (on|taken to) the (CAAC members) page$")
    @Given("^I am (on|taken to) the (CAOC) page$")
    @Given("^I am (on|taken to) the (CAOC large agencies) page$")
    @Given("^I am (on|taken to) the (CAOC small agencies) page$")
    @Given("^I am (on|taken to) the (CAOC charter) page$")
    @Given("^I am (on|taken to) the (CAOC history) page$")
    @Given("^I am (on|taken to) the (FARC) page$")
    @Given("^I am (on|taken to) the (FARC members) page$")
    @Given("^I am (on|taken to) the (FARC meeting) page$")
    @Given("^I am (on|taken to) the (FARC memoranda) page$")
    @Given("^I am (on|taken to) the (ISDC) page$")
    @Given("^I am (on|taken to) the (ISDC officials) page$")
    @Given("^I am (on|taken to) the (ISDC debarment) page$")
    @Given("^I am (on|taken to) the (ISDC reporting) page$")
    @Given("^I am (on|taken to) the (ISDC compelling reasons) page$")
    @Given("^I am (on|taken to) the (site search) page$")
    @Given("^I am (on|taken to) the (regulation search) page$")
    @Given("^I am (on|taken to) the (smart matrix) page$")
    @Given("^I am (on|taken to) the (procurement forecasts) page$")
    @Given("^I am (on|taken to) the (psc manual) page$")
    @Given("^I am (on|taken to) the (far regulation) page$")
    @Given("^I am (on|taken to) the (news) page$")
    @Given("^I am (on|taken to) the (updates) page$")
    @Given("^I am (on|taken to) the (889 information) page$")
    @Given("^I am (on|taken to) the (coronavirus information) page$")
    @Given("^I am (on|taken to) the (accessibility aids) page$")
    @Given("^I am (on|taken to) the (acquisition systems) page$")
    @Given("^I am (on|taken to) the (contact us) page$")
    @Given("^I am (on|taken to) the (far resources) page$")
    @Given("^I am (on|taken to) the (privacy and security) page$")
    @Given("^I am (on|taken to) the (training) page$")
    @Given("^I am (on|taken to) the (useful links) page$")
    @Given("^I am (on|taken to) the (chapter 99) page$")
    @Given("^I am (on|taken to) the (AFFARS regulation) page$")
    @Given("^I am (on|taken to) the (AFARS regulation) page$")
    @Given("^I am (on|taken to) the (AGAR regulation) page$")
    @Given("^I am (on|taken to) the (AIDAR regulation) page$")
    @Given("^I am (on|taken to) the (CAR regulation) page$")
    @Given("^I am (on|taken to) the (DARS regulation) page$")
    @Given("^I am (on|taken to) the (DEARS regulation) page$")
    @Given("^I am (on|taken to) the (DFARS regulation) page$")
    @Given("^I am (on|taken to) the (DFARSPGI regulation) page$")
    @Given("^I am (on|taken to) the (DIARS regulation) page$")
    @Given("^I am (on|taken to) the (DLAD regulation) page$")
    @Given("^I am (on|taken to) the (DOLARS regulation) page$")
    @Given("^I am (on|taken to) the (DOSARS regulation) page$")
    @Given("^I am (on|taken to) the (DTAR regulation) page$")
    @Given("^I am (on|taken to) the (EDAR regulation) page$")
    @Given("^I am (on|taken to) the (EPAAR regulation) page$")
    @Given("^I am (on|taken to) the (FAR regulation) page$")
    @Given("^I am (on|taken to) the (FEHBAR regulation) page$")
    @Given("^I am (on|taken to) the (GSAM regulation) page$")
    @Given("^I am (on|taken to) the (HHSAR regulation) page$")
    @Given("^I am (on|taken to) the (HSAR regulation) page$")
    @Given("^I am (on|taken to) the (HUDAR regulation) page$")
    @Given("^I am (on|taken to) the (IAAR regulation) page$")
    @Given("^I am (on|taken to) the (JAR regulation) page$")
    @Given("^I am (on|taken to) the (LIFAR regulation) page$")
    @Given("^I am (on|taken to) the (NFS regulation) page$")
    @Given("^I am (on|taken to) the (NMCARS regulation) page$")
    @Given("^I am (on|taken to) the (NRCAR regulation) page$")
    @Given("^I am (on|taken to) the (SOFARS regulation) page$")
    @Given("^I am (on|taken to) the (TAR regulation) page$")
    @Given("^I am (on|taken to) the (TRANSFARS regulation) page$")
    @Given("^I am (on|taken to) the (VAAR regulation) page$")
    public void goToPage(String onOrTakenTo, String desiredPage) {
        if ("on".equals(onOrTakenTo)) {
            pageRegistry.getPage(desiredPage).goToPage();
        } else {
            if (!pageRegistry.getPage(desiredPage).areOnPage()) {
                blazeLibrary.assertion().fail("Should have been on %s page, but were not.", desiredPage);
            }
        }
    }

    static {
        // Start Header Pages
        PageRegistry.registerClass("home", HomePage.class);
        PageRegistry.registerClass("archive search", ArchiveSearchPage.class);
        PageRegistry.registerClass("main regulation", RegulationMainPage.class);
        PageRegistry.registerClass("main policy network", MainPolicyNetworkPage.class);
        PageRegistry.registerClass("CAAC", CaacMainPage.class);
        PageRegistry.registerClass("CAAC letters", CaacLettersPage.class);
        PageRegistry.registerClass("CAAC members", CaacMembersPage.class);
        PageRegistry.registerClass("CAOC", CaocMainPage.class);
        PageRegistry.registerClass("CAOC large agencies", CaocLargeAgenciesPage.class);
        PageRegistry.registerClass("CAOC small agencies", CaocSmallAgenciesPage.class);
        PageRegistry.registerClass("CAOC charter", CaocCharterPage.class);
        PageRegistry.registerClass("CAOC history", CaocHistoryPage.class);
        PageRegistry.registerClass("FARC", FarcMainPage.class);
        PageRegistry.registerClass("FARC members", FarcMembersPage.class);
        PageRegistry.registerClass("FARC meeting", FarcMeetingPage.class);
        PageRegistry.registerClass("FARC memoranda", FarcMemorandaPage.class);
        PageRegistry.registerClass("ISDC", IsdcMainPage.class);
        PageRegistry.registerClass("ISDC officials", IsdcOfficialsPage.class);
        PageRegistry.registerClass("ISDC debarment", IsdcDebarmentPage.class);
        PageRegistry.registerClass("ISDC reporting", IsdcReportingPage.class);
        PageRegistry.registerClass("ISDC compelling reasons", IsdcCompellingReasonsPage.class);
        PageRegistry.registerClass("site search", SiteSearchPage.class);
        PageRegistry.registerClass("regulation search", RegulationSearchPage.class);
        PageRegistry.registerClass("smart matrix", SmartMatrixPage.class);
        PageRegistry.registerClass("procurement forecasts", ProcurementForecastsPage.class);
        PageRegistry.registerClass("psc manual", PscManualPage.class);
        PageRegistry.registerClass("news", NewsPage.class);
        PageRegistry.registerClass("updates", UpdatePage.class);
        PageRegistry.registerClass("889 information", Section889Page.class);
        PageRegistry.registerClass("coronavirus information", CoronavirusPage.class);
        PageRegistry.registerClass("accessibility aids", AccessibilityAidsPage.class);
        PageRegistry.registerClass("acquisition systems", AcquisitionSystemsPage.class);
        PageRegistry.registerClass("contact us", ContactUsPage.class);
        PageRegistry.registerClass("far resources", FarResourcesPage.class);
        PageRegistry.registerClass("privacy and security", PrivacySecurityPage.class);
        PageRegistry.registerClass("training", TrainingPage.class);
        PageRegistry.registerClass("useful links", UsefulLinksPage.class);
        PageRegistry.registerClass("chapter 99", Chapter99Page.class);
        PageRegistry.registerClass("AFARS regulation", AfarsRegulationPage.class);
        PageRegistry.registerClass("AFFARS regulation", AffarsRegulationPage.class);
        PageRegistry.registerClass("AGAR regulation", AgarRegulationPage.class);
        PageRegistry.registerClass("AIDAR regulation", AidarRegulationPage.class);
        PageRegistry.registerClass("CAR regulation", CarRegulationPage.class);
        PageRegistry.registerClass("DARS regulation", DarsRegulationPage.class);
        PageRegistry.registerClass("DEARS regulation", DearsRegulationPage.class);
        PageRegistry.registerClass("DFARS regulation", DfarsRegulationPage.class);
        PageRegistry.registerClass("DFARSPGI regulation", DfarspgiRegulationPage.class);
        PageRegistry.registerClass("DIAR regulation", DiarsRegulationPage.class);
        PageRegistry.registerClass("DIARS regulation", DiarsRegulationPage.class);
        PageRegistry.registerClass("DLAD regulation", DladRegulationPage.class);
        PageRegistry.registerClass("DOLAR regulation", DolarsRegulationPage.class);
        PageRegistry.registerClass("DOLARS regulation", DolarsRegulationPage.class);
        PageRegistry.registerClass("DOSAR regulation", DosarsRegulationPage.class);
        PageRegistry.registerClass("DOSARS regulation", DosarsRegulationPage.class);
        PageRegistry.registerClass("DTAR regulation", DtarRegulationPage.class);
        PageRegistry.registerClass("EDAR regulation", EdarRegulationPage.class);
        PageRegistry.registerClass("EPAAR regulation", EpaarRegulationPage.class);
        PageRegistry.registerClass("FAR regulation", FarRegulationPage.class);
        PageRegistry.registerClass("FEHBAR regulation", FehbarRegulationPage.class);
        PageRegistry.registerClass("GSAM regulation", GsamRegulationPage.class);
        PageRegistry.registerClass("HHSAR regulation", HhsarRegulationPage.class);
        PageRegistry.registerClass("HSAR regulation", HsarRegulationPage.class);
        PageRegistry.registerClass("HUDAR regulation", HudarRegulationPage.class);
        PageRegistry.registerClass("IAAR regulation", IaarRegulationPage.class);
        PageRegistry.registerClass("JAR regulation", JarRegulationPage.class);
        PageRegistry.registerClass("LIFAR regulation", LifarRegulationPage.class);
        PageRegistry.registerClass("NFS regulation", NfsRegulationPage.class);
        PageRegistry.registerClass("NMCARS regulation", NmcarsRegulationPage.class);
        PageRegistry.registerClass("NRCAR regulation", NrcarRegulationPage.class);
        PageRegistry.registerClass("SOFARS regulation", SofarsRegulationPage.class);
        PageRegistry.registerClass("TAR regulation", TarRegulationPage.class);
        PageRegistry.registerClass("TRANSFARS regulation", TransfarsRegulationPage.class);
        PageRegistry.registerClass("VAAR regulation", VaarRegulationPage.class);
    }
}
