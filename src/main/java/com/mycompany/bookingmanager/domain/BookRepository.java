package com.mycompany.bookingmanager.domain;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findByIsbn(int isbn);

    List<Book> findAll();

    void save(Book book);

    void delete(int isbn);
}
