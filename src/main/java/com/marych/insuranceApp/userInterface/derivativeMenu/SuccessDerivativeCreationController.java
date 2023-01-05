package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.service.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.Objects;

public class SuccessDerivativeCreationController {

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("/com/marych/insuranceApp/userInterface/mainMenu/MainScene.fxml")));
    }
}

