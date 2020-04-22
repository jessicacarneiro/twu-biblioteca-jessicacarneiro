package com.twu.biblioteca;

import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.collections.UserList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.items.Movie;
import com.twu.biblioteca.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {
    private ItemList itemList;
    private Menu menu;
    private UserList userList;

    public BibliotecaApp() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Book("Clean Code", "Robert C. Martin", 2008));
        items.add(new Book("Harry Potter and the Order of the Phoenix", "J. K. Rowling", 2003));
        items.add(new Book("The Lord of the Rings: The Fellowship of the Ring", "J. R. R. Tolkien", 1954));
        items.add(new Book("Foundation", "Isaac Asimov", 1951));
        items.add(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968));
        items.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1978));
        items.add(new Movie("Parasite", 2019,"Bong Joon-ho", 8.6));
        items.add(new Movie("Singin' in the Rain", 1952, "Stanley Donen, Gene Kelly", 8.3));

        this.itemList = new ItemList(items);

        this.menu = new Menu(System.out, new BufferedReader(new InputStreamReader(System.in)));

        List<User> users = new ArrayList<User>();
        users.add(new User("John Doe", "123-4567", "1234567", "413-34567", "johndoe@gmail.com"));
        users.add(new User("Mary Doe", "890-1234", "8901234", "413-34567", "marydoe@gmail.com"));

        userList = new UserList(users);
    }

    public void startApp() {
        this.menu.printWelcomeMessage();
    }

    public void run() {
        while (true) {
            try {
                this.menu.runMenu(userList, this.itemList);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }


}
