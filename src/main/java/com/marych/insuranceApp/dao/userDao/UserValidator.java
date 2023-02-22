package com.marych.insuranceApp.dao.userDao;

import com.marych.insuranceApp.dao.ConnectionPool;
import com.marych.insuranceApp.service.HashPasswordService;

import java.sql.*;

public class UserValidator {

    public boolean isLoginExist(String login) {
        Connection connection;
        Statement statement;
        String query = "SELECT * FROM \"user\" WHERE login = '" + login + "'";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateUser(String login , String enteredPassword) {
        String actualPassword = getUserPassword(login);
        HashPasswordService hashPasswordService = new HashPasswordService();
        assert actualPassword != null;
        return hashPasswordService.validatePassword(enteredPassword,actualPassword);
    }
    private String getUserPassword(String login){
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT * FROM \"user\" WHERE login = ? ";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
