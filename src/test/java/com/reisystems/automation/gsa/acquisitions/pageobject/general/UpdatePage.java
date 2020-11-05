package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.elements.PageObject;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UpdatePage extends PageObject {

    public UpdatePage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "content/list-sections-affected");
    }

    public List<UpdateItem> getUpdates(Integer numberOfUpdates) {
        List<UpdateItem> updateItems = new ArrayList<>();

        List<BlazeWebElement> updateElements = blazeLibrary.getElements(By.xpath("//article//tbody//tr"));

        for (int i = 0; i < numberOfUpdates; i++) {
            if (i == updateElements.size()) {
                break;
            }
            updateItems.add(new UpdateItem(
                    updateElements.get(i).findElement(By.xpath(".//td[1]")).getText(),
                    updateElements.get(i).findElement(By.xpath(".//td[3]")).getText()
            ));
        }

        return updateItems;
    }


    public static class UpdateItem {
        public final String section;
        public final String caseNumber;

        public UpdateItem(String section, String caseNumber) {
            this.section = section;
            this.caseNumber = caseNumber;
        }

        public String toString() {
            return String.format("UpdateItem(section='%s',caseNumber='%s')", section, caseNumber);
        }

        public static final Comparator<UpdateItem> comparator = new Comparator<UpdateItem>() {
            @Override
            public int compare(UpdatePage.UpdateItem o1, UpdatePage.UpdateItem o2) {
                return (o1.section.contains(o2.section) || o2.section.contains(o1.section)) && o1.caseNumber.equals(o2.caseNumber) ? 0 : 1;
            }

            public String toString() {
                return "case numbers are the same and homepage sections are contained in update page sections";
            }
        };
    }
}
