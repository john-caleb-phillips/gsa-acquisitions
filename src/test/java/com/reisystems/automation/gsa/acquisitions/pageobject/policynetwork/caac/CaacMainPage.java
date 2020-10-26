package com.reisystems.automation.gsa.acquisitions.pageobject.policynetwork.caac;

import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class CaacMainPage extends PageObject {

    public CaacMainPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "content/civilian-agency-acquisition-council-caac");
    }

    public void returnToMainPage() {
        blazeLibrary.getElement(By.xpath("//a[.='Return to CAAC Page']"))
                .click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToLettersPage() {
        blazeLibrary.getElement(By.xpath("//a[.='CAAC Letters' or .='Listing of CAAC Letters']"))
                .click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public void goToMembersPage() {
        blazeLibrary.getElement(By.xpath("//a[.='List of CAAC Members' or .='Listing of CAAC Members']"))
                .click(blazeLibrary.clickResults().REFRESH_PAGE);
    }

    public String getHeaderText() {
        return blazeLibrary.getElement(By.xpath("//h1[@class='page-title']")).getText();
    }

    public String getContentText() {
        return blazeLibrary.getElements(
                By.xpath(""
                        + "//*[local-name()='p' or local-name()='h2' or local-name()='ul']"
                        + "[preceding-sibling::p[.//a[.='Return to CAAC Page' or .='List of CAAC Members']]]")
        ).stream().map(BlazeWebElement::getText).collect(Collectors.joining("\n"));
    }

    public List<String> getLetterTableHeaders() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'caac_table-head')]//div[not(.=' ')]"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public void forEachLetter(Consumer<Letter> el) {
        for (Letter letter : getLetters()) {
            el.accept(letter);
        }
    }

    public void forEachDeviation(Consumer<Deviation> el) {
        for (Deviation letter : getDeviations()) {
            el.accept(letter);
        }
    }

    public List<String> getAttachmentsTableHeaders() {
        return blazeLibrary.getElements(By.xpath("//div[contains(@class, 'field-name-field-file-attachments')]//table[contains(@class, 'tableheader-processed')]//th"))
                .stream().map(BlazeWebElement::getText).collect(Collectors.toList());
    }

    public void forEachAttachment(Consumer<Attachment> el) {
        for (Attachment letter : getAttachments()) {
            el.accept(letter);
        }
    }

    private List<Letter> getLetters() {
        return blazeLibrary.getElements(By.cssSelector("div.caac_table-row"))
                .stream().map(Letter::new).collect(Collectors.toList());
    }

    private List<Deviation> getDeviations() {
        return blazeLibrary.getElements(By.cssSelector("div.caac_table-child-row"))
                .stream().map(Deviation::new).collect(Collectors.toList());
    }

    private List<Attachment> getAttachments() {
        return blazeLibrary.getElements(By.xpath("//table[contains(@class, 'tableheader-processed')]//tbody/tr"))
                .stream().map(Attachment::new).collect(Collectors.toList());
    }

    public class Letter {
        BlazeWebElement row;

        private Letter(BlazeWebElement row) {
            this.row = row;
        }

        public LocalDate getLetterNumber() {
            String date = row.findElement(By.xpath(".//div[1]")).getText().split(" ")[0] + "-01";
            String year = date.split("-")[0];
            if (year.length() == 2 && Integer.parseInt(year) >= 90) {
                date = "19" + date;
            }
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern("uuuu-MM-dd"));
            } catch (Exception ignored) {}

            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern("uu-MM-dd"));
            } catch (Exception ignored) {}

            return null;
        }

        public String getSubject() {
            return row.findElement(By.xpath("./div[2]")).getText();
        }

        public LocalDate getDate() {
            String date = row.findElement(By.xpath(".//div[3]")).getText();
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMMM d, uuuu"));
            } catch (Exception ignored) {}

            return null;
        }

        public void clickSubject() {
            row.findElement(By.xpath("./div[2]/a")).click(blazeLibrary.clickResults().OPEN_WINDOW_OR_TAB);
        }
    }

    public static class Deviation {
        BlazeWebElement row;

        private Deviation(BlazeWebElement row) {
            this.row = row;
        }

        public int getNumberOfDeviationsInRow() {
            return Integer.parseInt(row.findElement(By.xpath(".//div[1]")).getText().trim());
        }

        public int getNumberOfDeviationsInDropdown() {
            return row.findElements(By.xpath("./following-sibling::div[1]/div")).size();
        }
    }

    public static class Attachment {
        BlazeWebElement row;

        private Attachment(BlazeWebElement row) {
            this.row = row;
        }

        public String getFileName() {
            return row.findElement(By.xpath(".//td[1]")).getText();
        }

        public URL getFileUrl() {
            try {
                BlazeWebElement zipFileLink = row.findElement(By.xpath(".//td[1]//a"));
                if (zipFileLink.isPresent()) {
                    return new URL(zipFileLink.getAttribute("href"));
                } else {
                    return null;
                }
            } catch (MalformedURLException e) {
                return null;
            }
        }

        public String getSize() {
            return row.findElement(By.xpath(".//td[2]")).getText();
        }
    }

    public Person getCaacChair() {
        BlazeWebElement chair = blazeLibrary.getElement(By.xpath("//div[@class='caacmembers0']//table//tr[1]"));
        List<String> lines = Arrays.asList(chair.getText().split("\n"));
        return new Person(
                lines.get(1).split("\\(")[0].trim(),
                lines.get(lines.size() - 2).split("#")[1].trim(),
                lines.get(lines.size() - 1).trim()
        );
    }

    public List<Person> getCouncilMembers() {
        List<Person> councilMembers = new ArrayList<>();
        for (BlazeWebElement element : blazeLibrary.getElements(By.xpath("//div[@class='caacmembers0']//table//tr[position() > 2 and .//following-sibling::tr[.//.='GSA POINTS OF CONTACT']]"))) {
            List<String> lines = Arrays.asList(element.getText().split("\n"));
            councilMembers.add(new Person(
                    lines.get(1).split("\\(")[0].trim(),
                    lines.get(lines.size() - 2).split("#")[1].trim(),
                    lines.get(lines.size() - 1).trim()
            ));
        }
        return councilMembers;
    }

    public List<Person> getGsaContacts() {
        return null;
    }

    public List<Person> getSacRepresentatives() {
        return null;
    }

    public Person getFarSecretariat() {
        return null;
    }

    public List<Person> getFarStaff() {
        return null;
    }

    public static class Person {

        public final String name;
        public final String phoneNumber;
        public final String emailAddress;

        public Person(String name, String phoneNumber, String emailAddress) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }
    }

}
