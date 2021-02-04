package com.reisystems.automation.gsa.acquisitions.pageobject.regulations;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.report.LogLevel;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class SidebarPage extends HasBlazeLibrary {
    public SidebarPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
    }

    public String title() {
        return blazeLibrary.getElement(By.xpath("//h1[@class='page-title']")).getText();
    }

    public String partTitle() {
        return blazeLibrary.getElement(By.xpath("//h1[contains(@class, 'topictitle1') or contains(@class, 'GSAR') or @id='ariaid-title1']")).getText();
    }

    public List<String> breadcrumbs() {
        return blazeLibrary.getElements(By.xpath("//div[@id='breadcrumbs']//a"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public List<String> sidebarLinks() {
        return blazeLibrary.getElements(By.xpath("//div[@id='parts-column']//p//a"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public List<String> sidebarParts() {
        return blazeLibrary.getElements(By.xpath("//div[@id='parts-column']//div[@class='clearfix']//a"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public void clickSideBarLink(String linkText) {
        if (!sidebarLinks().contains(linkText)) {
            blazeLibrary.report().write(LogLevel.WARN,
                    String.format("Regulation sidebar link '%s' was not present."
                                    + "%n    The available links were [%s]",
                            linkText, String.join(", ", sidebarLinks())));
            return;
        }
        blazeLibrary.getElement(By.xpath(String.format("//div[@id='parts-column']//p//a[.='%s']", linkText)))
                .click(blazeLibrary.clickResults().REFRESH_PAGE, blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
    }

    public void clickSideBarPart(String partNumber) {
        if (!sidebarParts().contains(partNumber)) {
            blazeLibrary.report().write(LogLevel.WARN,
                    String.format("Regulation sidebar part '%s' was not present."
                                    + "%n    The available parts were [%s]",
                            partNumber, String.join(", ", sidebarLinks())));
            return;
        }
        blazeLibrary.getElement(By.xpath(String.format("//div[@id='parts-column']//div[@class='clearfix']//a[.='%s']", partNumber)))
                .click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public Map<String, String> tocLinks() {
        Map<String, String> tocLinks = new TreeMap<>();
        for (BlazeWebElement tocElement : blazeLibrary.getElements(By.xpath("//main//h1[@id='ariaid-title1']//following-sibling::div[@class='body']//a[@href]"), 0)) {
            String[] id = tocElement.getAttribute("href").split("#");
            tocLinks.put(StringUtils.normalizeSpace(tocElement.getText()), id.length > 1 ? id[1] : id[0]);
        }
        for (Map.Entry<String, String> entry : tocLinks.entrySet()) {
            BlazeWebElement contentLink = blazeLibrary.getElement(By.xpath(String.format("//a[not(@href) and @id='%s']/..", entry.getValue())));
            if (contentLink.isPresent()) {
                entry.setValue(StringUtils.normalizeSpace(contentLink.getText()));
            } else {
                entry.setValue(null);
            }
        }
        return tocLinks;
    }

    public FullDownloadTable fullDownloadTable() {
        return new FullDownloadTable();
    }

    public List<PartRow.Info> getRows() {
        List<String> headers = blazeLibrary
                .getElements(By.xpath("//table[@id='browse-table']//tr//th//following-sibling::th"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
        List<PartRow.Info> rows = new ArrayList<>();
        for (int i = 1; i <= blazeLibrary.getElements(By.xpath("//table[@id='browse-table']//tr[not(.//th)]")).size(); i++) {
            rows.add(new PartRow(headers, i).getInfo());
        }
        return rows;
    }

    public void forEachRow(Consumer<PartRow> rowConsumer) {
        List<String> headers = blazeLibrary
                .getElements(By.xpath("//table[@id='browse-table']//tr//th//following-sibling::th"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
        for (int i = 1; i <= blazeLibrary.getElements(By.xpath("//table[@id='browse-table']//tr[not(.//th)]")).size(); i++) {
            rowConsumer.accept(new PartRow(headers, i));
        }
    }

    public List<String> parts() {
        return blazeLibrary.getElement(By.xpath("//select[@id='partname']"))
                .asDropdown().getOptions().stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String currentPart() {
        return blazeLibrary.getElement(By.xpath("//select[@id='partname']"))
                .asDropdown().getFirstSelectedOption().getText();
    }

    public void selectPart(String partNumber) {
        blazeLibrary.getElement(By.xpath("//select[@id='partname']"))
                .asDropdown().selectByVisibleText(partNumber);
    }

    public List<String> subParts() {
        return blazeLibrary.getElement(By.xpath("//select[@id='subpartname']"))
                .asDropdown().getOptions().stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String currentSubPart() {
        return blazeLibrary.getElement(By.xpath("//select[@id='subpartname']"))
                .asDropdown().getFirstSelectedOption().getText();
    }

    public void selectSubPart(String subPartNumber) {
        blazeLibrary.getElement(By.xpath("//select[@id='subpartname']"))
                .asDropdown().selectByVisibleText(subPartNumber);
    }

    public List<String> sections() {
        return blazeLibrary.getElement(By.xpath("//select[@id='subtopicname']"))
                .asDropdown().getOptions().stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String currentSection() {
        return blazeLibrary.getElement(By.xpath("//select[@id='subtopicname']"))
                .asDropdown().getFirstSelectedOption().getText();
    }

    public void selectSection(String sectionNumber) {
        blazeLibrary.getElement(By.xpath("//select[@id='subtopicname']"))
                .asDropdown().selectByVisibleText(sectionNumber);
    }


    public class FullDownloadTable {
        private FullDownloadTable() {
        }

        public List<String> headers() {
            return blazeLibrary.getElements(By.xpath("//table[@id='browse-table-full']//tr/th"))
                    .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
        }

        public Map<String, Cell> getCells() {
            List<String> headers = headers();
            Map<String, Cell> cells = new HashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                cells.put(headers.get(i), new Cell(headers.get(i), i + 1));
            }
            return cells;
        }

        public class Cell {
            private final String headerText;
            private final int cellNumber;

            private Cell(String headerText, int cellNumber) {
                this.headerText = headerText;
                this.cellNumber = cellNumber;
            }

            public String text() {
                return blazeLibrary.getElement(By.xpath(String.format(
                        "//table[@id='browse-table-full']//tr[not(.//th)]//td[%s]",
                        cellNumber
                ))).getText();
            }

            public URL href() {
                BlazeWebElement link = blazeLibrary.getElement(By.xpath(String.format(
                        "//table[@id='browse-table-full']//tr[not(.//th)]//td[%s]/a",
                        cellNumber
                )));
                if (link.isPresent()) {
                    try {
                        return new URL(link.getAttribute("href"));
                    } catch (MalformedURLException e) {
                        blazeLibrary.report().write(LogLevel.WARN, String.format(
                                "The '%s' cell in the Full Download table had a malformed href attribute: %s",
                                headerText, e.getMessage()));
                    }
                } else {
                    blazeLibrary.report().write(LogLevel.WARN, String.format(
                            "The '%s' cell in the Full Download table did not have a link.",
                            headerText));
                }
                return null;
            }

            public void click() {
                if (href() != null) {
                    blazeLibrary.getElement(By.xpath(String.format(
                            "//table[@id='browse-table-full']//tr[not(.//th)]//td[%s]/a",
                            cellNumber
                    ))).click(blazeLibrary.clickResults().REFRESH_PAGE, blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
                } else {
                    blazeLibrary.report().write(LogLevel.WARN, String.format(
                            "The '%s' cell in the Full Download table did not have a valid link."
                                    + "%n    Click was not attempted.", headerText));
                }
            }
        }
    }


    public class PartRow {
        private final List<String> headers;
        private final int rowNumber;

        private PartRow(List<String> headers, int rowNumber) {
            this.headers = headers;
            this.rowNumber = rowNumber;
        }

        public Info getInfo() {
            return new Info();
        }

        public void clickTitle() {
            blazeLibrary.getElement(By.xpath(
                    String.format("//table[@id='browse-table']//tr[not(.//th)][%s]/td[1]/a", rowNumber)))
                    .click(blazeLibrary.clickResults().REFRESH_PAGE);
        }

        public boolean isSubPart() {
            return "subpart".equals(blazeLibrary.getElement(By.xpath(
                    String.format("//table[@id='browse-table']//tr[not(.//th)][%s]/td[1]/a", rowNumber)))
                    .getAttribute("class"));
        }

        public String getPartTitle() {
            return blazeLibrary.getElement(By.xpath(
                    String.format("//table[@id='browse-table']//tr[not(.//th)][%s]/td[1]", rowNumber)))
                    .getText();
        }

        public Map<String, URL> getDownloadLinks() {
            Map<String, URL> links = new HashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                URL theUrl = null;
                try {
                    theUrl = new URL(blazeLibrary.getElement(By.xpath(
                            String.format("//table[@id='browse-table']//tr[not(.//th)][%s]//td[%s]/a",
                                    rowNumber, i + 2))).getAttribute("href"));
                } catch (MalformedURLException ignored) {
                }
                links.put(headers.get(i), theUrl);
            }
            return links;
        }

        public class Info {
            public final boolean isSubPart = isSubPart();
            public final String partName = getPartTitle();
            public final Map<String, URL> downloadLinks = getDownloadLinks();

            private Info() {
            }
        }

    }
}
