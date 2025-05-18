package com.project.bookStore.Controller;



import com.project.bookStore.Entity.Book;
import com.project.bookStore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")  // All endpoints here start with /api/books
public class BookController {

    @Autowired
    private BookService bookService;

    // 🔹 1. GET all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // 🔹 2. GET one book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return ResponseEntity.of(bookService.getBookById(id));
    }

    // 🔹 3. POST- Add a new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    // 🔹 4. PUT - Update a book by ID
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        updatedBook.setId(id);
        return bookService.saveBook(updatedBook);
    }

    // 🔹 5. DELETE - Delete a book by ID
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
