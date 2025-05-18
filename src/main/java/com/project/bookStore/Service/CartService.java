package com.project.bookStore.Service;

import com.project.bookStore.Entity.*;
import com.project.bookStore.Repository.BookRepository;
import com.project.bookStore.Repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartItemRepository cartRepo;
    private final BookRepository bookRepo;

    public CartService(CartItemRepository cartRepo, BookRepository bookRepo) {
        this.cartRepo = cartRepo;
        this.bookRepo = bookRepo;
    }

    public List<CartItem> getCart(User user) {
        return cartRepo.findByUser(user);
    }

    public CartItem addToCart(User user, Long bookId, int qty) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        CartItem existing = cartRepo.findByUser(user).stream()
                .filter(c -> c.getBook().getId().equals(bookId))
                .findFirst().orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + qty);
            return cartRepo.save(existing);
        } else {
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setBook(book);
            newItem.setQuantity(qty);
            return cartRepo.save(newItem);
        }
    }

    public void removeFromCart(User user, Long cartItemId) {
        CartItem item = cartRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (!item.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        cartRepo.delete(item);
    }

    public void clearCart(User user) {
        List<CartItem> items = cartRepo.findByUser(user);
        cartRepo.deleteAll(items);
    }
}
