package com.automation.oc.projectsetup;

import com.testingblaze.controller.UsersController;
import com.testingblaze.register.EnvironmentFactory;

public class UsersAndURL implements UsersController {

    /**
     * @REI-Systems
     * @author nauman.shahid
     * @category Handles users based on different environments
     */

    /****
     * BELOW ARE SAMPLE METHODS TO HANDLE USERS BASED ON DIFFERENT ENVIRONEMNTS
     *******/
    /****
     * IT ALSO HANDLE ANY ADDITIONAL URL IN ADDITION TO STANDARD PROJECT
     **********/
    /****
     * SINCE THESE ARE GENERIC METHODS SO YOU MAY KEEP THE SAME WITH MODIFICTION
     ******/

    @Override
    public String getUserName(String userLevel) {
        String userName = null;
        if ((EnvironmentFactory.getEnvironmentName() != null) && (EnvironmentFactory.getEnvironmentName().contains("QA"))) {
            userName = getQAEnvironmentUser(userLevel);
        } else if ((EnvironmentFactory.getEnvironmentName()!= null) && (EnvironmentFactory.getEnvironmentName().contains("UAT"))) {
            // To be implemented
        } else if ((EnvironmentFactory.getEnvironmentName() != null) && (EnvironmentFactory.getEnvironmentName().contains("DEMO"))) {
            userName = getDemoEnvironmentUser(userLevel);
        } else {
            userName = getQAEnvironmentUser(userLevel);
        }
        return userName;
    }

    @Override
    public String getPassword(String userLevel) {
        String password = null;
        if (EnvironmentFactory.getEnvironmentName().contains("QA")) {
            password = getQAEnvironmentPassword(userLevel);
        } else if (EnvironmentFactory.getEnvironmentName().contains("uat")) {
            // To be implemented
        } else if (EnvironmentFactory.getEnvironmentName().contains("DEMO")) {
            password = getDemoEnvironmentPassword(userLevel);
        }
        return password;
    }


    public String getQAEnvironmentPassword(String userLevel) {
        String password;    
        switch (userLevel.toUpperCase()) {
            case "User 1":
                password = "";
                break;
            case "User 2":
                password = "";
                break;
            default:
                password = "default password";
        }
        return password;
    }
    
    public String getDemoEnvironmentPassword(String userLevel) {
        String password;    
        switch (userLevel.toUpperCase()) {
        case "User 1":
            password = "";
            break;
        case "User 2":
            password = "";
            break;
        default:
            password = "default password";
        }
        return password;
    }

    public String getQAEnvironmentUser(String userLevel) {
        String username;

        switch (userLevel.toUpperCase()) {
        case "ADMIN":
            username = "any user";
            break;
        default:
            username = "any user";
        }
        return username;
    }
    
    public String getDemoEnvironmentUser(String userLevel) {
        String username;

        switch (userLevel.toUpperCase()) {
        case "ADMIN":
            username = "any user";
            break;
        default:
            username = "any user";
        }
        return username;
    }
   
}
