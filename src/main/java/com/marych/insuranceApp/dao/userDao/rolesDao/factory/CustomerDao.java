package com.marych.insuranceApp.dao.userDao.rolesDao.factory;

import com.marych.insuranceApp.dao.ConnectionPool;
import com.marych.insuranceApp.dao.Dao;
import com.marych.insuranceApp.user.personalData.RegistrationPersonalData;
import com.marych.insuranceApp.user.userRole.Customer;
import com.marych.insuranceApp.user.userRole.UserRole;

import java.sql.*;
import java.util.Optional;

public class CustomerDao implements UserRoleDao, Dao<Customer> {
    private static CustomerDao customerDao;

    public static CustomerDao getInstance() {
        if (customerDao == null) {
            customerDao = new CustomerDao();
        }
        return customerDao;
    }

    @Override
    public Optional<Customer> get(int user_id) {
        Customer customer = null;
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT * FROM \"customer\" WHERE user_id = ? ";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String birthDate = resultSet.getString("birth_date");
                String ITN = resultSet.getString("ITN");
                String email = resultSet.getString("email");
                customer = new Customer(user_id);
                customer.setUserRole(UserRole.CUSTOMER)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setBirthDate(birthDate)
                        .setITN(ITN)
                        .setEmail(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(customer);
    }

    public boolean addUserRole(RegistrationPersonalData registrationPersonalData) {
        Connection connection;
        PreparedStatement statement;
        String query = "INSERT INTO \"customer\" VALUES ( ?, ?, ?, ?, ?, ?)";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, registrationPersonalData.getUserId());
            statement.setString(2, registrationPersonalData.getDiiaCopy().getFirstName());
            statement.setString(3, registrationPersonalData.getDiiaCopy().getLastName());
            statement.setDate(4, Date.valueOf(registrationPersonalData.getDiiaCopy().getBirthDate()));
            statement.setString(5,  registrationPersonalData.getDiiaCopy().getITN());
            statement.setString(6, registrationPersonalData.getEmail());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
