package com.mycompany.bookingmanager.service;

import com.mycompany.bookingmanager.entity.Book;
import com.mycompany.bookingmanager.model.BookRepository;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;

public class BookService {

    @Inject
    private BookRepository bookRepository;

    public Book findBook(Long id) {
        return bookRepository
                .findById(id)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book create(Book book) {
        try {
            return bookRepository.create(book);
        } catch (PersistenceException exception) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    public void delete(Long id) {
        try {
            bookRepository.delete(id);
        } catch (IllegalArgumentException exception) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public Book update(Book book) {
        try {
            return bookRepository.create(book);
        } catch (PersistenceException exception) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
