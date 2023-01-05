package com.marych.insuranceApp.document.policy.policyType.personal;

import com.marych.insuranceApp.document.policy.InsurancePolicy;

public class PersonalInsurance extends InsurancePolicy {
    public PersonalInsurance(int policyId, boolean compulsory, int holderId, int insurerId, int companyId) {
        super(policyId, compulsory, holderId, insurerId, companyId);
    }

    public PersonalInsurance() {
    }
}
