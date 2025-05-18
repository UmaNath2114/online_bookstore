package com.project.bookStore.Repository;

import com.project.bookStore.Entity.Order;
import com.project.bookStore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}