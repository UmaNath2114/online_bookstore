package com.project.bookStore.Controller;

import com.project.bookStore.Entity.BookOrder;
import com.project.bookStore.Entity.CartItem;
import com.project.bookStore.Service.CartService;
import com.project.bookStore.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired private OrderService service;

    @PostMapping("/{username}")
    public BookOrder placeOrder(@PathVariable String username) {
        return service.placeOrder(username);
    }

    @GetMapping("/{username}")
    public List<BookOrder> getOrders(@PathVariable String username) {
        return service.getOrders(username);
    }
}
