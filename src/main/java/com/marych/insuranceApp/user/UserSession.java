package com.marych.insuranceApp.user;

public class  UserSession {
    private static UserSession instance;
    private String login;
    private int userId;
    private static int loginAttemptsNumber;

    private UserSession(int userId,String userName) {
        this.userId = userId;
        this.login = userName;
    }
    public static void createInstance(int userId,String login) {
        if(instance == null) {
            instance = new UserSession(userId,login);
            loginAttemptsNumber = 0;
        }
    }
    public static void increaseLoginAttemptsNumber(){
        loginAttemptsNumber++;
    }
    public static UserSession getInstance() {
        return instance;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public static int getLoginAttemptsNumber() {
        return loginAttemptsNumber;
    }
}
