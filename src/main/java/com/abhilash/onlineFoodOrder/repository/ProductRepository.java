package com.abhilash.onlineFoodOrder.repository;

import com.abhilash.onlineFoodOrder.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findAllByOrderByProductTypeAsc();

}
