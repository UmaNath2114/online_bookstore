package com.project.bookStore.Controller;


import com.project.bookStore.Entity.CartItem;
import com.project.bookStore.Entity.User;
import com.project.bookStore.Service.CartService;
import com.project.bookStore.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    private final UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    // Pass userId as param for simplicity
    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartService.getCart(user);
    }

    @PostMapping("/{userId}/add")
    public CartItem addToCart(@PathVariable Long userId, @RequestBody Map<String, Object> body) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long bookId = Long.valueOf(body.get("bookId").toString());
        int qty = Integer.parseInt(body.get("quantity").toString());

        return cartService.addToCart(user, bookId, qty);
    }

    @DeleteMapping("/{userId}/remove/{cartItemId}")
    public void removeFromCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartService.removeFromCart(user, cartItemId);
    }
}
