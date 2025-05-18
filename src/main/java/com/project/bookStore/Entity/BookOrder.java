package com.project.bookStore.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private double total;
    private LocalDateTime orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CartItem> items;
}
