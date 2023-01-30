package com.marych.insuranceApp.user.userSession;

import com.marych.insuranceApp.user.userRole.UserRole;

public class  UserSession {
    private static UserSession instance;
    private final UserSessionLogStatus userSessionLogStatus;
    private String login;
    private final int userId;
    private UserRole userRole;


    private UserSession(int userId,String userName,UserRole userRole) {
        this.userId = userId;
        this.login = userName;
        this.userRole = userRole;
        this.userSessionLogStatus = new UserSessionLogStatus(this);
    }
    public static void createInstance(int userId,String login,UserRole userRole) {
        if(instance == null) {
            instance = new UserSession(userId,login,userRole);
        }
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

    public UserSessionLogStatus status() {
        return userSessionLogStatus;
    }
}
