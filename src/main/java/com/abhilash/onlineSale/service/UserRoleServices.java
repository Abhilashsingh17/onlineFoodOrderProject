package com.abhilash.onlineSale.service;

import com.abhilash.onlineSale.entity.UserRole;

import java.util.List;

public interface UserRoleServices {
    public UserRole saveUserRole(UserRole userRole);

    UserRole findUserRoleByEmailId(String email);

    public Boolean deleteUserRole(UserRole userRole);

    List<UserRole> findAllUserByRole(String role);
}
