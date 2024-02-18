package com.abhilash.onlineSale.service;

import com.abhilash.onlineSale.entity.MyOrders;

import java.util.List;

public interface MyOrdersServices {
    MyOrders saveOrder(Long userId);

//    Integer deleteFromCart(Long userId);

    List<MyOrders> findAllOrders(Long userId);

    List<MyOrders> findAllOrders();

    void updateMyOrder(Integer orderDetailsId);

    void updateMyorderTotalQtyAndTotalAmount(Integer orderNumber);
}
