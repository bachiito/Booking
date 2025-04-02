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
        if (newBook.getPrice() <= 0) {
            showAlert("El precio del libro debe de ser mayor a cero");
            return;
        }

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
        refreshTable();
        resetSelectedBook();
        showAlert(alertMsg);
    }

    private void refreshTable() {
        PrimeFaces.current().ajax().update("form:dt-books");
    }

    private void showAlert(String alertMsg) {
        FacesContext.getCurrentInstance().addMessage("This is supposed to be a string", new FacesMessage(alertMsg));
        PrimeFaces.current().ajax().update("form:alert");
    }

    private void resetSelectedBook() {
        selectedBook = null;
    }
}
