package com.demo.ecom.controller;

import com.demo.ecom.dto.ApiResponse;
import com.demo.ecom.model.Order;
import com.demo.ecom.model.User;
import com.demo.ecom.service.OrderService;
import com.demo.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    // place order after checkout
    @PostMapping("/checkout")
    public ResponseEntity<ApiResponse> placeOrder(@RequestParam("userId") int userId) {

        User user = userService.getUserById(userId);
        // place the order
        orderService.placeOrder(user);
        return new ResponseEntity<>(new ApiResponse(true, "Order has been placed"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getOrders(@RequestParam("userId") int userId) {
        User user = userService.getUserById(userId);
        List<Order> orderList= orderService.getOrdersByUser(user);
        return new ResponseEntity<List<Order>>(orderList,HttpStatus.OK);
    }

}
