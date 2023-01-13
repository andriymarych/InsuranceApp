package com.marych.insuranceApp.userInterface.derivativeMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.service.WindowLoader;
import com.marych.insuranceApp.service.info.CompanyInfoService;
import com.marych.insuranceApp.service.info.document.derivative.DerivativeInfo;
import com.marych.insuranceApp.service.info.document.policy.PolicyInfo;
import com.marych.insuranceApp.user.userSession.UserSession;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.document.policy.ObservableInsurancePolicy;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateDerivativeController implements Initializable {

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
    private TableColumn<ObservableInsurancePolicy, Short> infoType;
    private ObservableList<ObservableInsurancePolicy> policyList;
    @FXML
    private ChoiceBox<String> insuranceSpecialists;
    @FXML
    private TextField policyNoField;
    @FXML
    private Label errorLabel;
    private int derivativeCompanyId;
    private int derivativeInsurerId;
    private int derivativeId;
    String[] policyNoArray;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceSpecialists.getItems().addAll(CompanyInfoService.getInsuranceSpecialists(AppData.getInstance().get("insuranceCompany")));
        policyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        compulsory.setCellValueFactory(new PropertyValueFactory<>("compulsory"));
        insuredId.setCellValueFactory(new PropertyValueFactory<>("holderId"));
        insurerId.setCellValueFactory(new PropertyValueFactory<>("insurerId"));
        companyId.setCellValueFactory(new PropertyValueFactory<>("companyId"));
        insuredSum.setCellValueFactory(new PropertyValueFactory<>("insuredSum"));
        insuredPayment.setCellValueFactory(new PropertyValueFactory<>("insuredPayment"));
        signDate.setCellValueFactory(new PropertyValueFactory<>("signDate"));
        infoType.setCellValueFactory(new PropertyValueFactory<>("infoType"));
        policyList = DatabaseHandler.getInstance().getInsurancePolicyData(UserSession.getInstance().getUserId());
        tableView.setItems(policyList);
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("/com/marych/insuranceApp/userInterface/insuranceMenu/policyCreation/SelectCompanyScene.fxml")));
    }

    @FXML
    private void createDerivative(ActionEvent event) {
        if (checkPolicyList()) {
            derivativeId = DerivativeInfo.getInstance().getNextDerivativeId();
            addDerivative();
            addPolicyList();
            WindowLoader.load(event, Objects.requireNonNull(getClass().getResource("../derivativeMenu/SuccessDerivativeCreationScene.fxml")));
        }
    }

    private void addDerivative() {
        double price = selectPrice()*1.02;
        int userId = UserSession.getInstance().getUserId();
        String query = "INSERT INTO \"derivative\" " + " VALUES (" +
                derivativeId + ", " +
                userId + ", " +
                derivativeInsurerId + ", " +
                derivativeCompanyId + ", " +
                price + ", '" +
                LocalDate.now() + "') ";
        DatabaseHandler.getInstance().execUpdate(query);
    }

    private double selectPrice() {
        StringBuilder query = new StringBuilder("SELECT SUM(insured_payment) " +
                "FROM insurance_policy " +
                "WHERE policy_id IN (");
        for (String policyNo : policyNoArray) {
            query.append(policyNo).append(",");
        }
        query.deleteCharAt(query.length() - 1);
        query.append(")");
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(query.toString());
        try {
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addPolicyList() {
        StringBuilder SQL = new StringBuilder("INSERT INTO \"derivative_policy_list\" " + " VALUES ");
        for (String policyNo : policyNoArray) {
            SQL.append("(")
                    .append(derivativeId)
                    .append(", ")
                    .append(policyNo)
                    .append("),");
        }
        SQL.deleteCharAt(SQL.length() - 1);
        DatabaseHandler.getInstance().execUpdate(SQL.toString());
    }

    private boolean checkPolicyList() {
        int userId = UserSession.getInstance().getUserId();
        int policyNo;
        policyNoArray = policyNoField.getText().split(" ");
        for (String s : policyNoArray) {
            if(policyNoArray.length < 2){
                errorLabel.setText("Введіть мінімум 2 страхових договори для формування деривативу");
            }
            policyNo = Integer.parseInt(s);
            if (!PolicyInfo.getInstance().containsPolicy(userId, policyNo)) {
                errorLabel.setText("Страхового договору № " + policyNo + " не існує");
                return false;
            }
        }
        return true;
    }
}
