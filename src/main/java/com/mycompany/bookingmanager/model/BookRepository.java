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
        return entityManager.createQuery("SELECT b FROM books b", Book.class).getResultList();
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    public void delete(Long id) {
        var book = findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));
        book.setIsActive(false);
        entityManager.merge(book);
    }

    public Book update(Book book) {
        return entityManager.merge(book);
    }
}
