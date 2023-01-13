package com.marych.insuranceApp.user.userRole;

public enum UserRole {
    CUSTOMER("Customer"),
    INSURANCE_SPECIALIST("InsuranceSpecialist");
    private final String userRoleStr;

    UserRole(String userRoleIndex) {
        this.userRoleStr = userRoleIndex;
    }

    public String getUserRoleStr() {
        return userRoleStr;
    }

    public static UserRole getUserRole(String userRoleStr){
        for(UserRole userRole : UserRole.values()){
            if(userRole.getUserRoleStr().equals(userRoleStr)){
                return userRole;
            }
        }
        return null;
    }
    public static String getUserRoleStr(UserRole userRole){
        return userRole.getUserRoleStr();
    }
}
