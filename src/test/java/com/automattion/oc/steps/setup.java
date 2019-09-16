package com.automattion.oc.steps;


import com.automation.oc.projectsetup.UsersAndURL;
import com.automation.rei.handlers.GovGrantsHandler;
import eyethink.automation.bots.Register.AuthorizedProject;
import eyethink.automation.bots.Register.EyeThink;
import eyethink.automation.bots.Report.CSS;
import eyethink.automation.bots.controller.TestSetupController;
import eyethink.automation.bots.objects.InstanceRecording;
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

    public setup(TestSetupController userController, GovGrantsHandler saasLayer) {
        this.userController = userController;
        EyeThink.setRegister(AuthorizedProject.SAAS_GOVGRANTS_OC);
        InstanceRecording.recordInstance(GovGrantsHandler.class, saasLayer);
        if(report == null) {
            this.report = new CSS();
            report.reportConfigWriteUp(System.getProperty("user.dir"),"GovGrants Orange County Test Automation");
        }
    }

    @Before(order = 2)
    public void setUpUsers() throws IOException, AWTException {
        userController.usersController(new UsersAndURL());
    }
}
