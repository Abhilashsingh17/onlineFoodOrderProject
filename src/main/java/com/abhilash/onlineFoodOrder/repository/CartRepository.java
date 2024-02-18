package com.abhilash.onlineFoodOrder.repository;

import com.abhilash.onlineFoodOrder.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findCartByUserId(Long userId);

    Cart findCartByProductIdAndUserId(Integer productId, Long userId);

    Integer deleteByProductIdAndUserId(Integer productId, Long userId);

    Long countByUserId(Long userId);

    @Query("SELECT SUM(c.totalAmount) FROM cart c WHERE c.userId = :userId")
    Double getTotalAmountByUserId(Long userId);

    @Query("SELECT SUM(c.productQty) FROM cart c WHERE c.userId = :userId")
    Long getTotalQtyByUserId(Long userId);


    Integer deleteProductByUserId(Long userId);

    Integer deleteFromCartByUserId(Long userId);
}
