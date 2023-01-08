package com.marych.insuranceApp.userInterface.loginMenu;

import com.marych.insuranceApp.dao.userDao.UserDao;
import com.marych.insuranceApp.service.WindowLoader;
import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.service.info.AppLogger;
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
    private void signInButton(ActionEvent event)  {
        String login = usernameField.getText();
        String enteredPassword = passwordField.getText();
        UserDao userDao = new UserDao();
        String actualPassword = userDao.validateUser(login);
        if (actualPassword == null) {
            errorLabel.setText("Користувача із логіном " + login + " не існує");
        } else if (enteredPassword.equals(actualPassword)) {
            AppLogger.info("User " + UserSession.getInstance().getLogin() + "(Id" + UserSession.getInstance().getUserId() + ") is logged in");
            WindowLoader.load(event,Objects.requireNonNull(getClass().getResource( "../mainMenu/MainScene.fxml")));
        } else {
            UserSession.increaseLoginAttemptsNumber();
            errorLabel.setText("Ви ввели невірний пароль");
            if (UserSession.getLoginAttemptsNumber() == 3) {
                AppLogger.error("User " + UserSession.getInstance().getLogin() + "(Id" + UserSession.getInstance().getUserId() + ") entered the wrong password three times");
            }
        }
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../loginMenu/LoginScene.fxml")));
    }
}
