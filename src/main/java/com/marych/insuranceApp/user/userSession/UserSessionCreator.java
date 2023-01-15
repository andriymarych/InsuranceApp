package com.marych.insuranceApp.user.userSession;

import com.marych.insuranceApp.dao.userDao.UserDao;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.user.userRole.UserRole;

public class UserSessionCreator {
    public static int create(String userLogin){
        int userId = UserDao.getInstance().getUserId(userLogin);
        String userRoleStr = AppData.getInstance().get("userRole");
        UserRole userRole = UserRole.getUserRole(userRoleStr);
        UserSession.createInstance(userId,userLogin, userRole);
        return userId;
    }
}
