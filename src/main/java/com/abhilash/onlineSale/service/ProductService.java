package com.abhilash.onlineSale.service;

import com.abhilash.onlineSale.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> showAllProducts();

    Product saveOneProduct(Product product);

    Product findProductById(Integer productId);

   void deleteProductByProductId(Integer productId);

    String uploadProductImage(String path, MultipartFile file) throws IOException;
}
