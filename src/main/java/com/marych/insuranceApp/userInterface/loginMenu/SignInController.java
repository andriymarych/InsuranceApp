package com.marych.insuranceApp.userInterface.loginMenu;

import com.marych.insuranceApp.dao.userDao.UserDao;
import com.marych.insuranceApp.dao.userDao.UserDaoValidator;
import com.marych.insuranceApp.service.loader.WindowLoader;
import com.marych.insuranceApp.user.userSession.UserSession;
import com.marych.insuranceApp.service.info.AppLogger;
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
    private void signInButton(ActionEvent event)  {
        int userId;
        UserDaoValidator userDaoValidator = new UserDaoValidator();
        String login = usernameField.getText();
        String enteredPassword = passwordField.getText();
        String actualPassword = userDaoValidator.getUserPassword(login);
        WindowLoader windowLoader = new WindowLoader(event);
        if (actualPassword == null) {
            errorLabel.setText("Користувача із логіном " + login + " не існує");
        } else if (enteredPassword.equals(actualPassword)) {
            userId = UserDao.getInstance().getUserId(login);
            UserSessionCreator.create(userId);
            AppLogger.info("User " + UserSession.getInstance().getLogin() + "(Id" + UserSession.getInstance().getUserId() + ") is logged in");
            windowLoader.load(Objects.requireNonNull(getClass().getResource( "../mainMenu/MainScene.fxml")));
        } else {
            userId = UserDao.getInstance().getUserId(login);
            UserSessionCreator.create(userId);
            UserSession.increaseLoginAttemptsNumber();
            errorLabel.setText("Ви ввели невірний пароль");
            if (UserSession.isExceededLoginAttemptsNumber()) {
                AppLogger.error("User " + UserSession.getInstance().getLogin() + "(Id" + UserSession.getInstance().getUserId() + ") entered the wrong password three times");
            }
        }
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(event);
        windowLoader.load(Objects.requireNonNull(getClass().getResource("../loginMenu/LoginScene.fxml")));
    }
}
