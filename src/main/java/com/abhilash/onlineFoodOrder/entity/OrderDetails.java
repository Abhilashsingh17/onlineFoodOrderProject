package com.abhilash.onlineFoodOrder.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "orderDetails")
public class OrderDetails {

    @Id
    @Column(name = "orderDetailsId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderDetailsId;
    @Column(name = "orderNumber")
    Integer orderNumber;
    @Column(name = "userId")
    Long userId;
    @Column(name = "orderDate")
    LocalDate orderDate;
    @Column(name = "orderTime")
    LocalTime orderTIme;
    @Column(name = "cartId")
    Long cartId;
    @Column(name = "productId")
    Integer productId;
    @Column(name = "orderStatus")
    String orderStatus;
    @Column(name = "productName")
    String productName;
    @Column(name = "productAmount")
    Double productAmount;
    @Column(name = "productQty")
    Long productQty;
    @Column(name = "totalAmount")
    Double totalAmount;
    @Column(name = "productImage")
    String productImage;

//    @Column(name = "orderAmount")
//    Double orderAmount;
//    @Column(name = "orderQty")
//    Long orderQty;

    public OrderDetails() {
        super();
    }


    public OrderDetails(Integer orderDetailsId, Integer orderNumber, Long userId, LocalDate orderDate, LocalTime orderTIme, Long cartId, Integer productId, String orderStatus, String productName, Double productAmount, Long productQty, Double totalAmount, String productImage) {
        this.orderDetailsId = orderDetailsId;
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderTIme = orderTIme;
        this.cartId = cartId;
        this.productId = productId;
        this.orderStatus = orderStatus;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productQty = productQty;
        this.totalAmount = totalAmount;
        this.productImage = productImage;

    }


    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

//    public Double getOrderAmount() {
//        return orderAmount;
//    }
//
//    public void setOrderAmount(Double orderAmount) {
//        this.orderAmount = orderAmount;
//    }
//
//    public Long getOrderQty() {
//        return orderQty;
//    }
//
//    public void setOrderQty(Long orderQty) {
//        this.orderQty = orderQty;
//    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(Double productAmount) {
        this.productAmount = productAmount;
    }

    public Long getProductQty() {
        return productQty;
    }

    public void setProductQty(Long productQty) {
        this.productQty = productQty;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderDetailsId=" + orderDetailsId +
                ", orderNumber=" + orderNumber +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", orderTIme=" + orderTIme +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", orderStatus='" + orderStatus + '\'' +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", productQty=" + productQty +
                ", totalAmount=" + totalAmount +
                ", productImage='" + productImage + '\'' +

                '}';
    }
}
