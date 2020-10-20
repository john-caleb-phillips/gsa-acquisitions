package com.reisystems.automation.gsa.acquisitions.steps.smartmatrix;

import com.reisystems.automation.gsa.acquisitions.pageobject.smartmartix.SmartMatrixPage;
import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmartMatrixPageSteps extends HasBlazeLibrary {
    SmartMatrixPage smartMatrixPage;

    public SmartMatrixPageSteps(BlazeLibrary blazeLibrary, SmartMatrixPage smartMatrixPage) {
        super(blazeLibrary);
        this.smartMatrixPage = smartMatrixPage;
    }

    @Given("I am on the smart matrix page")
    public void goToSmartMatrixPage() {
        blazeLibrary.browser().navigateToUrl("https://www.acquisition.gov/far-smart-matrix");
    }

    @When("^I (expand|collapse) the smart matrix legend$")
    public void expandOrCollapseSmartMatrixLegend(String expandOrCollapse) {
        if ("expand".equals(expandOrCollapse)) {
            smartMatrixPage.expandLegend();
        } else {
            smartMatrixPage.collapseLegend();
        }
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
        int columnNumber = headers.indexOf("Provision or Clause");
        for (int i = 1; i <= column.size(); i++) {
            smartMatrixPage.clickProvisionOrClause(i, columnNumber);
            blazeLibrary.assertion().assertThat(1).isEqualTo(2);
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
        List<String> headers = smartMatrixPage.getTableHeaders();
        Collections.reverse(headers);
        for (String headerName : headers) {
            if (!"Prescribed in".equals(headerName)) {
                continue;
            }
            smartMatrixPage.clickHeader(headerName);
            blazeLibrary.assertion().assertThat(smartMatrixPage.getTableColumn(headerName))
                    .as("Verifying the '%s' column is sorted", headerName)
                    .isSortedAccordingTo(columnSorting);
        }
    }

    @Then("all values under the \"Prescribed in\" column are in the right format")
    public void verifyPrescribedInFormat() {
        List<String> prescribedInColumn = smartMatrixPage.getTableColumn("Prescribed in");
        for (String cell : prescribedInColumn) {
            Matcher matcher = pattern.matcher(cell);
            if (matcher.find()) {
                for (int i = 0; i <= matcher.groupCount(); i++) {
                    System.out.printf("Group %s: %s%n", i, matcher.group(i));
                }
            }

        }
        blazeLibrary.assertion().assertThat(prescribedInColumn)
                .as("Verifying the format of the 'Prescribed in' column")
                .allMatch(el -> el.matches("\\d+\\.\\d+(?:-\\d+)?(?:\\([a-z]\\)(?:\\([1-9]\\))?)?"), "#.#-#(a-z)(#), with hyphen onwards being optional");
    }

    private final static Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)(?:-(\\d+))?(?:\\(([a-z]+)\\)(?:\\(([1-9]+)\\))?)?(.*)");
    Comparator<String> columnSorting = (o1, o2) -> {
        Matcher matcher1 = pattern.matcher(o1);
        Matcher matcher2 = pattern.matcher(o2);
        if (matcher1.find() && matcher2.find()) {
            if (matcher1.group(1) != null && matcher2.group(1) != null && !matcher1.group(1).equals(matcher2.group(1))) {
                System.out.printf("First if statement: (%s:%s) vs (%s:%s)%n", matcher1.group(0), matcher1.group(1), matcher2.group(0), matcher2.group(1));
                return Integer.compare(Integer.parseInt(matcher1.group(1)), Integer.parseInt(matcher2.group(1)));
            } else if (matcher1.group(2) != null && matcher2.group(2) != null && !matcher1.group(2).equals(matcher2.group(2))) {
                if (matcher1.group(3) != null && matcher2.group(3) == null && (matcher2.group(4) != null || matcher2.group(5) != null)) {
                    return -1;
                }

                System.out.printf("Second if statement: (%s:%s) vs (%s:%s)%n", matcher1.group(0), matcher1.group(2), matcher2.group(0), matcher2.group(2));
                return matcher1.group(2).compareTo(matcher2.group(2));
            } else if (matcher1.group(3) != null && matcher2.group(3) != null && !matcher1.group(3).equals(matcher2.group(3))) {
                System.out.printf("Third if statement: (%s:%s) vs (%s:%s)%n", matcher1.group(0), matcher1.group(3), matcher2.group(0), matcher2.group(3));
                return Integer.compare(Integer.parseInt(matcher1.group(3)), Integer.parseInt(matcher2.group(3)));
            } else if (matcher1.group(4) != null && matcher2.group(4) != null && !matcher1.group(4).equals(matcher2.group(4))) {
                System.out.printf("Fourth if statement: (%s:%s) vs (%s:%s)%n", matcher1.group(0), matcher1.group(4), matcher2.group(0), matcher2.group(4));
                return matcher1.group(4).compareTo(matcher2.group(4));
            } else if (matcher1.group(5) != null && matcher2.group(5) != null && !matcher1.group(5).equals(matcher2.group(5))) {
                System.out.printf("Fifth if statement: (%s:%s) vs (%s:%s)%n", matcher1.group(0), matcher1.group(5), matcher2.group(0), matcher2.group(5));
                return Integer.compare(Integer.parseInt(matcher1.group(5)), Integer.parseInt(matcher2.group(5)));
            } else {
                System.out.printf("Sixth if statement: (%s:%s) vs (%s:%s)%n", matcher1.group(0), matcher1.group(6), matcher2.group(0), matcher2.group(6));
                return matcher1.group(6).compareTo(matcher2.group(6));
            }
        } else {
            System.out.printf("Else statement: %s vs %s%n", o1, o2);
            return o1.compareTo(o2);
        }
    };
}
