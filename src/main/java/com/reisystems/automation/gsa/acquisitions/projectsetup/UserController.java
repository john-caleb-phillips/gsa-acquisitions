package com.reisystems.automation.gsa.acquisitions.projectsetup;


import com.reisystems.blaze.controller.EnvironmentController;
import com.reisystems.blaze.interfaces.UserInfoController;

public class UserController implements UserInfoController {


    @Override
    public String getUserInfo(String userIdentifier, String userInfo) {
        return switch (userInfo) {
            case "Username" -> getUserName(userIdentifier);
            case "Password" -> getPassword(userIdentifier);
            case "Email" -> getEmail(userIdentifier);
            default -> throw new RuntimeException("This info is not supported");
        };
    }

    public String getUserName(String userLevel) {
        String userName = null;
        if ((EnvironmentController.getEnvironmentName() != null) && (EnvironmentController.getEnvironmentName().contains("QA"))) {
            userName = getQAEnvironmentUser(userLevel);
        } else if ((EnvironmentController.getEnvironmentName()!= null) && (EnvironmentController.getEnvironmentName().contains("UAT"))) {
            // To be implemented
        } else if ((EnvironmentController.getEnvironmentName() != null) && (EnvironmentController.getEnvironmentName().contains("DEMO"))) {
            userName = getDemoEnvironmentUser(userLevel);
        } else {
            userName = getQAEnvironmentUser(userLevel);
        }
        return userName;
    }

    public String getPassword(String userLevel) {
        String password = null;
        if (EnvironmentController.getEnvironmentName().contains("QA")) {
            password = getQAEnvironmentPassword(userLevel);
        } else if (EnvironmentController.getEnvironmentName().contains("uat")) {
            // To be implemented
        } else if (EnvironmentController.getEnvironmentName().contains("DEMO")) {
            password = getDemoEnvironmentPassword(userLevel);
        }
        return password;
    }

    public String getEmail(String userLevel) {
        String password = null;
        if (EnvironmentController.getEnvironmentName().contains("QA")) {
            password = getQAEnvironmentPassword(userLevel);
        } else if (EnvironmentController.getEnvironmentName().contains("uat")) {
            // To be implemented
        } else if (EnvironmentController.getEnvironmentName().contains("DEMO")) {
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
