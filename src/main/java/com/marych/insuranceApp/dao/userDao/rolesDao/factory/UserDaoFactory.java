package com.marych.insuranceApp.dao.userDao.rolesDao.factory;


public class UserDaoFactory {
    public static UserRoleDao getUserDao(String userRole){
        return switch (userRole) {
            case "customer" -> new CustomerDao();
            case "insuranceSpecialist" -> new InsuranceSpecialistDao();
            default -> null;
        };
    }
}
