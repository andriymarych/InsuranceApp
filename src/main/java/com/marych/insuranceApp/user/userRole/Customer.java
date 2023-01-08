package com.marych.insuranceApp.user.userRole;

import com.marych.insuranceApp.user.User;

public class Customer extends User {
    private String firstName;
    private String lastName;
    private String birthDate;
    private String ITN;
    private String email;

    public Customer(int userId) {
        super(userId);

    }

    @Override
    public Customer setUserRole(UserRole userRole) {
        super.setUserRole(userRole);
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public Customer setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getITN() {
        return ITN;
    }

    public Customer setITN(String ITN) {
        this.ITN = ITN;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }
}
