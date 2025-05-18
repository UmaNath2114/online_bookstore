package com.project.bookStore.Controller;

import com.project.bookStore.Entity.CartItem;
import com.project.bookStore.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
 public class CartController {
    @Autowired
    private CartService service;

    @PostMapping
    public CartItem add(@RequestBody CartItem item) {
        return service.addToCart(item);
    }

    @GetMapping("/{username}")
    public List<CartItem> get(@PathVariable String username) {
        return service.getCart(username);
    }

    @DeleteMapping("/{username}")
    public void clear(@PathVariable String username) {
        service.clearCart(username);
    }
}
