package com.abhilash.onlineFoodOrder.repository;

import com.abhilash.onlineFoodOrder.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    List<UserRole> findUserRoleByRole(String role);

}
