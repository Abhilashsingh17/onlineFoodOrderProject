package com.abhilash.onlineFoodOrder.service;

import com.abhilash.onlineFoodOrder.entity.Product;
import com.abhilash.onlineFoodOrder.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    ProductRepository productRepository;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    // GET ALL THE PRODUCTS
    @Override
    public List<Product> showAllProducts() {
        //Find product and order them by product type
        List<Product> productList = productRepository.findAllByOrderByProductTypeAsc();
        return productList;
    }


    //SAVING THE PRODUCT DETAILS IN THE DATABASE  --{ WE ARE UPLOADING IMAGE TO THE FOLDER USING }
    @Override
    public Product saveOneProduct(Product product) {
        Product save = productRepository.save(product);
        return save;
    }

    @Override
    public Product findProductById(Integer productId) {

        Optional<Product> product = productRepository.findById(productId);
        Product myProduct = null;
        if (product.isPresent()) {
            myProduct = product.get();
        } else {
            throw new RuntimeException("Product Not Found");
        }

        return myProduct;
    }

    @Override
    public void deleteProductByProductId(Integer productId) {

        productRepository.deleteById(productId);

    }


    // UPLOADING THE IMAGE TO FOLDER
    @Override
    public String uploadProductImage(String path, MultipartFile file) throws IOException {

        //FILE NAME
        String name = file.getOriginalFilename();

        //PATH
        String filePath = path + name;

        //create folder if it is not exist
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdir();
        }

        //FILE COPY TO FOLDER
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);


        return name;

    }


}
