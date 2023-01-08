package com.marych.insuranceApp.dao.userDao.rolesDao;

import com.marych.insuranceApp.service.diia.DiiaCopy;

public abstract class UserRoleDao {
    public abstract boolean addUserRole(int userId, DiiaCopy diiaCopy);
}
