package com.twu.biblioteca.user;

import com.twu.biblioteca.collections.BookList;
import com.twu.biblioteca.items.Book;

import java.util.ArrayList;

public class User extends UserBase {
    private BookList checkedOutBooks;

    public User(String name) {
        super(name);
        this.checkedOutBooks = new BookList(new ArrayList<Book>());
    }

    public void addCheckedOutBook(Book book) {
        this.checkedOutBooks.getBooks().add(book);
    }

    public BookList getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public void setCheckedOutBooks(BookList checkedOutBooks) {
        this.checkedOutBooks = checkedOutBooks;
    }

    public String returnAllCheckedOutBooks() {
        String booksString = "";

        for (int i = 0; i < this.checkedOutBooks.getBooks().size(); i++) {
                booksString += i  + ". " + this.checkedOutBooks.getBooks().get(i).toString() + "\n";
        }

        return booksString;
    }

    public boolean checkInBook(int bookIndex) {
        if (bookIndex >= 0 && bookIndex < this.checkedOutBooks.getBooks().size()) {
            this.checkedOutBooks.getBooks().get(bookIndex).checkin();
            return true;
        }
        return false;
    }
}
