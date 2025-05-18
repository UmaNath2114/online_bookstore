package com.project.bookStore.Controller;

import com.project.bookStore.Entity.Book;
import com.project.bookStore.Service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*") // allow frontend access
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/add-book")
    public Book save(@RequestBody Book book) {
        return service.save(book);
    }

    @GetMapping("/get-book")
    public List<Book> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
