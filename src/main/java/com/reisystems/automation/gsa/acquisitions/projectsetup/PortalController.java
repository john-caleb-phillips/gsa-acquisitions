package com.reisystems.automation.gsa.acquisitions.projectsetup;

import com.reisystems.blaze.interfaces.PortalUrlController;

public class PortalController implements PortalUrlController {


    @Override
    public String getPortalUrl(String portalType) {
        return "https://www.acquisition.gov/";
    }
}
