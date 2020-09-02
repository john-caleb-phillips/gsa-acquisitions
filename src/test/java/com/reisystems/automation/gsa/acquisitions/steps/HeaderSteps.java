package com.reisystems.automation.gsa.acquisitions.steps;

import com.reisystems.automation.gsa.acquisitions.pageobject.Header;
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
        WebElement regulationLink = blazeLibrary.getElement(header.linkLocator("Regulations"));
        blazeLibrary.mouseAndKeyboard()
                .moveByOffset(
                        regulationLink.getLocation().getX() + (regulationLink.getSize().getWidth() / 2),
                        regulationLink.getLocation().getY() + (regulationLink.getSize().getHeight() / 2)
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

    @When("^I perform header (generic|site|regulation) search for \"([^\"]*)\"$")
    public void performSearch(String searchType, String searchTerm) {
        WebElement searchLink = blazeLibrary.getElement(header.linkLocator("Search"));
        blazeLibrary.mouseAndKeyboard()
                .moveByOffset(
                        searchLink.getLocation().getX() + (searchLink.getSize().getWidth() / 2),
                        searchLink.getLocation().getY() + (searchLink.getSize().getHeight() / 2)
                ).build().perform();

        blazeLibrary.getElement(header.searchTextBox()).sendKeys(searchTerm);

        // check search type
        if ("site".equals(searchType)) {
            blazeLibrary.getElement(header.searchSiteToggle()).click();
        } else if ("regulation".equals(searchType)) {
            blazeLibrary.getElement(header.searchRegulationsToggle()).click();
        }

        // click search button
        blazeLibrary.getElement(header.searchSubmitButton()).click(blazeLibrary.defaults().REFRESH_PAGE);
    }

}
