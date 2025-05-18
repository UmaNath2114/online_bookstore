package com.project.bookStore.Service;

import com.project.bookStore.Entity.Book;
import com.project.bookStore.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book save(Book book) {
        return (Book) repo.save(book);
    }

    public List<Book> getAll() {
        return repo.findAll();
    }

    public Book getById(Long id) {
        return (Book) repo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}

