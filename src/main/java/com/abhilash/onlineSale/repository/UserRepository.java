package com.abhilash.onlineSale.repository;

import com.abhilash.onlineSale.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmailId(String emailId);


}
