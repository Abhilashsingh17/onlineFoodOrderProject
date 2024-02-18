package com.abhilash.onlineSale.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "userForm")
@Table(name = "userForm", uniqueConstraints = @UniqueConstraint(columnNames = "emailId"))
public class UserForm {


    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotNull(message = "Enter First Name")
    @Size(min = 1, message = "Enter First Name")
    @Column(name = "firstName")
    private String firstName;
    @NotNull(message = "Enter Last Name")
    @Column(name = "lastName")
    private String lastName;
    @NotNull(message = "Mobile No. Required")
    @Pattern(regexp = "^\\d{10}$", message = "Enter 10 digit Mobile No")
    @Column(name = "mobileNo")
    private String mobileNo;
    @Past
    @NotNull(message = "Date of Birth Required")
    @Column(name = "dateOfBirth", nullable = false)
    private LocalDate dateOfBirth;
    @NotNull(message = "Enter Correct Email ID")
    @Column(name = "emailId")
    private String emailId;
    @NotNull(message = "Enter Address")
    @Column(name = "address")
    private String address;
    @NotNull(message = "Enter city")
    @Column(name = "city")
    private String city;
    @NotNull(message = "Enter state")
    @Column(name = "state")
    private String state;
    @NotNull(message = "Enter pinCode")
//    @Min(value = 100000, message = "Pin Code must be at least 6 digits")
//    @Max(value = 999999, message = "Pin Code must be at most 6 digits")
    @Pattern(regexp = "^\\d{6}$", message = "Enter 6 digit Pin Code")
    @Column(name = "pinCode")
    private String pinCode;

    @NotNull(message = "Enter Correct Password")
    @Column(name = "password")
    private String password;


    @NotNull(message = "Answer The Question")
    @Column(name = "securityQuestion")
    private String securityQuestion;

    public UserForm() {
        super();
    }

    public UserForm(Long userId, String firstName, String lastName, String mobileNo, LocalDate dateOfBirth, String emailId, String address, String city, String state, String pinCode, String password, String securityQuestion) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNo = mobileNo;
        this.dateOfBirth = dateOfBirth;
        this.emailId = emailId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.password = password;
        this.securityQuestion = securityQuestion;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        this.dateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", emailId='" + emailId + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", password='" + password + '\'' +
                ", securityQuestion='" + securityQuestion + '\'' +
                '}';
    }
}
