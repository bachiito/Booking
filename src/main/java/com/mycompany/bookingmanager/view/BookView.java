package com.mycompany.bookingmanager.view;

import com.mycompany.bookingmanager.entity.Book;
import com.mycompany.bookingmanager.controller.BookController;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import org.primefaces.PrimeFaces;

@Named
@ApplicationScoped
public class BookView {

    @Inject
    private BookController bookService;

    private List<Book> books;

    private Book newBook;

    @PostConstruct
    public void fetchBooks() {
        books = bookService.findAll();
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getNewBook() {
        return newBook;
    }

    public void clearNewBook() {
        newBook = new Book();
    }

    public void saveBook() {
        bookService.create(newBook);
        fetchBooks();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Libro guardado correctamente."));
        PrimeFaces.current().executeScript("PF('bookCreationDialog').hide()");
        PrimeFaces.current().ajax().update("form:alert", "form:dt-books");
    }
}
