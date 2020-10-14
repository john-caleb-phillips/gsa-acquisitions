package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.blaze.elements.HasBlazeLibrary;
import com.reisystems.blaze.elements.PageObject;
import com.reisystems.blaze.elements.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class UpdatePage extends HasBlazeLibrary {

    public UpdatePage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary);
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
            return "UpdateItem(section='%s',caseNumber='%s')".formatted(section, caseNumber);
        }
    }
}
