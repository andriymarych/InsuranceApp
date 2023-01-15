package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.service.loader.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.Objects;

public class SuccessDerivativeCreationController {

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(event);
        windowLoader.load(Objects.requireNonNull(getClass().getResource("/com/marych/insuranceApp/userInterface/mainMenu/MainScene.fxml")));
    }
}

