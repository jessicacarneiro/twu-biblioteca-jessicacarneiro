package com.twu.biblioteca.user;

import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.collections.UserList;

public class Librarian extends UserBase {

    public Librarian(String name, String login, String password, String phone, String email) {
        super(name, login, password, phone, email);
    }

    public String seeAllReturnedBooks(ItemList bookList) {
        return bookList.toString();
    }

    public String seeUsersAndCheckedOutItemsRelation(UserList userList) {
        return userList.getAllItemsAndUsersRelation();
    }
}
