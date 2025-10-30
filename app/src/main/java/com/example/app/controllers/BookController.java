package com.example.app.controllers;

import com.example.app.entities.Book;
import com.example.app.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        System.out.println("id = " + id);
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void postBook(@RequestBody Book book) {
        System.out.println("book = " + book);
        bookService.saveBook(book);
    }
}
