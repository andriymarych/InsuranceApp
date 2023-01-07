package com.marych.insuranceApp.dao.documentDao.derivative;

import com.marych.insuranceApp.dao.Dao;
import com.marych.insuranceApp.dao.ConnectionPool;
import com.marych.insuranceApp.document.derivative.Derivative;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DerivativeDao implements Dao <Derivative> {
    @Override
    public Optional<Derivative> get(int derivativeId) {
        Connection connection ;
        PreparedStatement statement ;
        ResultSet resultSet ;
        String SqlStatement = "SELECT * FROM \"derivative\" WHERE derivative_id = ? ORDER BY derivative_id";
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(SqlStatement);
            statement.setString(1, String.valueOf(derivativeId));
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Derivative derivative = new Derivative(
                        resultSet.getInt("derivative_id"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                derivative.setPrice(resultSet.getDouble("price"))
                        .setSignDate(resultSet.getDate("sign_date").toString());
                return Optional.of(derivative);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Derivative> getAll(int holderId) {
        Connection connection ;
        PreparedStatement statement ;
        ResultSet resultSet ;
        String SqlStatement = "SELECT * FROM \"derivative\" WHERE holder_id = ? ORDER BY derivative_id";
        ArrayList<Derivative> derivativeList = new ArrayList<>();
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(SqlStatement);
            statement.setInt(1, holderId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Derivative derivative = new Derivative(
                        resultSet.getInt("derivative_id"),
                        resultSet.getInt("holder_id"),
                        resultSet.getInt("insurer_id"),
                        resultSet.getInt("company_id")
                );
                derivative.setPrice(resultSet.getDouble("price"))
                        .setSignDate(resultSet.getDate("sign_date").toString());
                derivativeList.add(derivative);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return derivativeList;
    }
}
