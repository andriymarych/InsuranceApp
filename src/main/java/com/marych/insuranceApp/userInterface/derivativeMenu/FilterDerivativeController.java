package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.document.policy.ObservableInsurancePolicy;
import com.marych.insuranceApp.service.WindowLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FilterDerivativeController implements Initializable {

    @FXML
    private TextField derivativeNoField;
    @FXML
    private TableView<ObservableInsurancePolicy> tableView;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> policyId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Boolean> compulsory;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> companyId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> insuredId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Integer> insurerId;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Double> insuredSum;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Double> insuredPayment;
    @FXML
    private TableColumn<ObservableInsurancePolicy, String> signDate;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Short> riskPercentage;
    @FXML
    private TableColumn<ObservableInsurancePolicy, Short> infoType;
    @FXML
    private TextField startSumField;
    @FXML
    private TextField endSumField;

    private ObservableList<ObservableInsurancePolicy> policyList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        policyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        compulsory.setCellValueFactory(new PropertyValueFactory<>("compulsory"));
        insuredId.setCellValueFactory(new PropertyValueFactory<>("holderId"));
        insurerId.setCellValueFactory(new PropertyValueFactory<>("insurerId"));
        companyId.setCellValueFactory(new PropertyValueFactory<>("companyId"));
        insuredSum.setCellValueFactory(new PropertyValueFactory<>("insuredSum"));
        insuredPayment.setCellValueFactory(new PropertyValueFactory<>("insuredPayment"));
        signDate.setCellValueFactory(new PropertyValueFactory<>("signDate"));
        riskPercentage.setCellValueFactory(new PropertyValueFactory<>("riskPercentage"));
        infoType.setCellValueFactory(new PropertyValueFactory<>("infoType"));

    }
    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event,Objects.requireNonNull(getClass().getResource("../derivativeMenu/ViewDerivativeMenuScene.fxml")));
    }
    @FXML
    private void filterButton(ActionEvent event) {
        policyList = DatabaseHandler.getInstance().
                getInsurancePolicyDataByPrice(derivativeNoField.getText(),startSumField.getText(),endSumField.getText());
        tableView.setItems(policyList);
    }

}
