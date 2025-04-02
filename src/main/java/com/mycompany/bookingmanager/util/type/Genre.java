package com.mycompany.bookingmanager.util.type;

public enum Genre {
    FICITION("Ficción"),
    NOVEL("Novel"),
    MYSTERY("Misterio"),
    THRILLER("Suspenso"),
    NARRATIVE("Narrativa");

    private String genre;

    Genre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
