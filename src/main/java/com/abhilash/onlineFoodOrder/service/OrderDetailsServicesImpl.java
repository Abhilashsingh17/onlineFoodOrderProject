package com.abhilash.onlineFoodOrder.service;


import com.abhilash.onlineFoodOrder.entity.Cart;
import com.abhilash.onlineFoodOrder.entity.MyOrders;
import com.abhilash.onlineFoodOrder.entity.OrderDetails;
import com.abhilash.onlineFoodOrder.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderDetailsServicesImpl implements OrderDetailsServices {

    private CartServices cartServices;
    private OrderDetailsRepository orderDetailsRepository;
    private MyOrdersServices myOrdersServices;


    @Lazy
    @Autowired
    OrderDetailsServicesImpl(CartServices cartServices, OrderDetailsRepository orderDetailsRepository, MyOrdersServices myOrdersServices) {
        this.cartServices = cartServices;
        this.orderDetailsRepository = orderDetailsRepository;
        this.myOrdersServices = myOrdersServices;
    }

    //SAVE THE ORDER IN THE ORDER DETAILS TABLE
    @Override
    public List<OrderDetails> saveOrderDetails(Long userId, MyOrders myOrder) {
//        List<OrderDetails> orderDetailsList = new ArrayList<>();
        List<OrderDetails> savedOrderDetails = new ArrayList<>();

        // FIND THE PRODUCTS IN THE CART
        List<Cart> cartByUserId = cartServices.findCartByUserId(userId);

        if (cartByUserId != null) {

            for (Cart i : cartByUserId) {

                //CREATE OBJECT OF ORDER DETAILS
                OrderDetails orderDetails = new OrderDetails();

                // RETRIEVE THE ORDER NUMBER , DATE, TIME,USERID, FROM MYORDER  AND SET FOR ALL PRODUCT IN ORDER DETAILS OBJECT
                // RETRIEVE EACH PRODUCT FROM CART AND SET THE DETAILS IN ORDER DETAILS OBJECT
                orderDetails.setOrderDate(myOrder.getOrderDate());
                orderDetails.setOrderTIme(myOrder.getOrderTIme());
                orderDetails.setOrderNumber(myOrder.getOrderNumber());
                orderDetails.setOrderStatus("Order Processing");
                orderDetails.setUserId(myOrder.getUserId());
//                orderDetails.setOrderQty(myOrder.getOrderQty());
//                orderDetails.setOrderAmount(myOrder.getOrderAmount());
                orderDetails.setProductAmount(i.getProductAmount());
                orderDetails.setProductId(i.getProductId());
                orderDetails.setProductImage(i.getProductImage());
                orderDetails.setProductQty(i.getProductQty());
                orderDetails.setProductName(i.getProductName());
                orderDetails.setTotalAmount(i.getTotalAmount());
                orderDetails.setCartId(i.getCartId());

                // SAVE THE ORDER DETAILS OBJECT IN DATABASE
                OrderDetails save = orderDetailsRepository.save(orderDetails);

            }

        }
        return savedOrderDetails;
    }


    // FIND ALL THE PRODUCT BY ORDER NUMBER
    @Override
    public List<OrderDetails> findOrderByOrderNo(Integer orderNo) {
        List<OrderDetails> allOrders = orderDetailsRepository.findOrderDetailsByOrderNumber(orderNo);

        return allOrders;
    }

    //CHECK STATUS AND CHANGE  THE ORDER STATUS
    @Override
    public String checkStatusAndChangeTheStatus(Integer orderDetailsId, String orderStatus) {

        Optional<OrderDetails> orderDetailsOptional = orderDetailsRepository.findById(orderDetailsId);
        OrderDetails savedOrder = null;
        // GET OBJECT FROM THE OPTIONAL
        if (orderDetailsOptional.isPresent()) {
            OrderDetails orderDetails = orderDetailsOptional.get();
            String oldOrderStatus = orderDetails.getOrderStatus();

            //CHECK IF ORDER IS ALREADY NOT CANCELED OR DELIVERED
            if (oldOrderStatus.equals("Canceled")) {
                return "Canceled";
            } else if (oldOrderStatus.equals("Delivered")) {
                return "Delivered";
            }
            // IF ITS NOT CANCELED OR DELIVERED UPDATE THE STATUS
            orderDetails.setOrderStatus(orderStatus);
            savedOrder = orderDetailsRepository.save(orderDetails);
        }
        if (savedOrder != null) {
            return "Done";
        }
        return null;
    }


    // UPDATE ORDER STATUS
//    @Override
//    public String updateOrderDetails(String newStatus, Integer orderDetailsId) {
//        OrderDetails orderDetails = findOrderByOrderDetailsId(orderDetailsId);
//        String oldOrderStatus = orderDetails.getOrderStatus();
//
//        if (oldOrderStatus.equals("Canceled")) {
//            return "Canceled";
//        } else if (oldOrderStatus.equals("Delivered")) {
//            return "Delivered";
//        }
//
//        orderDetails.setOrderStatus(newStatus);
//        OrderDetails orderDetailsSaved = orderDetailsRepository.save(orderDetails);
//
//        return orderDetailsSaved.getOrderStatus();
//
//    }

    // FIND THE ORDER DETAILS, BY THE ORDER DETAILS ID
    @Override
    public OrderDetails findOrderByOrderDetailsId(Integer orderDetailsId) {
        Optional<OrderDetails> byId = orderDetailsRepository.findById(orderDetailsId);
        if (byId.isPresent()) {
            OrderDetails orderDetails = byId.get();
            return orderDetails;
        }
        return null;
    }

    //UPDATE THE ORDER QTY  IN  ORDER DETAILS
    @Override
    public OrderDetails updateOrderQty(Integer orderDetailsId, Long newQty) {
        //FINDING ORDER DETAILS
        OrderDetails orderDetails = findOrderByOrderDetailsId(orderDetailsId);

        //GET AMOUNT FOR 1 PRODUCT
        Double productAmount = orderDetails.getProductAmount();

        //SET NEW QTY
        orderDetails.setProductQty(newQty);

        //SET NEW TOTAL AMOUNT FOR THAT PRODUCT AS PER THE NEW QTY
        orderDetails.setTotalAmount(productAmount * newQty);

        //SAVE AFTER UPDATING THE QTY AND AMOUNT
        OrderDetails orderDetailsSaved = orderDetailsRepository.save(orderDetails);
        return orderDetailsSaved;
    }

    // FIND SUM OF PRODUCT QTY BY ORDER NUMBER FROM  ORDER DETAILS
    @Override
    public Long updateMyOrderQty(Integer orderNumber) {

        return orderDetailsRepository.findSumOfProductQty(orderNumber);

    }

    // FIND SUM OF PRODUCT AMOUNT BY ORDER NUMBER FROM  ORDER DETAILS
    @Override
    public Double updateMyTotalAmount(Integer orderNumber) {

        return orderDetailsRepository.findSumOfTotalAmount(orderNumber);
    }

}
