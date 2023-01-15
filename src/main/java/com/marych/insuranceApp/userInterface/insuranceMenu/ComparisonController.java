package com.marych.insuranceApp.userInterface.insuranceMenu;

import com.marych.insuranceApp.dao.DatabaseHandler;
import com.marych.insuranceApp.service.loader.WindowLoader;
import com.marych.insuranceApp.user.userSession.UserSession;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.document.policy.policyType.liability.ProfessionalActivityInsurancePolicy;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ComparisonController implements Initializable {
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

    private ObservableList<ProfessionalActivityInsurancePolicy> liabilityList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        liabilityPolicyId.setCellValueFactory(new PropertyValueFactory<>("policyId"));
        liabilityFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        liabilityLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        liabilityCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        liabilityProfessionalActivity.setCellValueFactory(new PropertyValueFactory<>("professionalActivity"));
        liabilityPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        liabilityList = DatabaseHandler.getInstance().getLiabilityPolicyList(UserSession.getInstance().getUserId(), AppData.getInstance().get("PolicyComparisonNo"));
        liabilityView.setItems(liabilityList);
    }

    @FXML
    private void returnButton(ActionEvent event) {
        WindowLoader windowLoader = new WindowLoader(event);
        windowLoader.load(Objects.requireNonNull(getClass().getResource("../insuranceMenu/ViewPolicyScene.fxml")));
    }

}
