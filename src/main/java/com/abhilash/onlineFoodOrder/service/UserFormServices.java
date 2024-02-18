package com.abhilash.onlineFoodOrder.service;

import com.abhilash.onlineFoodOrder.entity.UserForm;

import java.util.List;

public interface UserFormServices {

    UserForm findByUserId(Integer userId);

    List<UserForm> findAllUser(String role);

    public UserForm saveUserForm(UserForm userForm, String forEmployeeRole);

    UserForm saveUserFormForUpdatingPassword(UserForm userForm);

    public UserForm findByEmailId(String email);

    UserForm updateUserForm(UserForm newUserForm, String forEmployeeRole);

    Boolean deleteUserFromDatabase(Integer userId);
}
