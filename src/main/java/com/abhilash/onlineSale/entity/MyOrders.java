package com.abhilash.onlineSale.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "myOrder")
public class MyOrders {

    @Id
    @Column(name = "orderNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderNumber;
    @Column(name = "orderAmount")
    Double orderAmount;
    @Column(name = "orderQty")
    Long orderQty;
    @Column(name = "orderDate")
    LocalDate orderDate;
    @Column(name = "orderTime")
    LocalTime orderTIme;
    @Column(name = "orderStatus")
    String orderStatus;
    @Column(name = "userId")
    Long userId;

    public MyOrders() {
        super();
    }

    public MyOrders(Integer orderNumber, Double orderAmount, Long orderQty, LocalDate orderDate, LocalTime orderTIme, String orderStatus, Long userId) {
        this.orderNumber = orderNumber;
        this.orderAmount = orderAmount;
        this.orderQty = orderQty;
        this.orderDate = orderDate;
        this.orderTIme = orderTIme;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Long orderQty) {
        this.orderQty = orderQty;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTIme() {
        return orderTIme;
    }

    public void setOrderTIme(LocalTime orderTIme) {
        this.orderTIme = orderTIme;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "MyOrders{" +
                "orderNumber=" + orderNumber +
                ", orderAmount=" + orderAmount +
                ", orderQty=" + orderQty +
                ", orderDate=" + orderDate +
                ", orderTIme=" + orderTIme +
                ", orderStatus='" + orderStatus + '\'' +
                ", userId=" + userId +
                '}';
    }
}
