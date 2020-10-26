package com.reisystems.automation.gsa.acquisitions.steps.smartmatrix;

import com.reisystems.automation.gsa.acquisitions.pageobject.smartmartix.SmartMatrixPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SmartMatrixPageSteps extends HasBlazeLibrary {
    SmartMatrixPage smartMatrixPage;

    public SmartMatrixPageSteps(BlazeLibrary blazeLibrary, SmartMatrixPage smartMatrixPage) {
        super(blazeLibrary);
        this.smartMatrixPage = smartMatrixPage;
    }

    @When("^I (expand|collapse) the smart matrix legend$")
    public void expandOrCollapseSmartMatrixLegend(String expandOrCollapse) {
        if ("expand".equals(expandOrCollapse)) {
            smartMatrixPage.expandLegend();
        } else {
            smartMatrixPage.collapseLegend();
        }
    }

    @When("I click on the video link")
    public void clickVideoLink() {
        smartMatrixPage.clickVideoLink();
    }

    @When("I click on the Copy button")
    public void clickCopyButton() {
        smartMatrixPage.clickCopyButton();
    }

    @When("I click on the CSV button")
    public void clickCsvButton() {
        smartMatrixPage.clickCsvButton();
    }

    @When("I click on the PDF button")
    public void clickPdfButton() {
        smartMatrixPage.clickPdfButton();
    }

    @When("I click on the Print button")
    public void clickPrintButton() {
        smartMatrixPage.clickPrintButton();
    }

    @When("I run a test")
    public void testmethod() {
        System.out.println("Title: " + blazeLibrary.browser().getPageTitle());
        System.out.println("Source: " + blazeLibrary.browser().getPageSource());
    }

    @When("^I (check|uncheck) the box to show the complete matrix$")
    public void checkOrUncheckShowCompleteMatrix(String checkOrUncheck) {
        smartMatrixPage.selectShowCompleteMatrix("check".equals(checkOrUncheck));
    }

    private boolean shouldBeChecked;
    @When("^I (check|uncheck) any of the contract checkboxes$")
    public void stubMethod(String checkOrUncheck) {
        shouldBeChecked = "check".equals(checkOrUncheck);
    }

    @When("I choose any of the contract dropdown options")
    public void stubMethod() {
    }

    @Then("^the corresponding contract dropdown becomes (active|inactive)$")
    public void verifyContractDropdownActivationStatus(String activeOrInactive) {
        for (String contractCheckbox : smartMatrixPage.getContracts()) {
            SmartMatrixPage.Contract contract = SmartMatrixPage.Contract.valueOf(contractCheckbox);
            smartMatrixPage.selectContract(contract, shouldBeChecked);
            blazeLibrary.assertion().assertThat(smartMatrixPage.contractDropdownIsActive(contract))
                    .as("Verifying if the contract dropdown for %s is active", contract)
                    .isEqualTo("active".equals(activeOrInactive));
        }
    }

    @Then("applying the filters results in the corresponding column showing only the selected option")
    public void verifyContractDropdownFiltering() {
        for (String contractCheckbox : smartMatrixPage.getContracts()) {
            smartMatrixPage.selectContract(SmartMatrixPage.Contract.valueOf(contractCheckbox), true);
        }
        for (String contractCheckbox : smartMatrixPage.getContracts()) {
            SmartMatrixPage.Contract contract = SmartMatrixPage.Contract.valueOf(contractCheckbox);
            smartMatrixPage.selectContractDropdownValue(contract, "");
            smartMatrixPage.applyFilters();
            blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(contract.name()))
                    .as("Verifying filtering by '' in the %s contract dropdown", contract)
                    .containsOnly("");
            smartMatrixPage.selectContractDropdownValue(contract, "R");
            smartMatrixPage.applyFilters();
            blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(contract.name()))
                    .as("Verifying filtering by 'R' in the %s contract dropdown", contract)
                    .containsOnly("R");
            smartMatrixPage.selectContractDropdownValue(contract, "A");
            smartMatrixPage.applyFilters();
            blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(contract.name()))
                    .as("Verifying filtering by 'A' in the %s contract dropdown", contract)
                    .containsOnly("A");
            smartMatrixPage.selectContractDropdownValue(contract, "O");
            smartMatrixPage.applyFilters();
            blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(contract.name()))
                    .as("Verifying filtering by 'O' in the %s contract dropdown", contract)
                    .containsOnly("O");
            smartMatrixPage.selectContractDropdownValue(contract, "- Any -");
        }
    }

    @Then("^applying the filters (adds) the contract column to the table$")
    @Then("^applying the filters (removes) the contract column from the table$")
    public void verifyTableContractColumns(String addsOrRemoves) {
        for (String contractCheckbox : smartMatrixPage.getContracts()) {
            SmartMatrixPage.Contract contract = SmartMatrixPage.Contract.valueOf(contractCheckbox);
            smartMatrixPage.selectContract(contract, shouldBeChecked);
            smartMatrixPage.applyFilters();
            if ("adds".equals(addsOrRemoves)) {
                blazeLibrary.assertion().assertThat(smartMatrixPage.getTableHeaders())
                        .as("Verifying the table headers contain '%s'", contract)
                        .contains(contractCheckbox);
            } else {
                blazeLibrary.assertion().assertThat(smartMatrixPage.getTableHeaders())
                        .as("Verifying the table headers do not contain '%s'", contract)
                        .doesNotContain(contractCheckbox);
            }
        }
    }

    @When("^I (check|uncheck) the IBR checkbox$")
    public void checkOrUncheckIbrCheckbox(String checkOrUnchecked) {
        smartMatrixPage.selectIBR("check".equals(checkOrUnchecked));
    }

    @When("^I (check|uncheck) the UCF checkbox$")
    public void checkOrUncheckUcfCheckbox(String checkOrUnchecked) {
        smartMatrixPage.selectUCF("check".equals(checkOrUnchecked));
    }

    @When("I apply the filters")
    public void applyFilters() {
        smartMatrixPage.applyFilters();
    }

    @When("I search for {string}")
    public void performSearch(String searchTerm) {
        smartMatrixPage.searchTableFor(searchTerm);
    }

    @When("^I (check|uncheck) the \"([^\"]+)\" contract checkbox$")
    public void checkOrUncheckContractCheckbox(String checkOrUncheck, String contract) {
        smartMatrixPage.selectContract(SmartMatrixPage.Contract.valueOf(contract), "check".equals(checkOrUncheck));
    }

    String columnName;

    @When("^I click any of the links in the \"(Provision or Clause|Prescribed in)\" column$")
    public void stubMethod2(String columnName) {
        this.columnName = columnName;
    }

    @Then("I am taken to the correct page")
    public void verifyCorrectPage() {
        List<String> column = smartMatrixPage.getTableColumn(columnName);
        List<String> headers = smartMatrixPage.getTableHeaders();
        int columnNumber = headers.indexOf(columnName) + 1;
        for (int rowNumber = 1; rowNumber <= column.size(); rowNumber++) {
            String cellText = smartMatrixPage.getTableCell(rowNumber, columnNumber);

            smartMatrixPage.clickTableCell(rowNumber, columnNumber);

            if (cellText.contains("_Alternate")) {
                blazeLibrary.assertion().assertThat(blazeLibrary.getElements(By.xpath("//h1 | //em"))
                        .stream().map(BlazeWebElement::getText).collect(Collectors.toList()))
                        .as("Verifying link '%s'", cellText)
                        .anyMatch(el -> el.contains(cellText.substring(cellText.indexOf("_") + 1)));
            } else if (cellText.contains("(")) {
                blazeLibrary.assertion().assertThat(blazeLibrary.getElements(By.xpath("//h1 | //em"))
                        .stream().map(BlazeWebElement::getText).collect(Collectors.toList()))
                        .as("Verifying link '%s'", cellText)
                        .anyMatch(el -> el.contains(cellText.substring(0, cellText.indexOf("("))));
            } else {
                blazeLibrary.assertion().assertThat(blazeLibrary.getElements(By.xpath("//h1 | //em"))
                        .stream().map(BlazeWebElement::getText).collect(Collectors.toList()))
                        .as("Verifying link '%s'", cellText)
                        .anyMatch(el -> el.contains(cellText));
            }



            blazeLibrary.browser().closeTab();
        }
    }

    @Then("I see every value in the {string} column contains {string}")
    public void verifyTableColumnContains(String columnName, String expectedValue) {
        blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(columnName))
                .as("Verifying the every value under '%s' column contains '%s'", columnName, expectedValue)
                .allMatch(el -> el.contains(expectedValue));
    }


    @Then("^the smart matrix legend (is|is not) visible$")
    public void verifyLegendIsVisible(String isOrIsNot) {
        blazeLibrary.assertion().assertThat(smartMatrixPage.isLegendVisible())
                .as("Verifying the smart matrix %s visible", isOrIsNot)
                .isEqualTo("is".equals(isOrIsNot));
    }

    @Then("the smart matrix legend has the following text:")
    public void verifyLegendText(String expectedText) {
        blazeLibrary.assertion().assertThat(smartMatrixPage.getLegend())
                .as("Verifying if the text of the smart matrix")
                .isEqualToIgnoringWhitespace(expectedText);
    }

    @Then("^the IBR checkbox (is|is not) checked$")
    public void verifyIbrChecked(String isOrIsNot) {
        blazeLibrary.assertion().assertThat(smartMatrixPage.ibrIsSelected())
                .as("Verifying the IBR checkbox %s checked")
                .isEqualTo("is".equals(isOrIsNot));
    }

    @Then("^the UCF checkbox (is|is not) checked$")
    public void verifyUcfChecked(String isOrIsNot) {
        blazeLibrary.assertion().assertThat(smartMatrixPage.ucfIsSelected())
                .as("Verifying the UCF checkbox %s checked")
                .isEqualTo("is".equals(isOrIsNot));
    }

    @Then("^the following contract checkboxes (are|are not) checked:$")
    public void verifyContractCheckboxes(String areOrAreNot, List<String> expectedContractCheckboxes) {
        expectedContractCheckboxes.removeIf(String::isBlank);
        for (String expectedContractCheckbox : expectedContractCheckboxes) {
            blazeLibrary.assertion().assertThat(smartMatrixPage.contractIsSelected(SmartMatrixPage.Contract.valueOf(expectedContractCheckbox)))
                    .as("Verifying the %s contract checkbox %s checked", expectedContractCheckbox, "are".equals(areOrAreNot) ? "is" : "is not")
                    .isEqualTo("are".equals(areOrAreNot));
        }
    }

    @Then("the smart matrix contains the following headers:")
    public void verifySmartMatrixHeaders(List<String> expectedHeaders) {
        blazeLibrary.assertion().assertThat(smartMatrixPage.getTableHeaders())
                .as("Verifying headers of the smart matrix")
                .containsExactlyElementsOf(expectedHeaders);
    }

    @Then("the table can be sorted on any of the columns")
    public void verifyTableSorting() {
        for (String headerName : smartMatrixPage.getTableHeaders()) {
            smartMatrixPage.selectShowCompleteMatrix(true);
            smartMatrixPage.selectShowCompleteMatrix(false);
            if ("IBR".equals(headerName)) {
                smartMatrixPage.selectIBR(true);
            } else if ("UCF".equals(headerName)) {
                smartMatrixPage.selectUCF(true);
            } else if (!"Provision or Clause".equals(headerName)
                    && !"Prescribed in".equals(headerName)
                    && !"P or C".equals(headerName)) {
                smartMatrixPage.selectContract(SmartMatrixPage.Contract.valueOf(headerName), true);
            }
            smartMatrixPage.applyFilters();
            smartMatrixPage.sortColumn(headerName, SmartMatrixPage.Sort.ASCENDING);
            blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(headerName))
                    .as("Verifying the '%s' column is sorted in ascending order", headerName)
                    .isSortedAccordingTo(columnSorting);
            smartMatrixPage.sortColumn(headerName, SmartMatrixPage.Sort.DESCENDING);
            blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(headerName))
                    .as("Verifying the '%s' column is sorted in descending order", headerName)
                    .isSortedAccordingTo(columnSorting.reversed());
        }
    }

    @Then("all values under the \"Provision or Clause\" column are in the right format")
    public void verifyProvisionOrClauseFormat() {
        for (String cell : smartMatrixPage.getTableColumn("Provision or Clause")) {
            blazeLibrary.assertion().assertThat(cell)
                    .matches(el -> el.matches("\\d+\\.\\d+-\\d+(?: .+|_Alternate (?:I|II|III|IV|V))"), "#.#-# Title, or #.#-#_Alternate (roman numeral)");
        }
    }

    @Then("all values under the \"Prescribed in\" column are in the right format")
    public void verifyPrescribedInFormat() {
        for (String cell : smartMatrixPage.getTableColumn("Prescribed in")) {
            blazeLibrary.assertion().assertThat(cell)
                    .matches(el -> el.matches("\\d+\\.\\d+(?:-\\d+)?(?:\\([a-z]\\)(?:\\([1-9]\\))?)?"), "#.#-#(a-z)(#), with hyphen onwards being optional");
        }
    }

    private final static Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)(?:-(\\d+))?(?:\\(([a-z]+)\\)(?:\\(([1-9]+)\\))?)?(.*)");
    Comparator<String> columnSorting = new Comparator<>() {
        @Override
        public int compare(String o1, String o2) {
            Matcher matcher1 = pattern.matcher(o1);
            Matcher matcher2 = pattern.matcher(o2);
            if (matcher1.find() && matcher2.find()) {
                if (matcher1.group(1) != null && matcher2.group(1) != null && !matcher1.group(1).equals(matcher2.group(1))) {
                    return Integer.compare(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher2.group(1)));
                } else if (matcher1.group(2) != null && matcher2.group(2) != null && !matcher1.group(2).equals(matcher2.group(2))) {
                    if ((matcher1.group(3) == null && matcher1.group(4) == null && matcher1.group(5) == null) &&
                            (matcher2.group(3) != null || matcher2.group(4) != null || matcher2.group(5) != null)) {
                        return 1;
                    } else if ((matcher2.group(3) == null && matcher2.group(4) == null && matcher2.group(5) == null) &&
                            (matcher1.group(3) != null || matcher1.group(4) != null || matcher1.group(5) != null)) {
                        return -1;
                    } else if (matcher1.group(3) == null && matcher1.group(4) == null && matcher1.group(5) == null) {
                        return matcher1.group(2).compareTo(matcher2.group(2));
                    } else {
                        return Integer.compare(Integer.parseInt(matcher1.group(2)), Integer.parseInt(matcher2.group(2)));
                    }
                } else if (matcher1.group(3) != null && matcher2.group(3) != null && !matcher1.group(3).equals(matcher2.group(3))) {
                    return Integer.compare(Integer.parseInt(matcher1.group(3)), Integer.parseInt(matcher2.group(3)));
                } else if (matcher1.group(4) != null && matcher2.group(4) != null && !matcher1.group(4).equals(matcher2.group(4))) {
                    return matcher1.group(4).compareTo(matcher2.group(4));
                } else if (matcher1.group(5) != null && matcher2.group(5) != null && !matcher1.group(5).equals(matcher2.group(5))) {
                    return Integer.compare(Integer.parseInt(matcher1.group(5)), Integer.parseInt(matcher2.group(5)));
                } else {
                    return matcher1.group(6).compareTo(matcher2.group(6));
                }
            } else {
                return o1.compareTo(o2);
            }
        }

        @Override
        public String toString() {
            return "an obscure sorting algorithm";
        }
    };


}
