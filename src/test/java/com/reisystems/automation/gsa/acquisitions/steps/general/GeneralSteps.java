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
import com.reisystems.blaze.elements.PageObject;
import com.reisystems.blaze.report.LogLevel;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class GeneralSteps extends HasBlazeLibrary {
    public GeneralSteps(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
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
        try {
            PageObject thePage = pages.get(desiredPage).getConstructor(BlazeLibrary.class).newInstance(blazeLibrary);
            if ("on".equals(onOrTakenTo)) {
                thePage.goToPage();
            } else {
                blazeLibrary.assertion().assertThat(pages.get(desiredPage).getConstructor(BlazeLibrary.class).newInstance(blazeLibrary).areOnPage())
                        .as("Verifying that are on %s page", desiredPage)
                        .isTrue();
            }
        } catch (NoSuchMethodException e) {
            blazeLibrary.report().write(LogLevel.ERROR, String.format("Cannot create page object '%s' as it does not have a constructor with parameter list (BlazeLibrary)", desiredPage));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            blazeLibrary.report().write(LogLevel.ERROR, "Unable to instantiate page object '%s': " + e.getMessage());
        }
    }

    private static final Map<String, Class<? extends PageObject>> pages = new HashMap<>();

    static {
        // Start Header Pages
        pages.put("home", HomePage.class);
        pages.put("archive search", ArchiveSearchPage.class);
        pages.put("main regulation", RegulationMainPage.class);
        pages.put("main policy network", MainPolicyNetworkPage.class);
        pages.put("CAAC", CaacMainPage.class);
        pages.put("CAAC letters", CaacLettersPage.class);
        pages.put("CAAC members", CaacMembersPage.class);
        pages.put("CAOC", CaocMainPage.class);
        pages.put("CAOC large agencies", CaocLargeAgenciesPage.class);
        pages.put("CAOC small agencies", CaocSmallAgenciesPage.class);
        pages.put("CAOC charter", CaocCharterPage.class);
        pages.put("CAOC history", CaocHistoryPage.class);
        pages.put("FARC", FarcMainPage.class);
        pages.put("FARC members", FarcMembersPage.class);
        pages.put("FARC meeting", FarcMeetingPage.class);
        pages.put("FARC memoranda", FarcMemorandaPage.class);
        pages.put("ISDC", IsdcMainPage.class);
        pages.put("ISDC officials", IsdcOfficialsPage.class);
        pages.put("ISDC debarment", IsdcDebarmentPage.class);
        pages.put("ISDC reporting", IsdcReportingPage.class);
        pages.put("ISDC compelling reasons", IsdcCompellingReasonsPage.class);
        pages.put("site search", SiteSearchPage.class);
        pages.put("regulation search", RegulationSearchPage.class);
        pages.put("smart matrix", SmartMatrixPage.class);
        pages.put("procurement forecasts", ProcurementForecastsPage.class);
        pages.put("psc manual", PscManualPage.class);
        pages.put("news", NewsPage.class);
        pages.put("updates", UpdatePage.class);
        pages.put("889 information", EightEightNineInformationPage.class);
        pages.put("coronavirus information", CoronavirusInformationPage.class);
        pages.put("accessibility aids", AccessibilityAidsPage.class);
        pages.put("acquisition systems", AcquisitionSystemsPage.class);
        pages.put("contact us", ContactUsPage.class);
        pages.put("far resources", FarResourcesPage.class);
        pages.put("privacy and security", PrivacySecurityPage.class);
        pages.put("training", TrainingPage.class);
        pages.put("useful links", UsefulLinksPage.class);
        pages.put("chapter 99", Chapter99Page.class);
        pages.put("AFARS regulation", AfarsRegulationPage.class);
        pages.put("AFFARS regulation", AffarsRegulationPage.class);
        pages.put("AGAR regulation", AgarRegulationPage.class);
        pages.put("AIDAR regulation", AidarRegulationPage.class);
        pages.put("CAR regulation", CarRegulationPage.class);
        pages.put("DARS regulation", DarsRegulationPage.class);
        pages.put("DEARS regulation", DearsRegulationPage.class);
        pages.put("DFARS regulation", DfarsRegulationPage.class);
        pages.put("DFARSPGI regulation", DfarspgiRegulationPage.class);
        pages.put("DIARS regulation", DiarsRegulationPage.class);
        pages.put("DLAD regulation", DladRegulationPage.class);
        pages.put("DOLARS regulation", DolarsRegulationPage.class);
        pages.put("DOSARS regulation", DosarsRegulationPage.class);
        pages.put("DTAR regulation", DtarRegulationPage.class);
        pages.put("EDAR regulation", EdarRegulationPage.class);
        pages.put("EPAAR regulation", EpaarRegulationPage.class);
        pages.put("FAR regulation", FarRegulationPage.class);
        pages.put("FEHBAR regulation", FehbarRegulationPage.class);
        pages.put("GSAM regulation", GsamRegulationPage.class);
        pages.put("HHSAR regulation", HhsarRegulationPage.class);
        pages.put("HSAR regulation", HsarRegulationPage.class);
        pages.put("HUDAR regulation", HudarRegulationPage.class);
        pages.put("IAAR regulation", IaarRegulationPage.class);
        pages.put("JAR regulation", JarRegulationPage.class);
        pages.put("LIFAR regulation", LifarRegulationPage.class);
        pages.put("NFS regulation", NfsRegulationPage.class);
        pages.put("NMCARS regulation", NmcarsRegulationPage.class);
        pages.put("NRCAR regulation", NrcarRegulationPage.class);
        pages.put("SOFARS regulation", SofarsRegulationPage.class);
        pages.put("TAR regulation", TarRegulationPage.class);
        pages.put("TRANSFARS regulation", TransfarsRegulationPage.class);
        pages.put("VAAR regulation", VaarRegulationPage.class);
    }
}
