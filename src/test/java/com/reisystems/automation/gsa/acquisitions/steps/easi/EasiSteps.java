package com.reisystems.automation.gsa.acquisitions.steps.easi;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import io.cucumber.java.en.When;

public class EasiSteps extends HasBlazeLibrary {

    public EasiSteps(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    private static int test1 = 1;
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
