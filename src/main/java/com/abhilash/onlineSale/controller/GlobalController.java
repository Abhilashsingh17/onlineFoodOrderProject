package com.abhilash.onlineSale.controller;


import com.abhilash.onlineSale.entity.UserForm;
import com.abhilash.onlineSale.service.UserFormServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalController {

    private UserFormServices userFormServices;


    @Autowired
    GlobalController(UserFormServices userFormServices) {
        this.userFormServices = userFormServices;

    }

    // this method define a current logged in user object which is available to all the controller.
    @ModelAttribute("currentUser")
    public UserForm getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserForm userForm = userFormServices.findByEmailId(email);
        if (userForm == null) {
            // Handle the case where the user is not found
            // For example, you can return a default UserForm object or throw an exception
            return new UserForm(); // Return a default UserForm object
        }
        return userForm;
    }

}
