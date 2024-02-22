package com.abhilash.onlineFoodOrder.controller;

import com.abhilash.onlineFoodOrder.entity.UserForm;
import com.abhilash.onlineFoodOrder.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private UserServices userServices;
    private UserFormServices userFormServices;
    private CartServices cartServices;

    @Autowired
    LoginController(CartServices cartServices, ProductService productService, UserServices userServices,
            PasswordEncoder passwordEncoder, UserRoleServices userRoleServices, UserFormServices userFormServices) {

        this.userServices = userServices;
        this.userFormServices = userFormServices;
        this.cartServices = cartServices;
    }

    // DISPLAY HOME PAGE
    @GetMapping("/")
    public String homePage(@ModelAttribute("currentUser") UserForm userForm, Model model) {

        // GET QTY IN CART , FOR NAVBAR CART ICON
        Long totalQtyInCart = cartServices.getTotalQtyByUserId(userForm.getUserId());
        model.addAttribute("totalQtyInCart", totalQtyInCart);

        return "home/homePage";
    }

    /**
     * DISPLAYING THE LOGIN PAGE
     */
    @GetMapping("/public/loginPage")
    public String loginPage(Model model) {

        return "login/loginPage";
    }

    // DISPLAY THE REGISTRATION PAGE
    @GetMapping("/public/registration")
    public String registrationPage(@RequestParam(value = "userId", required = false) Integer userId, Model model) {

        if (userId == null) {

            model.addAttribute("theNewUserFormModel", new UserForm());
            return "login/signUpPage";
        }

        UserForm userToUpdate = userFormServices.findByUserId(userId);

        model.addAttribute("theNewUserFormModel", userToUpdate);

        return "login/signUpPage";
    }

    // SIGNUP PROCESS
    @PostMapping("/public/signUp")
    public String signUp(@Valid @ModelAttribute("theNewUserFormModel") UserForm newUserForm,
            BindingResult bindingUserFormResult,
            @RequestParam("forEmployeeRole") String forEmployeeRole, Model model

    ) {
        /*
         * AFTER DECLARING @MODELATTRIBUTE() USERFORM NEWUSERFORM, WE MUST DECLARE
         * BINDINGRESULT.
         * AND CHECK IF BINDING RESULT HAS ANY ERROR OR NOT
         */

        if (bindingUserFormResult.hasErrors()) {
            return "login/signUpPage";
        }

        if (newUserForm.getUserId() != null) {
            UserForm userFormUpdated = userFormServices.updateUserForm(newUserForm, forEmployeeRole);

            if (userFormUpdated != null) {
                model.addAttribute("userRegInDatabaseModel", userFormUpdated);
                return "notification/userUpdateConfirmation";
            }
            return "notification/updateEmailNotAllowed";
        } else {

            // CREATE NEW USER IN USER, USER FORM AND USER ROLE
            UserForm userFormReg = userFormServices.saveUserForm(newUserForm, forEmployeeRole);

            if (userFormReg != null) {
                model.addAttribute("userRegInDatabaseModel", userFormReg);
                return "notification/signUpConfirm";
            }
        }

        model.addAttribute("theNewUserFormModel", new UserForm());
        return "login/signUpPage";
    }

    // ENDPOINT FOR ACCESS DENIED , IF SOME ON TRY TO ACCESS ENDPOINT WHICH HE DOES
    // NOT HAVE ACCESS
    @GetMapping("/accessDenied")
    public String accessDeniedPage() {

        return "notification/accessDeniedPage";
    }

    // REMOVE SPACE FROM THE INPUT TAG
    @InitBinder
    public void inItBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);

    }

    // PASSWORD RESET PAGE
    @GetMapping("/public/passwordResetPage")
    public String passwordResetPage(Model model) {

        return "login/passwordReset";
    }

    @PostMapping("/public/resetPassword")
    public String checkSecurityQuestion(@RequestParam("emailId") String emailId,
            @RequestParam("favoriteTeam") String favoriteTeam, @RequestParam("newPassword") String newPassword) {

        // RESET PASSWORD USING
        Boolean afterResetPassword = userServices.updateThePassword(emailId, favoriteTeam, newPassword);

        // IF TRUE RETURN SUCCESS PAGE
        if (afterResetPassword) {
            return "/notification/passwordUpdated";
        }
        // IF FALSE RETURN WRONG ANSWER PAGE
        return "notification/wrongAnswer";
    }

    @GetMapping("/updateUserDetails")
    public String updateUserDetails(@RequestParam("emailId") String emailId, Model model) {

        // FIND THE USER FORM TO UPDATE
        UserForm userToUpdate = userFormServices.findByEmailId(emailId);

        if (userToUpdate != null) {

            model.addAttribute("theNewUserFormModel", userToUpdate);
            return "login/signUpPage";
        }

        return "notification/userUpdateConfirmation";
    }

}
