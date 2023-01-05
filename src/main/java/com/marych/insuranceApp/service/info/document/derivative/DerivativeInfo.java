package com.marych.insuranceApp.service.info.document.derivative;

import com.marych.insuranceApp.dao.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DerivativeInfo {

    private static DerivativeInfo derivativeInfo;

    public static DerivativeInfo getInstance() {
        if (derivativeInfo == null) {
            derivativeInfo = new DerivativeInfo();
        }
        return derivativeInfo;
    }
    public int getNextDerivativeId() {
        try {
            String SQL = "SELECT MAX(derivative_id) FROM \"derivative\" ";
            ResultSet resultSet = DatabaseHandler.getInstance().execQuery(SQL);
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
