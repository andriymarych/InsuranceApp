package com.marych.insuranceApp.dao.userDao.rolesDao.factory;


public class UserDaoFactory {
    public static UserRoleDao getUserDao(String userRole){
        return switch (userRole) {
            case "Customer" -> new CustomerDao();
            case "InsuranceSpecialist" -> new InsuranceSpecialistDao();
            default -> null;
        };
    }
}
