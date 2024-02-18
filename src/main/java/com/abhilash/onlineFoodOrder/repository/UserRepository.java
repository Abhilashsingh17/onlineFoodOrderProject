package com.abhilash.onlineFoodOrder.repository;

import com.abhilash.onlineFoodOrder.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmailId(String emailId);


}
