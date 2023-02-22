package com.marych.insuranceApp.user.userSession;

import com.marych.insuranceApp.dao.userDao.UserDao;
import com.marych.insuranceApp.service.info.AppLogger;
import com.marych.insuranceApp.user.userRole.UserRole;

public class UserSessionCreator {
    public static UserSession create(String userLogin) {
        UserDao userDao = UserDao.getInstance();
        int userId = userDao.getUserId(userLogin);
        UserRole userRole = userDao.getUserRole(userLogin).orElse(UserRole.UNKNOWN);
        UserSession.createInstance(userId, userLogin, userRole);
        UserSession userSession = UserSession.getInstance();
        AppLogger.info("User " + userSession.getLogin() + "(Id" + userSession.getUserId() + ") is logged in");
        return UserSession.getInstance();
    }
}
