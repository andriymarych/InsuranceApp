package com.marych.insuranceApp.userInterface.insuranceMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.user.UserSession;
import com.marych.insuranceApp.document.policy.policyType.liability.ProfessionalActivityInsurance;
import com.marych.insuranceApp.document.policy.policyType.personal.PersonalInsurance;
import com.marych.insuranceApp.document.policy.policyType.property.PropertyInsurance;
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
    private TableView<PersonalInsurance> personalView;
    @FXML
    private TableColumn<PersonalInsurance, Integer> personalPolicyId;
    @FXML
    private TableColumn<PersonalInsurance, String> personalFirstName;
    @FXML
    private TableColumn<PersonalInsurance, String> personalLastName;
    @FXML
    private TableColumn<PersonalInsurance, String> personalAddress;
    @FXML
    private TableColumn<PersonalInsurance, String> personalBirthDate;


    @FXML
    private TableView<ProfessionalActivityInsurance> liabilityView;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, Integer> liabilityPolicyId;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityFirstName;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityLastName;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityCompanyName;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityProfessionalActivity;
    @FXML
    private TableColumn<ProfessionalActivityInsurance, String> liabilityPosition;


    @FXML
    private TableView<PropertyInsurance> propertyView;
    @FXML
    private TableColumn<PropertyInsurance, Integer> propertyPolicyId;
    @FXML
    private TableColumn<PropertyInsurance, Integer> propertyFirstName;
    @FXML
    private TableColumn<PropertyInsurance, String> propertyLastName;
    @FXML
    private TableColumn<PropertyInsurance, String> propertyCarBrand;
    @FXML
    private TableColumn<PropertyInsurance, String> propertyCarModel;
    @FXML
    private TableColumn<PropertyInsurance, String> propertyLicensePlate;

    private ObservableList<PersonalInsurance> personalList;
    private ObservableList<ProfessionalActivityInsurance> liabilityList;
    private ObservableList<PropertyInsurance> propertyList;

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
