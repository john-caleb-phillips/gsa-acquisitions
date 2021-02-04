package com.reisystems.automation.gsa.acquisitions.steps.easi;

import com.reisystems.automation.gsa.acquisitions.pageobject.regulations.RegulationPages;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import io.cucumber.java.en.When;

public class EasiSteps extends HasBlazeLibrary {

    RegulationPages regulationPages;

    public EasiSteps(BlazeLibrary blazeLibrary,
                     RegulationPages regulationPages) {
        super(blazeLibrary);
        this.regulationPages = regulationPages;
    }

    private static int test1 = 1;

    @When("I run a test")
    public void runATest() {
        System.out.printf("    Parts: %s%n", regulationPages.sidebarPage().parts());
        System.out.printf(" SubParts: %s%n", regulationPages.sidebarPage().subParts());
        System.out.printf("Sectionss: %s%n", regulationPages.sidebarPage().sections());
    }

    @When("I run a test 1")
    public void runATest1() {
        test1--;
        blazeLibrary.assertion().assertThat(test1).isLessThanOrEqualTo(0);
    }

    private static int test2 = 2;

    @When("I run a test 2")
    public void runATest2() {
        test2--;
        blazeLibrary.assertion().assertThat(test2).isLessThanOrEqualTo(0);
    }

    private static int test3 = 3;

    @When("I run a test 3")
    public void runATest3() {
        test3--;
        blazeLibrary.assertion().assertThat(test3).isLessThanOrEqualTo(0);
    }
}
