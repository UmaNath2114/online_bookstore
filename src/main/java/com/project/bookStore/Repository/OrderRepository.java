package com.project.bookStore.Repository;


import com.project.bookStore.Entity.BookOrder;
//import com.project.bookStore.Entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
//public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    List<CartItem> findByUsername(String username);
//    void deleteByUsername(String username);


public interface OrderRepository extends JpaRepository<BookOrder, Long> {
    List<BookOrder> findByUsername(String username);
}
