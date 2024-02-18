package com.abhilash.onlineSale.service;

import com.abhilash.onlineSale.entity.Cart;

import java.util.List;

public interface CartServices {

    public Cart addToCart(Integer productId, Long id);

    Cart updateCartAddOne(Integer productId, Long userId);

    Cart deleteFromCart(Integer productId, Long userId);

    public List<Cart> showAllCart(Long id);

    //REMOVE THE PRODUCTS FROM THE CART BY USER ID
    Integer deleteFromCart(Long userId);

    public Cart updateCartDeleteOne(Integer productId, Long userId);

    public Double getTotalAmountByUserId(Long userId);

    public Long getTotalQtyByUserId(Long userId);

    List<Cart> findCartByUserId(Long userId);

    Integer deleteFromCartByUserId(Long userId);

}
