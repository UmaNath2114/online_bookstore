package com.project.bookStore.Repository;

import com.project.bookStore.Entity.CartItem;
import com.project.bookStore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
}