package com.project.bookStore.Controller;

import com.project.bookStore.Entity.Order;
import com.project.bookStore.Entity.User;
import com.project.bookStore.Service.OrderService;
import com.project.bookStore.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderService.placeOrder(user);
    }

    @GetMapping("/{userId}")
    public List<Order> getOrders(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderService.getOrders(user);
    }
}

