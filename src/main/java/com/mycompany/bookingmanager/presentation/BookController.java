package com.mycompany.bookingmanager.presentation;

import com.mycompany.bookingmanager.application.BookService;
import com.mycompany.bookingmanager.domain.Book;
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
public class BookController {

    @Inject
    private BookService bookService;

    private List<Book> activeBooks;

    private Book newBook;

    private Book selectedBook;

    @PostConstruct
    public void fetchBooks() {
        activeBooks = bookService.getAllActive();
    }

    public List<Book> getBooks() {
        return activeBooks;
    }

    public Book getNewBook() {
        return newBook;
    }

    public void clearNewBook() {
        newBook = new Book();
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book book) {
        selectedBook = book;
    }

    public void saveBook() {
        bookService.create(newBook);
        PrimeFaces.current().executeScript("PF('bookCreationDialog').hide()");
        updateUI("Libro guardado");
    }

    public void deleteBook() {
        var isbn = selectedBook.getIsbn();
        bookService.delete(isbn);
        updateUI("Libro eliminado");
    }

    private void updateUI(String alertMsg) {
        fetchBooks();
        selectedBook = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(alertMsg));
        PrimeFaces.current().ajax().update("form:alert", "form:dt-books");
    }
}
