package com.marych.insuranceApp.dao;

import com.marych.insuranceApp.document.policy.ObservableInsurancePolicy;
import com.marych.insuranceApp.document.policy.PolicyNode;
import com.marych.insuranceApp.document.policy.policyType.liability.ProfessionalActivityInsurancePolicy;
import com.marych.insuranceApp.document.policy.policyType.personal.LifeInsurancePolicy;
import com.marych.insuranceApp.document.policy.policyType.property.CarInsurancePolicy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DatabaseHandler {
    private static DatabaseHandler handler;
    private static Connection connection = null;
    private static Statement stmt = null;

    static {
        createConnection();
    }

    private static void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            InputStream input = new FileInputStream("src/main/resources/dbconfig.properties");
            Properties properties = new Properties();
            properties.load(input);
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = connection.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        }
        return result;
    }

    public boolean execUpdate(String qu) {
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(qu);
            return true;
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        }
    }

    public ObservableList<ObservableInsurancePolicy> getInsurancePolicyData(int userId) {
        ArrayList<ObservableInsurancePolicy> arrayList = new ArrayList<>();
        ObservableList<ObservableInsurancePolicy> observableInsurancePolicyList = FXCollections.observableArrayList();
        observableInsurancePolicyList.addAll(arrayList);
        String SQL = "SELECT * FROM \"insurance_policy\" WHERE holder_id = " + userId + "ORDER BY policy_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                ObservableInsurancePolicy observableInsurancePolicy = new ObservableInsurancePolicy(
                        resultSet.getInt("policy_id"),
                        resultSet.getBoolean("compulsory"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                observableInsurancePolicy.setInsuredSum(resultSet.getDouble("insured_sum"))
                        .setInsuredPayment(resultSet.getDouble("insured_payment"))
                        .setSignDate(resultSet.getDate("sign_date").toString())
                        .setRiskPercentage(resultSet.getShort("risk_percentage"))
                        .setInfoType(resultSet.getShort("info_type"));
                observableInsurancePolicyList.add(observableInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableInsurancePolicyList;
    }
    public ObservableList<ObservableInsurancePolicy> getInsurancePolicyDataByPrice(String derivativeId, String startSum, String endSum) {
        ObservableList<ObservableInsurancePolicy> observableInsurancePolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT  insurance_policy.policy_id, compulsory,holder_id,insurer_id," +
                "company_id,insured_sum,insured_payment,sign_date,risk_percentage,info_type " +
                "FROM insurance_policy " +
                "INNER JOIN derivative_policy_list " +
                "ON insurance_policy.policy_id = derivative_policy_list.policy_id " +
                "WHERE derivative_id = " + derivativeId +
                " AND  insured_sum BETWEEN " + startSum + " AND " + endSum +
                " ORDER BY insurance_policy.policy_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                ObservableInsurancePolicy observableInsurancePolicy = new ObservableInsurancePolicy(
                        resultSet.getInt("policy_id"),
                        resultSet.getBoolean("compulsory"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                observableInsurancePolicy.setInsuredSum(resultSet.getDouble("insured_sum"))
                        .setInsuredPayment(resultSet.getDouble("insured_payment"))
                        .setSignDate(resultSet.getDate("sign_date").toString())
                        .setRiskPercentage(resultSet.getShort("risk_percentage"))
                        .setInfoType(resultSet.getShort("info_type"));
                observableInsurancePolicyList.add(observableInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableInsurancePolicyList;
    }

    public ObservableList<PolicyNode> getDerivativePolicyList(String derivativeId) {
        ObservableList<PolicyNode> policyNodeList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM \"derivative_policy_list\" " +
                "WHERE derivative_id = " + derivativeId +
                " ORDER BY policy_id";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                PolicyNode policyNode = new PolicyNode(
                        resultSet.getInt("derivative_id"),
                        resultSet.getInt("policy_id")
                );
                policyNodeList.add(policyNode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return policyNodeList;
    }

    public ObservableList<LifeInsurancePolicy> getPersonalPolicyData(int userId) {
        ObservableList<LifeInsurancePolicy> lifeInsurancePolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN personal_info " +
                "ON insurance_policy.policy_id = personal_info.policy_id " +
                "WHERE holder_id = " + userId +
                " AND info_type = 1 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                LifeInsurancePolicy lifeInsurancePolicy = new LifeInsurancePolicy(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"))
                        .setAddress(resultSet.getString("address"))
                        .setBirthDate(resultSet.getString("birth_date"));
                lifeInsurancePolicyList.add(lifeInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lifeInsurancePolicyList;
    }
    public ObservableList<ProfessionalActivityInsurancePolicy> getLiabilityPolicyData(int userId){
        ObservableList<ProfessionalActivityInsurancePolicy> professionalActivityInsurancePolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN liability_info " +
                "ON insurance_policy.policy_id = liability_info.policy_id " +
                "WHERE holder_id = " + userId +
                "AND info_type = 2 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                ProfessionalActivityInsurancePolicy professionalActivityInsurancePolicy = new ProfessionalActivityInsurancePolicy(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"),
                        resultSet.getString("insured_company_name"))
                        .setProfessionalActivity(resultSet.getString("professional_activity"))
                        .setPosition(resultSet.getString("position"));
                professionalActivityInsurancePolicyList.add(professionalActivityInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professionalActivityInsurancePolicyList;
    }
    public ObservableList<ProfessionalActivityInsurancePolicy> getLiabilityPolicyList(int userId, String policyNoList){
        ObservableList<ProfessionalActivityInsurancePolicy> professionalActivityInsurancePolicyList = FXCollections.observableArrayList();
        StringBuilder policyNoBuilder = new StringBuilder();
        policyNoBuilder.append("(");
        for (String policyNo : policyNoList.split(" ")) {
            policyNoBuilder.append(policyNo)
                    .append(", ");
        }
        policyNoBuilder.deleteCharAt(policyNoBuilder.length() - 1);
        policyNoBuilder.deleteCharAt(policyNoBuilder.length() - 1);
        policyNoBuilder.append(")");
        String SQL = "SELECT * " +
                "FROM insurance_policy " +
                "INNER JOIN liability_info " +
                "ON insurance_policy.policy_id = liability_info.policy_id " +
                "WHERE holder_id = " + userId +
                " AND info_type = 2 " +
                "AND liability_info.policy_id IN " + policyNoBuilder;
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                ProfessionalActivityInsurancePolicy professionalActivityInsurancePolicy = new ProfessionalActivityInsurancePolicy(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"),
                        resultSet.getString("insured_company_name"))
                        .setProfessionalActivity(resultSet.getString("professional_activity"))
                        .setPosition(resultSet.getString("position"));
                professionalActivityInsurancePolicyList.add(professionalActivityInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professionalActivityInsurancePolicyList;
    }
    public ObservableList<CarInsurancePolicy> getPropertyPolicyData(int userId){
        ObservableList<CarInsurancePolicy> carInsurancePolicyList = FXCollections.observableArrayList();
        String SQL = "SELECT  * " +
                "FROM insurance_policy " +
                "INNER JOIN car_property_info " +
                "ON insurance_policy.policy_id = car_property_info.policy_id " +
                "WHERE holder_id = " + userId +
                "AND info_type = 3 ";
        ResultSet resultSet = execQuery(SQL);
        try {
            while (resultSet.next()) {
                CarInsurancePolicy carInsurancePolicy = new CarInsurancePolicy(
                        resultSet.getInt(1),
                        resultSet.getString("insured_first_name"),
                        resultSet.getString("insured_last_name"))
                        .setCarBrand(resultSet.getString("car_brand"))
                        .setCarModel(resultSet.getString("car_model"))
                        .setLicensePlate(resultSet.getString("license_plate"));
                carInsurancePolicyList.add(carInsurancePolicy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carInsurancePolicyList;
    }

}

