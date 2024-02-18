package com.abhilash.onlineSale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhilash.onlineSale.entity.MyOrders;

public interface MyOrdersRepository extends JpaRepository<MyOrders, Integer> {

    List<MyOrders> findMyOrdersByUserId(Long userId);


}
