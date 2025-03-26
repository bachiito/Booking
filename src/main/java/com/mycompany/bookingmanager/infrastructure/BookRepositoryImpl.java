package com.mycompany.bookingmanager.infrastructure;

import com.mycompany.bookingmanager.domain.Book;
import com.mycompany.bookingmanager.domain.BookRepository;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public Optional<Book> findByIsbn(int isbn) {
        return Optional.ofNullable(entityManager.find(Book.class, isbn));
    }

    @Override
    public void delete(int isbn) {
        var book = findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("Invalid book isbn: " + isbn));
        book.setIsActive(false);
        entityManager.merge(book);
    }
}
