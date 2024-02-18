package com.abhilash.onlineSale.repository;

import com.abhilash.onlineSale.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {


    List<Product> findAllByOrderByProductTypeAsc();

}
