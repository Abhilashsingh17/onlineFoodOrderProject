package com.abhilash.onlineSale.repository;

import com.abhilash.onlineSale.entity.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFormRepository extends JpaRepository<UserForm, Integer> {
    public UserForm findByEmailId(String email);

}
