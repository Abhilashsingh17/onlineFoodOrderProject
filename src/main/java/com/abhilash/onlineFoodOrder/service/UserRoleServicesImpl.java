package com.abhilash.onlineFoodOrder.service;

import com.abhilash.onlineFoodOrder.entity.UserRole;
import com.abhilash.onlineFoodOrder.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserRoleServicesImpl implements UserRoleServices {

    public UserRoleRepository userRoleRepository;

    @Autowired
    UserRoleServicesImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRole saveUserRole(UserRole userRole) {

        return userRoleRepository.save(userRole);
    }

    // GET ROLE BY EMAIL ID
    @Override
    public UserRole findUserRoleByEmailId(String email) {
        // FIND BY EMAIL ID
        Optional<UserRole> byId = userRoleRepository.findById(email);
        if (byId.isPresent()) {
            UserRole userRole = byId.get();
            return userRole;
        }

        return null;
    }

    @Override
    public Boolean deleteUserRole(UserRole userRole) {

        if (userRole != null) {

            userRoleRepository.delete(userRole);

            return true;
        }

        return false;
    }

    @Override
    public List<UserRole> findAllUserByRole(String role) {
        return userRoleRepository.findUserRoleByRole(role);

    }

}
