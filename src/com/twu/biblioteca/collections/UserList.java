package com.twu.biblioteca.collections;

import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.user.User;

import java.util.List;

public class UserList {
    private List<User> userList;
    private User loggedInUser;

    public UserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getAllItemsAndUsersRelation() {
        String relationUsersAndItems = "";

        for (User user: userList) {
            for (Item checkedOutItem: user.getCheckedOutItems().getItems()) {
                relationUsersAndItems += "User " + user.getLogin() + " | " + checkedOutItem.getTitle() + "\n";
            }
        }

        return relationUsersAndItems;
    }

    public User logInUser(String libraryNumber, String password) {
        for (User user: userList) {
            if (user.validateCredentials(libraryNumber, password)) {
                this.setLoggedInUser(user);
                return user;
            }
        }

        return null;
    }
}
