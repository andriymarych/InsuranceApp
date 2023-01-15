package com.marych.insuranceApp.dao.userDao;

import com.marych.insuranceApp.dao.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoValidator {

    public boolean isLoginExist(String login) {
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT * FROM \"user\" WHERE login = ?";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUserPassword(String login) {
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
