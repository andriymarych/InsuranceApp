package com.marych.insuranceApp.dao.userDao.rolesDao.factory;

import com.marych.insuranceApp.service.diia.DiiaCopy;

public interface UserRoleDao {

    boolean addUserRole(int userId, DiiaCopy diiaCopy);
}
