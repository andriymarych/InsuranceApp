package com.marych.insuranceApp.user.userSession;

import com.marych.insuranceApp.user.userRole.UserRole;

public class  UserSession {
    private static UserSession instance;
    private String login;
    private final int userId;
    private UserRole userRole;
    private static int loginAttemptsNumber;

    private UserSession(int userId,String userName,UserRole userRole) {
        this.userId = userId;
        this.login = userName;
        this.userRole = userRole;
    }
    public static void createInstance(int userId,String login,UserRole userRole) {
        if(instance == null) {
            instance = new UserSession(userId,login,userRole);
            loginAttemptsNumber = 0;
        }
    }
    public static void increaseLoginAttemptsNumber(){
        loginAttemptsNumber++;
    }
    public static UserSession getInstance() {
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public static int getLoginAttemptsNumber() {
        return loginAttemptsNumber;
    }
}
