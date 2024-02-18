package com.abhilash.onlineSale.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "userRole")
public class UserRole {
    @Id
    @Column(name = "emailId")
    String emailId;

    @Column(name = "role")
    String role;

    public UserRole() {
        super();
    }

    public UserRole(String emailId, String role) {
        this.emailId = emailId;
        this.role = role;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "emailId='" + emailId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
