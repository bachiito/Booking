package com.mycompany.bookingmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    private enum Genre {
        FICITION,
        NOVEL,
        MYSTERY,
        THRILLER,
        NARRATIVE,
    }

    /* 
     * TODO: change from long to UUID
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isbn;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;
}
