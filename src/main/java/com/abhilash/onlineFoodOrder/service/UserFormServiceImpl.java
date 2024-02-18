package com.abhilash.onlineFoodOrder.service;

import com.abhilash.onlineFoodOrder.entity.User;
import com.abhilash.onlineFoodOrder.entity.UserForm;
import com.abhilash.onlineFoodOrder.entity.UserRole;
import com.abhilash.onlineFoodOrder.repository.UserFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserFormServiceImpl implements UserFormServices {

    private UserFormRepository userFormRepository;
    private PasswordEncoder passwordEncoder;
    private UserServices userServices;
    private UserRoleServices userRoleServices;

    @Autowired
    UserFormServiceImpl(UserFormRepository userFormRepository, PasswordEncoder passwordEncoder, UserServices userServices, UserRoleServices userRoleServices) {
        this.userFormRepository = userFormRepository;
        this.passwordEncoder = passwordEncoder;
        this.userServices = userServices;
        this.userRoleServices = userRoleServices;
    }

    // AFTER UPDATING THE PASSWORD SAVE THE USER
    @Override
    public UserForm saveUserFormForUpdatingPassword(UserForm userForm) {
        return userFormRepository.save(userForm);
    }

    @Override
    public UserForm findByEmailId(String email) {
        return userFormRepository.findByEmailId(email);
    }

    @Override
    public UserForm findByUserId(Integer userId) {
        Optional<UserForm> byId = userFormRepository.findById(userId);
        if (byId.isPresent()) {
            UserForm userForm = byId.get();

            return userForm;
        }
        return null;
    }

    @Override
    public List<UserForm> findAllUser(String role) {

        List<UserRole> allUserByRole = userRoleServices.findAllUserByRole(role);

        if (allUserByRole != null) {

            List<UserForm> allUser = new ArrayList<>();
            for (UserRole i : allUserByRole) {
                UserForm userFound = userFormRepository.findByEmailId(i.getEmailId());
                allUser.add(userFound);
            }
            return allUser;
        }
        return null;
    }

    @Override
    public UserForm saveUserForm(UserForm newUserForm, String forEmployeeRole) {

        // CREATING USER OBJECT AND PASSING TO DATABASE TABLE USER_FORM
        UserForm userForm = new UserForm();
        userForm.setFirstName(newUserForm.getFirstName().toLowerCase());
        userForm.setLastName(newUserForm.getLastName().toLowerCase());
        userForm.setMobileNo(newUserForm.getMobileNo());
        userForm.setDateOfBirth(newUserForm.getDateOfBirth());
        userForm.setEmailId(newUserForm.getEmailId().toLowerCase());
        userForm.setAddress(newUserForm.getAddress().toLowerCase());
        userForm.setCity(newUserForm.getCity().toLowerCase());
        userForm.setState(newUserForm.getState().toLowerCase());
        userForm.setPinCode(newUserForm.getPinCode());
        userForm.setSecurityQuestion(newUserForm.getSecurityQuestion().toLowerCase());
        userForm.setPassword(passwordEncoder.encode(newUserForm.getPassword()));

        UserForm userFormReg = userFormRepository.save(userForm);

        User user = new User();
        user.setPassword(passwordEncoder.encode(newUserForm.getPassword()));
        user.setActiveStatus(1);
        user.setEmailId(newUserForm.getEmailId());
        userServices.saveUserDetails(user);

        UserRole userRole = new UserRole();
        userRole.setEmailId(newUserForm.getEmailId());
        userRole.setRole(forEmployeeRole);

        // SAVING DATA IN USER_ROLE TABLE
        userRoleServices.saveUserRole(userRole);

        return userFormReg;
    }

    @Override
    public UserForm updateUserForm(UserForm newUserForm, String forEmployeeRole) {

        // FIND THE USER FORM OBJECT AND PASSING TO DATABASE TABLE USER_FORM
        UserForm userForm = userFormRepository.findByEmailId(newUserForm.getEmailId());

        if (userForm != null) {
            userForm.setFirstName(newUserForm.getFirstName().toLowerCase());
            userForm.setLastName(newUserForm.getLastName().toLowerCase());
            userForm.setMobileNo(newUserForm.getMobileNo());
            userForm.setDateOfBirth(newUserForm.getDateOfBirth());

            userForm.setAddress(newUserForm.getAddress().toLowerCase());
            userForm.setCity(newUserForm.getCity().toLowerCase());
            userForm.setState(newUserForm.getState().toLowerCase());
            userForm.setPinCode(newUserForm.getPinCode());
            userForm.setSecurityQuestion(newUserForm.getSecurityQuestion().toLowerCase());
            // USING PASSWORD ENCODER TO SAVE PASSWORD IN BCRYPT
            userForm.setPassword(passwordEncoder.encode(newUserForm.getPassword()));

            // SAVING DATA IN USER FORM
            UserForm userFormReg = userFormRepository.save(userForm);

            // GETTING AND SETTING PASSWORD/ACTIVE STATUS / EMAIL ID IN THE USER TABLE
            User user = userServices.findUserByEmailIds(newUserForm.getEmailId());
            user.setPassword(passwordEncoder.encode(newUserForm.getPassword()));
            user.setActiveStatus(1);

            userServices.saveUserDetails(user);

            // DEFINE ROLES IN THE USER_ROLE TABLE
            UserRole userRole = userRoleServices.findUserRoleByEmailId(newUserForm.getEmailId());
            userRole.setRole(forEmployeeRole);

            userRoleServices.saveUserRole(userRole);

            return userFormReg;
        }
        return null;
    }

    @Override
    public Boolean deleteUserFromDatabase(Integer userId) {

        Optional<UserForm> userForm = userFormRepository.findById(userId);
        if (userForm.isPresent()) {
            UserForm userFormFound = userForm.get();

            User user = userServices.findUserByEmailIds(userFormFound.getEmailId());
            UserRole userRole = userRoleServices.findUserRoleByEmailId(userFormFound.getEmailId());

            userFormRepository.delete(userFormFound);
            userRoleServices.deleteUserRole(userRole);
            userServices.deleteUser(user);
            return true;
        }
        return false;
    }

}
