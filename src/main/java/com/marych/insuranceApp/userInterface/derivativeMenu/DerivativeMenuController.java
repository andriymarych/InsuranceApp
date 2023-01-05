package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.service.WindowLoader;
import com.marych.insuranceApp.service.info.AppData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Objects;

public class DerivativeMenuController {

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event,Objects.requireNonNull(getClass().getResource("../mainMenu/MainScene.fxml")));
    }
    @FXML
    private void createDerivativeButton(ActionEvent event) {
        AppData.getInstance().put("nextWindow","Derivative");
        WindowLoader.load(event,Objects.requireNonNull(getClass().getResource("/com/marych/insuranceApp/userInterface/insuranceMenu/policyCreation/SelectCompanyScene.fxml")));
    }
    @FXML
    private void viewDerivativeButton(ActionEvent event) {
        WindowLoader.load(event,Objects.requireNonNull(getClass().getResource("../derivativeMenu/ViewDerivativeMenuScene.fxml")));
    }
}
