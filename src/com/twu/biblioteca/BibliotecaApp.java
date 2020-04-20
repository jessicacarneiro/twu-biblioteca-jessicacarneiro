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
        books.add(new Book("Clean Code"));
        books.add(new Book("Harry Potter and the Order of the Phoenix"));
        books.add(new Book("The Lord of the Rings: The Fellowship of the Ring"));
        books.add(new Book("Foundation"));
        books.add(new Book("Do Androids Dream of Electric Sheep?"));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy"));

        this.bookList = new BookList(books);

        this.menu = new Menu(System.out, new BufferedReader(new InputStreamReader(System.in)), bookList);
    }
}
