package com.marych.insuranceApp.userInterface.loginMenu;

import com.marych.insuranceApp.service.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button signInButton, signUpButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void signInButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("SignInScene.fxml")));

    }

    @FXML
    private void signUpButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("DiiaSignUpScene.fxml")));
    }
}
