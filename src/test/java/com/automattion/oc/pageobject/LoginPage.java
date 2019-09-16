package com.automattion.oc.pageobject;

import com.automation.oc.projectsetup.ProjectManager;
import eyethink.automation.bots.Register.EyeThink;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Handles all login based methods and locators for Project
 */
public class LoginPage extends ProjectManager {


    /******* "Key Methods with complete functionality" *****************/

    //Just a sample method. Remove it
    public void assertLoggedIn() {
        By logo = By.xpath("//img[@id='govGrantsHeaderImagePlaceHolderId']");
        Assert.assertTrue(EyeThink.core().is().onWeb().isDisplayed(logo));
    }

    /******* "Local Methods with Limited functionality " **********/

}
