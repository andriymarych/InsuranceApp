package com.marych.insuranceApp.dao.userDao.rolesDao.factory;

import com.marych.insuranceApp.dao.ConnectionPool;
import com.marych.insuranceApp.dao.Dao;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.user.personalData.RegistrationPersonalData;
import com.marych.insuranceApp.user.userRole.InsuranceSpecialist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class InsuranceSpecialistDao implements UserRoleDao,Dao<InsuranceSpecialist> {
    private static InsuranceSpecialistDao insuranceSpecialistDao;

    public static InsuranceSpecialistDao getInstance() {
        if (insuranceSpecialistDao == null) {
            insuranceSpecialistDao = new InsuranceSpecialistDao();
        }
        return insuranceSpecialistDao;
    }
    @Override
    public Optional<InsuranceSpecialist> get(int user_id) {
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT * FROM \"insurance_specialist\" WHERE user_id = ? ";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int insuranceCompanyId = resultSet.getInt("insurace_company_id");
                String email = resultSet.getString("email");
                InsuranceSpecialist insuranceSpecialist = new InsuranceSpecialist(user_id);
                insuranceSpecialist.setFirstName(firstName)
                        .setLastName(lastName)
                        .setCompanyId(insuranceCompanyId)
                        .setEmail(email);
                return Optional.of(insuranceSpecialist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean addUserRole(RegistrationPersonalData registrationPersonalData) {
        Connection connection;
        PreparedStatement statement;
        String query = "INSERT INTO \"insurance_specialist\"  VALUES ( ?, ?, ?, ?, ?)";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, registrationPersonalData.getUserId());
            statement.setString(2, registrationPersonalData.getDiiaCopy().getFirstName());
            statement.setString(3, registrationPersonalData.getDiiaCopy().getLastName());
            statement.setInt(4, Integer.parseInt(AppData.getInstance().get("InsuranceCompanyId")));
            statement.setString(5, registrationPersonalData.getEmail());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
