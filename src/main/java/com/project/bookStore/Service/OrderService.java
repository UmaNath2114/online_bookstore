package com.project.bookStore.Service;

import com.project.bookStore.Entity.BookOrder;
import com.project.bookStore.Entity.CartItem;
import com.project.bookStore.Repository.CartItemRepository;
import com.project.bookStore.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired private CartItemRepository cartRepo;

    public BookOrder placeOrder(String username) {
        List<CartItem> items = cartRepo.findByUsername(username);
        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

        BookOrder order = new BookOrder();
        order.setUsername(username);
        order.setItems(items);
        order.setTotal(total);
        order.setOrderDate(LocalDateTime.now());

        cartRepo.deleteByUsername(username); // clear cart after order
        return orderRepo.save(order);
    }

    public List<BookOrder> getOrders(String username) {
        return orderRepo.findByUsername(username);
    }
}
