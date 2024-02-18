package com.abhilash.onlineSale.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name = "user")
@Table(name = "user")
public class User {

    @Column(name = "activeStatus")
    private Integer activeStatus;

    @NotNull(message = "Enter Correct Password")
    @Column(name = "password")
    private String password;

    @Id
    @NotNull(message = "Enter Correct Email ID")
    @Column(name = "emailId")
    private String emailId;

    public User() {
        super();
    }

    public User(Integer activeStatus, String password, String emailId) {
        this.activeStatus = activeStatus;
        this.password = password;
        this.emailId = emailId;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }
    public void setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "User{" +
                "activeStatus=" + activeStatus +
                ", password='" + password + '\'' +
                ", emailId='" + emailId + '\'' +
                '}';
    }
}
