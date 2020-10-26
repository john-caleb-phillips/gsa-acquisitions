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

    Map<String, Class<? extends PageObject>> pages = Map.ofEntries(
            // Start Header Pages
            Map.entry("home", HomePage.class),
            Map.entry("archive search", ArchiveSearchPage.class),
            Map.entry("main regulation", RegulationMainPage.class),
            Map.entry("main policy network", MainPolicyNetworkPage.class),
            Map.entry("CAAC", CaacMainPage.class),
            Map.entry("CAAC letters", CaacLettersPage.class),
            Map.entry("CAAC members", CaacMembersPage.class),
            Map.entry("CAOC", CaocMainPage.class),
            Map.entry("CAOC large agencies", CaocLargeAgenciesPage.class),
            Map.entry("CAOC small agencies", CaocSmallAgenciesPage.class),
            Map.entry("CAOC charter", CaocCharterPage.class),
            Map.entry("CAOC history", CaocHistoryPage.class),
            Map.entry("FARC", FarcMainPage.class),
            Map.entry("FARC members", FarcMembersPage.class),
            Map.entry("FARC meeting", FarcMeetingPage.class),
            Map.entry("FARC memoranda", FarcMemorandaPage.class),
            Map.entry("ISDC", IsdcMainPage.class),
            Map.entry("ISDC officials", IsdcOfficialsPage.class),
            Map.entry("ISDC debarment", IsdcDebarmentPage.class),
            Map.entry("ISDC reporting", IsdcReportingPage.class),
            Map.entry("ISDC compelling reasons", IsdcCompellingReasonsPage.class),
            Map.entry("site search", SiteSearchPage.class),
            Map.entry("regulation search", RegulationSearchPage.class),
            Map.entry("smart matrix", SmartMatrixPage.class),
            Map.entry("procurement forecasts", ProcurementForecastsPage.class),
            Map.entry("psc manual", PscManualPage.class),
            Map.entry("news", NewsPage.class),
            Map.entry("updates", UpdatePage.class),
            Map.entry("889 information", EightEightNineInformationPage.class),
            Map.entry("coronavirus information", CoronavirusInformationPage.class),
            Map.entry("accessibility aids", AccessibilityAidsPage.class),
            Map.entry("acquisition systems", AcquisitionSystemsPage.class),
            Map.entry("contact us", ContactUsPage.class),
            Map.entry("far resources", FarResourcesPage.class),
            Map.entry("privacy and security", PrivacySecurityPage.class),
            Map.entry("training", TrainingPage.class),
            Map.entry("useful links", UsefulLinksPage.class),
            Map.entry("chapter 99", Chapter99Page.class),
            Map.entry("AFARS regulation", AfarsRegulationPage.class),
            Map.entry("AFFARS regulation", AffarsRegulationPage.class),
            Map.entry("AGAR regulation", AgarRegulationPage.class),
            Map.entry("AIDAR regulation", AidarRegulationPage.class),
            Map.entry("CAR regulation", CarRegulationPage.class),
            Map.entry("DARS regulation", DarsRegulationPage.class),
            Map.entry("DEARS regulation", DearsRegulationPage.class),
            Map.entry("DFARS regulation", DfarsRegulationPage.class),
            Map.entry("DFARSPGI regulation", DfarspgiRegulationPage.class),
            Map.entry("DIARS regulation", DiarsRegulationPage.class),
            Map.entry("DLAD regulation", DladRegulationPage.class),
            Map.entry("DOLARS regulation", DolarsRegulationPage.class),
            Map.entry("DOSARS regulation", DosarsRegulationPage.class),
            Map.entry("DTAR regulation", DtarRegulationPage.class),
            Map.entry("EDAR regulation", EdarRegulationPage.class),
            Map.entry("EPAAR regulation", EpaarRegulationPage.class),
            Map.entry("FAR regulation", FarRegulationPage.class),
            Map.entry("FEHBAR regulation", FehbarRegulationPage.class),
            Map.entry("GSAM regulation", GsamRegulationPage.class),
            Map.entry("HHSAR regulation", HhsarRegulationPage.class),
            Map.entry("HSAR regulation", HsarRegulationPage.class),
            Map.entry("HUDAR regulation", HudarRegulationPage.class),
            Map.entry("IAAR regulation", IaarRegulationPage.class),
            Map.entry("JAR regulation", JarRegulationPage.class),
            Map.entry("LIFAR regulation", LifarRegulationPage.class),
            Map.entry("NFS regulation", NfsRegulationPage.class),
            Map.entry("NMCARS regulation", NmcarsRegulationPage.class),
            Map.entry("NRCAR regulation", NrcarRegulationPage.class),
            Map.entry("SOFARS regulation", SofarsRegulationPage.class),
            Map.entry("TAR regulation", TarRegulationPage.class),
            Map.entry("TRANSFARS regulation", TransfarsRegulationPage.class),
            Map.entry("VAAR regulation", VaarRegulationPage.class)
    );
}
