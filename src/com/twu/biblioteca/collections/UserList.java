package com.twu.biblioteca.collections;

import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.user.User;

import java.util.List;

public class UserList {
    private List<User> userList;

    public UserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getAllItemsAndUsersRelation() {
        String relationUsersAndItems = "";

        for (User user: userList) {
            for (Item checkedOutItem: user.getCheckedOutItems().getItems()) {
                relationUsersAndItems += "User " + user.getLibraryNumber() + " | " + checkedOutItem.getTitle() + "\n";
            }
        }

        return relationUsersAndItems;
    }

    public User getLoggedInUser() {
        for (User user: userList) {
            if (user.isLoggedIn()) {
                return user;
            }
        }

        return null;
    }

    public User logInUser(String password, String libraryNumber) {
        for (User user: userList) {
            if (user.getLibraryNumber().equals(libraryNumber)) {
                if (user.logIn(password, libraryNumber)) {
                    return user;
                }
            }
        }

        return null;
    }

    public User logInUser(User userCredentials) {
        for (User user: userList) {
            if (user.equals(userCredentials)) {
                if (user.logIn(userCredentials))
                    return user;
            }
        }

        return null;
    }
}
