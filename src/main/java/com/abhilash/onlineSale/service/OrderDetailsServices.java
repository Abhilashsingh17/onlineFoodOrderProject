package com.abhilash.onlineSale.service;

import com.abhilash.onlineSale.entity.MyOrders;
import com.abhilash.onlineSale.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsServices {

    public List<OrderDetails> saveOrderDetails(Long userId, MyOrders myOrder);

    List<OrderDetails> findOrderByOrderNo(Integer orderNo);

    String checkStatusAndChangeTheStatus(Integer orderDetailsId, String orderStatus);

    OrderDetails findOrderByOrderDetailsId(Integer orderDetailsId);


    OrderDetails updateOrderQty(Integer orderDetailsId, Long newQty);


    Long updateMyOrderQty(Integer orderNumber);

    Double updateMyTotalAmount(Integer orderNumber);
}
