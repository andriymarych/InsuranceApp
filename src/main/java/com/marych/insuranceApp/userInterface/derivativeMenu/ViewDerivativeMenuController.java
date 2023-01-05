package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.service.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Objects;

public class ViewDerivativeMenuController {
    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../derivativeMenu/DerivativeMenuScene.fxml")));
    }

    @FXML
    private void viewDerivativeButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../derivativeMenu/ViewDerivativeScene.fxml")));
    }

    @FXML
    private void filterDerivativeButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../derivativeMenu/filterDerivativeScene.fxml")));
    }
}
