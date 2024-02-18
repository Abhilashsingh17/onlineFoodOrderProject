package com.abhilash.onlineSale.repository;

import com.abhilash.onlineSale.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    List<UserRole> findUserRoleByRole(String role);

}
