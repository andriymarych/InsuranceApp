package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.dao.documentDao.derivative.DerivativeDao;
import com.marych.insuranceApp.service.WindowLoader;
import com.marych.insuranceApp.user.userSession.UserSession;
import com.marych.insuranceApp.document.derivative.Derivative;
import com.marych.insuranceApp.document.policy.PolicyNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewDerivativeController implements Initializable {
    @FXML
    private TableView<Derivative> tableView;
    @FXML
    private TableView<PolicyNode> policyListView;
    @FXML
    private TableColumn<Derivative, Integer> derivativeId;
    @FXML
    private TableColumn<Derivative, Integer> companyId;
    @FXML
    private TableColumn<Derivative, Integer> insuredId;
    @FXML
    private TableColumn<Derivative, Integer> insurerId;
    @FXML
    private TableColumn<Derivative, Double> derivativePrice;
    @FXML
    private TableColumn<Derivative, String> signDate;

    @FXML
    private TableColumn<PolicyNode, Integer> policyViewId;
    @FXML
    private TextField derivativeNoField;

    private ObservableList<Derivative> derivativeObservableListList  = FXCollections.observableArrayList();
    private ObservableList<PolicyNode> policyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DerivativeDao derivativeDao = new DerivativeDao();
        policyViewId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        derivativeId.setCellValueFactory(new PropertyValueFactory<>("derivativeId"));
        insuredId.setCellValueFactory(new PropertyValueFactory<>("holderId"));
        insurerId.setCellValueFactory(new PropertyValueFactory<>("insurerId"));
        companyId.setCellValueFactory(new PropertyValueFactory<>("companyId"));
        derivativePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        signDate.setCellValueFactory(new PropertyValueFactory<>("signDate"));
        ArrayList<Derivative> derivativeList = (ArrayList<Derivative>) derivativeDao.getAll(UserSession.getInstance().getUserId());
        derivativeObservableListList.addAll(derivativeList);
        tableView.setItems(derivativeObservableListList);
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event,Objects.requireNonNull(getClass().getResource("../derivativeMenu/ViewDerivativeMenuScene.fxml")));
    }

    @FXML
    private void viewPolicyList(ActionEvent event) {
        policyList = DatabaseHandler.getInstance().getDerivativePolicyList(derivativeNoField.getText());
        policyListView.setItems(policyList);
    }
}
