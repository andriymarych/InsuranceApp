package com.marych.insuranceApp.dao.userDao;

import com.marych.insuranceApp.dao.ConnectionPool;
import com.marych.insuranceApp.dao.Dao;
import com.marych.insuranceApp.dao.userDao.rolesDao.UserDaoFactory;
import com.marych.insuranceApp.dao.userDao.rolesDao.UserRoleDao;
import com.marych.insuranceApp.service.diia.DiiaCopy;
import com.marych.insuranceApp.service.info.AppData;
import com.marych.insuranceApp.user.User;
import com.marych.insuranceApp.user.userSession.UserSession;
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

    public boolean checkLogin(String login) {
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT * FROM \"user\" WHERE login = ?";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String validateUser(String login) {
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT * FROM \"user\" WHERE login = ? ";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("id");
                UserSessionCreator.create(userId);
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser() {
        Connection connection;
        PreparedStatement statement;
        String query = "INSERT INTO \"user\"  VALUES ( ?, ?, ?, ?)";
        int userId = getNextUserId();
        int diiaId = Integer.parseInt(AppData.getInstance().get("diiaId"));
        DiiaCopy userDiiaCopy = new DiiaCopy(diiaId);
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, AppData.getInstance().get("login"));
            statement.setString(3, AppData.getInstance().get("password"));
            statement.setString(4, userDiiaCopy.getBirthDate());
            statement.executeUpdate();
            addUserRoleSpecificDao(userId,userDiiaCopy);
            UserSessionCreator.create(userId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void addUserRoleSpecificDao(int userId, DiiaCopy userDiiaCopy){
        String userRole = AppData.getInstance().get("userRole");
        UserRoleDao userRoleDao = UserDaoFactory.getUserDao(userRole);
        userRoleDao.addUserRole(userId,userDiiaCopy);
    }

}
