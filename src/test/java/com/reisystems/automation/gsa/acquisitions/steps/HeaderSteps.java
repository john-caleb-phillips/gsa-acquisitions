package com.reisystems.automation.gsa.acquisitions.steps;

import com.reisystems.automation.gsa.acquisitions.pageobject.Header;
import com.reisystems.blaze.controller.BlazeDriver;
import com.reisystems.blaze.interfaces.ClickResult;
import io.cucumber.java.en.When;


public class HeaderSteps {

    Header header;

    public HeaderSteps(Header header) {
        this.header = header;
    }

    @When("I click on header link {string}")
    public void clickHeaderLink(String headerLinkText) {
        BlazeDriver.getElement(header.link(headerLinkText)).click(ClickResult.REFRESH_PAGE);
    }

    @When("I run a header test")
    public void runatet() {

    }


}
