package com.marych.insuranceApp.userInterface.insuranceMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.user.userSession.UserSession;
import com.marych.insuranceApp.document.policy.policyType.liability.ProfessionalActivityInsurancePolicy;
import com.marych.insuranceApp.document.policy.policyType.personal.LifeInsurancePolicy;
import com.marych.insuranceApp.document.policy.policyType.property.CarInsurancePolicy;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewPolicyDetailsController implements Initializable {
    @FXML
    private TableView<LifeInsurancePolicy> personalView;
    @FXML
    private TableColumn<LifeInsurancePolicy, Integer> personalPolicyId;
    @FXML
    private TableColumn<LifeInsurancePolicy, String> personalFirstName;
    @FXML
    private TableColumn<LifeInsurancePolicy, String> personalLastName;
    @FXML
    private TableColumn<LifeInsurancePolicy, String> personalAddress;
    @FXML
    private TableColumn<LifeInsurancePolicy, String> personalBirthDate;


    @FXML
    private TableView<ProfessionalActivityInsurancePolicy> liabilityView;
    @FXML
    private TableColumn<ProfessionalActivityInsurancePolicy, Integer> liabilityPolicyId;
    @FXML
    private TableColumn<ProfessionalActivityInsurancePolicy, String> liabilityFirstName;
    @FXML
    private TableColumn<ProfessionalActivityInsurancePolicy, String> liabilityLastName;
    @FXML
    private TableColumn<ProfessionalActivityInsurancePolicy, String> liabilityCompanyName;
    @FXML
    private TableColumn<ProfessionalActivityInsurancePolicy, String> liabilityProfessionalActivity;
    @FXML
    private TableColumn<ProfessionalActivityInsurancePolicy, String> liabilityPosition;


    @FXML
    private TableView<CarInsurancePolicy> propertyView;
    @FXML
    private TableColumn<CarInsurancePolicy, Integer> propertyPolicyId;
    @FXML
    private TableColumn<CarInsurancePolicy, Integer> propertyFirstName;
    @FXML
    private TableColumn<CarInsurancePolicy, String> propertyLastName;
    @FXML
    private TableColumn<CarInsurancePolicy, String> propertyCarBrand;
    @FXML
    private TableColumn<CarInsurancePolicy, String> propertyCarModel;
    @FXML
    private TableColumn<CarInsurancePolicy, String> propertyLicensePlate;

    private ObservableList<LifeInsurancePolicy> personalList;
    private ObservableList<ProfessionalActivityInsurancePolicy> liabilityList;
    private ObservableList<CarInsurancePolicy> propertyList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        personalFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        personalLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        personalAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        personalBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        personalList = DatabaseHandler.getInstance().getPersonalPolicyData(UserSession.getInstance().getUserId());
        personalView.setItems(personalList);

        liabilityPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        liabilityFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        liabilityLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        liabilityCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        liabilityProfessionalActivity.setCellValueFactory(new PropertyValueFactory<>("professionalActivity"));
        liabilityPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        liabilityList = DatabaseHandler.getInstance().getLiabilityPolicyData(UserSession.getInstance().getUserId());
        liabilityView.setItems(liabilityList);

        propertyPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        propertyFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        propertyLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        propertyCarBrand.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        propertyCarModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        propertyLicensePlate.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        propertyList = DatabaseHandler.getInstance().getPropertyPolicyData(UserSession.getInstance().getUserId());
        propertyView.setItems(propertyList);
    }
    @FXML
    private void returnButton(ActionEvent event) {
        loadWindow(event, "../insuranceMenu/ViewPolicyScene.fxml");
    }
    private void loadWindow(ActionEvent event, String name) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(name)));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
