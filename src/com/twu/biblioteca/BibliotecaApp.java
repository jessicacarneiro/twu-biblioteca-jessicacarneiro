package com.twu.biblioteca;

import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.items.Movie;
import com.twu.biblioteca.user.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    private ItemList itemList;
    private Menu menu;
    private User user;

    public BibliotecaApp(String userName) {
        List<Item> items = new ArrayList<Item>();
        items.add(new Book("Clean Code", "Robert C. Martin", 2008));
        items.add(new Book("Harry Potter and the Order of the Phoenix", "J. K. Rowling", 2003));
        items.add(new Book("The Lord of the Rings: The Fellowship of the Ring", "J. R. R. Tolkien", 1954));
        items.add(new Book("Foundation", "Isaac Asimov", 1951));
        items.add(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968));
        items.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1978));
        items.add(new Movie("Parasite", 2019,"Bong Joon-ho", 4.3));
        items.add(new Movie("Singin' in the Rain", 1952, "Stanley Donen, Gene Kelly", 4.1));

        this.itemList = new ItemList(items);

        this.menu = new Menu(System.out, new BufferedReader(new InputStreamReader(System.in)));

        this.user = new User(userName);
    }

    public void startApp() {
        this.menu.printWelcomeMessage();
    }

    public void run() {
        while (true)
            this.menu.runMenu(this.user, this.itemList);
    }
}
