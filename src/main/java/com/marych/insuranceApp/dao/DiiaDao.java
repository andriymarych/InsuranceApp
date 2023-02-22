package com.marych.insuranceApp.dao;

import com.marych.insuranceApp.service.diia.DiiaCopy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DiiaDao implements Dao<DiiaCopy>{
    private static DiiaDao diiaDao;

    public static DiiaDao getInstance() {
        if (diiaDao == null) {
            diiaDao = new DiiaDao();
        }
        return diiaDao;
    }

    public Optional<DiiaCopy> getDiiaCopy(int diiaId){
        DiiaCopy diiaCopy = null;
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT * FROM \"diia\" WHERE id = ? ";
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, diiaId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                diiaCopy = DiiaCopy.newBuilder()
                        .setDiiaId(resultSet.getInt("id"))
                        .setFirstName(resultSet.getString("first_name"))
                        .setLastName(resultSet.getString("last_name"))
                        .setBirthDate(resultSet.getString("birth_date"))
                        .setITN(resultSet.getString("ITN"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(diiaCopy);
    }


    public String getDiiaSign(int diiaId){
        Connection connection;
        PreparedStatement statement;
        String query = "SELECT diia_sign FROM \"diia\" WHERE id = ? ";
        ResultSet resultSet;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, diiaId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("diia_sign");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<DiiaCopy> get(int id) {
        return Optional.empty();
    }
}
