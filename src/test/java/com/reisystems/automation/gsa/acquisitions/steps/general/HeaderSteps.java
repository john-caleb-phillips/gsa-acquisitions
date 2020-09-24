package com.reisystems.automation.gsa.acquisitions.steps.general;

import com.reisystems.automation.gsa.acquisitions.pageobject.general.Header;
import com.reisystems.blaze.controller.BlazeLibrary;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HeaderSteps {

    Header header;
    private final BlazeLibrary blazeLibrary;

    public HeaderSteps(BlazeLibrary blazelibrary, Header header) {
        this.blazeLibrary = blazelibrary;
        this.header = header;
    }


    @When("I do a test:")
    public void testing(List<String> regulations) {
        int totalRows = 0;
        Map<String, Integer> strToInt = new HashMap<>();
        for (String regulation : regulations) {
            clickRegulationsDropdownLink(regulation);

            if (blazeLibrary.getElement(By.xpath("//table[@id='regulation-index-browse']")).isPresent()) {
                int totalForRegulation = blazeLibrary.getElements(By.xpath("//table[@id='regulation-index-browse']//tr[not(.//th)]")).size();
                totalRows += totalForRegulation;
                strToInt.put(regulation, totalForRegulation);

                blazeLibrary.assertion().assertThat(blazeLibrary.getElement(By.xpath("//table[@id='regulation-index-browse']//following-sibling::div")).getText())
                        .as("Verifying footer for regulation: %s", regulation)
                        .isEqualTo("Showing 1 to %s of %s entries".formatted(totalForRegulation, totalForRegulation));
            }
        }

        System.out.println("Grand Total: " + totalRows);
        System.out.println("Sub-Totals:");
        for (String key : strToInt.keySet()) {
            System.out.printf("%s: %s%n", key, strToInt.get(key));
        }
    }

    @When("I click on coronavirus link in the header")
    public void clickCoronavirusLink() {
        header.clickCoronavirusLink();
    }

    @When("I click on 889 information link in the header")
    public void click889InformationLink() {
        header.click889InformationLink();
    }

    @When("I click on header logo")
    public void clickHeaderLogo() {
        header.clickLogo();
    }

    @When("I click on header link {string}")
    public void clickHeaderLink(String headerLinkText) {
        header.clickTopLevelLink(headerLinkText);
    }

    @When("I click on header Regulations dropdown link {string}")
    public void clickRegulationsDropdownLink(String linkText) {
        header.clickRegulationDropdownLink(linkText);
    }

    @When("I click on header Policy Network dropdown link {string}")
    public void clickPolicyNNetworkDropdownLink(String linkText) {
        header.clickPolicyNetworkDropdownLink(linkText);
    }

    @When("^I perform header (generic|site|regulation) search for \"([^\"]*)\"$")
    public void performSearch(Header.SearchType searchType, String searchTerm) {
        header.performSiteSearch(searchType, searchTerm);
    }

}
