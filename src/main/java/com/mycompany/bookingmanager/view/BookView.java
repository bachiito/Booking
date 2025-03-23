package com.mycompany.bookingmanager.view;

import com.mycompany.bookingmanager.entity.Book;
import com.mycompany.bookingmanager.controller.BookController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class BookView {

    @Inject
    private BookController bookService;

    private List<Book> books;

    @PostConstruct
    public void init() {
        books = bookService.findAll();
    }

    public List<Book> getBooks() {
        return books;
    }
}
