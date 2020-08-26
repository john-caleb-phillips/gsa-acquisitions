package com.reisystems.automation.gsa.acquisitions.steps;

import com.reisystems.automation.gsa.acquisitions.pageobject.Header;
import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;


public class HeaderSteps {

    Header header;
    private final BlazeLibrary blazeLibrary;

    public HeaderSteps(BlazeLibrary blazelibrary, Header header) {
        this.blazeLibrary = blazelibrary;
        this.header = header;
    }

    @When("I click on coronavirus link in the header")
    public void clickCoronavirusLink() {
        blazeLibrary.getElement(header.coronavirusLinkLocator()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I click on 889 information link in the header")
    public void click889InformationLink() {
        blazeLibrary.getElement(header.information889Locator()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I click on header logo")
    public void clickHeaderLogo() {
        blazeLibrary.getElement(header.logoLocator()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I click on header link {string}")
    public void clickHeaderLink(String headerLinkText) {
        blazeLibrary.getElement(header.linkLocator(headerLinkText)).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

    @When("I click on header Regulations dropdown link {string}")
    public void clickRegulationsDropdownLink(String linkText) {
        WebElement policyLink = blazeLibrary.getElement(header.linkLocator("Regulations"));
        blazeLibrary.mouseAndKeyboard()
                .moveByOffset(
                        policyLink.getLocation().getX() + (policyLink.getSize().getWidth() / 2),
                        policyLink.getLocation().getY() + (policyLink.getSize().getHeight() / 2)
                ).build().perform();
        blazeLibrary.getElement(header.regulationsDropdownLink(linkText)).click();
    }

    @When("I click on header Policy Network dropdown link {string}")
    public void clickPolicyNNetworkDropdownLink(String linkText) {
        WebElement policyLink = blazeLibrary.getElement(header.linkLocator("Policy Network"));
        blazeLibrary.mouseAndKeyboard()
                .moveByOffset(
                        policyLink.getLocation().getX() + (policyLink.getSize().getWidth() / 2),
                        policyLink.getLocation().getY() + (policyLink.getSize().getHeight() / 2)
                ).build().perform();
        blazeLibrary.getElement(header.policyNetworkDropdownLink(linkText)).click();
    }

}
