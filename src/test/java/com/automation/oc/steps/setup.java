package com.automation.oc.steps;

import com.automation.oc.projectsetup.UsersAndURL;
import com.testingblaze.controller.TestSetupController;
import com.testingblaze.register.EnvironmentFactory;
import com.testingblaze.report.CSS;
import io.cucumber.java.Before;

import java.awt.*;
import java.io.IOException;

/**
 * @author nauman.shahid
 * @for REI-Systems
 * @category Handle browser initialization , appium , hooks and environments
 */

public class setup {
    private TestSetupController userController;
    private static CSS report;

    public setup(TestSetupController userController) {
        this.userController = userController;
        if (report == null) {
            this.report = new CSS();
            EnvironmentFactory.setProjectName("GovGrants Orange County Test Automation");
            EnvironmentFactory.setOrgName("REI Systems [Saas Business Unit]");
            EnvironmentFactory.setProjectPath(System.getProperty("user.dir"));
            report.reportConfigWriteUp();
        }
    }

    @Before(order = 2)
    public void setUpUsers() throws IOException, AWTException {
        userController.usersController(new UsersAndURL());
    }
}
