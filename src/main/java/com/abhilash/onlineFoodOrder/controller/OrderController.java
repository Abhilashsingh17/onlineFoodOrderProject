package com.abhilash.onlineFoodOrder.controller;

import com.abhilash.onlineFoodOrder.entity.Cart;
import com.abhilash.onlineFoodOrder.entity.MyOrders;
import com.abhilash.onlineFoodOrder.entity.OrderDetails;
import com.abhilash.onlineFoodOrder.entity.UserForm;
import com.abhilash.onlineFoodOrder.service.CartServices;
import com.abhilash.onlineFoodOrder.service.MyOrdersServices;
import com.abhilash.onlineFoodOrder.service.OrderDetailsServices;
import com.abhilash.onlineFoodOrder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    private CartServices cartServices;
    private MyOrdersServices myOrdersServices;
    private OrderDetailsServices orderDetailsServices;

    @Autowired
    OrderController(ProductService productService, CartServices cartServices, MyOrdersServices myOrdersServices,
            OrderDetailsServices orderDetailsServices) {
        this.cartServices = cartServices;
        this.myOrdersServices = myOrdersServices;
        this.orderDetailsServices = orderDetailsServices;
    }

    // ADD PRODUCT TO CART
    @PostMapping("/addProductToCart")
    public String addItemToCart(@RequestParam("id") Integer productId,
            @ModelAttribute("currentUser") UserForm userForm) {

        Long id = userForm.getUserId();
        cartServices.addToCart(productId, id);
        return "redirect:/productPage";
    }

    // SHOW MY CART
    @GetMapping("/showMyCart")
    public String showCart(@ModelAttribute("currentUser") UserForm userForm, Model model) {
        // SHOWING ALL THE PRODUCTS
        List<Cart> findCart = cartServices.showAllCart(userForm.getUserId());

        // FINDING TOTAL AMOUNT AND TOTAL QTY IN THE CART
        Double totalAmountInCart = cartServices.getTotalAmountByUserId(userForm.getUserId());
        Long totalQtyInCart = cartServices.getTotalQtyByUserId(userForm.getUserId());

        // PASS IT TOTAL AMOUNT AND TOTAL QTY TO THE MODEL
        model.addAttribute("cartModel", findCart);
        model.addAttribute("totalQtyInCart", totalQtyInCart);
        model.addAttribute("totalAmountInCart", totalAmountInCart);

        return "/order/mycart";
    }

    // INCREASE THE QTY IN CART
    @PutMapping("/increaseQtyByOneInCart")
    public String increaseQtyByOne(@RequestParam("productId") Integer productId,
            @ModelAttribute("currentUser") UserForm userForm) {

        cartServices.updateCartAddOne(productId, userForm.getUserId());

        return "redirect:/showMyCart";
    }

    // DECREASE THE QTY IN CART
    @PutMapping("/decreaseQtyByOneInCart")
    public String decreaseQtyByOne(@RequestParam("productId") Integer productId,
            @ModelAttribute("currentUser") UserForm userForm) {

        cartServices.updateCartDeleteOne(productId, userForm.getUserId());

        return "redirect:/showMyCart";
    }

    // DELETE PRODUCT FROM THE CART
    @DeleteMapping("/deleteProductFromCart")
    public String deleteProduct(@RequestParam("productId") Integer productId,
            @ModelAttribute("currentUser") UserForm userForm) {

        cartServices.deleteFromCart(productId, userForm.getUserId());

        return "redirect:/showMyCart";
    }

    /**
     * WE ARE SELECTING THE PRODUCT FROM THE CART , ADDING THEM IN MYORDER , WHICH
     * GENERATE THE ORDER ID
     * THAN WE SAVE ORDER NUMBER AND PRODUCT DETAILS IN THE ORDER DETAILS TABLE.
     * AND AFTER THAT DELETE THE PRODUCT FROM THE CART , SO CART IS NOW FREE.
     */

    // PLACE THE ORDER || COPY THE PRODUCT FROM CART TO MYORDER AND DELETE THEM FROM
    // CART
    @PostMapping("/placeOrder")
    public String placeOrder(@ModelAttribute("currentUser") UserForm userForm, Model model) {
        // SAVE THE ORDER TO MYORDER
        MyOrders myOrders = myOrdersServices.saveOrder(userForm.getUserId());

        // IF THE CART IS EMPTY DISPLAY EMPTY CART PAGE
        if (myOrders == null) {
            return "/notification/emptyCartPage";
        }

        // SAVE THE ORDER DETAILS IN ORDER DETAILS TABLE
        List<OrderDetails> afterSaveOrderDetails = orderDetailsServices.saveOrderDetails(userForm.getUserId(),
                myOrders);

        // DELETING PRODUCTS FROM CART AFTER PLACING THE ORDER
        Integer deleteFromCart = cartServices.deleteFromCart(userForm.getUserId());

        model.addAttribute("orderNumberModel", myOrders.getOrderNumber());

        return "/notification/orderConfirmationPage";
    }

    // DISPLAY THE ORDER DETAILS PAGE
    @GetMapping("/viewOrderDetails")
    public String viewOrderDetails(@RequestParam("orderNo") Integer orderNo, Model model) {
        // GET LIST OF ORDER DETAILS BY ORDER NO FROM ORDER DETAILS TABLE
        List<OrderDetails> allOrder = orderDetailsServices.findOrderByOrderNo(orderNo);

        Long totalQty = orderDetailsServices.updateMyOrderQty(orderNo);
        Double totalAmount = orderDetailsServices.updateMyTotalAmount(orderNo);

        model.addAttribute("orderModel", allOrder);
        model.addAttribute("totalQtyInOrder", totalQty);
        model.addAttribute("totalAmountInOrder", totalAmount);

        return "/order/orderDetails";
    }

    // CANCELING THE ORDER
    @PutMapping("/cancelOrder")
    public String cancelOrder(@RequestParam("id") Integer orderDetailsId) {

        String orderStatus = "Canceled";
        // CHANGE THE PRODUCT STATUS TO CANCEL
        String cancel = orderDetailsServices.checkStatusAndChangeTheStatus(orderDetailsId, orderStatus);

        // CHECKING IF PRODUCT IS ALREADY CANCEL OR DELIVERED
        if (cancel.equals("Canceled")) {
            return "/notification/orderAllReadyCanceled";
        } else if (cancel.equals("Delivered")) {
            return "/notification/orderAllReadyDelivered";
        }
        // IF NOT CANCELED OR NOT DELIVERED THEN CANCEL THE ORDER , UPDATE THE ORDER
        // AMOUNT AND ORDER QTY IN MYORDER TABLE
        myOrdersServices.updateMyOrder(orderDetailsId);

        return "/notification/updateCancelStatus";
    }

    // UPDATING ORDER STATUS AS PER RECEIVED INPUT
    @PutMapping("/updateOrderStatus")
    public String updateOrderStatus(@RequestParam("orderDetailsId") Integer orderDetailsId,
            @RequestParam("newStatus") String newStatus) {

        // CHECKING IF ORDER IS CANCELED OR DELIVERED, IF NOT UPDATE THE NEW STATUS
        String saved = orderDetailsServices.checkStatusAndChangeTheStatus(orderDetailsId, newStatus);
        if (saved.equals("Canceled")) {
            return "/notification/orderAllReadyCanceled";
        } else if (saved.equals("Delivered")) {
            return "/notification/orderAllReadyDelivered";
        }
        // ON SUCCESSFUL UPDATING THE STATUS
        return "redirect:/viewAllOrder";
    }

    // CHANGING THE QTY, AFTER PLACING THE ORDER
    @PutMapping("/changeOrderQty")
    public String cancelQtyOrderStatus(@RequestParam("orderDetailsId") Integer orderDetailsId,
            @RequestParam("newQty") Long newQty) {

        OrderDetails orderByOrderDetailsId = orderDetailsServices.findOrderByOrderDetailsId(orderDetailsId);

        if (newQty <= 0) {
            cancelOrder(orderDetailsId);
        } else if (orderByOrderDetailsId.getOrderStatus().equals("Canceled")) {
            return "/notification/orderAllReadyCanceled";
        } else if (orderByOrderDetailsId.getOrderStatus().equals("Delivered")) {
            return "/notification/orderAllReadyDelivered";
        } else if (orderByOrderDetailsId.getOrderStatus().equals("Shipped")) {
            return "/notification/orderAllReadyShipped";
        } else if (orderByOrderDetailsId.getOrderStatus().equals("Out For Delivery")) {
            return "/notification/orderAllReadyOutForDelivery";
        } else {
            // UPDATE THE ORDER QTY IN ORDER DETAILS
            OrderDetails orderDetails = orderDetailsServices.updateOrderQty(orderDetailsId, newQty);
            // UPDATE THE ORDER AMOUNT AND QTY IN MYORDER , AFTER CHANGING THE QTY.
            myOrdersServices.updateMyorderTotalQtyAndTotalAmount(orderDetails.getOrderNumber());
        }

        return "redirect:/viewAllOrder";
    }

    // VIEW ALL ORDERS IN DATABASE FOR EMPLOYEE AND ADMIN
    @GetMapping("/viewAllOrder")
    public String viewAllOrder(Model model) {

        List<MyOrders> allorder = myOrdersServices.findAllOrders();
        model.addAttribute("myOrdersModel", allorder);
        return "/order/myOrdersPage";
    }

}
