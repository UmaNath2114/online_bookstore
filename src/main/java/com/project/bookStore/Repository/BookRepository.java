package com.project.bookStore.Repository;

import com.project.bookStore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}