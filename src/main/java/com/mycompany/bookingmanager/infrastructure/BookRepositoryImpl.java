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
        book.validateBeforeSave();
        entityManager.persist(book);
    }
    
    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }

    @Override
    public List<Book> findAllActive() {
        return entityManager.createQuery("SELECT b FROM Book b WHERE b.isActive = true", Book.class).getResultList();
    }

    @Override
    public Optional<Book> findByIsbn(int isbn) {
        return Optional.ofNullable(entityManager.find(Book.class, isbn));
    }

    @Override
    public void deactivate(int isbn) {
        var book = findByIsbn(isbn).orElseThrow(() -> new IllegalArgumentException("No se encontró el libro con el isbn " + isbn));
        book.setIsActive(false);
        entityManager.merge(book);
    }
}
