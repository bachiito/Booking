package com.mycompany.bookingmanager.application;

import com.mycompany.bookingmanager.domain.Book;
import com.mycompany.bookingmanager.domain.BookRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Stateless
public class BookService {

    @Inject
    private BookRepository bookRepository;

    public Book get(int isbn) {
        return bookRepository
                .findByIsbn(isbn)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> getAllActive() {
        return bookRepository.findAllActive();
    }

    public void create(Book book) {
        try {
            bookRepository.save(book);
        } catch (PersistenceException exception) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }

    public void delete(int isbn) {
        try {
            bookRepository.deactivate(isbn);
        } catch (IllegalArgumentException exception) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
    }

    public void update(Book book) {
        try {
            bookRepository.save(book);
        } catch (PersistenceException exception) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
}
