package com.marych.insuranceApp.dao.userDao.rolesDao;

import com.marych.insuranceApp.dao.userDao.rolesDao.factory.UserDaoFactory;
import com.marych.insuranceApp.dao.userDao.rolesDao.factory.UserRoleDao;
import com.marych.insuranceApp.service.diia.DiiaCopy;
import com.marych.insuranceApp.service.info.AppData;

public class UserRoleDaoAppender {
    public  void addUser(int userId, DiiaCopy userDiiaCopy){
        String userRole = AppData.getInstance().get("userRole");
        UserRoleDao userRoleDao = UserDaoFactory.getUserDao(userRole);
        userRoleDao.addUserRole(userId,userDiiaCopy);
    }
}
