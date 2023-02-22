package com.marych.insuranceApp.userInterface.loginMenu;


import com.marych.insuranceApp.dao.DiiaDao;
import com.marych.insuranceApp.service.HashPasswordService;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.service.loader.WindowLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        String diiaSign = diiaSignField.getText();
        WindowLoader windowLoader = new WindowLoader(event);
        if (validateDiiaSign(diiaId, diiaSign)) {
            AppData.getInstance().put("diiaId", String.valueOf(diiaId));
            windowLoader.load(Objects.requireNonNull(getClass().getResource("SignUpScene.fxml")));
        } else {
            errorLabel.setText("Ви ввели невірні дані цифрового документу");
        }
    }

    public boolean validateDiiaSign(int diiaId, String enteredDiiaSign) {
        DiiaDao diiaDao = DiiaDao.getInstance();
        String actualDiiaSign = diiaDao.getDiiaSign(diiaId);
        HashPasswordService hashPasswordService = new HashPasswordService();
        assert actualDiiaSign != null;
        return hashPasswordService.validatePassword(enteredDiiaSign, actualDiiaSign);
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(event);
        windowLoader.load(Objects.requireNonNull(getClass().getResource("../loginMenu/LoginScene.fxml")));
    }
}
