package com.abhilash.onlineFoodOrder.entity;


import jakarta.persistence.*;

@Entity(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    Long cartId;
    @Column(name = "productId")
    Integer productId;
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
    @Column(name = "userId")
    private Long userId;


    public Cart() {
        super();
    }

    public Cart(Long cartId, Integer productId, String productName, Double productAmount, Long productQty, Double totalAmount, String productImage, Long userId) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
        this.productQty = productQty;
        this.totalAmount = totalAmount;
        this.productImage = productImage;
        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
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

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productAmount=" + productAmount +
                ", productQty=" + productQty +
                ", totalAmount=" + totalAmount +
                ", productImage='" + productImage + '\'' +
                '}';
    }
}
