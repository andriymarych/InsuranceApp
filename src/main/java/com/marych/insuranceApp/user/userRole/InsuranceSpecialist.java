package com.marych.insuranceApp.user.userRole;

import com.marych.insuranceApp.user.User;

public class InsuranceSpecialist extends User {
    private int userId;
    private String firstName;
    private String lastName;
    private int  CompanyId;
    private String email;

    public InsuranceSpecialist(int userId) {
        super(userId);
    }

    public int getUserId() {
        return userId;
    }

    public InsuranceSpecialist setUserId(int userId) {
        this.userId = userId;
        return this;
    }
    public String getFullName(){
        return firstName + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public InsuranceSpecialist setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public InsuranceSpecialist setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public InsuranceSpecialist setCompanyId(int companyId) {
        CompanyId = companyId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InsuranceSpecialist setEmail(String email) {
        this.email = email;
        return this;
    }
}
