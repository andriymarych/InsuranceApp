package com.marych.insuranceApp.user.personalData;

import com.marych.insuranceApp.service.diia.DiiaCopy;
import com.marych.insuranceApp.user.userRole.UserRole;

public class RegistrationPersonalData {
    private int userId;
    private UserRole userRole;
    private UserCredential userCredential;
    private DiiaCopy diiaCopy;
    private String email;


    public static Builder newBuilder() {
        return new RegistrationPersonalData().new Builder();
    }

    public class Builder {
        public Builder() {
        }

        public Builder setUserId(int userId) {
            RegistrationPersonalData.this.userId = userId;
            return this;
        }

        public Builder setUserCredential(UserCredential userCredential) {
            RegistrationPersonalData.this.userCredential = userCredential;
            return this;
        }

        public Builder setDiiaCopy(DiiaCopy diiaCopy) {
            RegistrationPersonalData.this.diiaCopy = diiaCopy;
            return this;
        }

        public Builder setUserRole(UserRole userRole) {
            RegistrationPersonalData.this.userRole = userRole;
            return this;
        }

        public Builder setEmail(String email) {
            RegistrationPersonalData.this.email = email;
            return this;
        }

        public RegistrationPersonalData build() {
            return RegistrationPersonalData.this;
        }
    }

    public int getUserId() {
        return userId;
    }

    public UserCredential getUserCredential() {
        return userCredential;
    }

    public DiiaCopy getDiiaCopy() {
        return diiaCopy;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

}
