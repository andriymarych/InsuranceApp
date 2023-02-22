package com.marych.insuranceApp.dao.userDao;

import com.marych.insuranceApp.dao.ConnectionPool;
import com.marych.insuranceApp.dao.Dao;
import com.marych.insuranceApp.dao.userDao.rolesDao.UserRoleDaoAppender;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.personalData.RegistrationPersonalData;
import com.marych.insuranceApp.user.userRole.UserRole;
import com.marych.insuranceApp.user.userSession.UserSessionCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao implements Dao<User> {

    private static UserDao userDao;

    public static UserDao getInstance() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    public int getNextUserId() {
        Connection connection;
        String query = "SELECT MAX(id) FROM \"user\" ";
        try {
            connection = ConnectionPool.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:getNextUserId" + ex.getLocalizedMessage());
            return 0;
        }
    }

    public int getUserId(String login) {
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT id FROM \"user\" WHERE login = ? ";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:getUserId" + ex.getLocalizedMessage());
        }
        return 0;
    }

    public Optional<UserRole> getUserRole(String login) {
        UserRole userRole = null;
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT role_name " +
                "FROM user  " +
                "INNER JOIN user_role AS ur" +
                " ON user.user_role_id = ur.role_id  " +
                "WHERE login = ? ";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userRole = UserRole.getUserRole(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:getUserId" + ex.getLocalizedMessage());
        }
        return Optional.ofNullable(userRole);
    }

    public boolean addUser(RegistrationPersonalData registrationPersonalData) {
        UserRoleDaoAppender userRoleDaoAppender = new UserRoleDaoAppender();
        String query = "INSERT INTO \"user\"  VALUES ( ?, ?, ?, ?)";
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, registrationPersonalData.getUserId());
            statement.setString(2, registrationPersonalData.getUserCredential().getLogin());
            statement.setString(3, registrationPersonalData.getUserCredential().getPassword());
            statement.setInt(4, registrationPersonalData.getUserRole().getUserRoleIndex());
            statement.executeUpdate();
            userRoleDaoAppender.addUser(registrationPersonalData);
            UserSessionCreator.create(registrationPersonalData.getUserCredential().getLogin());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
