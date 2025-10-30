package com.example.app.services;

import com.example.app.entities.Book;
import com.example.app.repositories.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        bookDao.findAll().forEach(new Consumer<Book>() {
            @Override
            public void accept(Book book) {
                books.add(book);
            }
        });
        return books;
    }

    public Book getBookById(Long id) {
        Optional<Book> bookOpt = bookDao.findById(id);
        Book bookToReturn = null;
        if (bookOpt.isPresent()) {
            bookToReturn = bookOpt.get();
        }
        return bookToReturn;
    }

    public void saveBook(Book book) {
        bookDao.save(book);
    }

}
