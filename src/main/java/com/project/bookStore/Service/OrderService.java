package com.project.bookStore.Service;

import com.project.bookStore.Entity.*;
import com.project.bookStore.Repository.BookRepository;
import com.project.bookStore.Repository.CartItemRepository;
import com.project.bookStore.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final CartItemRepository cartRepo;
    private final BookRepository bookRepo;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepo, CartItemRepository cartRepo,
                        BookRepository bookRepo, CartService cartService) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.bookRepo = bookRepo;
        this.cartService = cartService;
    }

    public Order placeOrder(User user) {
        List<CartItem> cartItems = cartRepo.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = cartItems.stream().map(cartItem -> {
            Book book = cartItem.getBook();

            if (book.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Not enough stock for: " + book.getTitle());
            }

            book.setStock(book.getStock() - cartItem.getQuantity());
            bookRepo.save(book);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(book.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(order);
        cartService.clearCart(user);

        return savedOrder;
    }

    public List<Order> getOrders(User user) {
        return orderRepo.findByUser(user);
    }
}
