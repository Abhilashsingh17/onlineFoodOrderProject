package com.abhilash.onlineSale.controller;

import com.abhilash.onlineSale.entity.UserForm;
import com.abhilash.onlineSale.service.UserFormServices;
import com.abhilash.onlineSale.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    private UserFormServices userFormServices;
    private UserServices userServices;


    @Autowired
    AdminController(UserFormServices userFormServices, UserServices userServices) {
        this.userFormServices = userFormServices;
        this.userServices = userServices;

    }

    // DISPLAY ALL THE USER
    @GetMapping("/admin/showAllCustomer")
    public String showAllUserPage(@RequestParam(value = "role", required = false) String role, Model model) {
        // FIND ALL THE USER AND PASS IT TO THE MODEL

        List<UserForm> listAllUser = userFormServices.findAllUser(role);

        model.addAttribute("userModel", listAllUser);

        return "/admin/showAllUser";
    }

    // UPDATE USER - FOR ADMIN
    @PutMapping("/admin/updateUserDetailsByAdmin")
    public String updateUserDetailsByAdmin(@RequestParam("id") Integer userId, Model model) {

        UserForm userFound = userFormServices.findByUserId(userId);
        if (userFound != null) {
            model.addAttribute("theNewUserFormModel", userFound);
        }

        return "/login/signUpPage";
    }

    // DELETE USER - FOR ADMIN
    @DeleteMapping("/admin/deleteUserByAdmin")
    public String deleteUserDetailsByAdmin(@RequestParam("id") Integer userId, Model model) {

        Boolean deletedUser = userFormServices.deleteUserFromDatabase(userId);
        if (deletedUser) {
            return "/notification/userDeleteConfirmation";
        }

        return "/notification/userNotFound";
    }


    //CHANGE ACTIVE STATUS OF USER TO MAKE THEM DE-ACTIVE
    @PutMapping("/admin/disableUser")
    public String disableUser(@RequestParam("emailId") String emailId, Model model) {

        Boolean activeStatus = userServices.disableUser(emailId);
        if (!activeStatus) {
            return "/notification/UserAlreadyDisabled";
        }

        return "/notification/disabledUser";
    }

    //CHANGE ACTIVE STATUS OF USER TO MAKE THEM ACTIVE
    @PutMapping("/admin/enableUser")
    public String enableUser(@RequestParam("emailId") String emailId, Model model) {

        Boolean activeStatus = userServices.enableUser(emailId);
        if (!activeStatus) {
            return "/notification/UserAlreadyEnabled";
        }

        return "/notification/enableUser";
    }

}
