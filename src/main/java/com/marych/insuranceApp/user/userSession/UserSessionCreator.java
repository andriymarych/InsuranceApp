package com.marych.insuranceApp.user.userSession;

import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.user.userRole.UserRole;

public class UserSessionCreator {
    public static void create(int userId){
        String userLogin = AppData.getInstance().get("login");
        String userRoleStr = AppData.getInstance().get("userRole");
        UserRole userRole = UserRole.getUserRole(userRoleStr);
        UserSession.createInstance(userId,userLogin, userRole);
    }
}
