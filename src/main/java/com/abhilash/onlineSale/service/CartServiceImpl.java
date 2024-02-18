package com.abhilash.onlineSale.service;

import com.abhilash.onlineSale.entity.Cart;
import com.abhilash.onlineSale.entity.Product;
import com.abhilash.onlineSale.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CartServiceImpl implements CartServices {

    private ProductService productService;

    private CartRepository cartRepository;

    @Autowired
    CartServiceImpl(ProductService productService, CartRepository cartRepository) {
        this.productService = productService;
        this.cartRepository = cartRepository;
    }

    // FIND THE CART BY USERID
    @Override
    public List<Cart> findCartByUserId(Long userId) {

        return cartRepository.findCartByUserId(userId);
    }

    //DELETE PRODUCT FROM CART BY USERID
    @Override
    public Integer deleteFromCartByUserId(Long userId) {
        List<Cart> findCart = cartRepository.findCartByUserId(userId);
        Integer cartDeleted = 0;
        if (findCart != null) {
            cartDeleted = cartRepository.deleteProductByUserId(userId);
        }
        return cartDeleted;
    }

    //ADD PRODUCT TO CART, CHECK IF PRODUCT IS ALREADY IN THE CART OR NOT
    @Override
    public Cart addToCart(Integer productId, Long id) {

        // FINDING THE PRODUCT DETAILS FROM THE PRODUCT DATABASE
        Product product = productService.findProductById(productId);
        Cart cart = new Cart();

        //CHECK IF PRODUCT IS ALREADY IN THE CART OR NOT
        Cart cartCheck = cartRepository.findCartByProductIdAndUserId(productId, id);

        //  IF PRODUCT IS ALREADY IN THE CART INCREASE THE QTY BY 1
        if (cartCheck != null) {
            Cart updatedCart = updateCartAddOne(productId, id);
            return cartRepository.save(updatedCart);
        } else {
            // IF PRODUCT IS NOT IN THE CART ADD THE PRODUCT
            cart.setProductAmount(product.getProductAmount());
            cart.setProductId(productId);
            cart.setProductName(product.getProductName());
            cart.setProductQty(1L);
            cart.setTotalAmount(product.getProductAmount());
            cart.setProductImage(product.getProductImage());
            cart.setUserId(id);
        }

        return cartRepository.save(cart);
    }

    //SHOW ALL ITEMS  FROM THE CART
    @Override
    public List<Cart> showAllCart(Long id) {

        List<Cart> findAllCartData = cartRepository.findCartByUserId(id);

        return findAllCartData;
    }

    // FINDING TOTAL QTY IN THE CART
    @Override
    public Long getTotalQtyByUserId(Long userId) {
        Long totalQty = cartRepository.getTotalQtyByUserId(userId);
        return totalQty;
    }

    // FINDING TOTAL AMOUNT  IN THE CART
    @Override
    public Double getTotalAmountByUserId(Long userId) {
        Double totalAmount = cartRepository.getTotalAmountByUserId(userId);
        return totalAmount;
    }

    // IN CART , INCREASE THE QTY BY ONE
    @Override
    public Cart updateCartAddOne(Integer productId, Long userId) {
        Cart cartCheck = cartRepository.findCartByProductIdAndUserId(productId, userId);
        if (cartCheck != null) {
            Long newQty = cartCheck.getProductQty() + 1;
            cartCheck.setProductQty(newQty);
            cartCheck.setTotalAmount(cartCheck.getProductAmount() * newQty);
            return cartRepository.save(cartCheck);
        }
        return cartCheck;
    }

    //REMOVE THE ITEM FROM THE CART BY PRODUCT ID AND USER ID
    @Override
    public Cart deleteFromCart(Integer productId, Long userId) {
        Cart cartCheck = cartRepository.findCartByProductIdAndUserId(productId, userId);
        if (cartCheck != null) {
            Integer row = cartRepository.deleteByProductIdAndUserId(productId, userId);
        }
        return cartCheck;
    }

    //REMOVE THE PRODUCTS FROM THE CART BY USER ID
    @Override
    public Integer deleteFromCart(Long userId) {
        Integer deletedCart = cartRepository.deleteFromCartByUserId(userId);
        return deletedCart;
    }


    // IN CART , DECREASE THE QTY BY ONE
    @Override
    public Cart updateCartDeleteOne(Integer productId, Long userId) {
        // CHECK IF PRODUCT IS IN THE CART OT NOT
        Cart cartCheck = cartRepository.findCartByProductIdAndUserId(productId, userId);

        // IF PRODUCT IS PRESENT IN CART
        if (cartCheck != null) {

            Long oldQty = cartCheck.getProductQty();
            Long newQty = oldQty - 1;

            // CHECK IF OLD QTY IS GREATER THAN 1 DECREASE THE QTY BY 1
            if (oldQty > 1) {
                cartCheck.setProductQty(newQty);
                cartCheck.setTotalAmount(cartCheck.getProductAmount() * newQty);
                return cartRepository.save(cartCheck);
            } else {
                // IF IN CART ONLY 1 QTY IS THERE, THEN DELETE THE PRODUCT FORM CART
                cartRepository.deleteByProductIdAndUserId(productId, userId);
            }
        }
        return cartCheck;
    }

}
