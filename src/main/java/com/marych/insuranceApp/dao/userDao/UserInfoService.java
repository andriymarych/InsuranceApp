package com.marych.insuranceApp.dao.userDao;

import com.marych.insuranceApp.dao.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoService {
    public int getNextUserId() {
        try {
            String SQL = "SELECT MAX(id) FROM \"user\" ";
            ResultSet resultSet = DatabaseHandler.getInstance().execQuery(SQL);
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return 0;
        }
    }
    public boolean checkLogin(String login) {
        String SQL = "SELECT * FROM \"user\" WHERE login = '" + login + "'";
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(SQL);

        try {

            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
