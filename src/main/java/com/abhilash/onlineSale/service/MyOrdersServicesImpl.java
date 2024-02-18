package com.abhilash.onlineSale.service;

import com.abhilash.onlineSale.entity.MyOrders;
import com.abhilash.onlineSale.entity.OrderDetails;
import com.abhilash.onlineSale.repository.MyOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MyOrdersServicesImpl implements MyOrdersServices {

    private MyOrdersRepository myOrdersRepository;
    private CartServices cartServices;
    private OrderDetailsServices orderDetailsServices;

    @Autowired
    MyOrdersServicesImpl(MyOrdersRepository myOrdersRepository, CartServices cartServices, OrderDetailsServices orderDetailsServices) {
        this.myOrdersRepository = myOrdersRepository;
        this.cartServices = cartServices;
        this.orderDetailsServices = orderDetailsServices;
    }


    // SAVE THE ORDER IN MYORDER TABLE
    @Override
    public MyOrders saveOrder(Long userId) {

        MyOrders newMyorders = new MyOrders();

        //GET THE TOTAL QTY AND TOTAL AMOUNT FROM CART AND SAVE IN THE MYORDER
        Long totalQty = cartServices.getTotalQtyByUserId(userId);
        Double totalAmount = cartServices.getTotalAmountByUserId(userId);


        MyOrders savedOrders = null;

        // SAVE THE ORDER
        if (totalAmount != null || totalQty != null) {
            newMyorders.setUserId(userId);
            newMyorders.setOrderDate(LocalDate.now());
            newMyorders.setOrderQty(totalQty);
            newMyorders.setOrderAmount(totalAmount);
            newMyorders.setOrderStatus("Order Placed");
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String localTime = currentTime.format(timeFormatter);
            newMyorders.setOrderTIme(LocalTime.parse(localTime, timeFormatter));
            savedOrders = myOrdersRepository.save(newMyorders);
        }

        return savedOrders;
    }


    // GET LIST OF ORDERS FROM MYORDERS TABLE BY USERID
    @Override
    public List<MyOrders> findAllOrders(Long userId) {
        List<MyOrders> myOrdersList = myOrdersRepository.findMyOrdersByUserId(userId);

        return myOrdersList;
    }


    // GET ALL ORDERS FROM MYORDERS TABLE
    @Override
    public List<MyOrders> findAllOrders() {
        return myOrdersRepository.findAll();
    }


    // UPDATING THE ORDER AMOUNT AND QTY AFTER CANCELING THE PRODUCT
    @Override
    public void updateMyOrder(Integer orderDetailsId) {
        //FINDING THE ORDER DETAILS
        OrderDetails orderDetails = orderDetailsServices.findOrderByOrderDetailsId(orderDetailsId);

        Integer orderNumber = orderDetails.getOrderNumber();
        Double productAmount = orderDetails.getProductAmount();
        Long productQty = orderDetails.getProductQty();

        // FINDING MY ORDER AND DECREASE THE CANCEL ORDER AMOUNT AND ORDER QTY
        Optional<MyOrders> byId = myOrdersRepository.findById(orderNumber);

        if (byId.isPresent()) {

            MyOrders myOrders = byId.get();
            Double orderAmount = myOrders.getOrderAmount();
            Long orderQty = myOrders.getOrderQty();

            myOrders.setOrderAmount(orderAmount - (productAmount * productQty));
            myOrders.setOrderQty(orderQty - productQty);

            myOrdersRepository.save(myOrders);

        }
    }


    // UPDATE THE ORDER QTY AND AMOUNT IN MYORDER
    @Override
    public void updateMyorderTotalQtyAndTotalAmount(Integer orderNumber) {
        Long totalQty = orderDetailsServices.updateMyOrderQty(orderNumber);
        Double totalAmount = orderDetailsServices.updateMyTotalAmount(orderNumber);

        Optional<MyOrders> byId = myOrdersRepository.findById(orderNumber);
        if (byId.isPresent()) {
            MyOrders myOrders = byId.get();
            myOrders.setOrderQty(totalQty);
            myOrders.setOrderAmount(totalAmount);
            myOrdersRepository.save(myOrders);
        }

    }


}
