package com.marych.insuranceApp.user.userSession;

import com.marych.insuranceApp.service.info.AppLogger;

public class UserSessionLogStatus {
    private UserSession userSession;
    private static int loginAttemptsNumber;

    public UserSessionLogStatus(UserSession userSession) {
        this.userSession = userSession;
    }

    public boolean isExceededLoginAttemptsNumber(){
        if(loginAttemptsNumber == 3){
            AppLogger.error("User " + UserSession.getInstance().getLogin() + "(Id" + UserSession.getInstance().getUserId() + ") entered the wrong password three times");
            return true;
        }
        return false;
    }
    public void increaseLoginAttemptsNumber(){
        loginAttemptsNumber++;
    }
}
