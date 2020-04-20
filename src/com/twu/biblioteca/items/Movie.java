package com.twu.biblioteca.items;

public class Movie extends Item {
    public Movie(String title, int year) {
        super(title, year);
    }

    @Override
    public String toString() {
        return this.getTitle() + " | " + this.getYear();
    }
}
