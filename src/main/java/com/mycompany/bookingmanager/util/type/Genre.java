package com.mycompany.bookingmanager.util.type;

public enum Genre {
    FICTION("Ficción"),
    MYSTERY("Misterio"),
    THRILLER("Suspenso"),
    HISTORY("Historia"),
    ROMANCE("Romance"),
    FANTASY("Fantasía");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
