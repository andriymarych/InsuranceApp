package com.marych.insuranceApp.userInterface.loginMenu;


import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.service.diia.DiiaCopy;
import com.marych.insuranceApp.service.loader.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DiiaSignUpController {

    @FXML
    private TextField diiaIdField;
    @FXML
    private TextField diiaSignField;
    @FXML
    private Label errorLabel;

    @FXML
    private void signUpButton(ActionEvent event) {
        int diiaId = Integer.parseInt(diiaIdField.getText());
        int diiaSign = Integer.parseInt(diiaSignField.getText());
        WindowLoader windowLoader = new WindowLoader(event);
        if (validateDiiaDocument(diiaId, diiaSign)) {
            AppData.getInstance().put("diiaId", String.valueOf(diiaId));
            windowLoader.load(Objects.requireNonNull(getClass().getResource("SignUpScene.fxml")));
        } else {
            errorLabel.setText("Ви ввели невірні дані цифрового документу");
        }
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(event);
        windowLoader.load(Objects.requireNonNull(getClass().getResource("../loginMenu/LoginScene.fxml")));
    }

    private boolean validateDiiaDocument(int diiaId, int diiaSign) {
        DiiaCopy diiaCopy = new DiiaCopy(diiaId);
        return diiaSign == diiaCopy.getDiiaSign();
    }
}
