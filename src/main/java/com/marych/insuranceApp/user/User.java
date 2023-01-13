package com.marych.insuranceApp.user;

import com.marych.insuranceApp.user.userRole.UserRole;

public abstract class User {


    private int userId;
    private UserRole userRole;

    public User(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public User setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public User setUserRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

}
