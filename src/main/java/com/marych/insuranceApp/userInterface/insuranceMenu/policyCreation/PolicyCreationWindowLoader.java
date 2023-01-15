package com.marych.insuranceApp.userInterface.insuranceMenu.policyCreation;

import com.marych.insuranceApp.service.loader.WindowLoader;
import javafx.event.ActionEvent;

import java.util.Objects;

public class PolicyCreationWindowLoader {
    private ActionEvent event;

    public PolicyCreationWindowLoader(ActionEvent event) {
        this.event = event;
    }

    public void load(String policyType) {
        WindowLoader windowLoader = new WindowLoader(event);
        switch (policyType) {
            case "Особисте" ->
                    windowLoader.load(Objects.requireNonNull(getClass().getResource("../policyCreation/insurancePolicies/CreatePersonalPolicyScene.fxml")));
            case "Майно" ->
                    windowLoader.load(Objects.requireNonNull(getClass().getResource("../policyCreation/insurancePolicies/CreatePropertyPolicyScene.fxml")));
            case "Відповідальність" ->
                    windowLoader.load(Objects.requireNonNull(getClass().getResource("../policyCreation/insurancePolicies/CreateLiabilityPolicyScene.fxml")));
        }
    }
}
