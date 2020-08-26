package com.reisystems.automation.gsa.acquisitions.pageobject;

import com.reisystems.blaze.blazeElement.BlazeWebElement;
import com.reisystems.blaze.controller.BlazeLibrary;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class UpdatePage {

    BlazeLibrary blazeLibrary;

    public UpdatePage(BlazeLibrary blazeLibrary) {
        this.blazeLibrary = blazeLibrary;
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
            return String.format("UpdateItem(section='%s',caseNumber='%s')",
                    section, caseNumber);
        }
    }
}
