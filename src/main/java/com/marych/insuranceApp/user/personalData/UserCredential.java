package com.marych.insuranceApp.user.personalData;

import com.marych.insuranceApp.service.HashPasswordService;

public class UserCredential {
    private String login;
    private String password;

    public UserCredential(String login, String password) {
        HashPasswordService hashPasswordService = new HashPasswordService();
        this.login = login;
        this.password = hashPasswordService.generatePasswordHash(password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
