package com.twu.biblioteca.collections;

import com.twu.biblioteca.items.Book;

import java.util.List;

public class BookList {
    private List<Book> books;

    public BookList(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        String booksString = "";

        for (int i = 0; i < this.books.size(); i++) {
            if (this.books.get(i).isAvailable()) {
                booksString += i  + ". " + this.books.get(i).toString() + "\n";
            }
        }

        return booksString;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public boolean isBookAvailable(int bookIndex) {
        return bookIndex < this.books.size() && this.getBooks().get(bookIndex).isAvailable();
    }
}
