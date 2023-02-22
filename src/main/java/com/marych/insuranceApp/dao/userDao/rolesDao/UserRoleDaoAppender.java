package com.marych.insuranceApp.dao.userDao.rolesDao;

import com.marych.insuranceApp.dao.userDao.rolesDao.factory.UserDaoFactory;
import com.marych.insuranceApp.dao.userDao.rolesDao.factory.UserRoleDao;
import com.marych.insuranceApp.user.personalData.RegistrationPersonalData;

public class UserRoleDaoAppender {
    public  void addUser(RegistrationPersonalData registrationPersonalData){
        String userRole = registrationPersonalData.getUserRole().getUserRoleStr();
        UserRoleDao userRoleDao = UserDaoFactory.getUserDao(userRole);
        userRoleDao.addUserRole(registrationPersonalData);
    }
}
