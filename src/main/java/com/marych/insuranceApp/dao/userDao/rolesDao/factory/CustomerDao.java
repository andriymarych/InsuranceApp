package com.marych.insuranceApp.dao.userDao.rolesDao.factory;

import com.marych.insuranceApp.dao.ConnectionPool;
import com.marych.insuranceApp.dao.Dao;
import com.marych.insuranceApp.service.diia.DiiaCopy;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.user.userRole.Customer;
import com.marych.insuranceApp.user.userRole.UserRole;

import java.sql.*;
import java.util.Optional;

public class CustomerDao implements UserRoleDao,Dao<Customer> {
    private static CustomerDao customerDao;

    public static CustomerDao getInstance() {
        if (customerDao == null) {
            customerDao = new CustomerDao();
        }
        return customerDao;
    }

    @Override
    public Optional<Customer> get(int user_id) {
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
                Customer customer = new Customer(user_id);
                customer.setUserRole(UserRole.CUSTOMER)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setBirthDate(birthDate)
                        .setITN(ITN)
                        .setEmail(email);
                return Optional.of(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean addUserRole(int userId, DiiaCopy diiaCopy) {
        Connection connection;
        PreparedStatement statement;
        String query = "INSERT INTO \"customer\" VALUES ( ?, ?, ?, ?, ?, ?)";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, diiaCopy.getFirstName());
            statement.setString(3, diiaCopy.getLastName());
            statement.setDate(4, Date.valueOf(diiaCopy.getBirthDate()));
            statement.setString(5, diiaCopy.getITN());
            statement.setString(6, AppData.getInstance().get("email"));
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
