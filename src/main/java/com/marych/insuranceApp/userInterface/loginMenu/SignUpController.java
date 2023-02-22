package com.marych.insuranceApp.userInterface.loginMenu;

import com.marych.insuranceApp.dao.DiiaDao;
import com.marych.insuranceApp.dao.userDao.UserDao;
import com.marych.insuranceApp.dao.userDao.UserValidator;
import com.marych.insuranceApp.service.diia.DiiaCopy;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.service.loader.WindowLoader;
import com.marych.insuranceApp.service.validation.EmailValidation;
import com.marych.insuranceApp.user.personalData.UserCredential;
import com.marych.insuranceApp.user.personalData.RegistrationPersonalData;
import com.marych.insuranceApp.user.userRole.UserRole;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;
import java.util.Optional;

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
        UserValidator userValidator = new UserValidator();
        String login = loginField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        if (userValidator.isLoginExist(login)) {
            errorLabel.setText("Користувач із таким логіном уже існує");
        } else if (password.equals("")) {
            errorLabel.setText("Введіть пароль");
        } else if (!EmailValidation.validate(email)) {
            errorLabel.setText("Ви ввели невірний email");
        } else {
            addUser(login, password,email);
            WindowLoader windowLoader = new WindowLoader(event);
            windowLoader.load(Objects.requireNonNull(getClass().getResource("../mainMenu/MainScene.fxml")));
        }
    }

    private void addUser(String login, String password,String email) {
        UserDao userDao = UserDao.getInstance();
        int userId = userDao.getNextUserId();
        int diiaId = Integer.parseInt(AppData.getInstance().get("diiaId"));
        saveUserCredentialsToAppData();
        DiiaCopy diiaCopy = getDiiaCopy(diiaId);
        UserRole userRole = UserRole.getUserRole("Customer");
        RegistrationPersonalData registrationPersonalData = RegistrationPersonalData.newBuilder()
                .setUserId(userId)
                .setUserCredential(new UserCredential(login, password))
                .setDiiaCopy(diiaCopy)
                .setUserRole(userRole)
                .setEmail(email)
                .build();
        userDao.addUser(registrationPersonalData);
    }

    private DiiaCopy getDiiaCopy(int diiaId) {
        DiiaDao diiaDao = DiiaDao.getInstance();
        Optional<DiiaCopy> diiaCopy = diiaDao.getDiiaCopy(diiaId);
        return diiaCopy.orElseThrow();
    }

    private void saveUserCredentialsToAppData() {
        AppData.getInstance().put("login", loginField.getText());
        AppData.getInstance().put("password", passwordField.getText());
        AppData.getInstance().put("email", emailField.getText());
        AppData.getInstance().put("userRole", "Customer");
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(event);
        windowLoader.load(Objects.requireNonNull(getClass().getResource("../loginMenu/DiiaSignUpScene.fxml")));
    }
}
