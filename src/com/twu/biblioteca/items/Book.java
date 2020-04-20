package com.twu.biblioteca.items;

public class Book extends Item {
    private String author;
    private boolean isAvailable;

    public Book(String title, String author, int publicationYear) {
        super(title, publicationYear);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return this.getTitle() + " | " + this.author + " | " + this.getYear();
    }
}
