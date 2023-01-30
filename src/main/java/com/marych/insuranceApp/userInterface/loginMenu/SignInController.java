package com.marych.insuranceApp.userInterface.loginMenu;


import com.marych.insuranceApp.dao.userDao.UserValidator;
import com.marych.insuranceApp.service.loader.WindowLoader;
import com.marych.insuranceApp.user.userSession.UserSession;
import com.marych.insuranceApp.user.userSession.UserSessionCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class SignInController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorLabel;

    @FXML
    private void signInButton(ActionEvent event) {
        UserValidator userValidator = new UserValidator();
        WindowLoader windowLoader = new WindowLoader(event);
        String login = usernameField.getText();
        String enteredPassword = passwordField.getText();
        if (userValidator.isLoginExist(login)) {
            UserSessionCreator.create(login);
        } else {
            errorLabel.setText("Користувача із логіном " + login + " не існує");
        }
        if (userValidator.validateUser(login,enteredPassword)) {
            windowLoader.load(Objects.requireNonNull(getClass().getResource("../mainMenu/MainScene.fxml")));
        } else {
            increaseLoginAttemptsNumber();
        }
    }
    private void increaseLoginAttemptsNumber(){
        UserSession userSession = UserSession.getInstance();
        assert userSession != null;
        userSession.status().increaseLoginAttemptsNumber();
        if(userSession.status().isExceededLoginAttemptsNumber()){
            errorLabel.setText("Ви ввели тричі невірний пароль");
        }else {
            errorLabel.setText("Ви ввели невірний пароль");
        }
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(event);
        windowLoader.load(Objects.requireNonNull(getClass().getResource("../loginMenu/LoginScene.fxml")));
    }
}
