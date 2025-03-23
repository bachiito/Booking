package com.mycompany.bookingmanager.model;

import com.mycompany.bookingmanager.entity.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    public Optional<Book> findByIsbn(int isbn) {
        return Optional.ofNullable(entityManager.find(Book.class, isbn));
    }

    public void delete(int isbn) {
        var book = findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("Invalid book isbn: " + isbn));
        book.setIsActive(false);
        entityManager.merge(book);
    }

    public Book update(Book book) {
        return entityManager.merge(book);
    }
}
