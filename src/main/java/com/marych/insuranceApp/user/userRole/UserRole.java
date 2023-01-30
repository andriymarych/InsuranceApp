package com.marych.insuranceApp.user.userRole;

public enum UserRole {
    CUSTOMER("Customer", 1),
    INSURANCE_SPECIALIST("InsuranceSpecialist", 2);
    private final String userRoleStr;
    private final int userRoleIndex;

    UserRole(String userRoleStr, int userRoleIndex) {
        this.userRoleStr = userRoleStr;
        this.userRoleIndex = userRoleIndex;
    }

    public String getUserRoleStr() {
        return userRoleStr;
    }

    public int getUserRoleIndex() {
        return userRoleIndex;
    }

    public static int getUserRoleIndex(String userRoleStr){
        for(UserRole userRole : UserRole.values()){
            if(userRole.getUserRoleStr().equals(userRoleStr)){
                return userRole.getUserRoleIndex();
            }
        }
        return 0;
    }

    public static UserRole getUserRole(String userRoleStr){
        for(UserRole userRole : UserRole.values()){
            if(userRole.getUserRoleStr().equals(userRoleStr)){
                return userRole;
            }
        }
        return null;
    }
}
