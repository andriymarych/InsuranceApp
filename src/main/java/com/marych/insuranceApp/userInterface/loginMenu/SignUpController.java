package com.marych.insuranceApp.userInterface.loginMenu;

import com.marych.insuranceApp.dao.userDao.UserDao;
import com.marych.insuranceApp.service.WindowLoader;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.service.validation.EmailValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.Objects;

public class SignUpController {

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;
    @FXML
    private Label errorLabel;

    @FXML
    private void signUpButton(ActionEvent event) {
        UserDao userDao = UserDao.getInstance();
        String login = loginField.getText();
        if (!userDao.checkLogin(login)) {
            errorLabel.setText("Користувач із таким логіном уже існує");
        } else if (passwordField.getText().equals("")) {
            errorLabel.setText("Введіть пароль");
        } else if (!EmailValidation.validate(emailField.getText())) {
            errorLabel.setText("Ви ввели невірний email");
        } else {
            addUser();
            WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../mainMenu/MainScene.fxml")));
        }
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../loginMenu/DiiaSignUpScene.fxml")));
    }

    private void addUser() {
        UserDao userDao = UserDao.getInstance();
        addUserSessionData();
        int diiaId = Integer.parseInt(AppData.getInstance().get("diiaId"));
        userDao.addUser(diiaId);
    }
    private void addUserSessionData(){
        AppData.getInstance().put("login",loginField.getText());
        AppData.getInstance().put("password",passwordField.getText());
        AppData.getInstance().put("login",emailField.getText());
        AppData.getInstance().put("userRole","customer");
    }
}
