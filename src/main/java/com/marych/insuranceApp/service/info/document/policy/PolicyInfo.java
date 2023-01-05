package com.marych.insuranceApp.service.info.document.policy;

import com.marych.insuranceApp.dao.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PolicyInfo {
    private static PolicyInfo policyInfo;

    public static PolicyInfo getInstance() {
        if (policyInfo == null) {
            policyInfo = new PolicyInfo();
        }
        return policyInfo;
    }

    public int getNextPolicyId() {
        try {
            String SQL = "SELECT MAX(policy_id) FROM \"insurance_policy\" ";
            ResultSet resultSet = DatabaseHandler.getInstance().execQuery(SQL);
            resultSet.next();
            return resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean containsPolicy(int userId, int policyNo) {
        String SQL = "SELECT * FROM \"insurance_policy\" WHERE policy_id = '" + policyNo + "'" +
                "AND holder_id = " + userId;
        ResultSet resultSet = DatabaseHandler.getInstance().execQuery(SQL);
        try {
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
