package com.marych.insuranceApp.document.policy.policyType.property;

public class CarInsurancePolicy {
    private int policyId;
    private String firstName;
    private String lastName;
    private String carBrand;
    private String carModel;
    private String licensePlate;

    public CarInsurancePolicy(int policyId, String firstName, String lastName) {
        this.policyId = policyId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPolicyId() {
        return policyId;
    }

    public CarInsurancePolicy setPolicyId(int policyId) {
        this.policyId = policyId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CarInsurancePolicy setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CarInsurancePolicy setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public CarInsurancePolicy setCarBrand(String carBrand) {
        this.carBrand = carBrand;
        return this;
    }

    public String getCarModel() {
        return carModel;
    }

    public CarInsurancePolicy setCarModel(String carModel) {
        this.carModel = carModel;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;

    }

    public CarInsurancePolicy setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }
}
