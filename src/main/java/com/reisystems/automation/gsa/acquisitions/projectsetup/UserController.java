package com.reisystems.automation.gsa.acquisitions.projectsetup;


import com.reisystems.blaze.controller.StaticEnvironmentController;
import com.reisystems.blaze.interfaces.UserInfoController;

import java.util.Map;

public class UserController implements UserInfoController {

    Map<String, UserInfo> userInfoMap = Map.of(
            "QA_User", new UserInfo("", ""),
            "UAT_User", new UserInfo("", "")
    );

    @Override
    public String getUserInfo(String userIdentifier, String userInfo) {
        switch (userInfo) {
            case "Username": return getUserName(userIdentifier);
            case "Password": return getPassword(userIdentifier);
            default: throw new RuntimeException("This info is not supported");
        }
    }

    public String getUserName(String userLevel) {
        return userInfo(userLevel).username;
    }

    public String getPassword(String userLevel) {
        return userInfo(userLevel).password;
    }

    private UserInfo userInfo(String userLevel) {
        if (userInfoMap.containsKey(StaticEnvironmentController.getEnvironmentName() + "_" + userLevel)) {
            return userInfoMap.get(StaticEnvironmentController.getEnvironmentName() + "_" + userLevel);
        }
        return new UserInfo("invalid", "invalid");
    }

    private static class UserInfo {
        public final String username;
        public final String password;

        private UserInfo(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
