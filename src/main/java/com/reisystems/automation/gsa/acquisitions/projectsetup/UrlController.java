package com.reisystems.automation.gsa.acquisitions.projectsetup;

import com.reisystems.blaze.controller.EnvironmentController;

public class UrlController {
    public static String getPortalUrl() {
        return switch (EnvironmentController.getEnvironmentName()) {
            case "QA" -> "https://qa.digitaldashboard.gov/";
            case "UAT" -> "https://uat.digitaldashboard.gov/";
            case "PROD" -> "https://www.digitaldashboard.gov/";
            default -> "invalid environment";
        };
    }
}
