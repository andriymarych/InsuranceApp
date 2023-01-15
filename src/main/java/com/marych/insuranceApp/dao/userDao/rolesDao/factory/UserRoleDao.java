package com.marych.insuranceApp.dao.userDao.rolesDao.factory;

import com.marych.insuranceApp.service.diia.DiiaCopy;

public abstract class UserRoleDao {
    public abstract boolean addUserRole(int userId, DiiaCopy diiaCopy);
}
