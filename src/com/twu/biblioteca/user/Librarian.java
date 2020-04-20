package com.twu.biblioteca.user;

import com.twu.biblioteca.collections.ItemList;

public class Librarian extends UserBase {

    public Librarian(String name) {
        super(name);
    }

    public String seeAllReturnedBooks(ItemList bookList) {
        return bookList.toString();
    }
}
