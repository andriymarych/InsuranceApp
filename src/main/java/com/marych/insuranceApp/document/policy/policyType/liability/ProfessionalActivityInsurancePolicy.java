package com.marych.insuranceApp.document.policy.policyType.liability;

public class ProfessionalActivityInsurancePolicy extends LiabilityInsurance {

    private String firstName;
    private String lastName;
    private String companyName;
    private String professionalActivity;
    private String position;

    public ProfessionalActivityInsurancePolicy(int policyId, String firstName, String lastName, String companyName) {
        super();
        setPolicyId(policyId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfessionalActivityInsurancePolicy setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfessionalActivityInsurancePolicy setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ProfessionalActivityInsurancePolicy setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getProfessionalActivity() {
        return professionalActivity;
    }

    public ProfessionalActivityInsurancePolicy setProfessionalActivity(String professionalActivity) {
        this.professionalActivity = professionalActivity;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public ProfessionalActivityInsurancePolicy setPosition(String position) {
        this.position = position;
        return this;
    }
}
