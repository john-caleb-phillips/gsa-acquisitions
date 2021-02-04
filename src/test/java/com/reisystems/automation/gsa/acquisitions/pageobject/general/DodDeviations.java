package com.reisystems.automation.gsa.acquisitions.pageobject.general;

import com.reisystems.blaze.controller.BlazeLibrary;
import com.reisystems.blaze.elements.PageObject;

public class DodDeviations extends PageObject {
    public DodDeviations(BlazeLibrary blazeLibrary) {
        super(blazeLibrary, null, "https://www.acq.osd.mil/dpap/dars/class_deviations.html");
    }
}
