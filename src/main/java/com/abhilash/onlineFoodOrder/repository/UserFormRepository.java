package com.abhilash.onlineFoodOrder.repository;

import com.abhilash.onlineFoodOrder.entity.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFormRepository extends JpaRepository<UserForm, Integer> {
    public UserForm findByEmailId(String email);

}
