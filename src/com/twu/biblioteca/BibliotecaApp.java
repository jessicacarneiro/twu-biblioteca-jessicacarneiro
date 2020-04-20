package com.twu.biblioteca;

import com.twu.biblioteca.collections.BookList;
import com.twu.biblioteca.items.Book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    private BookList bookList;
    private Menu menu;

    public BibliotecaApp() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("Harry Potter and the Order of the Phoenix", "J. K. Rowling", 2003));
        books.add(new Book("The Lord of the Rings: The Fellowship of the Ring", "J. R. R. Tolkien", 1954));
        books.add(new Book("Foundation", "Isaac Asimov", 1951));
        books.add(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1978));

        this.bookList = new BookList(books);

        this.menu = new Menu(System.out, new BufferedReader(new InputStreamReader(System.in)), bookList);
    }
}
