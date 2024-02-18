package com.abhilash.onlineSale.controller;

import com.abhilash.onlineSale.entity.MyOrders;
import com.abhilash.onlineSale.entity.Product;
import com.abhilash.onlineSale.entity.UserForm;
import com.abhilash.onlineSale.service.CartServices;
import com.abhilash.onlineSale.service.MyOrdersServices;
import com.abhilash.onlineSale.service.OrderDetailsServices;
import com.abhilash.onlineSale.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Value("${project.image}")
    String path;
    private ProductService productService;
    private CartServices cartServices;
    private MyOrdersServices myOrdersServices;

    @Autowired
    ProductController(ProductService productService, CartServices cartServices, MyOrdersServices myOrdersServices,
                      OrderDetailsServices orderDetailsServices) {
        this.cartServices = cartServices;
        this.productService = productService;
        this.myOrdersServices = myOrdersServices;
    }

    // DISPLAY MYORDERS
    @GetMapping("/myOrdersPage")
    public String orderDetailsPage(@ModelAttribute("currentUser") UserForm userForm, Model model) {
        // GET TOTAL QTY IN THE CART, USED TO DISPLAY ON NAV BAR CART ICON
        Long totalQtyInCart = cartServices.getTotalQtyByUserId(userForm.getUserId());

        // GET ALL ITEMS IN FROM THE CART
        List<MyOrders> listMyOrders = myOrdersServices.findAllOrders(userForm.getUserId());

        model.addAttribute("totalQtyInCart", totalQtyInCart);
        model.addAttribute("myOrdersModel", listMyOrders);

        return "/order/myOrdersPage";
    }

    // DISPLAY PRODUCTS
    @GetMapping("/productPage")
    public String productPage(@ModelAttribute("currentUser") UserForm userForm, Model model) {
        // GET ALL THE PRODUCTS
        List<Product> productList = productService.showAllProducts();
        // DISPLAY ALL PRODUCTS
        model.addAttribute("productModel", productList);

        // GET TOTAL QTY IN THE CART, USED TO DISPLAY ON NAV BAR CART ICON
        Long totalQtyInCart = cartServices.getTotalQtyByUserId(userForm.getUserId());

        model.addAttribute("totalQtyInCart", totalQtyInCart);
        return "/food/productPage";
    }

    // UPDATE PRODUCT DETAILS IN PRODUCT TABLE
    @GetMapping("/updateProduct")
    public String updateProduct(@RequestParam("id") Integer productId, Model model) {

        Product foundProduct = productService.findProductById(productId);

        model.addAttribute("oneProduct", foundProduct);

        return "/food/AddProducts";
    }

    // DELETE PRODUCT FORM PRODUCT TABLE
    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("id") Integer productId) {

        productService.deleteProductByProductId(productId);

        return "/notification/deleteProductConfirmation";
    }

    // SHOW ADD PRODUCT PAGE
    @GetMapping("/showAddOneProduct")
    public String showProductPage(Model model) {

        Product product = new Product();
        model.addAttribute("oneProduct", product);

        return "/food/AddProducts";
    }

    // ADD ONE PRODUCT FOR EMPLOYEE AND ADMIN
    @PostMapping("/addOneProduct")
    public String addOnrProduct(@Valid @ModelAttribute("oneProduct") Product product, BindingResult bindingResult,
                                @RequestParam("productImgDir") MultipartFile image, Model model) {

        if (bindingResult.hasErrors()) {

            return "/food/AddProducts";
        }

        if (image.isEmpty()) {
            bindingResult.rejectValue("productImage", "file.empty", "Please Upload an image.");
            return "/food/AddProducts";
        }
        String fileName = null;

        try {
            fileName = this.productService.uploadProductImage(path, image);
            product.setProductImage(fileName);
            Product savedProduct = productService.saveOneProduct(product);
            model.addAttribute("savedImageModel", savedProduct);

            return "/notification/newProductAddConfirmation";

        } catch (IOException e) {
            bindingResult.rejectValue("productImage", "upload.error", "Failed to upload product image.");
            return "/food/AddProducts";
        }

    }

}
