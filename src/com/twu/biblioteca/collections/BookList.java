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
            booksString += (i+1) + ". " + this.books.get(i).getTitle() + "\n";
        }

        return booksString;
    }
}
