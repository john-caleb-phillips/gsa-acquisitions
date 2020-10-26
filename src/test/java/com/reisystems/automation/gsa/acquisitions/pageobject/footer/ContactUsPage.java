package com.reisystems.automation.gsa.acquisitions.pageobject.footer;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.PageObject;

public class ContactUsPage extends PageObject {
    public ContactUsPage(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, "HOMEPAGE", "contact-us?advagg=-1");
    }
}
