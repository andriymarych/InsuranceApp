package com.marych.insuranceApp.document.policy.policyType.personal;

public class LifeInsurancePolicy extends PersonalInsurance {
    private int policyId;
    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;

    public LifeInsurancePolicy(int policyId, String firstName, String lastName) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public LifeInsurancePolicy setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public LifeInsurancePolicy setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public LifeInsurancePolicy setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LifeInsurancePolicy setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public LifeInsurancePolicy setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        return this;
    }
}
