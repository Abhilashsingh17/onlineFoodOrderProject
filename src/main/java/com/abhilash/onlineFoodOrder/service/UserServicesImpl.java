package com.abhilash.onlineFoodOrder.service;

import com.abhilash.onlineFoodOrder.entity.User;
import com.abhilash.onlineFoodOrder.entity.UserForm;
import com.abhilash.onlineFoodOrder.entity.UserRole;
import com.abhilash.onlineFoodOrder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServicesImpl implements UserServices {

    private UserRepository userRepository;
    private UserFormServices userFormServices;
    private PasswordEncoder passwordEncoder;
    private UserRoleServices userRoleServices;

    @Lazy
    @Autowired
    UserServicesImpl(UserRepository userRepository, UserFormServices userFormServices, PasswordEncoder passwordEncoder, UserRoleServices userRoleServices) {
        this.userRepository = userRepository;
        this.userFormServices = userFormServices;
        this.passwordEncoder = passwordEncoder;
        this.userRoleServices = userRoleServices;
    }

    @Override
    public User saveUserDetails(User user) {
        return userRepository.save(user);
    }
    // @Override
    // public User findUserByEmailId(String userName,Long id) {
    //
    // return userRepository.findByEmailId(userName);
    // }

    @Override
    public User findUserByEmailIds(String emailId) {
        Optional<User> byId = userRepository.findById(emailId);
        if (byId.isPresent()) {
            User user = byId.get();
            return user;
        }
        return null;
    }

    // FOR FORGET PASSWORD
    @Override
    public Boolean updateThePassword(String emailId, String favoriteTeam, String newPassword) {
        // FIND USERFORM AND USER BY EMAIL ID
        UserForm userForm = userFormServices.findByEmailId(emailId);

        // FIND USER ROLE
        UserRole role = userRoleServices.findUserRoleByEmailId(emailId);

        // FIND THE USER
        User user = findUserByEmailIds(emailId);

        // CHECK OF THE SECURITY QUESTION ANSWER IS CORRECT OR NOT
        if (favoriteTeam.toLowerCase().equals(userForm.getSecurityQuestion())) {
            // IF IT IS CORRECT , UPDATE THE PASSWORD IN USER, USERFORM
            userForm.setPassword(passwordEncoder.encode(newPassword));
            user.setPassword(passwordEncoder.encode(newPassword));

            userFormServices.saveUserFormForUpdatingPassword(userForm);
            saveUserDetails(user);

            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteUser(User user) {
        if (user != null) {

            userRepository.delete(user);
            return true;
        }
        return false;
    }


    @Override
    public Boolean disableUser(String emailId) {
        User user = userRepository.findByEmailId(emailId);

        if (user != null) {
            if (user.getActiveStatus() == 1) {
                user.setActiveStatus(0);
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean enableUser(String emailId) {
        User user = userRepository.findByEmailId(emailId);

        if (user != null) {
            if (user.getActiveStatus() == 0) {
                user.setActiveStatus(1);
                return true;
            }
        }
        return false;
    }
}
