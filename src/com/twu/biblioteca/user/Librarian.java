package com.twu.biblioteca.user;

import com.twu.biblioteca.collections.BookList;

public class Librarian extends UserBase {

    public Librarian(String name) {
        super(name);
    }

    public String seeAllReturnedBooks(BookList bookList) {
        return bookList.toString();
    }
}
