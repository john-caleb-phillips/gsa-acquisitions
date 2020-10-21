package com.reisystems.automation.gsa.acquisitions.pageobject.smartmartix;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.interfaces.ClickResult;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SmartMatrixPage extends HasBlazeLibrary {
    public SmartMatrixPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public void expandLegend() {
        blazeLibrary.getElement(locators.expandButton()).click(
                () -> blazeLibrary.getElement(locators.farLegend()).isDisplayed() ? "expanded the FAR matrix legend key" : null
        );
    }

    public void collapseLegend() {
        blazeLibrary.getElement(locators.collapseButton()).click(
                () -> blazeLibrary.getElement(locators.farLegend()).isDisplayed() ? null : "collapsed the FAR matrix legend key"
        );
    }

    public String getLegend() {
        return blazeLibrary.getElement(locators.farLegend()).getText();
    }

    public boolean isLegendVisible() {
        return blazeLibrary.getElement(locators.farLegend()).isDisplayed();
    }

    public void clickVideoLink() {
        blazeLibrary.getElement(locators.videoLink()).click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    public void selectShowCompleteMatrix(boolean shouldBeChecked) {
        BlazeWebElement fullMatrixCheckbox = blazeLibrary.getElement(locators.showCompleteMatrixCheckbox());
        if (fullMatrixCheckbox.isSelected() != shouldBeChecked) {
            fullMatrixCheckbox.click(() -> fullMatrixCheckbox.isSelected() == shouldBeChecked ? "checked or unchecked the IBR checkbox" : null);
        }
    }

    public void selectIBR(boolean shouldBeChecked) {
        BlazeWebElement ibrCheckbox = blazeLibrary.getElement(locators.ibrCheckBox());
        if (ibrCheckbox.isSelected() != shouldBeChecked) {
            ibrCheckbox.click(() -> ibrCheckbox.isSelected() == shouldBeChecked ? "checked or unchecked the IBR checkbox" : null);
        }
    }

    public void selectUCF(boolean shouldBeChecked) {
        BlazeWebElement ucfCheckbox = blazeLibrary.getElement(locators.ucfCheckBox());
        if (ucfCheckbox.isSelected() != shouldBeChecked) {
            ucfCheckbox.click(() -> ucfCheckbox.isSelected() == shouldBeChecked ? "checked or unchecked the UCF checkbox" : null);
        }
    }

    public void selectContract(Contract contract, boolean shouldBeChecked) {
        BlazeWebElement contractCheckbox = blazeLibrary.getElement(locators.contractCheckbox(contract));
        if (contractCheckbox.isSelected() != shouldBeChecked) {
            contractCheckbox.click(() -> contractCheckbox.isSelected() == shouldBeChecked ? String.format("checked or unchecked the %s contract checkbox", contract) : null);
        }
    }

    public boolean contractDropdownIsActive(Contract contract) {
        return blazeLibrary.getElement(locators.contractSelectBox(contract)).isEnabled();
    }

    public List<String> getContracts() {
        return blazeLibrary.getElements(locators.contractList())
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public void selectContractDropdownValue(Contract contract, String value) {
        blazeLibrary.getElement(locators.contractSelectBox(contract)).asDropdown().selectByVisibleText(value);
    }

    public void applyFilters() {
        blazeLibrary.getElement(locators.applyButton()).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void resetFilters() {
        blazeLibrary.getElement(locators.resetButton()).click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void copyTable() {
        blazeLibrary.getElement(locators.copyButton()).click();
    }

    public void getTableAsCsv() {
        blazeLibrary.getElement(locators.csvButton()).click();
    }

    public void getTableAsPdf() {
        blazeLibrary.getElement(locators.pdfButton()).click();
    }

    public void printTable() {
        blazeLibrary.getElement(locators.printButton()).click();
    }

    public void searchTableFor(String searchTerm) {
        BlazeWebElement searchBox = blazeLibrary.getElement(locators.searchBox());
        searchBox.clear();
        searchBox.sendKeys(searchTerm);
    }

    public void clickTableCell(int rowNumber, int columnNumber) {
        blazeLibrary.getElement(locators.table())
                .findElement(By.xpath(String.format(".//tr[%s]//td[%s]//a", rowNumber, columnNumber)))
                .click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    public void sortColumn(String headerText, Sort ascOrDesc) {
        BlazeWebElement header = blazeLibrary.getElement(locators.table()).findElement(locators.tableHeader(headerText));
        if (ascOrDesc == Sort.ASCENDING) {
            if (!header.getAttribute("class").contains("sorting_asc")) {
                header.click(() -> header.getAttribute("class").contains("sorting_asc") ? String.format("sorted column '%s' in ascending order", headerText) : null);
            }
        } else {
            if (!header.getAttribute("class").contains("sorting_desc")) {
                header.click(() -> header.getAttribute("class").contains("sorting_desc") ? String.format("sorted column '%s' in descending order", headerText) : null);
            }
        }
    }

    /* Accessor Methods */

    public boolean ibrIsSelected() {
        return blazeLibrary.getElement(locators.ibrCheckBox()).isSelected();
    }

    public boolean ucfIsSelected() {
        return blazeLibrary.getElement(locators.ucfCheckBox()).isSelected();
    }

    public boolean contractIsSelected(Contract contract) {
        return blazeLibrary.getElement(locators.contractCheckbox(contract)).isSelected();
    }

    public boolean additionalFilteringIsSelected(Contract contract, String value) {
        return blazeLibrary.getElement(locators.contractSelectBox(contract))
                .asDropdown().getFirstSelectedOption().getText().equals(value);
    }

    public String getTableTitle() {
        return blazeLibrary.getElement(locators.table()).findElement(locators.tableTitle()).getText();
    }

    public List<String> getTableHeaders() {
        return blazeLibrary.getElement(locators.table()).findElements(locators.tableHeaders())
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public List<String> getTableColumn(String columnName) {
        return blazeLibrary.getElement(locators.table())
                .findElements(By.xpath(String.format(".//td[%s]", getTableHeaders().indexOf(columnName) + 1)))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public String getTableCell(int rowNumber, int columnNumber) {
        return blazeLibrary.getElement(locators.table())
                .findElement(By.xpath(String.format(".//tbody//tr[%s]", rowNumber)))
                .findElement(By.xpath(String.format(".//td[%s]", columnNumber))).getText();
    }

    public Map<String, List<String>> getTableColumns() {
        return getTableHeaders().stream()
                .collect(Collectors.toMap(tableHeader -> tableHeader, this::getTableColumn));
    }

    public int numberOfTableRows() {
        return blazeLibrary.getElement(locators.table())
                .findElements(locators.tableRow()).size();
    }

    public Map<String, String> getTableRow(int rowNumber) {
        BlazeWebElement row = blazeLibrary.getElement(locators.table()).findElements(locators.tableRow()).get(rowNumber);
        return getTableHeaders().stream().collect(Collectors.toMap(
                tableHeader -> tableHeader,
                tableHeader -> row.findElement(locators.tableColumn(tableHeader)).getText())
        );
    }

    public List<Map<String, String>> getTableRows() {
        List<Map<String, String>> tableRows = new ArrayList<>();
        for (int i = 0; i < numberOfTableRows(); i++) {
            tableRows.add(getTableRow(i));
        }
        return tableRows;
    }

    public enum Contract {
        FP_SUP("FP SUP"), CR_SUP("CR SUP"),
        FP_RND("FP R&D"), CR_RND("CR R&D"),
        FP_SVC("FP SVC"), CR_SVC("CR SVC"),
        FP_CON("FP CON"), CR_CON("CR CON"),
        TNM_LH("T&M LH"), LMV("LMV"),
        COM_SVC("COM SVC"), DDR("DDR"),
        ANE("A&E"), FAC("FAC"),
        IND_DEL("IND DEL"), TRN("TRN"),
        SAP("SAP"), UTL_SVC("UTL SVC"),
        CI("CI");

        // This constructor allows us to call Contract.getValue(String)
        // with the parameter name (with ampersands and spaces) rather
        // than the less readable enum name (with underscores)
        Contract(String name) {
            try {
                Field fieldName = getClass().getSuperclass().getDeclaredField("name");
                fieldName.setAccessible(true);
                fieldName.set(this, name);
                fieldName.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Could not reset the name of the Contract enum values: " + e.toString());
            }
        }
    }

    public enum Sort {
        ASCENDING,
        DESCENDING
    }

    private static class locators {
        private static By expandButton() {
            return By.xpath("//div[@id='legend-key']//a[.='Expand']");
        }

        private static By collapseButton() {
            return By.xpath("//div[@id='legend-key']//a[.='Collapse']");
        }

        private static By farLegend() {
            return By.xpath("//table[@id='far-legend-key']//tbody");
        }

        private static By videoLink() {
            return By.xpath("//img[@class='hoverZoomLink']");
        }

        private static By showCompleteMatrixCheckbox() {
            return By.xpath("//input[@id='edit-hide-show-all']");
        }

        private static By ibrCheckBox() {
            return By.xpath("//input[@id='edit-field-ibr']");
        }

        private static By ucfCheckBox() {
            return By.xpath("//input[@id='edit-field-ucf']");
        }

        private static By contractList() {
            return By.xpath("//div[@id='selection-contract']//input//following-sibling::label");
        }

        private static By contractCheckbox(Contract contract) {
            return By.xpath(String.format("//label[normalize-space(.)='%s']//preceding-sibling::input", contract.name())
            );
        }

        private static By contractSelectBox(Contract contract) {
            return By.xpath(String.format("//label[normalize-space(.)='%s']//following-sibling::div//select", contract.name())
            );
        }

        private static By applyButton() {
            return By.xpath("//input[@id='edit-submit-clause-matrix']");
        }

        private static By resetButton() {
            return By.xpath("//input[@id='edit-reset']");
        }

        private static By searchBox() {
            return By.xpath("//input[@type='search']");
        }

        private static By copyButton() {
            return By.xpath("//button[.='Copy']");
        }

        private static By csvButton() {
            return By.xpath("//button[.='CSV']");
        }

        private static By pdfButton() {
            return By.xpath("//button[.='PDF']");
        }

        private static By printButton() {
            return By.xpath("//button[.='Print']");
        }

        private static By table() {
            return By.xpath("//div[@class='dataTables_scroll']");
        }

        private static By tableTitle() {
            return By.xpath(".//caption");
        }

        private static By tableHeaders() {
            return By.xpath(".//div[@class='dataTables_scrollHead']//thead//th");
        }

        private static By tableHeader(String headerText) {
            return By.xpath(String.format(".//div[@class='dataTables_scrollHead']//thead//th[normalize-space(.)='%s']", headerText));
        }

        private static By tableColumn(String columnName) {
            return By.xpath(String.format("//td[.//ancestor::table//th[normalize-space(.)='%1$s'] and (count(preceding-sibling::td) = count(.//ancestor::table//th[normalize-space(.)='%1$s']/preceding-sibling::th))]", columnName));
        }

        private static By tableRow() {
            return By.xpath(".//div[@class='dataTables_scrollBody']//tbody//tr");
        }

        private static By tableRowCell() {
            return By.xpath(".//td");
        }
    }
}
