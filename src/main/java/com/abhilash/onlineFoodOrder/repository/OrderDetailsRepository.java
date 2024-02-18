package com.abhilash.onlineFoodOrder.repository;

import com.abhilash.onlineFoodOrder.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {

    List<OrderDetails> findOrderDetailsByOrderNumber(Integer orderNo);


    @Query("SELECT SUM(c.productQty) FROM orderDetails c WHERE c.orderNumber = :orderNumber AND c.orderStatus !='Canceled'")
    Long findSumOfProductQty(Integer orderNumber);

    @Query("SELECT SUM(c.totalAmount) FROM orderDetails c WHERE c.orderNumber = :orderNumber AND c.orderStatus !='Canceled' ")
    Double findSumOfTotalAmount(Integer orderNumber);
}
