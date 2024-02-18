package com.abhilash.onlineSale.repository;

import com.abhilash.onlineSale.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Integer> {


}
