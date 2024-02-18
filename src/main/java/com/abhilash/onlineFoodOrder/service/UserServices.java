package com.abhilash.onlineFoodOrder.service;

import com.abhilash.onlineFoodOrder.entity.User;

public interface UserServices {

    public User findUserByEmailIds(String emailId);

    public User saveUserDetails(User user);

    Boolean updateThePassword(String emailId, String favoriteTeam, String newPassword);

    public Boolean deleteUser(User user);

    Boolean disableUser(String emailId);

    Boolean enableUser(String emailId);

//    public User findUserByEmailId(String userName, Long id);

}
