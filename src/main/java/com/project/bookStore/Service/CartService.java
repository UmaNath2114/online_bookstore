package com.project.bookStore.Service;

import com.project.bookStore.Entity.CartItem;
import com.project.bookStore.Repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CartService {
        @Autowired
        private CartItemRepository cartRepo;

        public CartItem addToCart(CartItem item) {
            return cartRepo.save(item);
        }

        public List<CartItem> getCart(String username) {
            return cartRepo.findByUsername(username);
        }

        public void clearCart(String username) {
            cartRepo.deleteByUsername(username);
        }
    }

